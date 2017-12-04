package au.com.translatorss.service.impl;

import au.com.translatorss.bean.AmazonFile;
import au.com.translatorss.bean.Invoice;
import au.com.translatorss.bean.Quotation;
import au.com.translatorss.bean.Rate;
import au.com.translatorss.bean.ServiceRequest;
import au.com.translatorss.bean.Translator;
import au.com.translatorss.bean.dto.InvoicePdfDto;
import au.com.translatorss.dao.QuotationStandarDao;
import au.com.translatorss.service.*;

import org.apache.commons.io.IOUtils;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.internet.MimeMessage;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
@Transactional
public class EmailServiceImpl2 implements EmailService2{

	    /*Email From*/
	   public static final String FROM = "from";
	    /*Email To*/
	   public static final String TO = "to";
	    /*Email Subject*/
	   public static final String SUBJECT = "subject";
	    /*Email BCC*/
	   public static final String BCC_LIST = "bccList";
	    /*Email CCC*/
	   public static final String CCC_LIST = "ccList";
	
	   @Value("${java.mail.username}")
	   private String emailFrom;
	     
	   @Value("${java.mail.password}")
	   private String password;
	   
	   @Autowired
	   private JavaMailSender mailSender;
	    
	   @Autowired
	   private VelocityEngine velocityEngine;
	    
	   @Autowired
	   private TranslatorQuotationService quotationService;
	    
	   @Autowired
	   private CustomerServiceRequestService serviceRequestService;
    
	   @Autowired
	   private ServiceRequestConfigurationService serviceRequestConfigurationService;
	    
	   @Autowired
	   private TranslatorSettingsService translatorService;
	    
	   @Autowired
	   private QuotationStandarDao quotationStandarDao;
	   
	   @Autowired
	    private InvoiceService invoiceService;

	    @Autowired
	    private PDFService pdfService;

	    @Autowired
	    private AmazonService amazonService;

	    @Autowired
	    private RateService rateService;
	    
	    private static final String INVOICE_TEMPLATE_HTML = "invoiceTemplate.html";

	    private static final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	   
	   
	   @Override
	   public void sendEmailToCustomerAndUnquotedTranslatorsAferSRCreated( String customerEmail, String serviceRequestid, String customername) {
		   ServiceRequest serviceRequest = serviceRequestService.find(new Long(serviceRequestid));
		   Set<Quotation> quotationList = serviceRequest.getQuotationList();
		   int quotationNumber = quotationList.size();
		   int nimunAmountQuotes = serviceRequestConfigurationService.getServiceRequestMinimunMarket();
		   if(quotationNumber<nimunAmountQuotes  || quotationNumber==0){
			    Map<String, Object> modelCustomer = new HashMap<String, Object>();
			    modelCustomer.put("from", emailFrom);						
			    modelCustomer.put("subject", "Quote "+serviceRequestid);
			    modelCustomer.put("to", customerEmail);	
			    modelCustomer.put("ccList", new ArrayList<String>());
			    modelCustomer.put("bccList", new ArrayList<String>());
			    modelCustomer.put("customername", customername); 
			    modelCustomer.put("servicerequestid", serviceRequestid); 
			    sendEmail(modelCustomer, "2Uservicerequestcreation.vm");
			    
			    List<Translator> translatorList = quotationStandarDao.getAvailableTranslatorsWithoutQuotesSetUp(serviceRequest);
			    
			    for(Translator translator: translatorList){
			    	String translatorName= translator.getUser().getName();
			    	String translatorEmail= translator.getUser().getEmail();
			    	Map<String, Object> modelTranslator = new HashMap<String, Object>();
			    	modelTranslator.put("from", emailFrom);						
			    	modelTranslator.put("subject", "Proposition "+serviceRequestid+" To be completed");
			    	modelTranslator.put("to", translatorEmail);	
			    	modelTranslator.put("ccList", new ArrayList<String>());
			    	modelTranslator.put("bccList", new ArrayList<String>());
			    	modelTranslator.put("translatorname", translatorName); 
			    	modelTranslator.put("servicerequestid", serviceRequestid);
					sendEmail(modelTranslator,"1Tservicerequestcreation.vm");
			    }
		   }

	   }
	
	   @Override
	   public void sendEmailWelcomeTranslator(String translatorEmail, String translatorname, String password) {
		   Map<String, Object> modeltransaltor = new HashMap<String, Object>();
		   modeltransaltor.put("from", emailFrom);						
		   modeltransaltor.put("subject", translatorname+", Welcome to Translatorss");
		   modeltransaltor.put("translatorname", translatorname);
		   modeltransaltor.put("password", password);
		   modeltransaltor.put("to", translatorEmail);	
		   modeltransaltor.put("ccList", new ArrayList<String>());
		   modeltransaltor.put("bccList", new ArrayList<String>());
		   sendEmail(modeltransaltor,"welcometranslator.vm");
	   }
		
