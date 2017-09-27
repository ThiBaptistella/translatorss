package au.com.translatorss.controller.translator;

import au.com.translatorss.bean.*;
import au.com.translatorss.bean.dto.*;
import au.com.translatorss.controller.BaseController;
import au.com.translatorss.dao.ServiceRequestStatusDao;
import au.com.translatorss.dao.ServiceResponseStatusDao;
import au.com.translatorss.enums.FileType;
import au.com.translatorss.interceptors.ServiceRequestUpdateListener;
import au.com.translatorss.listeners.ChatMessagesListener;
import au.com.translatorss.service.*;
import au.com.translatorss.validation.PasswordChangeRequestValidator;
import au.com.translatorss.validation.QuotationDTOValidator;
import au.com.translatorss.validation.TranslatorValidatorSettings;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
import com.googlecode.charts4j.Plots;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static au.com.translatorss.utils.ConversationUtils.getCountOfUnreadMessage;

@Controller
@PreAuthorize("hasAnyAuthority('TRANSLATOR')")
public class TranslatorDashboardController extends BaseController{

    private static final Logger logger = LoggerFactory.getLogger(TranslatorDashboardController.class);

    private static final int BUFFER_SIZE = 4096;

    @Autowired
    private TranslatorSettingsService translatorService;

    @Autowired
    private CustomerServiceRequestService serviceRequestService;

    @Autowired
    private TranslatorQuotationService translatorQuotationService;

    @Autowired
    private QuotationDTOValidator quotationDTOValidator;

    @Autowired
    private TranslatorValidatorSettings translatorValidatorSettings;

    @Autowired
    private ConversationService conversationService;

    @Autowired
    private ChatMessageService chatMessageService;

    @Autowired
    private ServiceRequestStatusDao serviceRequestStatusDao;

    @Autowired
    private ServiceResponseService serviceResponseService;

    @Autowired
    private ServiceRequestUpdateListener serviceRequestUpdateListener;

    @Autowired
    private ChatMessagesListener chatMessagesListener;

    @Autowired
    private PasswordChangeRequestValidator passwordChangeRequestValidator;

    @Autowired
    private EmailService2 emailService2;

    @Autowired
    private UserService userService;

    @Autowired
	private ServiceResponseStatusDao responseDao;

    @Autowired
    private AmazonService amazonService;
    
	@Autowired
	private AmazonFilePhotoService amazonFilePhotoService;
    
	@Autowired
	private ServiceRequestApprovedRegisterService serviceRequestApprovedRegisterService;

    @InitBinder("quotationConfForm")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(quotationDTOValidator);
    }

    @InitBinder("updateTranslatorForm")
    protected void initTranslatorBinder(WebDataBinder binder) {
        binder.addValidators(translatorValidatorSettings);
    }

    @InitBinder("passwordDTOForm")
    protected void initPasswordBinder(WebDataBinder binder){
     binder.addValidators(passwordChangeRequestValidator);
    }

    @RequestMapping(value = "/dashboard")
    public String dashboard(HttpSession session,Model model) {
        logger.info("Welcome TranslatorDashboardController: dashboard");
    	Translator translator = (Translator) session.getAttribute("loggedInUser");
        AmazonFilePhoto photoView =amazonFilePhotoService.getAmazonFilePhotoByUserId(translator.getUser());

		List<ChatMessage> messageList= chatMessageService.getUnreadMessageByCustomerId(translator.getUser().getId());
		List<ChatMessageDTO> dtoList = getMessagesDTO(new HashSet(messageList));

		model.addAttribute("unreadMessageList", dtoList);
		model.addAttribute("newMessagesNumber", messageList.size());
        
        int assignmentsInProgress = serviceRequestService.getServiceRequestFromTanslator(translator.getId(), "OpenService").size();
        int specialAssignmentsUnquoted = serviceRequestService.getServiceRequestAvailableForTranslator(translator).size();
        int myAutomaticQuotedAssignments= serviceRequestService.getServiceRequestQuotedFromTranslator(translator, "Quoted").size();
        int myTotalApprovedAssignments=serviceRequestService.getServiceRequestFromTranslator(translator.getId(), "Approved").size();
        
        if(photoView ==null){
			model.addAttribute("photoUrl", "resources/assets/layouts/layout2/img/avatar.png");
		}else{
			model.addAttribute("photoUrl", photoView.getUrl());
		}
        model.addAttribute("assignmentsInProgress", assignmentsInProgress);
        model.addAttribute("specialAssignmentsUnquoted", specialAssignmentsUnquoted);
        model.addAttribute("myAutomaticQuotedAssignments", myAutomaticQuotedAssignments);
        model.addAttribute("myTotalApprovedAssignments", myTotalApprovedAssignments);
        if(translator.getStatus().equals("Active")){
            model.addAttribute("translatorStatus","Active");
        }else{
        	model.addAttribute("translatorStatus","Paused(Contact Us)");
        }
        model.addAttribute("barURL", getBarChart().toURLString());
        model.addAttribute("translatorName", translator.getUser().getName());
        TranslatorQuotationDTO dto=new TranslatorQuotationDTO();
        populateMediaRating(dto,translator);
        model.addAttribute("OnTimeDelivery", dto.getTimeDelivery());
        model.addAttribute("CustomerService", dto.getServiceDescribed());
        model.addAttribute("Quality", dto.getQuality());

        return "translatorDashboard/indexTranslator";
    }
    
