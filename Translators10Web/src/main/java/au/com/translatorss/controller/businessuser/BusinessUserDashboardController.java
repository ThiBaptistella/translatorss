package au.com.translatorss.controller.businessuser;

import au.com.translatorss.bean.AmazonFile;
import au.com.translatorss.bean.AmazonFilePhoto;
import au.com.translatorss.bean.BusinessUser;
import au.com.translatorss.bean.ChatMessage;
import au.com.translatorss.bean.Conversation;
import au.com.translatorss.bean.Invoice;
import au.com.translatorss.bean.Language;
import au.com.translatorss.bean.Quotation;
import au.com.translatorss.bean.Rate;
import au.com.translatorss.bean.ServiceRequest;
import au.com.translatorss.bean.ServiceRequestFiles;
import au.com.translatorss.bean.ServiceResponse;
import au.com.translatorss.bean.ServiceResponseFiles;
import au.com.translatorss.bean.Translator;
import au.com.translatorss.bean.User;
import au.com.translatorss.bean.dto.*;
import au.com.translatorss.controller.BaseController;
import au.com.translatorss.dao.ServiceRequestCategoryDao;
import au.com.translatorss.dao.ServiceRequestStatusDao;
import au.com.translatorss.dao.ServiceResponseStatusDao;
import au.com.translatorss.dao.TimeFrameDao;
import au.com.translatorss.enums.FileType;
import au.com.translatorss.interceptors.QuotationUpdateListener;
import au.com.translatorss.service.*;
import au.com.translatorss.utils.ConversationUtils;
import au.com.translatorss.validation.CreateServiceRequestValidator;
import com.googlecode.charts4j.AxisLabels;
import com.googlecode.charts4j.AxisLabelsFactory;
import com.googlecode.charts4j.AxisStyle;
import com.googlecode.charts4j.AxisTextAlignment;
import com.googlecode.charts4j.BarChart;
import com.googlecode.charts4j.BarChartPlot;
import com.googlecode.charts4j.Color;
import com.googlecode.charts4j.Data;
import com.googlecode.charts4j.Fills;
import com.googlecode.charts4j.GCharts;
import com.googlecode.charts4j.LinearGradientFill;
import com.googlecode.charts4j.PieChart;
import com.googlecode.charts4j.Plots;
import com.googlecode.charts4j.Slice;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import au.com.translatorss.utils.ServiceRequestSingleton;

@Controller
@PreAuthorize("hasAnyAuthority('CLIENT')")
public class BusinessUserDashboardController extends BaseController{

	private static final Logger logger = LoggerFactory.getLogger(BusinessUserDashboardController.class);

	private static final int BUFFER_SIZE = 4096;

	@Autowired
	private LanguageService languageService;

	@Autowired
	private CustomerServiceRequestService serviceRequestService;

	@Autowired
	private EmailService emailService;

	@Autowired
	private TranslatorSettingsService translatorService;

	@Autowired
	private TranslatorQuotationService quotationService;

	@Autowired
	private ConversationService conversationService;

	@Autowired
	private ChatMessageService chatMessageService;

	@Autowired
	private ServiceRequestStatusDao serviceRequestStatusDao;

	@Autowired
	private ServiceResponseStatusDao responseDao;

	@Autowired
	private TimeFrameDao timeFrameDao;

	@Autowired
	private ServiceRequestCategoryDao serviceRequestCategoryDao;

	@Autowired
	private ServiceResponseService serviceResponseService;

	@Autowired
	private CreateServiceRequestValidator createServiceRequestValidator;

	@Autowired
	private QuotationUpdateListener quotationUpdateListener;

	@Autowired
	private EmailService2 emailService2;

	@Autowired
	private ServiceRequestConfigurationService serviceRequestConfigurationService;

	@Autowired
	private UserService userService;

	@Autowired
	private AmazonService amazonService;

	@Autowired
	private AmazonFilePhotoService amazonFilePhotoService;

	@Autowired
	private ServiceRequestApprovedRegisterService serviceRequestApprovedRegisterService;

	@Autowired
	private RateService rateService;
	
	@Value("${donation.value}")
	private String donationValue;

	public BusinessUserDashboardController() {}