	   @Override
	   public void sendEmailWelcomeCutomer(String password, String customerEmail, String customername) {
		   Map<String, Object> modelcustomer = new HashMap<String, Object>();
		   modelcustomer.put("from", emailFrom);						
		   modelcustomer.put("subject", customername+", Welcome to Translatorss");
		   modelcustomer.put("customername", customername);
		   modelcustomer.put("password", password);
		   modelcustomer.put("to", customerEmail);	
		   modelcustomer.put("ccList", new ArrayList<String>());
		   modelcustomer.put("bccList", new ArrayList<String>());
		   sendEmail(modelcustomer,"welcomecustomer.vm");
	   } 
		

	   @Override
		public void sendEmailServiceRequestExpired(String customerEmail, String customername, String servicerequestid) {
		   Map<String, Object> modelcustomer = new HashMap<String, Object>();
		   modelcustomer.put("from", emailFrom);						
		   modelcustomer.put("subject","Sorry no Translator available");
		   modelcustomer.put("customername", customername);
		   modelcustomer.put("servicerequestid", servicerequestid);
		   modelcustomer.put("to", customerEmail);	
		   modelcustomer.put("ccList", new ArrayList<String>());
		   modelcustomer.put("bccList", new ArrayList<String>());
		   sendEmail(modelcustomer,"3Uservicerequestexpired.vm");

		}
	   
	@Override
	public void sendEmailNewQuoteFromTranslator(String customerEmail, String customername, String translatorname,
				String quote, String servicerequestid) {
			// TODO Auto-generated method stub
		   Map<String, Object> modelcustomer = new HashMap<String, Object>();
		   modelcustomer.put("from", emailFrom);						
		   modelcustomer.put("subject","Action Required Quotes Available");
		   modelcustomer.put("customername", customername);
	
		   modelcustomer.put("translatorname", translatorname);
		   modelcustomer.put("servicerequesid", servicerequestid);
		   modelcustomer.put("quotevalue", quote);
		   modelcustomer.put("to", customerEmail);	
		   modelcustomer.put("ccList", new ArrayList<String>());
		   modelcustomer.put("bccList", new ArrayList<String>());
		   sendEmail(modelcustomer,"4Uservicerequestnewquote.vm");
	}  

	@Override
	public void sendEmailNewQuoteFromCustomer(String customerEmail, String customername, String quote,
			String servicerequestid) {
		   Map<String, Object> modelcustomer = new HashMap<String, Object>();
		   modelcustomer.put("from", emailFrom);						
		   modelcustomer.put("subject","Action Required Quotes Available");
		   modelcustomer.put("customername", customername);
		   modelcustomer.put("servicerequesid", servicerequestid);
		   modelcustomer.put("quotevalue", quote);
		   modelcustomer.put("to", customerEmail);	
		   modelcustomer.put("ccList", new ArrayList<String>());
		   modelcustomer.put("bccList", new ArrayList<String>());
		   sendEmail(modelcustomer,"4Uservicerequestnewquote.vm");
	}
	
	@Override
	public void sendEmailQuoteAcceptedTranslator(String translatorEmail, String translatorName, String timeframe,
			String servicerequesid, Date finishDate) {
		   Map<String, Object> modelcustomer = new HashMap<String, Object>();
		   modelcustomer.put("from", emailFrom);						
		   modelcustomer.put("subject","Action Required");
		   modelcustomer.put("translatorname", translatorName);
		   modelcustomer.put("servicerequestid", servicerequesid);
		   modelcustomer.put("timeframe", timeframe);
		   modelcustomer.put("finishdate", finishDate.toString());
		   modelcustomer.put("to", translatorEmail);	
		   modelcustomer.put("ccList", new ArrayList<String>());
		   modelcustomer.put("bccList", new ArrayList<String>());
		   sendEmail(modelcustomer,"6Tquoteaceptedtranslator.vm");
	}

	public void sendEmailQuoteAcceptedCustomer(String customerEmail, String customername, Long quote, String servicerequesid, Date finishDate){
		   Map<String, Object> modelcustomer = new HashMap<String, Object>();
		   modelcustomer.put("from", emailFrom);						
		   modelcustomer.put("subject","Job "+servicerequesid +" Invoice");
		   modelcustomer.put("customername", customername);
		   modelcustomer.put("servicerequesid", servicerequesid);
		   modelcustomer.put("quotevalue", quote);
		   modelcustomer.put("to", customerEmail);	
		   modelcustomer.put("ccList", new ArrayList<String>());
		   modelcustomer.put("bccList", new ArrayList<String>());
		   sendEmail(modelcustomer,"5Uquoteaceptedcustomer.vm");
	}

