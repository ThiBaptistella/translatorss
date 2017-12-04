package au.com.translatorss.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.com.translatorss.service.EmailService;

@Service
@Transactional
public class EmailServiceImpl implements EmailService {

	   @Value("${java.mail.username}")
	   private String emailFrom;
	     
	   @Value("${java.mail.password}")
	   private String password;
	
	@Override
	public void sendMessage(String subject,String messageContent, String destination) {
	    System.out.println("Sending Email to: "+destination+", message:"+ messageContent);
	    
//		Properties props = new Properties();
//		props.put("mail.smtp.auth", "true");
//		props.put("mail.smtp.starttls.enable", "true");
//		props.put("mail.smtp.host", "smtp.gmail.com");
//		props.put("mail.smtp.port", "587");
//		
//		Session session = Session.getInstance(props,
//				  new javax.mail.Authenticator() {
//					protected PasswordAuthentication getPasswordAuthentication() {
//						return new PasswordAuthentication(username, password);
//					}
//		 });
//
//		try {
//			Message message = new MimeMessage(session);
//			message.setFrom(new InternetAddress(username));
//			message.setRecipients(Message.RecipientType.TO,
//				InternetAddress.parse(destination));
//			message.setSubject(subject);
//			message.setText(messageContent);
//
//			Transport.send(message);
//
//			System.out.println("Done");
//
//		} catch (MessagingException e) {
//			throw new RuntimeException(e);
//		}
		
	}

}
