package au.com.translatorss.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;

public class EmailTestPDF {

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
	
	   
	   
	public static void main(String[] args) {
		sendEmail2();
	}
	
	private static  void sendEmail2() {
		    String SMTP_HOST_NAME = "mail.domain.com";  
	        String SMTP_PORT = "111";   

	        String SMTP_FROM_ADDRESS="info@translatorss.com.au";  
	        String SMTP_TO_ADDRESS="njavier85@hotmail.com";  
	        String subject="Textmsg";  
	        String fileAttachment = "invoice.pdf";  

	          Properties props = new Properties();  

	          props.put("mail.smtp.host", "smtp.gmail.com");  

	          Session session = Session.getInstance(props,new javax.mail.Authenticator()  
	            {protected javax.mail.PasswordAuthentication   
	                getPasswordAuthentication()   
	            {return new javax.mail.PasswordAuthentication("xxxx@domain.com","password");}});  
	          try{  

	          Message msg = new MimeMessage(session);  

	          msg.setFrom(new InternetAddress(SMTP_FROM_ADDRESS));  
	       // create the message part   
	          MimeBodyPart messageBodyPart =   
	            new MimeBodyPart();  
	      //fill message  
	          messageBodyPart.setText("Test mail one");  
	          Multipart multipart = new MimeMultipart();  
	          multipart.addBodyPart(messageBodyPart);  
	       // Part two is attachment  
	          messageBodyPart = new MimeBodyPart();  
	          DataSource source =   
	            new FileDataSource(fileAttachment);  
	          messageBodyPart.setDataHandler(  
	            new DataHandler(source));  
	          messageBodyPart.setFileName(fileAttachment);  
	          multipart.addBodyPart(messageBodyPart);  
	      // Put parts in message  
	          msg.setContent(multipart);  


	          msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(SMTP_TO_ADDRESS));  

	          msg.setSubject(subject);  
	         // msg.setContent(content, "text/plain");  

	          Transport.send(msg);  
	          System.out.println("success....................................");  
	          }  
	          catch(Exception e){  
	              e.printStackTrace();        
	          }		
	}

//	private static void sendEmail() {
//        boolean r = false;
//    	try {
//    		MimeMessagePreparator preparator = new MimeMessagePreparator() {
//            @SuppressWarnings("unchecked")
//            public void prepare(MimeMessage mimeMessage) throws Exception {
//                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
//                message.setFrom("info@translatorss.com.au");
//                message.setTo("njavier85@hotmail.com");
//                message.setSubject("Test PDF");
//                message.setSentDate(new Date());
//               
//                String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "welcometranslator.vm","UTF-8", new HashMap<String, Object>());
//                message.setText(text, true);
//            }
//        };
//
//        mailSender.send(preparator); 
//        r = true;
//        }catch(Exception e) {
//        	e.printStackTrace();        	
//        }
//    }

}