	@Override
	public void sendEmailToTranslatorFileSentByCustomer(String translatorEmail, String translatorName,String customerName,List<MultipartFile> list, String serviceRequestid) {
		   Map<String, Object> modeltranslator = new HashMap<String, Object>();
		   
		   String fileList = new String();
		   for(MultipartFile file: list){
			   fileList= fileList.concat(" "+file.getOriginalFilename());
		   }
		   modeltranslator.put("from", emailFrom);						
		   modeltranslator.put("subject","File Sent By Customer "+customerName);
		   modeltranslator.put("customername", customerName);
		   modeltranslator.put("filename", fileList);
		   modeltranslator.put("translatorname", translatorName);
		   modeltranslator.put("servicerequestid", serviceRequestid);
		   modeltranslator.put("to", translatorEmail);	
		   modeltranslator.put("ccList", new ArrayList<String>());
		   modeltranslator.put("bccList", new ArrayList<String>());
		   sendEmail(modeltranslator,"fileSentToTranslator.vm");
	}

	@Override
	public void sendEmailToCustomerFileSentByTranslator(String customerEmail, String customername,String translatorName, List<MultipartFile> list, String servicerequestid) {
		   Map<String, Object> modelcustomer = new HashMap<String, Object>();

		   String fileList = new String();
		   for(MultipartFile file: list){
			   if(file==null)continue;
			   fileList= fileList.concat(" "+file.getOriginalFilename());
		   }
		   modelcustomer.put("from", emailFrom);						
		   modelcustomer.put("subject","File Sent By Translaor "+translatorName);
		   modelcustomer.put("customername", customername);
		   modelcustomer.put("servicerequestid", servicerequestid);
		   modelcustomer.put("translatorname", translatorName);
		   modelcustomer.put("filename", fileList);
		   modelcustomer.put("to", customerEmail);	
		   modelcustomer.put("ccList", new ArrayList<String>());
		   modelcustomer.put("bccList", new ArrayList<String>());
		   sendEmail(modelcustomer,"8UfileSentToCustomer.vm");
	}

	@Override
	public void sendEmailToCustomerMessageSentByTranslator(String customerEmail, String customername,String translatorName, String message, String servicerequestid) {
		   Map<String, Object> modelcustomer = new HashMap<String, Object>();
		   modelcustomer.put("from", emailFrom);						
		   modelcustomer.put("subject","Message Sent By Translaor "+translatorName);
		   modelcustomer.put("customername", customername);
		   modelcustomer.put("servicerequestid", servicerequestid);
		   modelcustomer.put("translatorname", translatorName);
		   modelcustomer.put("message", message);
		   modelcustomer.put("to", customerEmail);	
		   modelcustomer.put("ccList", new ArrayList<String>());
		   modelcustomer.put("bccList", new ArrayList<String>());
		   sendEmail(modelcustomer,"8UMessageSentToCustomer.vm");
	}
	
	@Override
	public void sendEmailToTranslatorMessageSentByCustomer(String translatorEmail, String translatorname,String customername, String message, String servicerequestid) {
		   Map<String, Object> modelcustomer = new HashMap<String, Object>();

		   modelcustomer.put("from", emailFrom);						
		   modelcustomer.put("subject","Message Sent By Customer "+customername);
		   modelcustomer.put("customername", customername);
		   modelcustomer.put("servicerequestid", servicerequestid);
		   modelcustomer.put("translatorname", translatorname);
		   modelcustomer.put("message", message);
		   modelcustomer.put("to", translatorEmail);	
		   modelcustomer.put("ccList", new ArrayList<String>());
		   modelcustomer.put("bccList", new ArrayList<String>());
		   sendEmail(modelcustomer,"7TMessageSentToTranslator.vm");
	}
	
	
	
	@Override
	public void sendEmailToCustomerServiceRequestExpired(String customerEmail, String customername,String servicerequestid) {
		Map<String, Object> modelcustomer = new HashMap<String, Object>();

		modelcustomer.put("from", emailFrom);						
		modelcustomer.put("subject","Case Resolution Required");
		modelcustomer.put("customername", customername);
		modelcustomer.put("servicerequestid", servicerequestid);
		modelcustomer.put("to", customerEmail);	
		modelcustomer.put("ccList", new ArrayList<String>());
		modelcustomer.put("bccList", new ArrayList<String>());
		sendEmail(modelcustomer,"countDownUp.vm");
	}

