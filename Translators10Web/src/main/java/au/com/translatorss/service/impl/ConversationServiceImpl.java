package au.com.translatorss.service.impl;

import au.com.translatorss.bean.ChatMessage;
import au.com.translatorss.bean.Conversation;
import au.com.translatorss.bean.ServiceRequest;
import au.com.translatorss.bean.ServiceResponse;
import au.com.translatorss.bean.Translator;
import au.com.translatorss.bean.User;
import au.com.translatorss.dao.ConversationDao;
import au.com.translatorss.service.ConversationService;
import au.com.translatorss.service.CustomerServiceRequestService;
import au.com.translatorss.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ConversationServiceImpl implements ConversationService{

    @Autowired
    private ConversationDao conversationDao;

	@Autowired
	private CustomerServiceRequestService serviceRequestService;
    
    @Autowired
    private UserService userService;

    @Override
    public void saveOrUpdate(Conversation entity) {
        conversationDao.saveOrUpdate(entity);
    }

    @Override
    public List<Conversation> getAll() {
        return conversationDao.getAll();
    }

    @Override
    public Conversation get(Long id) {
        return conversationDao.find(id);
    }

    @Override
    public void add(Conversation entity) {

    }

    @Override
    public void update(Conversation entity) {

    }

    @Override
    public void remove(Conversation entity) {
        conversationDao.remove(entity);
    }

    @Override
    public Conversation startOrContinueConversation(ServiceRequest serviceRequest) {
    	Conversation conversation = conversationDao.getConversationFromServiceRequestIdAndTranslatorId(serviceRequest.getId(),serviceRequest.getTranslator().getId());
    	if(conversation==null){
    		conversation = new Conversation();
    		conversation.setSender("Admin");
    		conversation.setLastMessage("Contract Started");
    		conversation.setUpdated(new Date());
    		serviceRequest.getConversationList().add(conversation);
    		conversation.setServiceRequest(serviceRequest);
    		conversation.setTranslator(serviceRequest.getTranslator());
    		
    		ChatMessage message = new ChatMessage();
    		message.setSender("Admin");
    		message.setDate(new Date());
    		message.setMessage("Contract Started");
    		message.setRead(false);
    		message.setConversation(conversation);
    		conversation.getMessageList().add(message);

    	}else{
    		//At this point the conversation already exists
    		ChatMessage message = new ChatMessage();
    		message.setSender("");
    		message.setDate(new Date());
    		message.setMessage("Contract Started");
    		message.setRead(false);
    		message.setConversation(conversation);
    		conversation.getMessageList().add(message);
    		conversation.setServiceRequest(serviceRequest);
    	}
    	conversationDao.saveOrUpdate(conversation);
    	return conversation;
    }


	public List<Conversation> getAllConversationsByCustomerId(Long customerId) {
		return conversationDao.getAllConversationsByCustomerId(customerId);
	}

    public List<Conversation> getAllConversationsByTranslatorId(Long translatorId){
		return conversationDao.getAllConversationsByTranslatorId(translatorId);
    }


    public Conversation getConversationFromServiceRequestIdAndTranslatorId(Long servicerequestid, Long translatorid){
    	return conversationDao.getConversationFromServiceRequestIdAndTranslatorId(servicerequestid, translatorid);
    }
    @Override
    public void messagesMarkAsRead(User producer, Long conversationId){
        conversationDao.markAsReadMessages(userService.getUserIdByRole(producer), conversationId);
    }

    @Override
    public Integer getAllUnreadMessageByUserId(Long userId) {
        return conversationDao.getAllUnreadMessageByUser(userId);
    }

    @Override
    public Integer getUnreadMessageByUserIdAndConversationId(Long userId, Long conversationId) {
        return conversationDao.getUnreadMessageByUserIdAndConversationId(userId, conversationId);
    }

	@Override
	public Conversation starConversation(ServiceRequest serviceRequest, Translator translator) {
		Conversation conv = new Conversation();
		conv.setServiceRequest(serviceRequest);
		conv.setTranslator(translator);
		conv.setSender("");
		conv.setUpdated(new Date());
		conv.setLastMessage("");
		serviceRequest.getConversationList().add(conv);
		serviceRequestService.saveOrUpdate(serviceRequest);
		conversationDao.saveOrUpdate(conv);
		return conv;
	}

	@Override
	public Conversation getConversationByServiceResponseId(Long servcieresponseid) {
		return this.conversationDao.getConversationByServiceResponseId(servcieresponseid);
	}

	@Override
	public ServiceResponse getServiceResponseByConversationId(Long id) {
		return this.conversationDao.getServiceResponseByConversationId(id);
	}
}
