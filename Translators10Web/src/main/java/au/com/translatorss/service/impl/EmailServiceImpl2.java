package au.com.translatorss.service.impl;

import au.com.translatorss.bean.Quotation;
import au.com.translatorss.bean.ServiceRequest;
import au.com.translatorss.bean.Translator;
import au.com.translatorss.dao.QuotationStandarDao;
import au.com.translatorss.service.*;
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
import java.io.ByteArrayOutputStream;
import java.util.*;

import static org.aspectj.bridge.Version.text;

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
	   
	   @Override
	   public void sendEmailToCustomerAndUnquotedTranslatorsAferSRCreated( String customerEmail, String serviceRequestid, String customername) {
		   ServiceRequest serviceRequest=serviceRequestService.find(new Long(serviceRequestid));
		   Set<Quotation> quotationList=serviceRequest.getQuotationList();
		   int quotationNumber= quotationList.size();
		   int nimunAmountQuotes=serviceRequestConfigurationService.getServiceRequestMinimunMarket();
		   if(quotationNumber<nimunAmountQuotes  || quotationNumber==0){
			    Map<String, Object> modelCustomer = new HashMap<String, Object>();
			    modelCustomer.put("from", emailFrom);						
			    modelCustomer.put("subject", "Quote "+serviceRequestid);
			    modelCustomer.put("to", customerEmail);	
			    modelCustomer.put("ccList", new ArrayList<String>());
			    modelCustomer.put("bccList", new ArrayList<String>());
			    modelCustomer.put("customername", customername); 
			    modelCustomer.put("servicerequestid", serviceRequestid); 
			    sendEmail(modelCustomer, "servicerequestcreationcustomer.vm");
			    
			    List<Translator> translatorList= quotationStandarDao.getAvailableTranslatorsWithoutQuotesSetUp(serviceRequest);
			    
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
					sendEmail(modelTranslator,"servicerequestcreationtranslator.vm");
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
		   modelcustomer.put("servicerequesid", servicerequestid);
		   modelcustomer.put("to", customerEmail);	
		   modelcustomer.put("ccList", new ArrayList<String>());
		   modelcustomer.put("bccList", new ArrayList<String>());
			
		}
	   
	  

	@Override
	public void sendEmailNewQuoteFromTranslator(String customerEmail, String customername, String quote, String servicerequestid) {
		Map<String, Object> modelcustomer = new HashMap<String, Object>();
		   modelcustomer.put("from", emailFrom);						
		   modelcustomer.put("subject","Action Required Quotes Available");
		   modelcustomer.put("customername", customername);
		   modelcustomer.put("servicerequesid", servicerequestid);
		   modelcustomer.put("quotevalue", quote);
		   modelcustomer.put("to", customerEmail);	
		   modelcustomer.put("ccList", new ArrayList<String>());
		   modelcustomer.put("bccList", new ArrayList<String>());
		   sendEmail(modelcustomer,"welcomecustomer.vm");
	}

	@Override
	public void sendEmailNewQuoteFromCustomer(String customerEmail, String customername, String quote,
			String servicerequestid) {

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
		   sendEmail(modelcustomer,"quoteaceptedtranslator.vm");
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
		   sendEmail(modelcustomer,"quoteaceptedcustomer.vm");

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
		   sendEmail(modelcustomer,"fileSentToCustomer.vm");
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
	public void sendEmailToCustomerServiceRequestAppoved(String servicerequestid, String customername,
			String customerEmail, String abname, String abnumber, String quote) {
		Map<String, Object> modelcustomer = new HashMap<String, Object>();

		modelcustomer.put("from", emailFrom);						
		modelcustomer.put("subject","Job "+servicerequestid+" has been approved");
		modelcustomer.put("customername", customername);
		modelcustomer.put("servicerequestid", servicerequestid);

		modelcustomer.put("to", customerEmail);	
		modelcustomer.put("ccList", new ArrayList<String>());
		modelcustomer.put("bccList", new ArrayList<String>());
		sendEmailInvoice(modelcustomer,abname,abnumber,quote,"11UServiceApproved.vm");
	}

    @Override
    public void sendInvoice(final byte[] bytes, final String translator, final String customer) {
		try {
			MimeMessagePreparator preparator = new MimeMessagePreparator() {
				@SuppressWarnings("unchecked")
				public void prepare(MimeMessage mimeMessage) throws Exception {
					String from = emailFrom;
					String[] to = {translator, customer};
					String subject = "Invoice";
					MimeMessageHelper message = new MimeMessageHelper(mimeMessage,true);
					message.setFrom(from);
					message.setTo(to);
					message.setSubject(subject);
					message.setSentDate(new Date());
					message.setText("Thank you for paymanet. " +
							"this is your invoice. ", true);
					message.addAttachment("invoice.pdf", new ByteArrayResource(bytes));
				}
			};

			mailSender.send(preparator);
		}catch(Exception e) {
			e.printStackTrace();
		}
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
}