	@Override
	public void sendEmailToTranslatorStatusChange(String translatoremail, String translatorname,String translatorstatus) {
		Map<String, Object> modelcustomer = new HashMap<String, Object>();
		modelcustomer.put("from", emailFrom);						
		modelcustomer.put("subject","Status Change");
		modelcustomer.put("translatorname", translatorname);
		modelcustomer.put("translatorstatus", translatorstatus);
		modelcustomer.put("to", translatoremail);	
		modelcustomer.put("ccList", new ArrayList<String>());
		modelcustomer.put("bccList", new ArrayList<String>());
		sendEmail(modelcustomer,"changeStatus.vm");
	}
	
	
	@Override
	public void sendEmailToTranslatorServiceRequestApproved(String servicerequestid, String translatorname,
			String translatorEmail, String translatorCommunication, String serviceAsDescribed, String wouldRecommend,
			String abname, String abnumber, String quote) {
		
		Map<String, Object> modelcustomer = new HashMap<String, Object>();

		modelcustomer.put("from", emailFrom);						
		modelcustomer.put("subject","Job "+servicerequestid+" has been approved");
		modelcustomer.put("customername", translatorname);
		modelcustomer.put("servicerequestid", servicerequestid);
		
		modelcustomer.put("translatorCommunication", translatorCommunication);
		modelcustomer.put("serviceAsDescribed", serviceAsDescribed);
		modelcustomer.put("wouldRecommend", wouldRecommend);

		modelcustomer.put("to", translatorEmail);	
		modelcustomer.put("ccList", new ArrayList<String>());
		modelcustomer.put("bccList", new ArrayList<String>());
		sendEmailInvoice(modelcustomer,abname,abnumber,quote,"10TServiceApproved.vm");
		

	}

