package au.com.translatorss.service;

import au.com.translatorss.bean.Conversation;
import au.com.translatorss.bean.ServiceRequest;
import au.com.translatorss.bean.ServiceResponse;
import au.com.translatorss.bean.Translator;
import au.com.translatorss.bean.User;

import java.util.List;

public interface ConversationService {

    public void saveOrUpdate(Conversation entity);

    public List<Conversation> getAllConversationsByCustomerId(Long customerId);
    
    public List<Conversation> getAll();

    public Conversation get(Long id);

    public  void add(Conversation entity);

    public  void update(Conversation entity);

    public  void remove(Conversation entity);
    
    public Conversation startOrContinueConversation(ServiceRequest serviceRequest);
    
    public Conversation starConversation(ServiceRequest serviceRequest, Translator translator);
    
    public List<Conversation> getAllConversationsByTranslatorId(Long customerId);
    
    public Conversation getConversationFromServiceRequestIdAndTranslatorId(Long servicerequestid, Long translatorid);
    
    void messagesMarkAsRead(User producer, Long conversationId);

    public Integer getAllUnreadMessageByUserId(Long userId);

    public Integer getUnreadMessageByUserIdAndConversationId(Long userId, Long conversationId);
    
    public Conversation getConversationByServiceResponseId(Long servcieresponseid);

	public ServiceResponse getServiceResponseByConversationId(Long id);
}
