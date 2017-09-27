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

		ServiceRequest serviceRequest = mapServiceRequestFromDTO(serviceRequestDTO, businessUser);
		serviceRequest.setServiceRequestStatus(serviceRequestStatusDao.findByDescription("Unquoted"));
        serviceRequest.setFinishDate(getFinishDate(serviceRequest.getTimeFrame().getDescription()));
		serviceRequestService.saveOrUpdate(serviceRequest);

		TranslatorQuotationDTO translatorQuotationDTO = (TranslatorQuotationDTO) session.getAttribute("homeQuotation");
		if(translatorQuotationDTO!=null){
	        serviceRequest.setTranslator(translatorService.getTranslatorById(translatorQuotationDTO.getTranslatorId()));
	        conversationService.startOrContinueConversation(serviceRequest);

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
		}
        return "redirect:/jobInProgress";
	}

	@RequestMapping(value = "/registerBusinessUser", method = RequestMethod.POST)
	public String processUser(HttpSession session, @ModelAttribute("businessUserForm") @Validated BusinessUserDTO businessUserDto, BindingResult result, Model model, RedirectAttributes redirectAttributes) throws IllegalStateException, IOException {
		logger.info("Welcome BusinessUserController: processUser");
		if (result.hasErrors()) {
            Loggin loggin = new Loggin();
			model.addAttribute("businessUserForm", businessUserDto);
            model.addAttribute("loginSRForm", loggin);

            return"businessUserForm";
		}

        redirectAttributes.addFlashAttribute("businessUserForm", businessUserDto);
        BusinessUser businessUser = businessUserDtoToBusinessUser(businessUserDto);
		businessUserService.saveOrUpdate(businessUser);

//	    emailService2.sendEmailWelcomeCutomer(businessUser.getUser().getPassword(), businessUser.getUser().getEmail(), businessUser.getUser().getName());

		ServiceRequestDTO serviceRequestDTO = (ServiceRequestDTO)session.getAttribute("servcieRequestLead");
        ServiceRequest serviceRequest = mapServiceRequestFromDTO(serviceRequestDTO, businessUser);

		serviceRequestService.saveWithoutFiles(serviceRequest, serviceRequestDTO);

        List<byte[]> bytes = (List<byte[]>) session.getAttribute("files");
        for(int i=0;i<bytes.size();i++) {
            MultipartFile multipartFile = serviceRequestDTO.getFiles().get(i);
            ByteArrayInputStream is = new ByteArrayInputStream(bytes.get(i));
            amazonService.saveServiceRequestFile(serviceRequest, multipartFile.getOriginalFilename(), is, multipartFile.getContentType());
        }

		TranslatorQuotationDTO translatorQuotationDTO = (TranslatorQuotationDTO) session.getAttribute("homeQuotation");
		if(translatorQuotationDTO!=null){
            Translator translator = translatorService.getTranslatorById(translatorQuotationDTO.getTranslatorId());
            serviceRequest.setTranslator(translator);
			serviceRequest.setServiceRequestStatus(serviceRequestStatusDao.findByDescription("OpenService"));

	        conversationService.startOrContinueConversation(serviceRequest);

	     // poner el serviceResponse
	        ServiceResponse serviceResponse = new ServiceResponse();
	        serviceResponse.setCreationDate(new Date());
	        serviceResponse.setDescription("description");
	        //serviceResponse.setServiceRequest(serviceRequest);
            serviceResponse.setTranslator(translator);
            serviceResponse.setUpdateDate(new Date());
            serviceResponseService.saveOrUpdate(serviceResponse);
            serviceRequestService.saveOrUpdate(serviceRequest);

            InvoicePdfDto dto = new InvoicePdfDto(translatorQuotationDTO, serviceRequest);
            createAndSendInvoice(dto, serviceRequest);
        }

        businessUser.setServiceRequestList(new HashSet<ServiceRequest>());
        session.setAttribute("servcieRequestLead", null);
        authorityHelper.updateGrantedAuthoritiesForCurrentUser(businessUser.getUser());
        securityService.autologin(businessUser.getUser().getEmail(), businessUser.getUser().getPassword());
        session.setAttribute("loggedInUser", businessUser);
        return "redirect:/jobInProgress";
	}

	@RequestMapping("/chose")
	public void chose(@RequestParam("quotationId") Long quotationId, @RequestParam(required = false, value = "isDonation") Boolean isDonation, HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("Welcome BusinessUserDashboardController: chose");
		String guid = UUID.randomUUID().toString().replaceAll("-", "");
		paypalController.checkoutSale(quotationId, isDonation, "/chose/payment/" + quotationId + "/?guid=","/chose/payment_cancel/" + quotationId + "/?guid=", guid, request, response);
	}

	@RequestMapping(value = "/chose/payment_cancel/{quotationId}", method = RequestMethod.GET)
	public String chosePaymentCancel(@PathVariable("quotationId") long id, @RequestParam("guid") String guid) throws Exception {
		paypalController.cancelPayment(guid);
		return "redirect:/myUserQuotations";
	}

	@RequestMapping(value = "/chose/payment/{quotationId}", method = RequestMethod.GET)
	public String chosePayment(@PathVariable("quotationId") long id, RedirectAttributes redirectAttributes, HttpServletRequest request) throws Exception {
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

            createAndSendInvoice(new InvoicePdfDto(quotation), serviceRequest);

            serviceRequestService.saveOrUpdate(serviceRequest);
            serviceResponseService.saveOrUpdate(serviceResponse);
            emailService2.sendEmailQuoteAcceptedCustomer(serviceRequest.getCustomer().getUser().getEmail(), serviceRequest.getCustomer().getUser().getName(), quotation.getValue().longValue(), serviceRequest.getId().toString(), serviceRequest.getFinishDate());
            emailService2.sendEmailQuoteAcceptedTranslator(serviceRequest.getTranslator().getUser().getEmail(), serviceRequest.getTranslator().getUser().getName(), serviceRequest.getTimeFrame().getDescription(), serviceRequest.getId().toString(), serviceRequest.getFinishDate());
            redirectAttributes.addFlashAttribute("serviceRequest", serviceRequest);
        } else {
            for (TranslatorQuotationDTO translatorQuotationDTO : quoteList) {
                if (translatorQuotationDTO.getQuotationId() == id) {
                    request.getSession().setAttribute("homeQuotation", translatorQuotationDTO);
                    request.getSession().setAttribute("businessUserForm", new BusinessUser());
                    request.getSession().removeAttribute("translatorQuoteList");
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

    private void createAndSendInvoice(InvoicePdfDto dto, ServiceRequest serviceRequest) throws IOException {
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

    private BusinessUser businessUserDtoToBusinessUser(BusinessUserDTO businessUserDto) {
        BusinessUser bu = new BusinessUser();
        bu.setAddress(businessUserDto.getAddress());
        bu.setCreationdate(new Date());
        bu.setPhone(businessUserDto.getPhone());
        bu.setUpdatedate(new Date());
        bu.setAcnabn("11");

        bu.getUser().setEmail(businessUserDto.getEmail());
        bu.getUser().setName(businessUserDto.getFullname());
        bu.getUser().setPassword(businessUserDto.getPassword());
        bu.getUser().setRole(Role.CLIENT);

        BusinessUserExtension businessUserExt = new BusinessUserExtension();
        businessUserExt.setNextSequenceNumber("0000");
        bu.setBusinessUserExtension(businessUserExt);
        businessUserExt.setBusinessUser(bu);
        return bu;
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

	private ServiceRequest mapServiceRequestFromDTO(ServiceRequestDTO serviceRequestDTO, BusinessUser businessUserLogger)
            throws IllegalStateException, IOException {
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
        serviceRequest.setFinishQuoteSelection(quoteService.getFinishDateSelectionQuote(serviceRequestDTO.getTimeFrame()));
        serviceRequest.setFinishDate(new Date()); //will be completed after the customer selects the candidate
        serviceRequest.setServiceRequestStatus(serviceRequestStatusDao.findByDescription("Unquoted"));//mal: depende de si es OpenService or no
        serviceRequest.setFinishDate(getFinishDate(serviceRequest.getTimeFrame().getDescription()));
        return serviceRequest;
    }
}
