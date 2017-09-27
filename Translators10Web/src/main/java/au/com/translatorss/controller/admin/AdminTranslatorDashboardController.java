package au.com.translatorss.controller.admin;

import au.com.translatorss.bean.*;
import au.com.translatorss.bean.dto.*;
import au.com.translatorss.controller.BaseController;
import au.com.translatorss.controller.LanguageEditor;
import au.com.translatorss.dao.ServiceRequestStatusDao;
import au.com.translatorss.enums.FileType;
import au.com.translatorss.enums.Role;
import au.com.translatorss.service.*;
import au.com.translatorss.validation.TranslatorValidator;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

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
import java.util.*;

@Controller
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class AdminTranslatorDashboardController extends BaseController{

	private static final Logger logger = LoggerFactory.getLogger(AdminTranslatorDashboardController.class);

	private static final int BUFFER_SIZE = 4096;

	@Autowired
	private TranslatorSettingsService translatorService;
	
    @Autowired
    private ServiceRequestConfigurationService serviceRequestConfigurationService;
    
    @Autowired
    private ServiceRequestPaymentService serviceRequestPaymentService;
    
    @Autowired
    private ServiceRequestStatusDao serviceRequestStatusDao;
    
    @Autowired
    private CustomerServiceRequestService serviceRequestService;
    
    @Autowired
    private ConversationService conversationService;

    @Autowired
    private ChatMessageService chatMessageService;
    
    @Autowired
    private ServiceResponseService serviceResponseService;
    
    @Autowired
	private LanguageService languageService;
    
    @Autowired
    private TranslatorValidator translatorValidator;
    
    @Autowired
    private TranslatorValidatorSettings translatorDTOValidator;
    
    @Autowired
    private PurchaseTypeService purchaseTypeService;
   
    @Autowired
    private PurchaseService purchaseService;
    
    @Autowired
	private EmailService2 emailService2;

    @Autowired
    private AmazonService amazonService;

    @Autowired
	private BusinessUserService businessUserService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
	private AmazonFilePhotoService amazonFilePhotoService;
    
    @Autowired
    private TranslatorQuotationService quoteService;
     
    @InitBinder("translatorFormAdmin")
    public void initBinder2(WebDataBinder binder) {
		binder.addValidators(translatorDTOValidator);
    }
    
    @InitBinder("translatorForm")
    public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Language.class, new LanguageEditor(languageService.getAllLanguages()));
		binder.addValidators(translatorValidator);
    }
    
	@RequestMapping(value = "/indexAdmin", method = RequestMethod.GET)
	public String listPersons(HttpSession session, Model model) {
		logger.info("Welcome AdminTranslatorDashboardCntroller: listPersons");
		model.addAttribute("loggedInAdmin",session.getAttribute("loggedInAdmin"));
		return "adminDashboard/indexAdmin";
	}

	//For add and update Translator both
    @RequestMapping(value= "/translator/add", method = RequestMethod.POST)
    public String addPerson(@ModelAttribute("translator") Translator translator){
        if(translator.getId() == 0){
            //new Translator, add it
            this.translatorService.saveTranslator(translator);
        }else{
            //existing Translator, call update
            this.translatorService.saveTranslator(translator);
        }  
        return "redirect:/translators";  
    }
	
	@RequestMapping("/remove/{id}")
	public String removeTranslator(@PathVariable("id") long id) {
		logger.info("Welcome AdminTranslatorDashboardCntroller: remove");
		this.translatorService.removeTransltor(id);
		return "redirect:/translators";
	}

	@RequestMapping("/activateTranslator/{id}")
	public String verifyTranslator(@PathVariable("id") long id){
		this.translatorService.verifyTranslator(id);
		return "redirect:/translators";
	}
	
	@RequestMapping("/pauseTranslator/{id}")
	public String pauseTranslator(@PathVariable("id") long id){
		this.translatorService.pauseTranslator(id);
		return "redirect:/translators";
	}
	
	@RequestMapping("/edit/{id}")
	public String editTranslator(HttpSession session,@PathVariable("id") long id, Model model) {
		logger.info("Welcome AdminTranslatorDashboardCntroller: edit");
		model.addAttribute("listTranslators", this.translatorService.getTranslators());
		//session.setAttribute("translatorEdit", translatorService.getTranslatorById(id));
		Translator translator = this.translatorService.getTranslatorById(id);
	    session.setAttribute("translatorEdit", translator);
	    model.addAttribute("translatorEdit", translator);
		return "AdminUser";
	}
	
	/**********************************************************************/

    @RequestMapping(value = "/translatorEdit/{id}")
    public String translatorEdit(@PathVariable("id") long id,HttpSession session) {
        logger.debug("show Translator() id: {}", id);
        Translator translator = translatorService.getTranslatorById(id);
        TranslatorDTO translatordto = translatorToTranslatorDTOMapper(translator);
        session.setAttribute("translatorFormAdmin", translatordto);
        session.setAttribute("serviceRequestList", getServiceRequestArrayDTO(translatorService.getServiceRequestList(translator.getId(), "OpenService")));
        session.setAttribute("translatorFormMessage", "Translator "+translatordto.getName()+" Details");
        return "redirect:/translatorForm";
    }


    @RequestMapping(value = "/translatorRateInformation/{id}")
    public String translatorRateInformation(@PathVariable("id") long id,HttpSession session) {
        logger.debug("show Translator() id: {}", id);
        Translator translator = (Translator) translatorService.getTranslatorById(id);
        List<Rate> rateList = translatorService.getAllTranslatorRates(translator);
        session.setAttribute("rateList",rateList);
        return "redirect:/translatorRateInformation";
    }

    @RequestMapping(value = "translatorRateInformation")
    public String translatorRateInformation(HttpSession session, Model model) {
        List<Rate> rateList = (List<Rate>) session.getAttribute("rateList");
        model.addAttribute("rateList",rateList);
        return "adminDashboard/translatorRateInformation";
    }

    @RequestMapping(value = "translatorForm")
    public String translatorForm(Model model, HttpSession session) {
    	TranslatorDTO translatordto = (TranslatorDTO) session.getAttribute("translatorFormAdmin");
    	String translatorFormMessage = (String) session.getAttribute("translatorFormMessage");
        //List<ServiceRequestDTO> list=(List<ServiceRequestDTO>) session.getAttribute("serviceRequestList");
        model.addAttribute("translatorFormAdmin", translatordto);
        model.addAttribute("natyVerified",translatordto.getNatyVerified());
        model.addAttribute("natyExtiryDate",translatordto.getNatyExtiryDate());
        model.addAttribute("validSuscription",translatordto.getValidSuscription());
        model.addAttribute("manualyPaused",translatordto.getManualyPaused());
        model.addAttribute("inactiveCancelled",translatordto.getInactiveCancelled());
        model.addAttribute("inactiveRefunded",translatordto.getInactiveRefunded());  
        model.addAttribute("translatorStatus",translatordto.getStatus());
        model.addAttribute("translatorFormMessage",translatorFormMessage);   
    	return "adminDashboard/translatorForm";
    }

    @RequestMapping(value = "/manuallyPause", method = RequestMethod.GET)
    public String manuallyPause(HttpSession session) {
        logger.info("Welcome AdminTranslatorDashboardCntroller: manuallyPause");
        TranslatorDTO translatordto = (TranslatorDTO) session.getAttribute("translatorFormAdmin");
        Translator translator = translatorService.getTranslatorById(translatordto.getId());
        translatorService.manuallyPauseTranslator(translator);
        translatordto.setStatus("Paused");
        translatordto.setManualyPaused(true);
        session.setAttribute("translatorFormMessage", "Translator "+translatordto.getName()+" was manually paused");
        return "redirect:/translatorForm";
    }
    
    @RequestMapping(value = "/manuallyUnpause", method = RequestMethod.GET)
    public String manuallyUnpause(HttpSession session) {
        logger.info("Welcome AdminTranslatorDashboardCntroller: manuallyUnPause");
        TranslatorDTO translatordto = (TranslatorDTO) session.getAttribute("translatorFormAdmin");
        Translator translator = translatorService.getTranslatorById(translatordto.getId());
        translatorService.manuallyUnPauseTranslator(translator);
//        translatordto.setStatus("Active")
//        translatordto.setManualyPaused(false);
        if(translatordto.getNatyExpiration().before(new Date())){
            translatordto.setNatyExtiryDate(true);
            translatordto.setStatus("Paused");
        }else{
        	translatordto.setStatus("Active");
          translatordto.setManualyPaused(false);
        }
        session.setAttribute("translatorFormMessage", "Translator "+translatordto.getName()+" was manually unpaused");
        return "redirect:/translatorForm";
    }
    