//    private void populateMediaRating(TranslatorQuotationDTO dto, Translator translator) {
//		List<Rate> rateList = translatorService.getAllTranslatorRates(translator);
//		int rateSize = rateList.size();
//		if (rateSize == 0) {
//			rateSize = 1;
//		}
//		int quality = 0;
//		int serviceDescribed = 0;
//		int time = 0;
//		for (Rate rate : rateList) {
//            quality += rate.getQuality();
//			serviceDescribed += rate.getServiceAsDescribed();
//			time += rate.getTimeDelivery();
//		}
//		dto.setTimeDelivery(time / rateSize);
//		dto.setServiceDescribed(serviceDescribed / rateSize);
//		dto.setQuality(quality / rateSize);
//	}
    
    
    
    private BarChart getBarChart(){
		String[] months = new String[4];
		months[0]="14/5 - 21/5";
		months[1]="22/5 - 4/6";
		months[2]="4/6 - 11/6";
		months[3]="11/6 - 18/6";

		double[] values = new double[4];
		values[0]=40;
		values[1]=20;
		values[2]=100;
		values[3]=50;

		BarChartPlot team1=Plots.newBarChartPlot(Data.newData(values),Color.newColor("3598dc"));
		BarChart barChart=GCharts.newBarChart(team1);
		AxisStyle axisStyle=AxisStyle.newAxisStyle(Color.BLACK,13,AxisTextAlignment.CENTER);
		AxisLabels score=AxisLabelsFactory.newAxisLabels("Amount",50.0);
		score.setAxisStyle(axisStyle);
		AxisLabels year=AxisLabelsFactory.newAxisLabels("Month",50.0);
		year.setAxisStyle(axisStyle);
		
		barChart.addXAxisLabels(AxisLabelsFactory.newAxisLabels(months));
		barChart.addYAxisLabels(AxisLabelsFactory.newNumericRangeAxisLabels(0,100));
		barChart.addYAxisLabels(score);
		barChart.addXAxisLabels(year);
		barChart.setSize(520,260);
        barChart.setBarWidth(100);
		barChart.setSpaceWithinGroupsOfBars(20);
		barChart.setDataStacked(true);
		barChart.setTitle("Translated Documents",Color.BLACK,16);
		barChart.setGrid(100,100,3,2);
		barChart.setBackgroundFill(Fills.newSolidFill(Color.newColor("eef1f5")));
		LinearGradientFill fill=Fills.newLinearGradientFill(0,Color.LAVENDER,100);
		fill.addColorAndOffset(Color.WHITE,0);
		barChart.setAreaFill(fill);
		return barChart;
	}
    

    @RequestMapping("/allconversations")
    public String allconversations(HttpSession session,Model model) throws Exception {
        logger.info("Welcome TranslatorDashboardController: allconversations");
        Translator translatorloged = (Translator) session.getAttribute("loggedInUser");
        List<Conversation> conversationList = conversationService.getAllConversationsByTranslatorId(translatorloged.getId());
        model.addAttribute("translatorName", translatorloged.getUser().getName());
        model.addAttribute("conversationList", conversationList);
        model.addAttribute("currentUser", userService.getUserIdByRole(userService.getCurrentUser()));
        
		List<ChatMessage> messageList= chatMessageService.getUnreadMessageByCustomerId(translatorloged.getUser().getId());
		List<ChatMessageDTO> dtoList = getMessagesDTO(new HashSet(messageList));

		model.addAttribute("unreadMessageList", dtoList);
		model.addAttribute("newMessagesNumber", messageList.size());
        
        AmazonFilePhoto photoView =amazonFilePhotoService.getAmazonFilePhotoByUserId(translatorloged.getUser());
        if(photoView ==null){
			model.addAttribute("photoUrl", "resources/assets/layouts/layout2/img/avatar.png");
		}else{
			model.addAttribute("photoUrl", photoView.getUrl());
		}		
        return "/translatorDashboard/conversations";
    }

    @RequestMapping(value = "/myJobInProgress")
    public String myTask(HttpSession session, Model model) {
        logger.info("Welcome TranslatorDashboardController: job in progress");
        Translator translatorloged = (Translator) session.getAttribute("loggedInUser");
        List<ServiceRequest> serviceRequestList = serviceRequestService.getServiceRequestFromTanslator(translatorloged.getId(), "OpenService");
        List<ServiceRequestDTO> serviceRequestDTOList = getServiceRequestArrayDTO(serviceRequestList);
        model.addAttribute("serviceRequestList", serviceRequestDTOList);
        model.addAttribute("translatorName", translatorloged.getUser().getName());
        
		List<ChatMessage> messageList= chatMessageService.getUnreadMessageByCustomerId(translatorloged.getUser().getId());
		List<ChatMessageDTO> dtoList = getMessagesDTO(new HashSet(messageList));

		model.addAttribute("unreadMessageList", dtoList);
		model.addAttribute("newMessagesNumber", messageList.size());
        
        AmazonFilePhoto photoView =amazonFilePhotoService.getAmazonFilePhotoByUserId(translatorloged.getUser());
        if(photoView ==null){
			model.addAttribute("photoUrl", "resources/assets/layouts/layout2/img/avatar.png");
		}else{
			model.addAttribute("photoUrl", photoView.getUrl());
		}        
        return "/translatorDashboard/jobInProgress";
    }

    @RequestMapping(value = "/myHistory")
    public String myHistory(HttpSession session,Model model) {
        logger.info("Welcome TranslatorDashboardController: myHistory");
        Translator translatorloged = (Translator) session.getAttribute("loggedInUser");
        List<ServiceRequest> serviceRequestListUnquoted = serviceRequestService.getServiceRequestFromTranslator(translatorloged.getId(), "Approved");
        List<ServiceRequest> serviceRequestListExpired = serviceRequestService.getServiceRequestFromTranslator(translatorloged.getId(), "Expired");
        List<ServiceRequestDTO> serviceRequestDTOList = getServiceRequestArrayDTO(serviceRequestListUnquoted);
        serviceRequestListUnquoted.addAll(serviceRequestListExpired);
        model.addAttribute("serviceRequestList", serviceRequestDTOList);
        model.addAttribute("translatorName", translatorloged.getUser().getName());
        
		List<ChatMessage> messageList= chatMessageService.getUnreadMessageByCustomerId(translatorloged.getUser().getId());
		List<ChatMessageDTO> dtoList = getMessagesDTO(new HashSet(messageList));

		model.addAttribute("unreadMessageList", dtoList);
		model.addAttribute("newMessagesNumber", messageList.size());
        
        AmazonFilePhoto photoView =amazonFilePhotoService.getAmazonFilePhotoByUserId(translatorloged.getUser());
        if(photoView ==null){
			model.addAttribute("photoUrl", "resources/assets/layouts/layout2/img/avatar.png");
		}else{
			model.addAttribute("photoUrl", photoView.getUrl());
		}        
        return "/translatorDashboard/myHistory";
    }

    //REVISED PENDING ACTION
    @RequestMapping(value = "/myPendingActions")
    public String pendingActions(HttpSession session,Model model) {
        logger.info("Welcome TranslatorDashboardController: myPendingActions");
        Translator translatorloged = (Translator) session.getAttribute("loggedInUser");
        if (translatorloged.getStatus().equals("Paused")) {
            model.addAttribute("message", "You are not available to get job");
            model.addAttribute("translatorName", translatorloged.getUser().getName());
            model.addAttribute("serviceRequestList", new ArrayList<ServiceRequest>());
            model.addAttribute("serviceRequestQuotedList", new ArrayList<ServiceRequest>());
            return "/translatorDashboard/pendingActions";
        }
        List<ServiceRequest> serviceRequestForManuallyQuoteList = serviceRequestService.getServiceRequestAvailableForTranslator(translatorloged);
        List<ServiceRequest> serviceRequestAutomaticQuotedList = serviceRequestService.getServiceRequestQuotedFromTranslator(translatorloged, "Quoted");
        model.addAttribute("translatorName", translatorloged.getUser().getName());
        model.addAttribute("serviceRequestList", getServiceRequestArrayDTO(serviceRequestForManuallyQuoteList));
        model.addAttribute("serviceRequestQuotedList", getServiceRequestArrayDTO(serviceRequestAutomaticQuotedList));
        model.addAttribute("message", "Jobs Available");
        
		List<ChatMessage> messageList= chatMessageService.getUnreadMessageByCustomerId(translatorloged.getUser().getId());
		List<ChatMessageDTO> dtoList = getMessagesDTO(new HashSet(messageList));

		model.addAttribute("unreadMessageList", dtoList);
		model.addAttribute("newMessagesNumber", messageList.size());
        
        AmazonFilePhoto photoView =amazonFilePhotoService.getAmazonFilePhotoByUserId(translatorloged.getUser());
        if(photoView ==null){
			model.addAttribute("photoUrl", "resources/assets/layouts/layout2/img/avatar.png");
		}else{
			model.addAttribute("photoUrl", photoView.getUrl());
		}        
        return "/translatorDashboard/pendingActions";
    }

    @RequestMapping("/sendManuallyQuote")
    public String sendManuallyQuote(Model model,HttpSession session,@RequestParam("serviceRequestId") Long serviceRequestId, @RequestParam("value") Long value)throws Exception {
        logger.info("Welcome BusinessUserDashboardController: SendManuallyQuote");
        ServiceRequest serviceRequest = serviceRequestService.find(serviceRequestId);
        Translator translatorloged = (Translator) session.getAttribute("loggedInUser");
        Quotation quotation = new Quotation();
        quotation.setServiceRequest(serviceRequest);
        quotation.setTranslator(translatorloged);
        quotation.setValue(new BigDecimal(value));
        quotation.setIsValid(true);
        quotation.setIsAutomatic(false);
        translatorQuotationService.update(quotation);
        model.addAttribute("translatorForm", translatorloged);
        if (serviceRequest.getServiceRequestStatus().getDescription().equals("Unquoted")) {
            serviceRequest.setServiceRequestStatus(serviceRequestStatusDao.findByDescription("Quoted"));
            serviceRequestService.saveOrUpdate(serviceRequest);
        }
        return "redirect:/myPendingActions";
    }