	@InitBinder({"serviceRequestDTO"})
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new Validator[] { createServiceRequestValidator });
	}

	@RequestMapping({"/indexUserIni"})
	public String indexBusinessUser(HttpSession session, HttpServletRequest request, Model model) {
		logger.info("Welcome BusinessUserDashboardController: indexBusinessUser");
		BusinessUser businessUser = (BusinessUser)session.getAttribute("loggedInUser");
		BusinessUserDTO businessDTO = getBusinessUserDTO(businessUser);
		List<ChatMessage> messageList = chatMessageService.getUnreadMessageByCustomerId(businessUser.getUser().getId());
		List<ChatMessageDTO> dtoList = getMessagesDTO(new HashSet<ChatMessage>(messageList));
		AmazonFilePhoto photoView = amazonFilePhotoService.getAmazonFilePhotoByUserId(businessUser.getUser());
		if (photoView == null) {
			model.addAttribute("photoUrl", "resources/assets/layouts/layout2/img/avatar.png");
		} else {
			model.addAttribute("photoUrl", photoView.getUrl());
		}
		model.addAttribute("unreadMessageList", dtoList);
		model.addAttribute("newMessagesNumber", Integer.valueOf(messageList.size()));
		model.addAttribute("businessUserForm", businessDTO);

		List<Quotation> quoteList = quotationService.getQuoteListByState("Quoted", businessUser.getId());
		List<ServiceRequest> serviceRequestOpenServiceList = serviceRequestService.getServiceRequestFromBusinessUser(businessUser, "OpenService");
		List<ServiceRequest> serviceRequestListApproved = serviceRequestService.getServiceRequestFromBusinessUser(businessUser, "Approved");
		List<ServiceRequest> serviceRequestListPaied = serviceRequestService.getServiceRequestFromBusinessUser(businessUser, "Paied");
		serviceRequestListApproved.addAll(serviceRequestListPaied);

		List<ServiceRequest> serviceRequestQuotedList = serviceRequestService.getServiceRequestFromBusinessUser(businessUser, "Quoted");
		List<ServiceRequest> serviceRequestUnquotedList = serviceRequestService.getServiceRequestFromBusinessUser(businessUser, "Unquoted");
		serviceRequestQuotedList.addAll(serviceRequestUnquotedList);

		int quoted = quoteList.size();
		int requests = serviceRequestQuotedList.size();
		int assignmets = serviceRequestOpenServiceList.size();
		if ((quoted == 0) && (requests == 0) && (assignmets == 0)) {
			model.addAttribute("displayPie", "nodisplay");
		} else {
			model.addAttribute("displayPie", "display");
		}
		model.addAttribute("barURL", getBarChart(businessUser).toURLString());
		model.addAttribute("pieURL", getPieChart(quoted, requests, assignmets).toURLString());
		model.addAttribute("quotesAvailableToSelectTranslators", Integer.valueOf(quoteList.size()));
		model.addAttribute("jobInProgress", Integer.valueOf(serviceRequestOpenServiceList.size()));
		model.addAttribute("history", Integer.valueOf(serviceRequestListApproved.size()));

		return "customerDashboard/indexUser";
	}

	@RequestMapping({"/pendingActions"})
	public String pendingActions(HttpSession session, Model model) {
		logger.info("Welcome BusinessUserDashboardController: pendingActions");
		BusinessUser businessUserLogger = (BusinessUser)session.getAttribute("loggedInUser");
		BusinessUserDTO businessDTO = getBusinessUserDTO(businessUserLogger);
		model.addAttribute("businessUserForm", businessDTO);

		List<ServiceRequest> unquotedServiceRequestList = serviceRequestService.getServiceRequestFromBusinessUser(businessUserLogger, "Unquoted");
		List<ServiceRequest> quotedServiceRequestList = serviceRequestService.getServiceRequestFromBusinessUser(businessUserLogger, "Quoted");
		unquotedServiceRequestList.addAll(quotedServiceRequestList);

		List<ServiceRequestDTO> serviceRequestDTOList = getServiceRequestArrayDTO(unquotedServiceRequestList);
		model.addAttribute("serviceRequestList", serviceRequestDTOList);

		String message = (String)session.getAttribute("message");
		session.setAttribute("message", null);
		if (message == null) {
			message = "Service Request List";
		}
		model.addAttribute("message", message);

		List<ChatMessage> messageList = chatMessageService.getUnreadMessageByCustomerId(businessUserLogger.getUser().getId());
		List<ChatMessageDTO> dtoList = getMessagesDTO(new HashSet(messageList));

		model.addAttribute("unreadMessageList", dtoList);
		model.addAttribute("newMessagesNumber", Integer.valueOf(messageList.size()));

		AmazonFilePhoto photoView = amazonFilePhotoService.getAmazonFilePhotoByUserId(businessUserLogger.getUser());
		if (photoView == null) {
			model.addAttribute("photoUrl", "resources/assets/layouts/layout2/img/avatar.png");
		} else {
			model.addAttribute("photoUrl", photoView.getUrl());
		}
		return "/customerDashboard/pendingActions";
	}

	@RequestMapping({"/expireServiceRequest/{id}"})
	public String expireServiceRequest(HttpSession session, @PathVariable("id") long servicerequestid) {
		System.out.println("expireServiceRequest: servicerequestid" + servicerequestid);
		ServiceRequest serviceRequest = serviceRequestService.find(servicerequestid);
		serviceRequest.setServiceRequestStatus(serviceRequestStatusDao.findByDescription("Expired"));
		serviceRequestService.saveOrUpdate(serviceRequest);
		session.setAttribute("message", "Service Request Expired");
		emailService.sendMessage("Translatorss Support team", "Sorry, no translator gave a quote", serviceRequest
				.getCustomer().getUser().getEmail());
		return "redirect:/pendingActions";
	}

	@RequestMapping({"/jobInProgress"})
	public String jobInProgress(HttpSession session, Model model) {
		logger.info("Welcome BusinessUserDashboardController: jobInProgress");
		BusinessUser businessUserLogger = (BusinessUser)session.getAttribute("loggedInUser");
		BusinessUserDTO businessDTO = getBusinessUserDTO(businessUserLogger);
		model.addAttribute("businessUserForm", businessDTO);
		model.addAttribute("businessUserForm", businessUserLogger);


		List<ServiceRequest> serviceRequestList = serviceRequestService.getServiceRequestFromBusinessUser(businessUserLogger, "OpenService");
		List<ServiceRequestDTO> serviceRequestDTOList = getServiceRequestArrayDTO(serviceRequestList);
		model.addAttribute("serviceRequestList", serviceRequestDTOList);

		List<ChatMessage> messageList = chatMessageService.getUnreadMessageByCustomerId(businessUserLogger.getUser().getId());
		List<ChatMessageDTO> dtoList = getMessagesDTO(new HashSet(messageList));

		model.addAttribute("unreadMessageList", dtoList);
		model.addAttribute("newMessagesNumber", Integer.valueOf(messageList.size()));

		AmazonFilePhoto photoView = amazonFilePhotoService.getAmazonFilePhotoByUserId(businessUserLogger.getUser());
		if (photoView == null) {
			model.addAttribute("photoUrl", "resources/assets/layouts/layout2/img/avatar.png");
		} else {
			model.addAttribute("photoUrl", photoView.getUrl());
		}
		return "/customerDashboard/jobInProgress";
	}

	@RequestMapping({"/history"})
	public String history(HttpSession session, Model model) {
		logger.info("Welcome BusinessUserDashboardController: history");

		BusinessUser businessUserLogger = (BusinessUser)session.getAttribute("loggedInUser");
		model.addAttribute("businessUserForm", businessUserLogger);

		List<String> statusList = new ArrayList<String>();
		statusList.add("Approved");
		statusList.add("Expired");
		statusList.add("Paid");
		statusList.add("Refunded");
		statusList.add("Cancelled");

		List<ServiceRequest> list = serviceRequestService.getServiceRequestFromBusinessUser(businessUserLogger, statusList);
		List<ServiceRequestDTO> serviceRequestDTOList = getServiceRequestArrayDTO(list);
		model.addAttribute("serviceRequestList", serviceRequestDTOList);
		model.addAttribute("businessUserForm", businessUserLogger);

		List<ChatMessage> messageList = chatMessageService.getUnreadMessageByCustomerId(businessUserLogger.getUser().getId());
		List<ChatMessageDTO> dtoList = getMessagesDTO(new HashSet(messageList));

		model.addAttribute("unreadMessageList", dtoList);
		model.addAttribute("newMessagesNumber", Integer.valueOf(messageList.size()));

		AmazonFilePhoto photoView = amazonFilePhotoService.getAmazonFilePhotoByUserId(businessUserLogger.getUser());
		if (photoView == null) {
			model.addAttribute("photoUrl", "resources/assets/layouts/layout2/img/avatar.png");
		} else {
			model.addAttribute("photoUrl", photoView.getUrl());
		}
		return "/customerDashboard/history";
	}

	@RequestMapping({"/quotationList/{id}"})
	public String quotationsServiceRequest(@PathVariable("id") long id, Model model, HttpSession session, RedirectAttributes redirectAttributes) throws Exception{
		logger.info("Welcome BusinessUserDashboardController: quotationsServiceRequest");
		session.setAttribute("serviceRequestid", Long.valueOf(id));
		return "redirect:/myUserQuotations";
	}

	@RequestMapping({"/myUserQuotations"})
	public String myUserQuotation2(HttpSession session, Model model) {
		logger.info("Welcome BusinessUserDashboardController: myUserQuotation");
		Long serviceRequestid = (Long)session.getAttribute("serviceRequestid");
		ServiceRequest serviceRequest = serviceRequestService.find(serviceRequestid.longValue());
		BusinessUser businessUserLogger = (BusinessUser)session.getAttribute("loggedInUser");

		model.addAttribute("businessUserForm", businessUserLogger);
		model.addAttribute("translatorQuoteList", getQuotationArrayDTO(quotationService.getQuotationList(serviceRequest)));
		model.addAttribute("donationValue", donationValue);
		model.addAttribute("serviceRequestid", serviceRequestid);
		model.addAttribute("message", "List of quotations");

		List<ChatMessage> messageList = chatMessageService.getUnreadMessageByCustomerId(businessUserLogger.getUser().getId());
		List<ChatMessageDTO> dtoList = getMessagesDTO(new HashSet(messageList));

		model.addAttribute("unreadMessageList", dtoList);
		model.addAttribute("newMessagesNumber", Integer.valueOf(messageList.size()));

		AmazonFilePhoto photoView = amazonFilePhotoService.getAmazonFilePhotoByUserId(businessUserLogger.getUser());
		if (photoView == null) {
			model.addAttribute("photoUrl", "resources/assets/layouts/layout2/img/avatar.png");
		} else {
			model.addAttribute("photoUrl", photoView.getUrl());
		}
		return "/customerDashboard/myUserQuotation";
	}

	@RequestMapping({"/checkQuoteUpdated/{serviceRequestId}/{guid}"})
	public SseEmitter handle(@PathVariable("serviceRequestId") long serviceRequestId, @PathVariable("guid") String guid)
	{
		SseEmitter emitter = quotationUpdateListener.reCreateEmitter(guid, Long.valueOf(serviceRequestId));
		return emitter;
	}

	@RequestMapping({"/userdashboard"})
	public String dashboard(Model model) {
		logger.info("Welcome BusinessUserDashboardController: dashboard");
		return "redirect:/indexUserIni";
	}

	@RequestMapping({"/uploadFile"})
	public String uploadFile(Model model, HttpSession session) {
		logger.info("Welcome BusinessUserDashboardController: uploadFile");
		ServiceRequestDTO ServiceRequestDTO = new ServiceRequestDTO();
		model.addAttribute("serviceRequestDTO", ServiceRequestDTO);
		model.addAttribute("languageList", initializeProfiles());
		BusinessUser businessUserLogger = (BusinessUser)session.getAttribute("loggedInUser");
		ServiceRequestDTO.setFullName(businessUserLogger.getUser().getName());
		model.addAttribute("businessUserForm", businessUserLogger);

		List<ChatMessage> messageList = chatMessageService.getUnreadMessageByCustomerId(businessUserLogger.getUser().getId());
		List<ChatMessageDTO> dtoList = getMessagesDTO(new HashSet(messageList));

		model.addAttribute("unreadMessageList", dtoList);
		model.addAttribute("newMessagesNumber", Integer.valueOf(messageList.size()));

		AmazonFilePhoto photoView = amazonFilePhotoService.getAmazonFilePhotoByUserId(businessUserLogger.getUser());
		if (photoView == null) {
			model.addAttribute("photoUrl", "resources/assets/layouts/layout2/img/avatar.png");
		} else {
			model.addAttribute("photoUrl", photoView.getUrl());
		}
		return "/customerDashboard/uploadFile";
	}

	@RequestMapping({"/serviceRequestProcesor"})
	public String serviceRequestProcesor(@ModelAttribute("serviceRequestDTO") @Validated ServiceRequestDTO serviceRequestDTO, BindingResult result, HttpSession session, Model model) throws IllegalStateException, IOException
	{
		logger.info("Welcome BusinessUserDashboardController: serviceRequestProcesor");
		BusinessUser businessUserLogger = (BusinessUser)session.getAttribute("loggedInUser");

		if (result.hasErrors()) {
			model.addAttribute("languageList", initializeProfiles());
			model.addAttribute("businessUserForm", businessUserLogger);
			return "/customerDashboard/uploadFile";
		}
		ServiceRequest serviceRequest = mapServiceRequestFromDTO(serviceRequestDTO, businessUserLogger);
		serviceRequestService.saveOrUpdate(serviceRequest, serviceRequestDTO);
		quotationService.getQuotationList(serviceRequest);
		//lastServiceRequest.addServiceRequest(serviceRequest);
		businessUserLogger.getServiceRequestList().add(serviceRequest);
		session.setAttribute("servcieRequest", serviceRequest);
		sendEmailAfterServiceRequestCreated(businessUserLogger.getUser().getEmail(), businessUserLogger.getUser().getName(), serviceRequest.getId().toString());
		model.addAttribute("businessUserForm", businessUserLogger);
		session.setAttribute("serviceRequestid", serviceRequest.getId());
		return "redirect:/myUserQuotations";
	}

	@RequestMapping(value={"/uploadPhoto"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
	public String uploadFileHandler(HttpSession session, @RequestParam("file") MultipartFile file) throws IOException {
		if (!file.isEmpty()) {
			BusinessUser businessUserLogger = (BusinessUser)session.getAttribute("loggedInUser");
			amazonFilePhotoService.savePhoto(businessUserLogger.getUser(), file.getOriginalFilename(), file.getInputStream(), file.getContentType());
		}
		session.setAttribute("info", 200);
		session.setAttribute("messageDisplay", "Your photo have been uploaded");

		return "redirect:userSettings";
	}

	@RequestMapping({"/detailServiceRequest/{id}"})
	public String detailServiceRequest(@PathVariable("id") long id, Model model)
	{
		logger.info("Welcome BusinessUserDashboardController: detailServiceRequest");
		ServiceRequest serviceRequest = serviceRequestService.find(id);
		model.addAttribute("serviceRequest", serviceRequest);
		model.addAttribute("languageList", initializeProfiles());
		return "/customerDashboard/uploadFile";
	}

	@RequestMapping({"/deleteServiceRequest/{id}"})
	public String deleteServiceRequest(@PathVariable("id") long id, Model model) throws Exception {
		logger.info("Welcome BusinessUserDashboardController: deleteServiceRequest");
		ServiceRequest serviceRequest = serviceRequestService.find(id);
		serviceRequestService.remove(serviceRequest);
		return "redirect:/pendingActions";
	}

	@RequestMapping(value={"/serviceResponseCustomerProcesor"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
	public String submitServiceResponse(HttpSession session, @ModelAttribute("serviceResponse") ServiceResponseDTO serviceResponseDTO) throws Exception {
		logger.info("Welcome BusinessUserDashboardController: submitServiceResponse");
		ServiceResponse serviceResponse = serviceResponseService.find(serviceResponseDTO.getId());
		ServiceRequest serviceRequest = (ServiceRequest)session.getAttribute("serviceRequest");
		serviceResponseService.saveFiles(serviceResponse, serviceResponseDTO.getFiles());
		emailService2.sendEmailToTranslatorFileSentByCustomer(serviceResponse.getTranslator().getUser().getEmail(), serviceResponse.getTranslator().getUser().getName(), serviceRequest.getCustomer().getUser().getName(), serviceResponseDTO.getFiles(), serviceRequest.getId().toString());
		return "redirect:/messages";
	}

	@RequestMapping({"/conversations"})
	public String conversations(HttpSession session, Model model) throws Exception {
		logger.info("Welcome BusinessUserDashboardController: conversations");
		BusinessUser businessUserLogger = (BusinessUser)session.getAttribute("loggedInUser");

		List<Conversation> conversationList = conversationService.getAllConversationsByCustomerId(businessUserLogger.getId());

		model.addAttribute("businessUserForm", businessUserLogger);
		model.addAttribute("conversationList", conversationList);

		List<ChatMessage> messageList = chatMessageService.getUnreadMessageByCustomerId(businessUserLogger.getUser().getId());
		List<ChatMessageDTO> dtoList = getMessagesDTO(new HashSet(messageList));

		model.addAttribute("unreadMessageList", dtoList);
		model.addAttribute("newMessagesNumber", Integer.valueOf(messageList.size()));

		AmazonFilePhoto photoView = amazonFilePhotoService.getAmazonFilePhotoByUserId(businessUserLogger.getUser());
		if (photoView == null) {
			model.addAttribute("photoUrl", "resources/assets/layouts/layout2/img/avatar.png");
		} else {
			model.addAttribute("photoUrl", photoView.getUrl());
		}
		return "/customerDashboard/conversations";
	}

	@RequestMapping(value={"/submitMessage"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
	public String submitMessage(@ModelAttribute("message") ChatMessageDTO message, HttpSession session, RedirectAttributes redirectAttributes) throws Exception {
		logger.info("Welcome BusinessUserDashboardController: submitMessage");
		ChatMessage chatMessage = new ChatMessage();
		chatMessage.setSender(message.getSender());
		User currentUser = userService.getCurrentUserOrNull();
		if (currentUser != null) {
			chatMessage.setSenderId(userService.getUserIdByRole(currentUser));
		}
		Date date = new Date();
		chatMessage.setDate(new Timestamp(date.getTime()));
		chatMessage.setMessage(message.getMessage());
		Conversation conversation = conversationService.get(message.getConversationid());
		chatMessage.setConversation(conversation);
		conversationService.messagesMarkAsRead(currentUser, conversation.getId());
		chatMessageService.saveOrUpdate(chatMessage);
		conversation.getMessageList().add(chatMessage);
		session.setAttribute("conversation", conversation);
		redirectAttributes.addFlashAttribute("conversation", conversation);
		return "redirect:/messages";
	}

	@RequestMapping({"/conversationWithTranslator/{id}"})
	public String conversation(@PathVariable("id") long id, HttpSession session, Model model, RedirectAttributes redirectAttributes) throws Exception {
		logger.info("Welcome BusinessUserDashboardController: conversation");
		ServiceRequest serviceRequest = serviceRequestService.find(id);
		Conversation conversation = conversationService.getConversationFromServiceRequestIdAndTranslatorId(serviceRequest.getId(), serviceRequest.getTranslator().getId());
		session.setAttribute("serviceRequest", serviceRequest);
		session.setAttribute("conversation", conversation);
		return "redirect:/messages";
	}

	@RequestMapping({"/seeConversation/{id}"})
	public String seeConversation(@PathVariable("id") long id, Model model, HttpSession session, RedirectAttributes redirectAttributes) throws Exception {
		logger.info("Welcome BusinessUserDashboardController: seeConversation");
		Conversation conversation = conversationService.get(Long.valueOf(id));
		ServiceRequest serviceRequest = conversation.getServiceRequest();
		session.setAttribute("serviceRequest", serviceRequest);
		session.setAttribute("conversation", conversation);
		return "redirect:/messages";
	}

	@RequestMapping({"/startConversation/{translatorid}/{serviceRequestid}/{quoteid}"})
	public String startConversation(@PathVariable("translatorid") long translatorid, @PathVariable("serviceRequestid") long serviceRequestid, @PathVariable("quoteid") long quoteid, Model model, HttpSession session) {
		logger.info("Welcome BusinessUserDashboardController: startConversation");
		ServiceRequest sr = serviceRequestService.find(serviceRequestid);
		Translator trans = translatorService.getTranslatorById(translatorid);
		Conversation conversation = conversationService.getConversationFromServiceRequestIdAndTranslatorId(sr.getId(), trans.getId());
		if (conversation == null) {
			conversation = conversationService.starConversation(sr, trans);
		}
		session.setAttribute("serviceRequest", sr);
		session.setAttribute("conversation", conversation);
		session.setAttribute("quoteid", Long.valueOf(quoteid));
		return "redirect:/messages";
	}

	@RequestMapping({"/messages"})
	public String messages(HttpSession session, Model model) throws Exception {
		logger.info("Welcome BusinessUserDashboardController: messages");
		Conversation conversation = (Conversation)session.getAttribute("conversation");
		conversation = conversationService.get(conversation.getId());
		ServiceRequest serviceRequest = conversation.getServiceRequest();
		User user = userService.getCurrentUser();
		Long quoteid = (Long)session.getAttribute("quoteid");

		if (quoteid != null) {
			Quotation quote = quotationService.find(quoteid.longValue());
			model.addAttribute("quote", quote);
		}
		conversationService.messagesMarkAsRead(user, conversation.getId());

		BusinessUser businessUserLogger = (BusinessUser)session.getAttribute("loggedInUser");

		List<ChatMessageDTO> dtoList = getMessagesDTO(new HashSet(conversation.getMessageList()));


		model.addAttribute("messageList", dtoList);
		model.addAttribute("businessUserForm", businessUserLogger);

		ChatMessageDTO message = new ChatMessageDTO();
		message.setConversationid(conversation.getId());
		message.setSender(businessUserLogger.getUser().getName());
		AmazonFilePhoto photo=this.amazonFilePhotoService.getAmazonFilePhotoByUserId(businessUserLogger.getUser());
		if(photo ==null){
			message.setPhotoUrl("resources/assets/layouts/layout2/img/avatar.png");
		}else {
			message.setPhotoUrl(photo.getUrl());
		}
		model.addAttribute("message", message);

		ServiceResponseDTO serviceResponseDTO = new ServiceResponseDTO();
		ServiceResponse serviceResponse = conversation.getServiceResponse();
		if (serviceResponse == null) {
			serviceResponse = new ServiceResponse();
			serviceResponse.setCreationDate(new Date());
			serviceResponse.setDescription("description");
			serviceResponse.setTranslator(conversation.getTranslator());
			serviceResponse.setUpdateDate(new Date());
			serviceResponse.setServiceResponseStatus(responseDao.findByDescription("Open"));
			conversation.setServiceResponse(serviceResponse);
			conversationService.saveOrUpdate(conversation);
		}


		serviceResponseDTO.setId(serviceResponse.getId().longValue());

		List<ServiceRequest> list = new ArrayList();
		list.add(conversation.getServiceRequest());
		
		List<ServiceRequestDTO> serviceRequestDTOList = getServiceRequestArrayDTO(list);

		
		model.addAttribute("serviceRequestList", serviceRequestDTOList);
		Set<AmazonFile> amazonFiles = new HashSet();
		amazonFiles.addAll(amazonService.findByServiceRequestIdAndType(serviceRequest.getId(), FileType.SERVICE_REQUEST));
		amazonFiles.addAll(amazonService.findByServiceResponseIdAndType(serviceResponse.getId(), FileType.SERVICE_RESPONSE));
		model.addAttribute("serviceResponse", serviceResponseDTO);
		model.addAttribute("serviceResponseid", serviceResponse.getId());
		model.addAttribute("conversationid", conversation.getId());
		model.addAttribute("serviceRequestStatus", serviceRequest.getServiceRequestStatus().getDescription());
		model.addAttribute("finishQuoteDate", serviceRequest.getFinishDate());
		model.addAttribute("serviceResponseFiles", serviceResponse.getServiceResponseFiles());
		model.addAttribute("fileList", convertToDto(amazonFiles));
		model.addAttribute("serviceRequestOriginalFiles", getServiceRequestFilesByVersion("original", serviceRequest.getServiceRequestFiles()));
		model.addAttribute("serviceRequestRevisionFiles", getServiceRequestFilesByVersion("revision", serviceRequest.getServiceRequestFiles()));
		model.addAttribute("donationValue", donationValue);

		List<ChatMessage> messageList = chatMessageService.getUnreadMessageByCustomerId(businessUserLogger.getUser().getId());
		model.addAttribute("unreadMessageList", messageList);
		model.addAttribute("newMessagesNumber", Integer.valueOf(messageList.size()));

		AmazonFilePhoto photoView = amazonFilePhotoService.getAmazonFilePhotoByUserId(businessUserLogger.getUser());
		if (photoView == null) {
			model.addAttribute("photoUrl", "resources/assets/layouts/layout2/img/avatar.png");
		} else {
			model.addAttribute("photoUrl", photoView.getUrl());
		}
		return "/customerDashboard/messages";
	}

	@RequestMapping(value={"/approbeJob"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
	public String approbeJob(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IllegalStateException, IOException {
		logger.info("Welcome BusinessUserDashboardController: approbeJob");
		ServiceRequest serviceRequest = (ServiceRequest)session.getAttribute("serviceRequest");
		serviceRequest.setServiceRequestStatus(serviceRequestStatusDao.findByDescription("Approved"));
		serviceRequest.setPaidDate(new Date());
		serviceRequestService.saveOrUpdate(serviceRequest);
		serviceRequestApprovedRegisterService.registerApprovedSR(serviceRequest);

		//Quotation quotation = this.quotationService.getQuoteFromServiceRequestAndTranslator(serviceRequest.getId(),serviceRequest.getTranslator().getId());
		//createAndSendInvoice(new InvoicePdfDto(quotation), serviceRequest);

		return "redirect:/rate";
	}

	@RequestMapping(value={"/approbeJob/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
	public String approbeJob(@PathVariable("id") long id,HttpSession session) throws IllegalStateException, IOException {
		logger.info("Welcome BusinessUserDashboardController: approbeJob");

		ServiceRequest serviceRequest = this.serviceRequestService.find(id);
		session.setAttribute("serviceRequest", serviceRequest);
		serviceRequest.setServiceRequestStatus(serviceRequestStatusDao.findByDescription("Approved"));
		serviceRequest.setPaidDate(new Date());
		serviceRequestService.saveOrUpdate(serviceRequest);
		serviceRequestApprovedRegisterService.registerApprovedSR(serviceRequest);

	//	Quotation quotation = this.quotationService.getQuoteFromServiceRequestAndTranslator(serviceRequest.getId(),serviceRequest.getTranslator().getId());
	//	createAndSendInvoice(new InvoicePdfDto(quotation), serviceRequest);

		return "redirect:/rate";
	}
	
	
	
	/*private void createAndSendInvoice(InvoicePdfDto dto, ServiceRequest serviceRequest) throws IOException {
		Invoice invoice = new Invoice(serviceRequest);
		invoice = invoiceService.save(invoice);
		dto.setInvoiceNum(String.valueOf(invoice.getId()));
		dto.setInvoiceDate(invoice.getCreatedAt());
		ByteArrayOutputStream invoiceOutStream = createPdf(dto);
		byte[] bytes = invoiceOutStream.toByteArray();
		String fileName = "invoice_" + invoice.getId().toString();
		AmazonFile amazonFile = amazonService.saveInvoice(serviceRequest, serviceRequest.getCustomer().getUser(), fileName, new ByteArrayInputStream(bytes));
		invoice.setFile(amazonFile);
		invoiceService.update(invoice);
		emailService2.sendInvoice(bytes, serviceRequest.getTranslator().getUser().getEmail(), serviceRequest.getCustomer().getUser().getEmail());
	}

	private ByteArrayOutputStream createPdf(InvoicePdfDto dto) throws IOException {
		ByteArrayOutputStream outputStream = null;
		InputStream templateStream;
		try {
			outputStream = new ByteArrayOutputStream();

			templateStream = getClass().getClassLoader().getResourceAsStream(INVOICE_TEMPLATE_HTML);

			String template = IOUtils.toString(templateStream, StandardCharsets.UTF_8);

			template = template.replace("{{TRANSLATOR_DETAILS}}", dto.getTranslatorDetails());
			template = template.replace("{{NAATI_NUMBER}}", dto.getNaatiNumber());
			template = template.replace("{{INVOICE_NUMBER}}", dto.getInvoiceNum());
			template = template.replace("{{CUSTOMER_DETAILS}}", dto.getCustomerDetails());
			template = template.replace("{{INVOICE_DATE}}", formatter.format(dto.getInvoiceDate()));
			template = template.replace("{{ITEM_NUM}}", "1");
			template = template.replace("{{DESCRIPTION}}", dto.getDescription());
			template = template.replace("{{QTY}}", dto.getQty().toString());
			template = template.replace("{{RATE}}", dto.getRate().toString());
			template = template.replace("{{PRICE}}", dto.getPrice().toString());

			InputStream contentStream = IOUtils.toInputStream(template, StandardCharsets.UTF_8);

			pdfService.createDocument(contentStream, outputStream, null, false);
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return outputStream;
	}*/


	@RequestMapping(value={"/rate"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
	public String rate(Model model, RedirectAttributes redirectAttributes, HttpSession session) {
		ServiceRequest serviceRequest = (ServiceRequest)session.getAttribute("serviceRequest");
		RateDTO rate = new RateDTO();
		rate.setCustomerId(serviceRequest.getCustomer().getId().intValue());
		rate.setTranslatorId(serviceRequest.getTranslator().getId().intValue());
		model.addAttribute("rateTranslator", rate);
		BusinessUser businessUserLogger = (BusinessUser)session.getAttribute("loggedInUser");

		List<Integer> numberList = new ArrayList();
		numberList.add(Integer.valueOf(1));
		numberList.add(Integer.valueOf(2));
		numberList.add(Integer.valueOf(3));
		numberList.add(Integer.valueOf(4));
		numberList.add(Integer.valueOf(5));
		model.addAttribute("numberList1", numberList);
		model.addAttribute("numberList2", numberList);
		model.addAttribute("numberList3", numberList);

		List<ChatMessage> messageList = chatMessageService.getUnreadMessageByCustomerId(businessUserLogger.getUser().getId());
		List<ChatMessageDTO> dtoList = getMessagesDTO(new HashSet(messageList));

		model.addAttribute("unreadMessageList", dtoList);
		model.addAttribute("newMessagesNumber", Integer.valueOf(messageList.size()));

		AmazonFilePhoto photoView = amazonFilePhotoService.getAmazonFilePhotoByUserId(businessUserLogger.getUser());
		if (photoView == null) {
			model.addAttribute("photoUrl", "resources/assets/layouts/layout2/img/avatar.png");
		} else {
			model.addAttribute("photoUrl", photoView.getUrl());
		}
		return "customerDashboard/rate";
	}

	@RequestMapping(value = "/rateInformation/{id}")
	public String translatorRateInformation(@PathVariable("id") long id,HttpSession session) {
		logger.debug("show Translator() id: {}", id);
		Translator translator = (Translator) translatorService.getTranslatorById(id);
		List<Rate> rateList = translatorService.getAllTranslatorRates(translator);
		session.setAttribute("rateList",rateList);
		return "redirect:/rateInformation";
	}

	@RequestMapping(value = "rateInformation")
	public String translatorRateInformation(HttpSession session, Model model) {
		List<Rate> rateList = (List<Rate>) session.getAttribute("rateList");
		model.addAttribute("rateList",rateList);
		return "customerDashboard/translatorRateInformation";
	}

	@RequestMapping(value={"/submitRate"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
	public String submitRate(@ModelAttribute("rateTranslator") RateDTO rateDTO, Model model, RedirectAttributes redirectAttributes, HttpSession session)
	{
		ServiceRequest serviceRequest = (ServiceRequest)session.getAttribute("serviceRequest");
		Translator translator = serviceRequest.getTranslator();
		BusinessUser businessUSer = serviceRequest.getCustomer();

		Rate rate = new Rate();
		rate.setServiceRequest(serviceRequest);
		rate.setCustomer(businessUSer);
		rate.setTranslator(translator);
		rate.setServiceAsDescribed(Integer.valueOf(rateDTO.getService()));
		rate.setTimeDelivery(Integer.valueOf(rateDTO.getTimeDelivery()));
		rate.setQuality(Integer.valueOf(rateDTO.getQuality()));
		rate.setFeedback(rateDTO.getFeedback());
		translatorService.rateTranslator(rate);

		if (serviceRequest.getServiceRequestPayment() != null) {
			
			Quotation quotation = this.quotationService.getQuoteFromServiceRequestAndTranslator(serviceRequest.getId(), serviceRequest.getTranslator().getId());
			emailService2.sendEmailToCustomerServiceRequestAppoved(serviceRequest, 
					serviceRequest.getCustomer().getFullname(),
					serviceRequest.getTranslator().getFullname(),
					serviceRequest.getCustomer().getUser().getEmail(),
					serviceRequest.getTranslator().getUser().getEmail(),
					serviceRequest.getTranslator().getAbn_name(),
					serviceRequest.getTranslator().getAbn_number(),
					quotation);			
			
		}
		return "redirect:/history";
	}

	@RequestMapping(value={"/downloadFile/{id}/{option}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
	public void downloadFileFromCustomer(HttpServletResponse response, @PathVariable("id") long id, @PathVariable("option") String option, HttpServletRequest request) throws IOException {
		logger.info("Welcome BusinessUserDashboardController: downloadFiles2");

		if (option.equals("request")) {
			ServiceRequestFiles serviceRequestFile = serviceRequestService.getServiceRequestFile(id);
			metodo(request, response, serviceRequestFile.getUrl(), serviceRequestFile.getFile());
		} else {
			ServiceResponseFiles serviceResponseFile = serviceResponseService.getServiceResponseById(id);
			metodo(request, response, serviceResponseFile.getUrl(), serviceResponseFile.getFile());
		}
	}

	@RequestMapping({"/userLogout"})
	public String translatorLogout(Model model, HttpSession session) {
		logger.info("Welcome BusinessUserDashboardController: translatorLogout");
		session.removeAttribute("loggedInUser");
		return "redirect:/";
	}

	private List<AmazonFileDto> convertToDto(Set<AmazonFile> files) {
		List<AmazonFileDto> dtos = new ArrayList();
		for (AmazonFile file : files) {
			AmazonFileDto dto = new AmazonFileDto(file);
			dtos.add(dto);
		}
		return dtos;
	}

	private List<FileInfoDTO> getAllFiles(Conversation conversation, ServiceRequest serviceRequest) {
		ServiceRequestFiles file;
		List<FileInfoDTO> fileList = new ArrayList();
		for (Iterator localIterator = serviceRequest.getServiceRequestFiles().iterator(); localIterator.hasNext();) { file = (ServiceRequestFiles)localIterator.next();
			FileInfoDTO fileInfoDTO = new FileInfoDTO();
			fileInfoDTO.setDate(file.getCreationDae());
			fileInfoDTO.setSender(serviceRequest.getCustomer().getUser().getName());
			fileInfoDTO.setName(file.getUrl());
			fileInfoDTO.setType("request");
			fileInfoDTO.setId(file.getId());
			fileList.add(fileInfoDTO);
		}
		ServiceResponse serviceResponse = conversationService.getServiceResponseByConversationId(conversation.getId());

		for (ServiceResponseFiles file2 : serviceResponse.getServiceResponseFiles()) {
			FileInfoDTO fileInfoDTO2 = new FileInfoDTO();
			fileInfoDTO2.setSender(file2.getSender());
			fileInfoDTO2.setName(file2.getUrl());
			fileInfoDTO2.setType("response");
			fileInfoDTO2.setDate(file2.getCreationDate());
			fileInfoDTO2.setId(file2.getId());
			fileList.add(fileInfoDTO2);
		}
		return fileList;
	}

	private List<ServiceRequestFiles> getServiceRequestFilesByVersion(String version, Set<ServiceRequestFiles> serviceRequestFiles)
	{
		List<ServiceRequestFiles> list = new ArrayList();
		for (ServiceRequestFiles srf : serviceRequestFiles) {
			if (version.equals("original")) {
				if (srf.getOriginal()) {
					list.add(srf);
				}
			}
			else if (!srf.getOriginal()) {
				list.add(srf);
			}
		}
		return list;
	}

	private List<TranslatorQuotationDTO> getQuotationArrayDTO(List<Quotation> setQuotations) { List<TranslatorQuotationDTO> list = new ArrayList();
		for (Quotation quotation : setQuotations) {
			TranslatorQuotationDTO dto = new TranslatorQuotationDTO();
			dto.setServiceRequestId(quotation.getServiceRequest().getId().longValue());
			dto.setQuotationId(quotation.getId().longValue());
			dto.setNaati(true);
			dto.setName(quotation.getTranslator().getUser().getName());
			dto.setTranslatorId(quotation.getTranslator().getId().longValue());
			dto.setQuote(quotation.getValue().toString());
			AmazonFilePhoto photo = amazonFilePhotoService.getAmazonFilePhotoByUserId(quotation.getTranslator().getUser());
			if (photo != null) {
				dto.setPhoto(photo.getUrl());
			} else {
				dto.setPhoto("resources/assets/layouts/layout2/img/avatar.png");
			}

			populateMediaRating(dto, quotation.getTranslator());
			list.add(dto);
		}
		return list;
	}


	public void metodo(HttpServletRequest request, HttpServletResponse response, String url, byte[] file) throws IOException {
		ServletContext context = request.getServletContext();

		File downloadFile = new File(url);
		FileUtils.writeByteArrayToFile(downloadFile, file);

		FileInputStream inputStream = new FileInputStream(downloadFile);

		String mimeType = context.getMimeType(url);
		if (mimeType == null) {
			mimeType = "application/octet-stream";
		}
		System.out.println("MIME type: " + mimeType);

		response.setContentType(mimeType);
		response.setContentLength((int)downloadFile.length());

		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"", new Object[] { downloadFile.getName() });
		response.setHeader(headerKey, headerValue);

		OutputStream outStream = response.getOutputStream();

		byte[] buffer = new byte[BUFFER_SIZE];
		int bytesRead = -1;

		while ((bytesRead = inputStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, bytesRead);
		}
		inputStream.close();
		outStream.close();
	}

	private ServiceRequest mapServiceRequestFromDTO(ServiceRequestDTO serviceRequestDTO, BusinessUser businessUserLogger) throws IllegalStateException, IOException {
		ServiceRequest serviceRequest = new ServiceRequest();
		Date requestCreationDate = new Date();
		serviceRequest.setCreationDate(requestCreationDate);
		serviceRequest.setCustomer(businessUserLogger);
		serviceRequest.setDescription(serviceRequestDTO.getDescription());
		serviceRequest.setHardcopy(serviceRequestDTO.getHardcopy());
		String languageFrom = serviceRequestDTO.getLanguagefrom().trim();
		serviceRequest.setLanguagefrom(languageFrom);
		serviceRequest.setLanguageTo("English");
		serviceRequest.setServiceRequestCategory(serviceRequestCategoryDao.findByDescription(serviceRequestDTO.getServiceRequestCategory()));
		serviceRequest.setServiceRequestStatus(serviceRequestStatusDao.findByDescription("Unquoted"));
		serviceRequest.setTimeFrame(timeFrameDao.findByDescription(serviceRequestDTO.getTimeFrame()));
		serviceRequest.setFinishQuoteSelection(getFinishDateSelectionQuote(serviceRequestDTO.getTimeFrame()));
		serviceRequest.setFinishDate(new Date());
		return serviceRequest;
	}

	private Date getFinishDate(String timeFrame) {
		Date finishDate = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(finishDate);
		int daysAfter = 0;
		if ("Urgent".equals(timeFrame)) {
			daysAfter = 1;
		}
		if ("Medium".equals(timeFrame)) {
			daysAfter = 2;
		}
		if ("Relaxed".equals(timeFrame)) {
			daysAfter = 7;
		}
		c.add(5, daysAfter);
		finishDate = c.getTime();
		return finishDate;
	}

	private Date getFinishDateSelectionQuote(String timeFrame) {
		Date finishDate = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(finishDate);
		int percentajeToApplyBefore = serviceRequestConfigurationService.getServiceRequestHoursLeft();
		float timerBeforeSelect = 0.0F;

		if ("Urgent".equals(timeFrame)) {
			timerBeforeSelect = percentajeToApplyBefore * 24 / 100.0F;
		}
		if ("Medium".equals(timeFrame)) {
			timerBeforeSelect = percentajeToApplyBefore * 72 / 100.0F;
		}
		if ("Relaxed".equals(timeFrame)) {
			timerBeforeSelect = percentajeToApplyBefore * 167 / 100.0F;
		}
		int hourpart = (int)Math.abs(timerBeforeSelect);
		int minutespart = (int)Math.abs((timerBeforeSelect - hourpart) * 100.0F);
		c.add(10, hourpart);
		c.add(12, minutespart);
		finishDate = c.getTime();
		return finishDate;
	}


	private void sendEmailAfterServiceRequestCreated(String customerEmail, String customername, String serviceRequestid) {
		emailService2.sendEmailToCustomerAndUnquotedTranslatorsAferSRCreated(customerEmail, serviceRequestid, customername);
	}

	private String getRemainingDateToSelectQuote(Date finishDate) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = Calendar.getInstance();
		c.setTime(finishDate);
		return dateFormat.format(c.getTime());
	}

	private List<ServiceRequestDTO> getServiceRequestArrayDTO(List<ServiceRequest> serviceRequestList) {
		List<ServiceRequestDTO> listDTO = new ArrayList();
		Long userIdByEmail = userService.getUserIdByRole(userService.getCurrentUser());

		for (ServiceRequest serviceRequest : serviceRequestList) {
			ServiceRequestDTO dto = new ServiceRequestDTO();
			dto.setServiceRequestCategory(serviceRequest.getServiceRequestCategory().getDescription());
			dto.setTimeFrame(serviceRequest.getTimeFrame().getDescription());
			dto.setLanguagefrom(serviceRequest.getLanguagefrom());
			dto.setLanguageTo(serviceRequest.getLanguageTo());
			dto.setHardcopy(serviceRequest.getHardcopy());
			dto.setStatus(serviceRequest.getServiceRequestStatus().getDescription());
			dto.setId(serviceRequest.getId());
			dto.setFinishDate(serviceRequest.getFinishDate());
			dto.setPaidDate(serviceRequest.getPaidDate());
			dto.setCreateDate(serviceRequest.getCreationDate());
			dto.setFinishQuoteDate(getRemainingDateToSelectQuote(serviceRequest.getFinishQuoteSelection()));
			dto.setCountOfUnreadMessages(ConversationUtils.getCountOfUnreadMessage(serviceRequest.getConversationList(), userIdByEmail));
			dto.setDescription(serviceRequest.getDescription());

			Set<AmazonFile> amazonFiles = new HashSet<AmazonFile>();
			amazonFiles.addAll(amazonService.findByServiceRequestIdAndType(serviceRequest.getId(), FileType.SERVICE_REQUEST));
			dto.setAmazonList(convertToDto(amazonFiles));

			Invoice invoice = serviceRequest.getInvoice();
			if (invoice != null)
				dto.setInvoiceUrl(invoice.getFile().getUrl());
			Translator translator = serviceRequest.getTranslator();
			if (translator != null) {
				dto.setTranslatorName(translator.getUser().getName());
				Quotation quote = quotationService.getQuoteFromServiceRequestAndTranslator(serviceRequest.getId(), serviceRequest.getTranslator().getId());
				dto.setQuote(quote.getValue());
			}
			
			 Rate rate = rateService.getRateByServiceRequest(serviceRequest);
	            if(rate!=null){
	                RateDTO rateDto = new RateDTO();
	                rateDto.setFeedback(rate.getFeedback());
	                rateDto.setService(rate.getServiceAsDescribed());
	                rateDto.setTimeDelivery(rate.getTimeDelivery());
	                rateDto.setQuality(rate.getQuality());
	                dto.setRateDto(rateDto);
	            }
			
			listDTO.add(dto);
		}
		return listDTO;
	}

	private PieChart getPieChart(int quoted, int request, int assignmets) {
		Slice s1 = Slice.newSlice(assignmets, Color.newColor("CACACA"), "Assignments");
		Slice s2 = Slice.newSlice(request, Color.newColor("32c5d2"), "Request");
		Slice s3 = Slice.newSlice(quoted, Color.newColor("3598dc"), "Quoted");
		PieChart pieChart = GCharts.newPieChart(new Slice[] { s1, s2, s3 });
		pieChart.setTitle("My Request in Progress", Color.BLACK, 15);
		pieChart.setBackgroundFill(Fills.newSolidFill(Color.newColor("eef1f5")));
		pieChart.setSize(520, 260);
		pieChart.setThreeD(true);
		return pieChart;
	}

	private BarChart getBarChart(BusinessUser businessUser) {
		List<ServiceRequestApprovedDTO> list = serviceRequestApprovedRegisterService.getApprovedSRByCustomer(businessUser);
		String[] months = new String[list.size()];
		double[] values = new double[list.size()];
		int i = 0;
		for (ServiceRequestApprovedDTO dto : list) {
			months[i] = dto.getMonth();
			values[i] = dto.getAmount();
			i++;
		}

		BarChartPlot team1 = Plots.newBarChartPlot(Data.newData(values), Color.newColor("3598dc"));
		BarChart barChart = GCharts.newBarChart(new BarChartPlot[] { team1 });
		AxisStyle axisStyle = AxisStyle.newAxisStyle(Color.BLACK, 13, AxisTextAlignment.CENTER);
		AxisLabels score = AxisLabelsFactory.newAxisLabels("Amount", 50.0D);
		score.setAxisStyle(axisStyle);
		AxisLabels year = AxisLabelsFactory.newAxisLabels("Month", 50.0D);
		year.setAxisStyle(axisStyle);

		barChart.addXAxisLabels(AxisLabelsFactory.newAxisLabels(months));
		barChart.addYAxisLabels(AxisLabelsFactory.newNumericRangeAxisLabels(0.0D, 100.0D));
		barChart.addYAxisLabels(score);
		barChart.addXAxisLabels(year);
		barChart.setSize(520, 260);
		barChart.setBarWidth(100);
		barChart.setSpaceWithinGroupsOfBars(20);
		barChart.setDataStacked(true);
		barChart.setTitle("Translated Documents", Color.BLACK, 16);
		barChart.setGrid(100.0D, 10.0D, 3, 2);
		barChart.setBackgroundFill(Fills.newSolidFill(Color.newColor("eef1f5")));
		LinearGradientFill fill = Fills.newLinearGradientFill(0, Color.LAVENDER, 100.0D);
		fill.addColorAndOffset(Color.WHITE, 0.0D);
		barChart.setAreaFill(fill);
		return barChart;
	}

	private void getDataLoad(Model model, BusinessUser businessUser, BusinessUserDTO businessUserDTO) {
		List<ChatMessage> messageList = chatMessageService.getUnreadMessageByCustomerId(businessUser.getUser().getId());
		List<ChatMessageDTO> dtoList = getMessagesDTO(new HashSet(messageList));
		model.addAttribute("businessUserForm", businessUserDTO);
		model.addAttribute("unreadMessageList", dtoList);
		model.addAttribute("newMessagesNumber", Integer.valueOf(messageList.size()));
		AmazonFilePhoto photoView = amazonFilePhotoService.getAmazonFilePhotoByUserId(businessUser.getUser());
		if (photoView == null) {
			model.addAttribute("photoUrl", "resources/assets/layouts/layout2/img/avatar.png");
		} else {
			model.addAttribute("photoUrl", photoView.getUrl());
		}
	}

	private List<Language> initializeProfiles() {
		List<Language> languageList = languageService.getAvailableLanguages();
		return languageList;
	}
}