//    @RequestMapping(value = "/manuallyInactiveNoRefound", method = RequestMethod.GET)
//    public String manuallyInactiveNoRefound(HttpSession session, Model model) {
//
//        logger.info("Welcome AdminTranslatorDashboardCntroller: manuallyUnPause");
//        Translator translator = (Translator) session.getAttribute("translatorSession");
//       // translatorService.manuallyInactiveTranslator(translator);
//        model.addAttribute("listTranslators",translatorService.getTranslators());
//        return "adminDashboard/AdminUser";
//    }
    
//    @RequestMapping(value = "/manuallyInactiveRefound", method = RequestMethod.GET)
//    public String manuallyInactiveRefound(HttpSession session, Model model) {
//
//        logger.info("Welcome AdminTranslatorDashboardCntroller: translatorAdminEdit");
//        Translator translator = (Translator) session.getAttribute("translatorSession");
//        model.addAttribute("listTranslators",translatorService.getTranslators());
//        return "adminDashboard/AdminUser";
//    }
    
//    @RequestMapping(value = "/manuallyReactivate", method = RequestMethod.GET)
//    public String manuallyReactivate(HttpSession session, Model model) {
//        logger.info("Welcome AdminTranslatorDashboardCntroller: translatorAdminEdit");
//        Translator translator = (Translator) session.getAttribute("translatorSession");
//        translatorService.manuallyReactive(translator);
//        model.addAttribute("listTranslators",translatorService.getTranslators());
//        return "adminDashboard/AdminUser";
//    }

    /****************************Nuevo dashboard*****************************************/
    @RequestMapping(value="/serviceRequestConfiguration")
    public String serviceRequestConfiguration(HttpSession session, Model model){
    	ServiceRequestConfiguration serviceReqConf=serviceRequestConfigurationService.getServiceRequestConfiguration();
    	ServiceReqConfigDTO conf=new ServiceReqConfigDTO();
    	conf.setMinquote(serviceReqConf.getMinimumMarket());
    	conf.setTimeper(serviceReqConf.getHoursLeft());
    	model.addAttribute("serviceRequestConfDTO", conf);
		model.addAttribute("loggedInAdmin",session.getAttribute("loggedInAdmin"));
    	session.setAttribute("serviceReqConf", serviceReqConf);
        return "adminDashboard/serviceRequestConfiguration";
    }

    
    @RequestMapping(value="/updateServiceRequestConfig", method = RequestMethod.POST)
    public String updateServiceRequestConfig(HttpSession session,@ModelAttribute("serviceRequestConfDTO") ServiceReqConfigDTO serviceReqConfigDTO){
    	ServiceRequestConfiguration serviceReqConf=(ServiceRequestConfiguration) session.getAttribute("serviceReqConf");
    	serviceReqConf.setHoursLeft(serviceReqConfigDTO.getTimeper());
    	serviceReqConf.setMinimumMarket(serviceReqConfigDTO.getMinquote());
    	serviceRequestConfigurationService.saveOrUpdate(serviceReqConf);
    	return "redirect:/serviceRequestConfiguration";
    }
    
    @RequestMapping(value="/translatorList", method = RequestMethod.GET)
    public String translatorList(Model model,HttpSession session,@ModelAttribute("serviceRequestConfDTO") ServiceReqConfigDTO serviceReqConfigDTO){
        model.addAttribute("listTranslators",getTranslatorsList(translatorService.getTranslators()));
        model.addAttribute("loggedInAdmin",session.getAttribute("loggedInAdmin"));
    	model.addAttribute("translatorListMessage", "All Translators Registered");
    	return "adminDashboard/translatorList";
    }
    
    @RequestMapping(value="/customerList", method = RequestMethod.GET)
    public String customerList(Model model,HttpSession session,@ModelAttribute("serviceRequestConfDTO") ServiceReqConfigDTO serviceReqConfigDTO){
        model.addAttribute("listCustomer",businessUserService.getAll());
        model.addAttribute("loggedInAdmin",session.getAttribute("loggedInAdmin"));
    	model.addAttribute("translatorListMessage", "All Customer Registered");
    	return "adminDashboard/customerList";
    }
    @RequestMapping(value="/caseResolution", method = RequestMethod.GET)
    public String caseResolution(Model model,HttpSession session){
    	
    	List<ServiceRequest> openServiceList=serviceRequestService.getServiceRequestByState("OpenService");
    	List<ServiceRequest> approvedList=serviceRequestService.getServiceRequestByState("Approved");
    	List<ServiceRequest> paiedList=serviceRequestService.getServiceRequestByState("Paied");
        List<ServiceRequest> cancelList=serviceRequestService.getServiceRequestByState("Canceled");

    	
    	openServiceList.addAll(approvedList);
    	openServiceList.addAll(paiedList);
    	openServiceList.addAll(cancelList);

    	List<ServiceRequestCSDTO> dtoList= serviceRequestToServiceRequestCSDTOMap(openServiceList);
    	model.addAttribute("dtoList", dtoList);
    	model.addAttribute("loggedInAdmin",session.getAttribute("loggedInAdmin"));
    	model.addAttribute("message", "");
    	return "adminDashboard/caseResolution";
    }

    private List<ServiceRequestCSDTO> serviceRequestToServiceRequestCSDTOMap(List<ServiceRequest> serviceRequestList) {
    	List<ServiceRequestCSDTO> list = new ArrayList<>();
    	for( ServiceRequest sr: serviceRequestList){
    		ServiceRequestCSDTO dto = getServiceRequestCSDTO(sr);
    		list.add(dto);
    	}
		return list;
	}

	private ServiceRequestCSDTO getServiceRequestCSDTO(ServiceRequest sr) {
		ServiceRequestCSDTO dto = new ServiceRequestCSDTO();
		dto.setCustomerId(sr.getCustomer().getId());
		dto.setClientName(sr.getCustomer().getUser().getName());
		if(sr.getTranslator()!=null){
			dto.setTranslatorId(sr.getTranslator().getId());
			dto.setTranslatorName(sr.getTranslator().getUser().getName());
			dto.setTranslatorStatus(sr.getTranslator().getStatus());
			dto.setQuote(BigDecimal.TEN);
		}
		
		dto.setStatus(sr.getServiceRequestStatus().getDescription());
		dto.setCreationDate(sr.getCreationDate());
		dto.setId(sr.getId());
		dto.setServiceRequestCategory(sr.getServiceRequestCategory().getDescription());
		dto.setDescription(sr.getDescription());
		dto.setHardcopy(sr.getHardcopy());
		dto.setTimeFrame(sr.getTimeFrame().getDescription());
		dto.setLanguagefrom(sr.getLanguagefrom());
		dto.setLanguageTo("English");
		dto.setFinishDate(sr.getFinishDate());
		return dto;
	}

	@RequestMapping(value="/paymentCenter")
    public String paymentCenter(){
        return "adminDashboard/paymentCenterOptions";
    }
    
    
    @RequestMapping(value="/paymentToTranslators", method = RequestMethod.GET)
    public String paymentToTranslators(Model model,HttpSession session){
    	model.addAttribute("paymentList", getServiceRequestPaymentDTOList(serviceRequestPaymentService.getServiceRequestPaymentApproved()));
    	model.addAttribute("loggedInAdmin",session.getAttribute("loggedInAdmin"));
    	return "adminDashboard/paymentCenter";
    }
    
    @RequestMapping(value = "/downloadCSV", method = RequestMethod.GET)
    public String downloadCSV(HttpServletResponse response) throws IOException {

        String csvFileName = "PaymentsToTranslators.csv";
 
        response.setContentType("text/csv");
 
        // creates mock data
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",csvFileName);
        response.setHeader(headerKey, headerValue);

        List<ServiceRequestPayment> approvedPaymentList = serviceRequestPaymentService.getServiceRequestPaymentApproved();
        List<ServiceRequestPaymentDTO> paymentDTOList = getServiceRequestPaymentDTOList(approvedPaymentList);
 
        // uses the Super CSV API to generate CSV data from the model data
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),CsvPreference.STANDARD_PREFERENCE);
 
        String[] header = { "paypalTransaction", "serviceRequestId", "amount","customer","translator"};
        csvWriter.writeHeader(header);
        for (ServiceRequestPaymentDTO payment : paymentDTOList) {
            csvWriter.write(payment,header);
        }
        
        for(ServiceRequestPayment serviceRequestPayment :approvedPaymentList ){
        	ServiceRequest serviceRequest = serviceRequestPayment.getServiceRequest();
            serviceRequest.setServiceRequestStatus(serviceRequestStatusDao.findByDescription("Paied"));
            serviceRequestService.saveOrUpdate(serviceRequest);
        }
        csvWriter.close();
        
        return "redirect:paymentCenter";
    }
    
    @RequestMapping(value="/paymentsHistory", method = RequestMethod.GET)
    public String paymentsHistory(){
    	return "adminDashboard/paymentsHistory";
    }

    @RequestMapping(value = "/downloadFile2/{id}/{option}", method = RequestMethod.GET)
    public void downloadFile(HttpServletResponse response, @PathVariable("id") long id,@PathVariable("option") String option, HttpServletRequest request) throws IOException {
        logger.info("Welcome BusinessUserDashboardController: downloadFiles2");

        if(option.equals("request")){
        	ServiceRequestFiles serviceRequestFile = serviceRequestService.getServiceRequestFile(id);
            metodo(request,response,serviceRequestFile.getUrl(),serviceRequestFile.getFile());
        }else{
        	ServiceResponseFiles serviceResponseFile = serviceResponseService.getServiceResponseById(id);
            metodo(request,response,serviceResponseFile.getUrl(),serviceResponseFile.getFile());
        }
        
    }
    
	@RequestMapping(value = "/submitMessageAdmin", method = RequestMethod.POST)
    public String submitMessage(@ModelAttribute("message") ChatMessageDTO message, HttpSession session, Model model) throws Exception {
        logger.info("Welcome BusinessUserDashboardController: submitMessage");
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setSender(message.getSender());
        Date date = new Date();
        chatMessage.setDate(new Timestamp(date.getTime()));
        chatMessage.setMessage(message.getMessage());
        Conversation conversation = conversationService.get(message.getConversationid());
        chatMessage.setConversation(conversation);
        chatMessageService.saveOrUpdate(chatMessage);
        conversation.getMessageList().add(chatMessage);
        session.setAttribute("conversation", conversation);

        Set<AmazonFile> amazonFiles = new HashSet<>();
        amazonFiles.addAll(amazonService.findByServiceRequestIdAndType(conversation.getServiceRequest().getId(), FileType.SERVICE_REQUEST));
        amazonFiles.addAll(amazonService.findByServiceResponseIdAndType(conversation.getServiceResponse().getId(), FileType.SERVICE_RESPONSE));
        ChatMessageDTO message2 = new ChatMessageDTO();
        message2.setConversationid(conversation.getId());
        message2.setSender("Admin");
        model.addAttribute("message", message2);
    	model.addAttribute("messageList", getMessagesDTO(conversation.getMessageList()));
    	List<ServiceRequest> list= new ArrayList<>();
    	list.add(conversation.getServiceRequest());
    	model.addAttribute("serviceRequestList", list);
        model.addAttribute("fileList", convertToDto(amazonFiles));
    	session.setAttribute("serviceRequest", conversation.getServiceRequest());
    	return "adminDashboard/serviceRequestInformation"; 
    }

    @RequestMapping(value = "/adminApproveSR", method = RequestMethod.GET)
    public String adminApproveSR(HttpSession session){
    	ServiceRequest serviceRequest = (ServiceRequest) session.getAttribute("serviceRequest");
    	serviceRequest.setServiceRequestStatus(this.serviceRequestStatusDao.findByDescription("Approved"));
    	serviceRequestService.saveOrUpdate(serviceRequest);
		return "adminDashboard/indexAdmin";
    }

    @RequestMapping(value = "/adminCancelSR/{id}", method = RequestMethod.GET)
    public String adminCancelSR(@PathVariable("id") long id){
        ServiceRequest serviceRequest = serviceRequestService.find(id);
    	serviceRequest.setServiceRequestStatus(this.serviceRequestStatusDao.findByDescription("Canceled"));
    	serviceRequestService.saveOrUpdate(serviceRequest);
        return "redirect:/caseResolution";
    }

    @RequestMapping(value = "/translatorSuscription", method = RequestMethod.GET)
    public String translatorSuscription(HttpSession session, Model model){
    	Translator translator = new Translator();
		model.addAttribute("translatorForm", translator);	
		model.addAttribute("languageList", initializeProfiles());

        String newmessage= (String)session.getAttribute("translatorRegistrationMessage2");
		if(newmessage != null){
			session.setAttribute("translatorRegistrationMessage", newmessage);
		}else{
			session.setAttribute("translatorRegistrationMessage", "Register a new Translator");
		}
        return "adminDashboard/translatorSuscription";
    }
    
    @RequestMapping("/translatorAdminEdit")
    public String updateTranslator(@ModelAttribute("translatorFormAdmin")@Validated TranslatorDTO translatordto,BindingResult result,Model model,HttpSession session)throws Exception {
        logger.info("Welcome BusinessUserDashboardController: translatorAdminEdit");
        if (result.hasErrors()) {
        	 model.addAttribute("translatorFormAdmin", translatordto);
             model.addAttribute("natyVerified",translatordto.getNatyVerified());
             model.addAttribute("natyExtiryDate",translatordto.getNatyExtiryDate());
             model.addAttribute("validSuscription",translatordto.getValidSuscription());
             model.addAttribute("manualyPaused",translatordto.getManualyPaused());
             model.addAttribute("inactiveCancelled",translatordto.getInactiveCancelled());
             model.addAttribute("inactiveRefunded",translatordto.getInactiveRefunded());  
             model.addAttribute("translatorStatus",translatordto.getStatus());
             model.addAttribute("translatorFormMessage","And Error Occured");   
         	return "adminDashboard/translatorForm";
		}
        Translator translator = translatorService.getTranslatorById(translatordto.getId());
        translator.setPaypalClientId(translatordto.getPaypalClientId());
        translator.setNaatiNumber(translatordto.getNaatiNumber());
        translator.getUser().setName(translatordto.getName());
        translator.setPhone(translatordto.getPhone());
        translator.setAddress(translatordto.getAddress());
        translator.getUser().setRole(Role.TRANSLATOR);
        translator.getUser().setEmail(translatordto.getEmail());
        translator.getUser().setPassword(translatordto.getPassword());
        translator.setNatyExpiration(translatordto.getNatyExpiration());
        translator.setAbn_name(translatordto.getAbn_name());
        translator.setAbn_number(translatordto.getAbn_number());
        
        if(translatordto.getNaatiNumber()==null){
        	translator.getTranslatorStatusFlags().setNatyVerified(false);
        	translatordto.setNatyVerified(false);
        }else{
        	translator.getTranslatorStatusFlags().setNatyVerified(true);
        	translatordto.setNatyVerified(true);
        }
        if(translatordto.getNatyExpiration().after(new Date())){
           translator.getTranslatorStatusFlags().setNatyExtiryDate(false);
           translatordto.setNatyExtiryDate(false);
           if(translator.getRemaininDays()>0){
        	   translator.setStatus("Active");
               translatordto.setStatus("Active");
           }else{
        	   translator.setStatus("Paused");
               translatordto.setStatus("Paused");
           }
        }else{
          translator.getTranslatorStatusFlags().setNatyExtiryDate(true);
          translatordto.setNatyExtiryDate(true);
          translator.setStatus("Paused");
          translatordto.setStatus("Paused");
        }
        translatorService.saveTranslator(translator);
        session.setAttribute("translatorFormAdmin", translatordto);
        session.setAttribute("translatorFormMessage", "Translator information was modified");
        return "redirect:/translatorForm";
    }
    
	@RequestMapping(value = "/registerTranslator", method = RequestMethod.POST)
	public String registerTranslator(HttpSession session, @ModelAttribute("translatorForm") @Validated Translator translator,BindingResult result, Model model) {
		logger.info("Welcome RegisterController: processTranslator");
		if (result.hasErrors()) {
			model.addAttribute("translatorForm", translator);	
			model.addAttribute("languageList", initializeProfiles());
			return"adminDashboard/translatorSuscription";
		}
		Purchase purchase = new Purchase(); 
        PurchaseType purchaseType = purchaseTypeService.findById(1);
        Date currentDate = new Date();
        purchase.setDate(currentDate);
        purchase.setPaypalTransactionId("Free trial");
        
        Date finishDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(finishDate);
        int daysAfter = purchaseType.getQuantityDays();
        c.add(Calendar.DATE, daysAfter);
        finishDate = c.getTime();
        purchase.setExpireDate(finishDate);

        purchase.setPurchasetype(purchaseType);
        purchase.setValue(BigDecimal.ZERO);
        purchase.setTranslator(translator);
        purchase.setRemaininDays(purchaseType.getQuantityDays());
        translator.getUser().setRole(Role.TRANSLATOR);
    	translator.setRemaininDays(purchaseType.getQuantityDays());
    	translator.setExpireSuscriptionDate(finishDate);
        purchase.setTranslator(translator);
		TranslatorStatusFlags flags = new TranslatorStatusFlags();

        if(translator.getNatyExpiration().before(new Date())){
    		translator.setStatus("Paused");
    		flags.setNatyExtiryDate(true);

        }else{
        	translator.setStatus("Active");
    		flags.setNatyExtiryDate(false);

        }
		flags.setInactiveCancelled(false);
		flags.setInactiveRefunded(false);
		flags.setManualyPaused(false);//que valor toma al registrarse?
		flags.setNatyVerified(true);
		flags.setValidSuscription(true);

        session.setAttribute("translatorRegistrationMessage2", "Translator "+translator.getUser().getName()+" Subscribed successfully");
		translator.setTranslatorStatusFlags(flags);
		
		
		translatorService.saveTranslator(translator);
        purchaseService.savePurchase(purchase);
        emailService2.sendEmailWelcomeTranslator(translator.getUser().getEmail(), translator.getUser().getName(), translator.getUser().getPassword());
		return "redirect:translatorSuscription";
	}
    
	
	@RequestMapping(value = "/serviceRequestExpiration", method = RequestMethod.GET)
	public String serviceRequestExpiration(Model model) {
		List<Quotation> list = quoteService.getQuotesFromServiceRequestQuotedAndUnquoted();
		List<ServiceRequest> list2= serviceRequestService.getServiceRequestByState("Unquoted");
		
		
		List<ServiceRequestQuoteDTO> dtolist1= mapServiceRequestQuoteDTO(list);
		List<ServiceRequestQuoteDTO> dtolist2= mapServiceRequestQuoteFromSR(list2);
		dtolist1.addAll(dtolist2);
		model.addAttribute("dtoList",dtolist1);
		return "adminDashboard/serviceRequestExpiration";
	}

	private List<ServiceRequestQuoteDTO> mapServiceRequestQuoteFromSR(List<ServiceRequest> list2) {
		List<ServiceRequestQuoteDTO> dtolist= new ArrayList<>();
		for(ServiceRequest sr: list2){
			ServiceRequestQuoteDTO dto= new ServiceRequestQuoteDTO();
			dto.setCategory(sr.getServiceRequestCategory().getDescription());
			dto.setCustomerID(sr.getCustomer().getId());
			dto.setCustomerName(sr.getCustomer().getUser().getName());
			dto.setDate(sr.getCreationDate());
			dto.setHardcopy(sr.getHardcopy());
			dto.setOrigenLanguage(sr.getLanguagefrom());
			dto.setServiceRequestID(sr.getId());
			dto.setServiceRequestStatus(sr.getServiceRequestStatus().getDescription());
			dto.setTimeFrame(sr.getTimeFrame().getDescription());
			dto.setTimeLeftCloseQuote(sr.getFinishQuoteSelection());
			
			dtolist.add(dto);
		}
		return dtolist;
	}

	private List<ServiceRequestQuoteDTO> mapServiceRequestQuoteDTO(List<Quotation> list) {
		List<ServiceRequestQuoteDTO> dtolist= new ArrayList<>();
		for(Quotation quote: list){
			ServiceRequestQuoteDTO dto= new ServiceRequestQuoteDTO();
			dto.setCategory(quote.getServiceRequest().getServiceRequestCategory().getDescription());
			dto.setCustomerID(quote.getServiceRequest().getCustomer().getId());
			dto.setCustomerName(quote.getServiceRequest().getCustomer().getUser().getName());
			dto.setDate(quote.getServiceRequest().getCreationDate());
			dto.setHardcopy(quote.getServiceRequest().getHardcopy());
			dto.setOrigenLanguage(quote.getServiceRequest().getLanguagefrom());
			dto.setQuote(quote.getValue());
			dto.setServiceRequestID(quote.getServiceRequest().getId());
			dto.setServiceRequestStatus(quote.getServiceRequest().getServiceRequestStatus().getDescription());
			dto.setTimeFrame(quote.getServiceRequest().getTimeFrame().getDescription());
			dto.setTimeLeftCloseQuote(quote.getServiceRequest().getFinishQuoteSelection());
			dto.setTimeLefToFinishAssignment(quote.getServiceRequest().getFinishDate());
			dto.setTranslatorid(quote.getTranslator().getId());
			dto.setTranslatorName(quote.getTranslator().getUser().getName());
			dto.setTranslatorStatus(quote.getTranslator().getStatus());
			dtolist.add(dto);
		}
		return dtolist;
	}

	@RequestMapping(value = "/expireServiceRequest2/{id}", method = RequestMethod.GET)
    public String expireServiceRequest(@PathVariable("id") long id, Model model) {
		ServiceRequest serviceRequest = serviceRequestService.find(id);
		serviceRequest.setServiceRequestStatus(serviceRequestStatusDao.findByDescription("Expired"));
		serviceRequestService.saveOrUpdate(serviceRequest);
		
		List<ServiceRequest> quotedServiceRequestList = serviceRequestService.getServiceRequestByState("Quoted");
		model.addAttribute("listServiceRequest", quotedServiceRequestList);
		return "redirect:/serviceRequestExpiration";
	}
	
	
	@RequestMapping("/seeServiceRequestDetials/{serviceRequestid}")
	public String seeServiceRequestDetials(@PathVariable("serviceRequestid") long serviceRequestid,HttpSession session){
		logger.info("Welcome TranslatorDashboardController: seeServiceRequestDetials");
		ServiceRequest sr=(ServiceRequest)serviceRequestService.find(serviceRequestid);
		Conversation conversation = conversationService.getConversationFromServiceRequestIdAndTranslatorId(serviceRequestid, sr.getTranslator().getId());
		session.setAttribute("conversation", conversation);
		session.setAttribute("sr", sr);
		return "redirect:/srDetails";
	}

	@RequestMapping("/seeServiceRequestDetials2/{serviceRequestid}/{translatorid}")
	public String seeServiceRequestDetials2(@PathVariable("serviceRequestid") long serviceRequestid,@PathVariable("translatorid") long translatorid, Model model, HttpSession session){
		logger.info("Welcome TranslatorDashboardController: seeServiceRequestDetials");
		ServiceRequest sr=(ServiceRequest)serviceRequestService.find(serviceRequestid);
		Conversation conversation = conversationService.getConversationFromServiceRequestIdAndTranslatorId(serviceRequestid, translatorid);
		session.setAttribute("conversation", conversation);
		session.setAttribute("sr", sr);
		return "redirect:/srDetails";
	}
	
	 @RequestMapping("/srDetails")
	 public String srDetails(HttpSession session, Model model) throws Exception {
			Conversation conversation = (Conversation) session.getAttribute("conversation");
			ServiceRequest serviceRequest = (ServiceRequest) session.getAttribute("sr");

			ChatMessageDTO message = new ChatMessageDTO();
	        message.setConversationid(conversation.getId());
	        message.setSender("Admin");
	        model.addAttribute("message", message);

	    	List<ServiceRequest> list= new ArrayList<ServiceRequest>();
	    	list.add(conversation.getServiceRequest());
	    	model.addAttribute("serviceRequestList", list);
	    	model.addAttribute("messageChat","");
	        model.addAttribute("conversationid", conversation.getId());
	    	model.addAttribute("serviceRequestStatus",conversation.getServiceRequest().getServiceRequestStatus().getDescription());
	        ServiceResponse serviceResponse = conversation.getServiceResponse();
	    	Set<AmazonFile> amazonFiles = new HashSet<AmazonFile>();
	        amazonFiles.addAll(amazonService.findByServiceRequestIdAndType(serviceRequest.getId(), FileType.SERVICE_REQUEST));
	        amazonFiles.addAll(amazonService.findByServiceResponseIdAndType(serviceResponse.getId(), FileType.SERVICE_RESPONSE));
	    	model.addAttribute("fileList", convertToDto(amazonFiles));
	    	
	        model.addAttribute("messageList", getMessagesDTO(conversation.getMessageList()));

	    	session.setAttribute("serviceRequest", conversation.getServiceRequest()); 
	    return "adminDashboard/serviceRequestInformation2";
	 }

    public List<Language> initializeProfiles() {
		List<Language> languageList = languageService.getAllLanguages();
        return languageList;
    }
 
    private List<TranslatorDTO> getTranslatorsList(List<Translator> translatorList){
    	List<TranslatorDTO> dtoList= new ArrayList<TranslatorDTO>();
    	for(Translator translator: translatorList){
    		TranslatorDTO dto=translatorToTranslatorDTOMapper(translator);
    		dtoList.add(dto);
    	}
    	return dtoList;
    }

    private TranslatorDTO translatorToTranslatorDTOMapper(Translator translator){
    	TranslatorDTO translatordto = new TranslatorDTO();
    	translatordto.setId(translator.getId());
        translatordto.setPaypalClientId(translator.getPaypalClientId());
        translatordto.setEmail(translator.getUser().getEmail());
        translatordto.setAddress(translator.getAddress());
        translatordto.setNaatiNumber(translator.getNaatiNumber());
        translatordto.setNatyExpiration(translator.getNatyExpiration());
        translatordto.setPassword(translator.getUser().getPassword());
        translatordto.setPhone(translator.getPhone());
        translatordto.setName(translator.getUser().getName());
        translatordto.setStatus(translator.getStatus());
        translatordto.setLanguage(translator.getLanguageList().get(0).getDescription());
        translatordto.setRemainingDays(translator.getRemaininDays());
        translatordto.setInactiveCancelled(translator.getTranslatorStatusFlags().getInactiveCancelled());
        translatordto.setInactiveRefunded(translator.getTranslatorStatusFlags().getInactiveRefunded());
        translatordto.setManualyPaused(translator.getTranslatorStatusFlags().getManualyPaused());
        translatordto.setNatyExtiryDate(translator.getTranslatorStatusFlags().getNatyExtiryDate());
        translatordto.setNatyVerified(translator.getTranslatorStatusFlags().getNatyVerified());
        translatordto.setValidSuscription(translator.getTranslatorStatusFlags().getValidSuscription());
        translatordto.setAbn_name(translator.getAbn_name());
        translatordto.setAbn_number(translator.getAbn_number());
        return translatordto;
    }