//    //TODO: Ver en detalle
//    @RequestMapping("/conversationWithCustomer/{id}")
//    public String conversationWithCustomer(@PathVariable("id") long id, HttpSession session, Model model)throws Exception {
//    	logger.info("Welcome BusinessUserDashboardController: conversationWithCustomer");
//    	ServiceRequest serviceRequest = serviceRequestService.find(id);
//        Translator trans = (Translator) session.getAttribute("loggedInUser");
//
//    	Conversation conversation = serviceRequest.getConversationList().iterator().next();
//
//        session.setAttribute("serviceRequest", serviceRequest);
//    	session.setAttribute("conversationid", conversation.getId());
//        return "redirect:/messagesWithCustomer";
//    }

    @RequestMapping("/seeMyConversation/{id}")
    public String seeMyConversation(@PathVariable("id") Long id, Model model,HttpSession session)
            throws Exception {
        logger.info("Welcome TranslatorDashboardController: seeMyConversation");
        Conversation conversation = conversationService.get(id);
        ServiceRequest serviceRequest = conversation.getServiceRequest();
        session.setAttribute("serviceRequest", serviceRequest);
        session.setAttribute("conversationid", conversation.getId());
        return "redirect:/messagesWithCustomer";
    }

	@RequestMapping("/startConversationWithCustomer/{serviceRequestid}")
	public String startConversation(@PathVariable("serviceRequestid") long serviceRequestid, Model model, HttpSession session){
		logger.info("Welcome TranslatorDashboardController: startConversation");
		ServiceRequest sr=serviceRequestService.find(serviceRequestid);
        Translator trans = (Translator) session.getAttribute("loggedInUser");
		Conversation conversation = conversationService.getConversationFromServiceRequestIdAndTranslatorId(sr.getId(), trans.getId());
		if(conversation==null){
			conversation = conversationService.starConversation(sr, trans);
		}
		session.setAttribute("serviceRequest", sr);
		session.setAttribute("conversationid", conversation.getId());
		return "redirect:/messagesWithCustomer";
	}



    @RequestMapping("/messagesWithCustomer")
    public String messages(HttpSession session, Model model) throws Exception {
        logger.info("Welcome BusinessUserDashboardController: messagesWithCustomer");

        Long conversationid = (Long) session.getAttribute("conversationid");
        Conversation conversation = conversationService.get(conversationid);
        ServiceRequest serviceRequest = conversation.getServiceRequest();

        User user = userService.getCurrentUser();
        conversationService.messagesMarkAsRead(user, conversationid);

    	Translator translatorloged = (Translator) session.getAttribute("loggedInUser");

        model.addAttribute("messageList", getMessagesDTO(conversation.getMessageList()));
        model.addAttribute("translatorName", translatorloged.getUser().getName());

        AmazonFilePhoto photo=this.amazonFilePhotoService.getAmazonFilePhotoByUserId(user);

        ChatMessageDTO message = new ChatMessageDTO();
        message.setPhotoUrl(photo.getUrl());
        message.setConversationid(conversation.getId());
        message.setSender(translatorloged.getUser().getName());
        model.addAttribute("message", message);

        ServiceResponseDTO serviceResponseDTO = new ServiceResponseDTO();
        ServiceResponse serviceResponse = conversation.getServiceResponse();
        if(serviceResponse==null){
			serviceResponse = new ServiceResponse();
			serviceResponse.setCreationDate(new Date());
			serviceResponse.setDescription("description");
			serviceResponse.setTranslator(conversation.getTranslator());
			serviceResponse.setUpdateDate(new Date());
			serviceResponse.setServiceResponseStatus(responseDao.findByDescription("Open"));
			conversation.setServiceResponse(serviceResponse);
			conversationService.saveOrUpdate(conversation);
		}
        serviceResponseDTO.setId(serviceResponse.getId());

        List<ServiceRequest> list= new ArrayList<ServiceRequest>();
    	list.add(conversation.getServiceRequest());
        Set<AmazonFile> amazonFiles = new HashSet<AmazonFile>();
        amazonFiles.addAll(amazonService.findByServiceRequestIdAndType(serviceRequest.getId(), FileType.SERVICE_REQUEST));
        amazonFiles.addAll(amazonService.findByServiceResponseIdAndType(serviceResponse.getId(), FileType.SERVICE_RESPONSE));

    	model.addAttribute("serviceRequestList", list);
        model.addAttribute("serviceRequestStatus",serviceRequest.getServiceRequestStatus().getDescription());

        model.addAttribute("fileList", convertToDto(amazonFiles));
        model.addAttribute("serviceResponse", serviceResponseDTO);
        model.addAttribute("conversationid", conversation.getId());
        model.addAttribute("finishQuoteDate", serviceRequest.getFinishDate());
//        model.addAttribute("serviceRequestFiles", amazonService.findByServiceRequestIdAndType(serviceRequest.getId(), FileType.INVOICE));

		List<ChatMessage> messageList= chatMessageService.getUnreadMessageByCustomerId(translatorloged.getUser().getId());
		List<ChatMessageDTO> dtoList = getMessagesDTO(new HashSet(messageList));

		model.addAttribute("unreadMessageList", dtoList);
		model.addAttribute("newMessagesNumber", messageList.size());
        
        AmazonFilePhoto photoView =amazonFilePhotoService.getAmazonFilePhotoByUserId(translatorloged.getUser());
        if(photoView ==null){
			model.addAttribute("photoUrl", "resources/assets/layouts/layout2/img/avatar.png");
		}else{
			model.addAttribute("photoUrl", photoView.getUrl());
		}        
        return "/translatorDashboard/messages";
    }

    private List<AmazonFileDto> convertToDto(Set<AmazonFile> files) {
        List<AmazonFileDto> dtos = new ArrayList<>();
        for (AmazonFile file : files) {
            AmazonFileDto dto = new AmazonFileDto(file);
            dtos.add(dto);
        }
        return dtos;
    }

    @Autowired
    private RateService rateService;

    private List<ServiceRequestDTO> getServiceRequestArrayDTO(List<ServiceRequest> serviceRequestList) {
        List<ServiceRequestDTO> listDTO = new ArrayList<>();
        Long userId = userService.getUserIdByRole(userService.getCurrentUser());
        for (ServiceRequest serviceRequest : serviceRequestList) {
            ServiceRequestDTO dto = new ServiceRequestDTO();
            serviceRequest = serviceRequestService.find(serviceRequest.getId());
            dto.setServiceRequestCategory(serviceRequest.getServiceRequestCategory().getDescription());
            dto.setTimeFrame(serviceRequest.getTimeFrame().getDescription());
            dto.setLanguagefrom(serviceRequest.getLanguagefrom());
            dto.setLanguageTo(serviceRequest.getLanguageTo());
            dto.setHardcopy(serviceRequest.getHardcopy());
            dto.setStatus(serviceRequest.getServiceRequestStatus().getDescription());
            dto.setFinishDate(serviceRequest.getFinishDate());
            dto.setId(serviceRequest.getId());
            dto.setDescription(serviceRequest.getDescription());
            Quotation quote = this.translatorQuotationService.getQuoteFromServiceRequestAndTranslator(serviceRequest.getId(),userId );
            if(quote!=null){
            	dto.setQuote(quote.getValue());
            }else{
            	dto.setQuote(null);
            }
            Invoice invoice = serviceRequest.getInvoice();
            if (invoice != null && invoice.getFile()!=null)
                dto.setInvoiceUrl(invoice.getFile().getUrl());
            dto.setCountOfUnreadMessages(getCountOfUnreadMessage(serviceRequest.getConversationList(), userId));
            dto.setFinishQuoteDate(getRemainingDateToSelectQuote(serviceRequest.getFinishQuoteSelection()));
            dto.setClientName(serviceRequest.getCustomer().getUser().getName());
            
            Set<AmazonFile> amazonFiles = new HashSet<AmazonFile>();
            amazonFiles.addAll(amazonService.findByServiceRequestIdAndType(serviceRequest.getId(), FileType.SERVICE_REQUEST));
            dto.setAmazonList(convertToDto(amazonFiles));

            Rate rate = rateService.getRateByServiceRequest(serviceRequest);

            if(rate!=null){
                RateDTO rateDto = new RateDTO();
                rateDto.setFeedback(rate.getFeedback());
                rateDto.setServiceDescribed(rate.getServiceAsDescribed());
                rateDto.setTranslatorComunication(rate.getTimeDelivery());
                rateDto.setWouldRecomend(rate.getQuality());
                dto.setRateDto(rateDto);
            }

            listDTO.add(dto);
        }
        return listDTO;
    }
    
    
    
    @RequestMapping(value = "/submitMessageToCustomer", method = RequestMethod.POST)
    public String submitMessage(@ModelAttribute("message") ChatMessageDTO message, HttpSession session,
                                RedirectAttributes redirectAttributes) throws Exception {
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
        redirectAttributes.addFlashAttribute("conversation", conversation);
        session.setAttribute("conversation", conversation);
        return "redirect:/messagesWithCustomer";
    }


    @RequestMapping(value = "/serviceResponseProcesor", method = RequestMethod.POST)
    public String submitServiceResponse(@ModelAttribute("serviceResponse") ServiceResponseDTO serviceResponseDTO,Model model, RedirectAttributes redirectAttributes) throws Exception {
        ServiceResponse serviceResponse = serviceResponseService.find(serviceResponseDTO.getId());
        serviceResponseService.saveFiles(serviceResponse, serviceResponseDTO.getFiles());
        ServiceRequest serviceRequest = this.conversationService.getConversationByServiceResponseId(serviceResponse.getId()).getServiceRequest();
        emailService2.sendEmailToCustomerFileSentByTranslator(serviceRequest.getCustomer().getUser().getEmail(), serviceRequest.getCustomer().getUser().getName(), serviceResponse.getTranslator().getUser().getName(), serviceResponseDTO.getFiles(), serviceRequest.getId().toString());
        redirectAttributes.addFlashAttribute("serviceRequest", serviceRequest);
        redirectAttributes.addFlashAttribute("conversation", serviceRequest.getConversationList().iterator().next());
        return "redirect:/messagesWithCustomer";
    }