	@Override
	public void sendEmailToCustomerServiceRequestAppoved(ServiceRequest servicerequest, String customername, String translatorname, String customerEmail, String translatorEmail,String abname, String abnumber, Quotation quotation) {

		Map<String, Object> modelcustomer = new HashMap<String, Object>();
		modelcustomer.put("from", emailFrom);						
		modelcustomer.put("subject","Job "+servicerequest.getId()+" has been approved");
		modelcustomer.put("customername", customername);
		modelcustomer.put("servicerequestid", servicerequest.getId());
		modelcustomer.put("to", customerEmail);	
		modelcustomer.put("ccList", new ArrayList<String>());
		modelcustomer.put("bccList", new ArrayList<String>());

		Rate rate = rateService.getRateByServiceRequest(servicerequest);
		Map<String, Object> modeltranslator = new HashMap<String, Object>();
		String translatorTemplate="10TServiceApprovedAdmin.vm";
		if(rate!=null) {
			modeltranslator.put("service", rate.getServiceAsDescribed());
			modeltranslator.put("timedelivery", rate.getTimeDelivery());
			modeltranslator.put("quality", rate.getQuality());
			translatorTemplate="10TServiceApproved.vm";
		}
		modeltranslator.put("from", emailFrom);						
		modeltranslator.put("subject","Job "+servicerequest.getId()+" has been approved");
		modeltranslator.put("translatorname", translatorname);
		modeltranslator.put("servicerequestid", servicerequest.getId());
		
		modeltranslator.put("to", translatorEmail);	
		modeltranslator.put("ccList", new ArrayList<String>());
		modeltranslator.put("bccList", new ArrayList<String>());
		
		try {
			createAndSendInvoice(modelcustomer,modeltranslator, "11UServiceApproved.vm",translatorTemplate ,new InvoicePdfDto(quotation), servicerequest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void sendEmail(final Map<String, Object> model,final String templateName) {
	        boolean r = false;
	    	try {
	    		MimeMessagePreparator preparator = new MimeMessagePreparator() {
	            @SuppressWarnings("unchecked")
	            public void prepare(MimeMessage mimeMessage) throws Exception {
	            	String from = (String) model.get(FROM);
	                String to = (String) model.get(TO);
	                String subject = (String) model.get(SUBJECT);
	                List<String> bCCList = (List<String>) model.get(BCC_LIST);
	                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
	                message.setFrom(from);
	                message.setTo(to);
	                message.setSubject(subject);
	                message.setSentDate(new Date());
	                if (bCCList != null) {
	                    for (String bcc : bCCList) {
	                        message.addBcc(bcc);
	                    }
	                }    
	                model.put("noArgs", new Object[]{});
	                String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, templateName,"UTF-8", model);
	                message.setText(text, true);
	            }
	        };
	
	        mailSender.send(preparator); 
	        r = true;
	        }catch(Exception e) {
	        	e.printStackTrace();        	
	        }
	    }

	@Override
	public void sendEmailNewQuoteFromTranslator(String customerEmail, String customerName, String quote,
			String servicerequesid) {
		// TODO Auto-generated method stub
		
	}

	

    private void sendEmailInvoice(final Map<String, Object> model, String abname, String abnumber, String quote, final String templateName) {
		boolean r = false;
    	try {
    		MimeMessagePreparator preparator = new MimeMessagePreparator() {
            @SuppressWarnings("unchecked")
            public void prepare(MimeMessage mimeMessage) throws Exception {
            	String from = (String) model.get(FROM);
                String to = (String) model.get(TO);
                String subject = (String) model.get(SUBJECT);
                List<String> bCCList = (List<String>) model.get(BCC_LIST);
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				message.setFrom(from);
                message.setTo(to);
                message.setSubject(subject);
                message.setSentDate(new Date());
                if (bCCList != null) {
                    for (String bcc : bCCList) {
                        message.addBcc(bcc);
                    }
                }    
                model.put("noArgs", new Object[]{});
                String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, templateName,"UTF-8", model);
                message.setText(text, true);
            }
        };

        mailSender.send(preparator); 
        r = true;
        }catch(Exception e) {
        	e.printStackTrace();        	
        }
	}
	
	
    private void createAndSendInvoice(Map<String, Object> modelcustomer,Map<String, Object> modeltranslator, String templateName,String templateName2, InvoicePdfDto dto, ServiceRequest serviceRequest) throws IOException {
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
        sendInvoice(modelcustomer,templateName , bytes);
        sendInvoice(modeltranslator,templateName2 , bytes);

    }

    public void sendInvoice(final Map<String, Object> model,final String templateName, final byte[] bytes) {
    	/*Modificar este contenido con el sen*/
		try {
			MimeMessagePreparator preparator = new MimeMessagePreparator() {
				@SuppressWarnings("unchecked")
				public void prepare(MimeMessage mimeMessage) throws Exception {
					String from = (String) model.get(FROM);
	                String to = (String) model.get(TO);
	                String subject = (String) model.get(SUBJECT);
					MimeMessageHelper message = new MimeMessageHelper(mimeMessage,true);
					message.setFrom(from);
					message.setTo(to);
					message.setSubject(subject);
					message.setSentDate(new Date());
					
					message.addAttachment("invoice.pdf", new ByteArrayResource(bytes));
					String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, templateName,"UTF-8", model);
	                message.setText(text, true);
				}
			};

			mailSender.send(preparator);
		}catch(Exception e) {
			e.printStackTrace();
		}
    }

    
    
    
    private ByteArrayOutputStream createPdf(InvoicePdfDto dto) throws IOException {
        ByteArrayOutputStream outputStream = null;
        InputStream templateStream;
        try {
            outputStream = new ByteArrayOutputStream();

            templateStream = getClass().getClassLoader().getResourceAsStream(INVOICE_TEMPLATE_HTML);

            String template = IOUtils.toString(templateStream);

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

            InputStream contentStream = IOUtils.toInputStream(template);

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

	@Override
	public void sendEmailtoTranslatorServiceRequestCancel(String translatorEmail, String translatorname, String servicerequestid) {

		Map<String, Object> modeltranslator = new HashMap<String, Object>();
		modeltranslator.put("from", emailFrom);						
		modeltranslator.put("subject","Job "+ servicerequestid +" has been cancelled");
		modeltranslator.put("translatorname", translatorname);
		modeltranslator.put("servicerequestid", servicerequestid);
		modeltranslator.put("to", translatorEmail);	
		modeltranslator.put("ccList", new ArrayList<String>());
		modeltranslator.put("bccList", new ArrayList<String>());
		
	    sendEmail(modeltranslator,"13TServiceRequestCancel.vm");
	}

	@Override
	public void sendEmailtoCustomerServiceRequestCancel(String customerEmail, String customername, String servicerequestid) {

		Map<String, Object> modelcustomer = new HashMap<String, Object>();
		modelcustomer.put("from", emailFrom);						
		modelcustomer.put("subject","Job cancelled");
		modelcustomer.put("customername", customername);
		modelcustomer.put("servicerequestid", servicerequestid);
		modelcustomer.put("to", customerEmail);	
		modelcustomer.put("ccList", new ArrayList<String>());
		modelcustomer.put("bccList", new ArrayList<String>());
		
	    sendEmail(modelcustomer,"12UServiceRequestCancel.vm");
	}


	
}