//	private List<ChatMessageDTO> getMessagesDTO(Set<ChatMessage> messageList) {
//		 List<ChatMessageDTO> messageListDTO = new ArrayList<ChatMessageDTO>();
//	        for (ChatMessage message : messageList) {
//				ChatMessageDTO dto = new ChatMessageDTO();
//				dto.setId(message.getId());
//				dto.setDate(message.getDate());
//				dto.setMessage(message.getMessage());
//				dto.setSender(message.getSender());
//				dto.setRead(message.getRead());
//				dto.setConversationid(message.getConversation().getId());
//				User user= userService.getById(message.getSenderId());
//				AmazonFilePhoto photo=amazonFilePhotoService.getAmazonFilePhotoByUserId(user);
//				if(photo!=null){
//					dto.setPhotoUrl(photo.getUrl());
//				}else{
//					dto.setPhotoUrl("resources/assets/layouts/layout2/img/avatar.png");
//				}
//
//				messageListDTO.add(dto);
//			}
//	        Collections.sort(messageListDTO);
//	        return messageListDTO;
//    }

    private List<ServiceRequestPaymentDTO> getServiceRequestPaymentDTOList(List<ServiceRequestPayment> all) {
    	List<ServiceRequestPaymentDTO> dtoList = new ArrayList<>();
    	for(ServiceRequestPayment servReqPay:all){
    		ServiceRequestPaymentDTO serviceRequestPaymentDTO = new ServiceRequestPaymentDTO();
    		serviceRequestPaymentDTO.setAmount(servReqPay.getValue());
    		serviceRequestPaymentDTO.setCustomer(servReqPay.getServiceRequest().getCustomer().getUser().getName());
    		serviceRequestPaymentDTO.setPaypalTransaction(servReqPay.getPaypalTransactionId());
    		serviceRequestPaymentDTO.setTranslator(servReqPay.getServiceRequest().getTranslator().getUser().getName());
    		serviceRequestPaymentDTO.setServiceRequestId(servReqPay.getServiceRequest().getId());
    		dtoList.add(serviceRequestPaymentDTO);
    	}
		return dtoList;
	}

    private List<AmazonFileDto> convertToDto(Set<AmazonFile> files) {
        List<AmazonFileDto> dtos = new ArrayList<>();
        for (AmazonFile file : files) {
            AmazonFileDto dto = new AmazonFileDto(file);
            dtos.add(dto);
        }
        return dtos;
    }

	private List<ServiceRequestDTO> getServiceRequestArrayDTO(List<ServiceRequest> serviceRequestList) {
        List<ServiceRequestDTO> listDTO = new ArrayList<>();
        for (ServiceRequest serviceRequest : serviceRequestList) {
            ServiceRequestDTO dto = new ServiceRequestDTO();
            dto.setServiceRequestCategory(serviceRequest.getServiceRequestCategory().getDescription());
            dto.setTimeFrame(serviceRequest.getTimeFrame().getDescription());
            dto.setLanguagefrom(serviceRequest.getLanguagefrom());
            dto.setLanguageTo(serviceRequest.getLanguageTo());
            dto.setHardcopy(serviceRequest.getHardcopy());
            dto.setStatus(serviceRequest.getServiceRequestStatus().getDescription());
            dto.setId(serviceRequest.getId());
            listDTO.add(dto);
        }
        return listDTO;
    }

