package au.com.translatorss.dao;

import au.com.translatorss.bean.Conversation;
import au.com.translatorss.bean.ServiceResponse;

import java.util.List;

public interface ConversationDao extends GenericDao<Conversation, Long>{

	public List<Conversation> getAllConversationsByCustomerId(Long customerId);

    public List<Conversation> getAllConversationsByTranslatorId(Long translatorId);

//	public Conversation getConversationFromServiceRequestId(Long servicerequestid);

	Integer getAllUnreadMessageByUser(Long id);

	void markAsReadMessages(Long id, Long conversationId);

	Integer getUnreadMessageByUserIdAndConversationId(Long userId, Long conversationId);

	public Conversation getConversationFromServiceRequestIdAndTranslatorId(Long id, Long id2);

	public Conversation getConversationByServiceResponseId(Long servcieresponseid);

	public ServiceResponse getServiceResponseByConversationId(Long id);
}