//    private List<ChatMessageDTO> getMessagesDTO(Set<ChatMessage> messageList) {
//        List<ChatMessageDTO> messageListDTO = new ArrayList();
//        for (ChatMessage message : messageList) {
//            ChatMessageDTO dto = new ChatMessageDTO();
//            dto.setId(message.getId().longValue());
//
//
//            String pattern ="yyyy-MM-dd HH:mm";
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
//            String dateInString = simpleDateFormat.format(message.getDate());
//
//            try {
//                dto.setDate(simpleDateFormat.parse(dateInString));
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//            dto.setMessage(message.getMessage());
//            dto.setSender(message.getSender());
//            dto.setRead(message.getRead());
//            dto.setConversationid(message.getConversation().getId());
//            User user = userService.getById(message.getSenderId());
//            AmazonFilePhoto photo = amazonFilePhotoService.getAmazonFilePhotoByUserId(user);
//            if (photo != null) {
//                dto.setPhotoUrl(photo.getUrl());
//            } else {
//                dto.setPhotoUrl("resources/assets/layouts/layout2/img/avatar.png");
//            }
//
//            messageListDTO.add(dto);
//        }
//        Collections.sort(messageListDTO);
//        return messageListDTO;
//    }


    @RequestMapping(value = "/settings")
    public String settings(HttpSession session, Model model) {
        logger.info("Welcome TranslatorDashboardController: settings");
        Translator translatorloged = (Translator) session.getAttribute("loggedInUser");

        TranslatorDTO translator = new TranslatorDTO();
        translator.setAddress(translatorloged.getAddress());
        translator.setId(translatorloged.getId());
        translator.setUserId(translatorloged.getUser().getId());
        translator.setEmail(translatorloged.getUser().getEmail());
        translator.setName(translatorloged.getUser().getName());
        translator.setFullName(translatorloged.getFullname());
        translator.setPaypalClientId(translatorloged.getPaypalClientId());
        translator.setPhone(translatorloged.getPhone());
        translator.setPassword(translatorloged.getUser().getPassword());
        translator.setStatus(translatorloged.getStatus());
        translator.setPreferedName(translatorloged.getUser().getName());
        translator.setAbn_name(translatorloged.getAbn_name() );
        translator.setAbn_number(translatorloged.getAbn_number());

        translator.setNaatiNumber(translatorloged.getNaatiNumber());
        translator.setNatyExpiration(translatorloged.getNatyExpiration());
        translator.setLanguage(translatorloged.getLanguageList().get(0).getDescription());

        PasswordDTO password = new PasswordDTO();
        password.setEmail(translator.getEmail());
        model.addAttribute("passwordDTOForm", password);
        model.addAttribute("translatorName", translatorloged.getUser().getName());
        model.addAttribute("updateTranslatorForm", translator);
        session.setAttribute("email", password.getEmail());
        
		List<ChatMessage> messageList= chatMessageService.getUnreadMessageByCustomerId(translatorloged.getUser().getId());
		List<ChatMessageDTO> dtoList = getMessagesDTO(new HashSet(messageList));

		model.addAttribute("unreadMessageList", dtoList);
		model.addAttribute("newMessagesNumber", messageList.size());
        
        AmazonFilePhoto photoView =amazonFilePhotoService.getAmazonFilePhotoByUserId(translatorloged.getUser());
        if(photoView ==null){
			model.addAttribute("photoUrl", "resources/assets/layouts/layout2/img/avatar.png");
		}else{
			model.addAttribute("photoUrl", photoView.getUrl());
		}
        
        return "/translatorDashboard/settings";
    }


    @RequestMapping(value = "/suscription")
    public String suscription(HttpSession session, Model model) {
    	Translator translator = (Translator) session.getAttribute("loggedInUser");
        model.addAttribute("translatorName", translator.getUser().getName());
        
        AmazonFilePhoto photoView =amazonFilePhotoService.getAmazonFilePhotoByUserId(translator.getUser());
        if(photoView ==null){
			model.addAttribute("photoUrl", "resources/assets/layouts/layout2/img/avatar.png");
		}else{
			model.addAttribute("photoUrl", photoView.getUrl());
		}
        
		List<ChatMessage> messageList= chatMessageService.getUnreadMessageByCustomerId(translator.getUser().getId());
		List<ChatMessageDTO> dtoList = getMessagesDTO(new HashSet(messageList));

		model.addAttribute("unreadMessageList", dtoList);
		model.addAttribute("newMessagesNumber", messageList.size());
		
        return "/translatorDashboard/suscription";
    }