//	private List<TranslatorQuotationDTO> getQuotationArrayDTO(List<Quotation> setQuotations) {
//        List<TranslatorQuotationDTO> list = new ArrayList<>();
//        for (Quotation quotation : setQuotations) {
//            TranslatorQuotationDTO dto = new TranslatorQuotationDTO();
//            dto.setServiceRequestId(quotation.getServiceRequest().getId());
//            dto.setQuotationId(quotation.getId());
//            dto.setNaati(true);
//            dto.setName(quotation.getTranslator().getUser().getName());
//            dto.setTranslatorId(quotation.getTranslator().getId());
//            dto.setQuote(quotation.getValue().toString());
//            populateMediaRating(dto,quotation.getTranslator());
//            list.add(dto);
//        }
//        return list;
//    }

//    private void populateMediaRating(TranslatorQuotationDTO dto, Translator translator) {
//        List<Rate> rateList = translatorService.getAllTranslatorRates(translator);
//        int rateSize = rateList.size();
//        if (rateSize == 0) {
//            rateSize = 1;
//        }
//        int quality = 0;
//        int serviceDescribed = 0;
//        int time = 0;
//        for (Rate rate : rateList) {
//            quality += rate.getQuality();
//            serviceDescribed += rate.getServiceAsDescribed();
//            time += rate.getTimeDelivery();
//        }
//        dto.setTimeDelivery(time / rateSize);
//        dto.setServiceDescribed(serviceDescribed / rateSize);
//        dto.setQuality(quality / rateSize);
//    }
	 
	 public void metodo(HttpServletRequest request, HttpServletResponse response, String url,byte[] file) throws IOException{
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
	 
//	    private List<FileInfoDTO> getAllFiles(Conversation conversation) {
//	    	List<FileInfoDTO> fileList= new ArrayList<>();
//	    	ServiceRequest serviceRequest = conversation.getServiceRequest();
//	    	for(ServiceRequestFiles file :serviceRequest.getServiceRequestFiles() ){
//	    		FileInfoDTO fileInfoDTO = new FileInfoDTO();
//	    		fileInfoDTO.setDate(file.getCreationDae());
//	    		fileInfoDTO.setSender(serviceRequest.getCustomer().getUser().getName());
//	    		fileInfoDTO.setName(file.getUrl());
//	    		fileInfoDTO.setType("request");
//	    		fileInfoDTO.setId(file.getId());
//	    		fileList.add(fileInfoDTO);
//	    	}
//
//	    	//ServiceResponse serviceResponse = serviceResponseService.getServiceResponseByServiceRequestId(serviceRequest.getId());
//	    	ServiceResponse serviceResponse = conversation.getServiceResponse();
//	    	for(ServiceResponseFiles file: serviceResponse.getServiceResponseFiles()){
//	    		FileInfoDTO fileInfoDTO2 = new FileInfoDTO();
//	    		fileInfoDTO2.setSender(serviceResponse.getTranslator().getUser().getName());
//	    		fileInfoDTO2.setName(file.getUrl());
//	    		fileInfoDTO2.setType("response");
//	    		fileInfoDTO2.setDate(file.getCreationDate());
//	    		fileInfoDTO2.setId(file.getId());
//	    		fileList.add(fileInfoDTO2);
//	    	}
//	    	return fileList;
//		}
}
