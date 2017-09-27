package au.com.translatorss.service;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

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

	public void sendEmailToCustomerServiceRequestAppoved(String servicerequestid, String customername,String customerEmail, String abname, String abnumber, String quote);

    void sendInvoice(byte[] bytes, String translator, String customer);
}
