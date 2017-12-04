package au.com.translatorss.controller.businessuser;

import au.com.translatorss.bean.*;
import au.com.translatorss.bean.dto.*;
import au.com.translatorss.controller.PaypalController;
import au.com.translatorss.dao.ServiceRequestCategoryDao;
import au.com.translatorss.dao.ServiceRequestStatusDao;
import au.com.translatorss.dao.TimeFrameDao;
import au.com.translatorss.enums.Role;
import au.com.translatorss.service.*;
import au.com.translatorss.service.AuthorityHelper;
import au.com.translatorss.validation.BusinessUserHomeValidation;
import au.com.translatorss.validation.BusinessUserValidator;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.RelatedResources;
import com.paypal.api.payments.Transaction;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class BusinessUserController {

    private static final Logger logger = LoggerFactory.getLogger(BusinessUserController.class);
    private static final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    private static final String INVOICE_TEMPLATE_HTML = "invoiceTemplate.html";

    @Autowired
	private BusinessUserService businessUserService;

	@Autowired
	private BusinessUserHomeValidation businessUserValidator;

	@Autowired
	private ServiceRequestCategoryDao serviceRequestCategoryDao;

	@Autowired
    private ServiceRequestStatusDao serviceRequestStatusDao;

	@Autowired
    private TimeFrameDao timeFrameDao;

    @Autowired
    private CustomerServiceRequestService serviceRequestService;

    @Autowired
    private TranslatorQuotationService quotationService;

    @Autowired
	private EmailService2 emailService2;

    @Autowired
    private TranslatorSettingsService translatorService;

    @Autowired
    private ConversationService conversationService;

    @Autowired
    private ServiceResponseService serviceResponseService;

    @Autowired
	private PaypalController paypalController;

    @Autowired
    private PDFService pdfService;

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private AmazonService amazonService;

    @Autowired
    private AuthorityHelper authorityHelper;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private TranslatorQuotationService quoteService;

	@InitBinder("businessUserForm")
	public void initBinder(WebDataBinder binder) {
	    binder.addValidators(businessUserValidator);
	}

	@RequestMapping(value = "/loginBusinessUser")
	public String loginSRHome(HttpSession session, Model model) throws IllegalStateException, IOException {
		logger.info("Welcome LoginController: loginSRHome");
		System.out.println("**************Hola***************");
		Loggin Loggin = (Loggin)session.getAttribute("loggin");

		BusinessUser businessUser = businessUserService.getUserByEmail(Loggin.getEmail(),Loggin.getPassword());
        session.setAttribute("loggedInUser", businessUser);

        ServiceRequestDTO serviceRequestDTO = (ServiceRequestDTO)session.getAttribute("servcieRequestLead");
		TranslatorQuotationDTO translatorQuotationDTO = (TranslatorQuotationDTO) session.getAttribute("homeQuotation");

        
		ServiceRequest serviceRequest = mapServiceRequestFromDTO(serviceRequestDTO, businessUser,translatorQuotationDTO);
		serviceRequest.setServiceRequestStatus(serviceRequestStatusDao.findByDescription("Unquoted"));
        serviceRequest.setFinishDate(getFinishDate(serviceRequest.getTimeFrame().getDescription()));
		serviceRequestService.saveOrUpdate(serviceRequest);

        List<byte[]> bytes = (List<byte[]>) session.getAttribute("files");
        for(int i=0;i<bytes.size();i++) {
            MultipartFile multipartFile = serviceRequestDTO.getFiles().get(i);
            ByteArrayInputStream is = new ByteArrayInputStream(bytes.get(i));
            amazonService.saveServiceRequestFile(serviceRequest, multipartFile.getOriginalFilename(), is, multipartFile.getContentType());
        }

		if(translatorQuotationDTO!=null){
	        serviceRequest.setTranslator(translatorService.getTranslatorById(translatorQuotationDTO.getTranslatorId()));
	        conversationService.startOrContinueConversation(serviceRequest);

	        ServiceRequestPayment srp = (ServiceRequestPayment)session.getAttribute("serviceRequestPayment");
            srp.setValue(new BigDecimal(translatorQuotationDTO.getQuote()));
            srp.setServiceRequest(serviceRequest);
			serviceRequest.setServiceRequestPayment(srp);
	        
	     // poner el serviceResponse
	        ServiceResponse serviceResponse = new ServiceResponse();
	        serviceResponse.setCreationDate(new Date());
	        serviceResponse.setDescription("description");
	        //serviceResponse.setServiceRequest(serviceRequest);
	        serviceResponse.setTranslator(translatorService.getTranslatorById(translatorQuotationDTO.getTranslatorId()));
	        serviceResponse.setUpdateDate(new Date());
	        businessUser.setServiceRequestList(new HashSet<ServiceRequest>());
			serviceRequest.setServiceRequestStatus(serviceRequestStatusDao.findByDescription("OpenService"));

	        serviceRequestService.saveOrUpdate(serviceRequest);
	        serviceResponseService.saveOrUpdate(serviceResponse);

	        emailService2.sendEmailQuoteAcceptedCustomer(serviceRequest.getCustomer().getUser().getEmail(), serviceRequest.getCustomer().getUser().getName(), Long.getLong(translatorQuotationDTO.getQuote()), serviceRequest.getId().toString(), serviceRequest.getFinishDate());
            emailService2.sendEmailQuoteAcceptedTranslator(serviceRequest.getTranslator().getUser().getEmail(), serviceRequest.getTranslator().getUser().getName(), serviceRequest.getTimeFrame().getDescription(), serviceRequest.getId().toString(), serviceRequest.getFinishDate());
	        
            return "redirect:/jobInProgress";
        }else{
		    session.setAttribute("serviceRequestid",serviceRequest.getId());
            return "redirect:/myUserQuotations";
        }
	}

	@RequestMapping(value = "/registerBusinessUser", method = RequestMethod.POST)
	public String processUser(HttpSession session, @ModelAttribute("businessUserForm") @Validated BusinessUserDTO businessUserDto, BindingResult result, Model model) throws IllegalStateException, IOException {
		logger.info("Welcome BusinessUserController: processUser");
		if (result.hasErrors()) {
            Loggin loggin = new Loggin();
			model.addAttribute("businessUserForm", businessUserDto);
            model.addAttribute("loginSRForm", loggin);
            return"businessUserForm";
		}

        BusinessUser businessUser = businessUserDtoToBusinessUser(businessUserDto);
        session.setAttribute("loggedInUser", businessUser);

        ServiceRequestDTO serviceRequestDTO = (ServiceRequestDTO)session.getAttribute("servcieRequestLead");
		TranslatorQuotationDTO translatorQuotationDTO = (TranslatorQuotationDTO) session.getAttribute("homeQuotation");
		
        ServiceRequest serviceRequest = mapServiceRequestFromDTO(serviceRequestDTO, businessUser, translatorQuotationDTO);
        
        businessUserService.saveOrUpdate(businessUser);
		serviceRequestService.saveWithoutFiles(serviceRequest, serviceRequestDTO);
        emailService2.sendEmailWelcomeCutomer(businessUser.getUser().getPassword(), businessUser.getUser().getEmail(), businessUser.getUser().getName());

        List<byte[]> bytes = (List<byte[]>) session.getAttribute("files");
        for(int i=0;i<bytes.size();i++) {
            MultipartFile multipartFile = serviceRequestDTO.getFiles().get(i);
            ByteArrayInputStream is = new ByteArrayInputStream(bytes.get(i));
            amazonService.saveServiceRequestFile(serviceRequest, multipartFile.getOriginalFilename(), is, multipartFile.getContentType());
        }

		if(translatorQuotationDTO!=null){
            Translator translator = translatorService.getTranslatorById(translatorQuotationDTO.getTranslatorId());
            ServiceRequestPayment srp = (ServiceRequestPayment)session.getAttribute("serviceRequestPayment");
            srp.setValue(new BigDecimal(translatorQuotationDTO.getQuote()));
            srp.setServiceRequest(serviceRequest);
			serviceRequest.setServiceRequestPayment(srp);

            serviceRequest.setTranslator(translator);
			serviceRequest.setServiceRequestStatus(serviceRequestStatusDao.findByDescription("OpenService"));
			serviceRequest.setServiceRequestPayment(srp);
	        conversationService.startOrContinueConversation(serviceRequest);

	     // poner el serviceResponse
	        ServiceResponse serviceResponse = new ServiceResponse();
	        serviceResponse.setCreationDate(new Date());
	        serviceResponse.setDescription("description");
            serviceResponse.setTranslator(translator);
            serviceResponse.setUpdateDate(new Date());
            serviceResponseService.saveOrUpdate(serviceResponse);
            serviceRequestService.saveOrUpdate(serviceRequest);

            emailService2.sendEmailQuoteAcceptedCustomer(serviceRequest.getCustomer().getUser().getEmail(), serviceRequest.getCustomer().getUser().getName(), Long.getLong(translatorQuotationDTO.getQuote()), serviceRequest.getId().toString(), serviceRequest.getFinishDate());
            emailService2.sendEmailQuoteAcceptedTranslator(serviceRequest.getTranslator().getUser().getEmail(), serviceRequest.getTranslator().getUser().getName(), serviceRequest.getTimeFrame().getDescription(), serviceRequest.getId().toString(), serviceRequest.getFinishDate());
            
        }

        businessUser.setServiceRequestList(new HashSet<ServiceRequest>());
        session.setAttribute("servcieRequestLead", null);
        authorityHelper.updateGrantedAuthoritiesForCurrentUser(businessUser.getUser());
        securityService.autologin(businessUser.getUser().getEmail(), businessUser.getUser().getPassword());
        if(translatorQuotationDTO!=null){
            return "redirect:/jobInProgress";

        }else{
            session.setAttribute("serviceRequestid", serviceRequest.getId());
            return "redirect:/myUserQuotations";

        }
	}

	@RequestMapping("/chose")
	public void chose(@RequestParam("quotationId") Long quotationId, @RequestParam(required = false, value = "isDonation") Boolean isDonation, HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("Welcome BusinessUserDashboardController: chose");
		String guid = UUID.randomUUID().toString().replaceAll("-", "");
		paypalController.checkoutSale(quotationId, isDonation, "/chose/payment/" + quotationId + "/?guid=","/chose/payment_cancel/" + quotationId + "/?guid=", guid, request, response);
	}

	@RequestMapping("/choseHP")
	public void choseHP(@RequestParam("quotationId") Long quotationId, @RequestParam(required = false, value = "isDonation") Boolean isDonation, HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("Welcome BusinessUserDashboardController: choseHP");
		String guid = UUID.randomUUID().toString().replaceAll("-", "");
		paypalController.checkoutSaleHP(quotationId, isDonation, "/chose/payment/" + quotationId + "/?guid=","/chose/payment_cancel/" + quotationId + "/?guid=", guid, request, response);
	}
	
	
	@RequestMapping(value = "/chose/payment_cancel/{quotationId}", method = RequestMethod.GET)
	public String chosePaymentCancel(@PathVariable("quotationId") long id, @RequestParam("guid") String guid) throws Exception {
		paypalController.cancelPayment(guid);
		return "redirect:/myUserQuotations";
	}

	@RequestMapping(value = "/chose/payment/{quotationId}", method = RequestMethod.GET)
	public String chosePayment(@PathVariable("quotationId") Long id, RedirectAttributes redirectAttributes, HttpServletRequest request) throws Exception {
		logger.info("Welcome BusinessUserDashboardController: chose");

		Payment paypalPayment = null;
		try {
			paypalPayment = paypalController.payment(request);
		} catch (Exception exc) {
			// TODO: Handle wrong guid case properly
			exc.printStackTrace();
		}
		List<Transaction> transactions = paypalPayment.getTransactions();
		List<TranslatorQuotationDTO> quoteList = (List<TranslatorQuotationDTO>) request.getSession().getAttribute("translatorQuoteList");
		if (quoteList == null) {
			Quotation quotation = quotationService.find(id);
			ServiceRequest serviceRequest = quotation.getServiceRequest();
			String saleId;
			if (transactions.size() > 0) {
				List<RelatedResources> relRes = transactions.get(0).getRelatedResources();
				if (relRes.size() > 0) {
					saleId = relRes.get(0).getSale().getId();
					ServiceRequestPayment payment = new ServiceRequestPayment();
					payment.setPaypalTransactionId(saleId);
					payment.setServiceRequest(serviceRequest);
					payment.setValue(quotation.getValue());
					serviceRequest.setServiceRequestPayment(payment);
				}
			}
			serviceRequest.setServiceRequestStatus(serviceRequestStatusDao.findByDescription("OpenService"));
			serviceRequest.setFinishDate(getFinishDate(serviceRequest.getTimeFrame().getDescription()));
			serviceRequest.setTranslator(quotation.getTranslator());

			// put the serviceResponse in case doesnt exists
			ServiceResponse serviceResponse = conversationService.startOrContinueConversation(serviceRequest).getServiceResponse();
			if(serviceResponse==null){
				serviceResponse= new ServiceResponse();
				serviceResponse.setCreationDate(new Date());
				serviceResponse.setDescription("description");
				serviceResponse.setTranslator(quotation.getTranslator());
				serviceResponse.setUpdateDate(new Date());
			}

//            createAndSendInvoice(new InvoicePdfDto(quotation), serviceRequest);
//
            serviceRequestService.saveOrUpdate(serviceRequest);
            serviceResponseService.saveOrUpdate(serviceResponse);
            emailService2.sendEmailQuoteAcceptedCustomer(serviceRequest.getCustomer().getUser().getEmail(), serviceRequest.getCustomer().getUser().getName(), quotation.getValue().longValue(), serviceRequest.getId().toString(), serviceRequest.getFinishDate());
            emailService2.sendEmailQuoteAcceptedTranslator(serviceRequest.getTranslator().getUser().getEmail(), serviceRequest.getTranslator().getUser().getName(), serviceRequest.getTimeFrame().getDescription(), serviceRequest.getId().toString(), serviceRequest.getFinishDate());
            redirectAttributes.addFlashAttribute("serviceRequest", serviceRequest);
        } else {
            for (TranslatorQuotationDTO translatorQuotationDTO : quoteList) {
            	long quoteValue = translatorQuotationDTO.getQuotationId();
                if (quoteValue==id) {
                    request.getSession().setAttribute("homeQuotation", translatorQuotationDTO);
                    request.getSession().setAttribute("businessUserForm", new BusinessUser());
                    request.getSession().removeAttribute("translatorQuoteList");
                    
        			String saleId;
        			if (transactions.size() > 0) {
        				List<RelatedResources> relRes = transactions.get(0).getRelatedResources();
        				if (relRes.size() > 0) {
        					saleId = relRes.get(0).getSale().getId();
        					ServiceRequestPayment serviceRequestPayment = new ServiceRequestPayment();
        					serviceRequestPayment.setPaypalTransactionId(saleId);
                            request.getSession().setAttribute("serviceRequestPayment", serviceRequestPayment);
        				}
        			}
                    return "redirect:/businessUserForm";
                }
            }
        }
        return "redirect:/jobInProgress";
    }

    @RequestMapping(value={"/sbmtSRHome"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public List<TranslatorQuotationDTO> submitMessageRest(@RequestBody ServiceRequestDTO message) throws Exception {
        List<TranslatorQuotationDTO> quoteList = new ArrayList<>();
        return quoteList;
    }


    private BusinessUser businessUserDtoToBusinessUser(BusinessUserDTO businessUserDto) {
        BusinessUser bu = new BusinessUser();
        bu.setAddress(businessUserDto.getAddress());
        bu.setFullname(businessUserDto.getFullname());
        bu.setCreationdate(new Date());
        bu.setPhone(businessUserDto.getPhone());
        bu.setUpdatedate(new Date());
        bu.setAcnabn("11");

        bu.getUser().setEmail(businessUserDto.getEmail());
        bu.setPaypalClientId(businessUserDto.getPaypalid());
        bu.getUser().setName(businessUserDto.getPreferedname());
        bu.getUser().setPassword(businessUserDto.getPassword());
        bu.getUser().setRole(Role.CLIENT);

        BusinessUserExtension businessUserExt = new BusinessUserExtension();
        businessUserExt.setNextSequenceNumber("0000");
        bu.setBusinessUserExtension(businessUserExt);
        businessUserExt.setBusinessUser(bu);
        return bu;
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
        c.add(Calendar.DATE, daysAfter);
        finishDate = c.getTime();
        return finishDate;
    }

	private ServiceRequest mapServiceRequestFromDTO(ServiceRequestDTO serviceRequestDTO, BusinessUser businessUserLogger, TranslatorQuotationDTO translatorQuotationDTO)
            throws IllegalStateException, IOException {
        ServiceRequest serviceRequest = new ServiceRequest();
        
        if(translatorQuotationDTO!=null) {
        	 Translator tran = this.translatorService.getTranslatorById(translatorQuotationDTO.getTranslatorId());
             Quotation quote = new Quotation();
             quote.setIsAutomatic(true);
             quote.setIsValid(true);
             quote.setServiceRequest(serviceRequest);
             quote.setTranslator(tran);
             quote.setValue(new BigDecimal(translatorQuotationDTO.getQuote()));
             serviceRequest.getQuotationList().add(quote);
        }
       
        
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
        serviceRequest.setFinishQuoteSelection(quoteService.getFinishDateSelectionQuote(serviceRequestDTO.getTimeFrame()));
        serviceRequest.setFinishDate(new Date()); //will be completed after the customer selects the candidate
        serviceRequest.setFinishDate(getFinishDate(serviceRequest.getTimeFrame().getDescription()));
        return serviceRequest;
    }
}