//    @RequestMapping(value = "/updateTranslator")
//    public String updateTranslator(HttpSession session,Model model, @ModelAttribute("updateTranslatorForm") @Validated TranslatorDTO translatorDTO,BindingResult result,RedirectAttributes redirectAttributes) {
//        Translator translatorloged = (Translator) session.getAttribute("loggedInUser");
//        if (result.hasErrors()) {
//        	PasswordDTO password = new PasswordDTO();
//            password.setEmail(translatorloged.getUser().getEmail());
//        	model.addAttribute("passwordDTOForm", password);
//            model.addAttribute("updateTranslatorForm", translatorDTO);
//            model.addAttribute("translatorName", translatorloged.getUser().getName());
//    		List<ChatMessage> messageList= chatMessageService.getUnreadMessageByCustomerId(translatorloged.getUser().getId());
//    		model.addAttribute("unreadMessageList", messageList);
//    		model.addAttribute("newMessagesNumber", messageList.size());
//            return "translatorDashboard/settings";
//        }
//
//    	translatorloged.setPaypalClientId(translatorDTO.getPaypalClientId());
//        translatorloged.getUser().setName(translatorDTO.getName());
//        translatorloged.setPhone(translatorDTO.getPhone());
//        translatorloged.getUser().setEmail(translatorDTO.getEmail());
//        translatorloged.setAddress(translatorDTO.getAddress());
//        this.translatorService.saveTranslator(translatorloged);
//        redirectAttributes.addFlashAttribute("message", "Your Personal Details have been updated");
//        return "redirect:/dashboard";
//    }
//
//
//    @RequestMapping(value = "/updateTranslatorPassword", method = RequestMethod.POST)
//    public String updateBusinessUserPassword(HttpSession session,@ModelAttribute("passwordDTOForm") @Validated PasswordDTO passwordDTO,BindingResult result, Model model, RedirectAttributes redirectAttributes) {
//        Translator translatorloged = (Translator) session.getAttribute("loggedInUser");
//
//    	if (result.hasErrors()) {
//            model.addAttribute("translatorName", translatorloged.getUser().getName());
//            TranslatorDTO translator = new TranslatorDTO();
//            translator.setAddress(translatorloged.getAddress());
//            translator.setEmail(translatorloged.getUser().getEmail());
//            translator.setName(translatorloged.getUser().getName());
//            translator.setPaypalClientId(translatorloged.getPaypalClientId());
//            translator.setPhone(translatorloged.getPhone());
//            translator.setPassword(translatorloged.getUser().getPassword());
//            model.addAttribute("updateTranslatorForm", translator);
//    		List<ChatMessage> messageList= chatMessageService.getUnreadMessageByCustomerId(translatorloged.getUser().getId());
//    		model.addAttribute("unreadMessageList", messageList);
//    		model.addAttribute("newMessagesNumber", messageList.size());
//            return "translatorDashboard/settings";
//        }
//        Translator translator = this.translatorService.getTranslatorByEmail(passwordDTO.getEmail(), passwordDTO.getCurrentPassword());
//        translator.getUser().setPassword(passwordDTO.getConfirmNewPassword());
//        translatorService.saveTranslator(translator);
//
//        User user = userService.getByEmail(translator.getUser().getEmail());
//        user.setPassword(translator.getUser().getPassword());
//        userService.saveOrUpdate(user);
//
//        model.addAttribute("translatorName", translatorloged.getUser().getName());
//        translatorloged.getUser().setPassword(passwordDTO.getConfirmNewPassword());
//        PasswordDTO password = new PasswordDTO();
//        password.setEmail(translator.getUser().getEmail());
//        model.addAttribute("passwordDTOForm", password);
//
//        return "redirect:/settings";
//    }


    @RequestMapping(value = "/downloadFileFromCustomer/{id}", method = RequestMethod.GET)
    public void downloadFiles2(HttpServletResponse response, @PathVariable("id") long id,
                               HttpServletRequest request) throws IOException {
        ServletContext context = request.getServletContext();

        ServiceRequestFiles serviceRequestFile = serviceRequestService.getServiceRequestFile(id);

        File downloadFile = new File(serviceRequestFile.getUrl());
        FileUtils.writeByteArrayToFile(downloadFile, serviceRequestFile.getFile());

        FileInputStream inputStream = new FileInputStream(downloadFile);

        String mimeType = context.getMimeType(serviceRequestFile.getUrl());
        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }
        System.out.println("MIME type: " + mimeType);

        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());

        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
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

//    @RequestMapping(value = "/checkServReqUpdated/{serviceRequestId}/{guid}")
//    public SseEmitter checkServReqUpdated(@PathVariable("serviceRequestId") long serviceRequestId, @PathVariable("guid") String guid) {
//        SseEmitter emitter = serviceRequestUpdateListener.reCreateEmitter(guid, serviceRequestId);
//        return emitter;
//    }

	@RequestMapping(value = "/uploadTranslatorPhoto", method = RequestMethod.POST)
	public String uploadFileHandler(HttpSession session,@RequestParam("file") MultipartFile file) throws IOException {
		if (!file.isEmpty()) {
	        Translator translator = (Translator) session.getAttribute("loggedInUser");
			amazonFilePhotoService.savePhoto(translator.getUser(), file.getOriginalFilename(), file.getInputStream(), file.getContentType());
		} 
		return "redirect:/settings";
	}
    
    private String getRemainingDateToSelectQuote(Date finishDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.setTime(finishDate);
		return dateFormat.format(c.getTime());
   }
}
