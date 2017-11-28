package au.com.translatorss.service;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import au.com.translatorss.bean.Quotation;
import au.com.translatorss.bean.ServiceRequest;

public interface EmailService2 {

	public void sendEmailToCustomerAndUnquotedTranslatorsAferSRCreated(String customerEmail, String serviceRequestid, String customername);

	public void sendEmailWelcomeTranslator(String translatorEmail, String translatorname, String password);
	
	public void sendEmailWelcomeCutomer(String password, String customerEmail, String customername);
	
	public void sendEmailServiceRequestExpired(String customerEmail, String customername, String servicerequestid);

	public void sendEmailNewQuoteFromTranslator(String customerEmail, String customerName, String quote, String servicerequesid);
	
	public void sendEmailNewQuoteFromCustomer(String customerEmail, String customername, String quote, String servicerequestid);
	
    public void sendEmailQuoteAcceptedTranslator(String translatorEmail, String translatorName, String quote, String servicerequesid, Date finishDate );
	
	public void sendEmailQuoteAcceptedCustomer(String customerEmail, String customername, Long quote, String servicerequesid, Date finishDate);

	public void sendEmailToCustomerFileSentByTranslator(String customerEmail, String customername, String translatorName, List<MultipartFile> list, String serviceRequestid);

	public void sendEmailToTranslatorFileSentByCustomer(String translatorEmail, String translatorName, String customerName, List<MultipartFile> list, String serviceRequestid);

	public void sendEmailToCustomerServiceRequestExpired(String customerEmail, String customername, String servicerequestid);

	public void sendEmailToTranslatorServiceRequestApproved(String servicerequestid, String translatorname, String translatorEmail, String translatorCommunication,String serviceAsDescribed, String wouldRecommend, String abname, String abnumber, String quote);

	public void sendEmailToCustomerServiceRequestAppoved(ServiceRequest servicerequest, String customername,String translatorname,String customerEmail,String translatorEmail, String abname, String abnumber, Quotation quote);

	public void sendEmailNewQuoteFromTranslator(String customerEmail, String customername, String translatorname, String quote,String servicerequestid);

	void sendEmailToCustomerMessageSentByTranslator(String customerEmail, String customername, String translatorName,
			String message, String servicerequestid);

	void sendEmailToTranslatorMessageSentByCustomer(String translatorEmail, String translatorname, String customername,
			String message, String servicerequestid);
	
	public void sendEmailtoTranslatorServiceRequestCancel(String translatorEmail, String translatorname,String servicerequestid);
	
	public void sendEmailtoCustomerServiceRequestCancel(String customerEmail, String customername,String servicerequestid);

	void sendEmailToTranslatorStatusChange(String translatoremail, String translatorname, String translatorstatus);

}
