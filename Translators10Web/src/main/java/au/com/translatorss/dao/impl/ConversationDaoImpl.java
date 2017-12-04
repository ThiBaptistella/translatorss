package au.com.translatorss.dao.impl;

import au.com.translatorss.bean.*;
import au.com.translatorss.dao.ChatMessageDao;
import au.com.translatorss.dao.ConversationDao;
import au.com.translatorss.realtime.RealtimeHandler;
import au.com.translatorss.service.UserService;
import org.hibernate.Criteria;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class ConversationDaoImpl extends GenericDaoImplementation<Conversation, Long> implements ConversationDao {

    @Autowired
    private RealtimeHandler realtimeHandler;

    @Autowired
    private UserService userService;

    @Autowired
    private ChatMessageDao chatMessageDao;
    
    @Override
    public List<Conversation> getAll() {
        String query = "from Conversation";

        @SuppressWarnings("unchecked")
        List<Conversation> conversationList = this.getSessionFactory().getCurrentSession().createQuery(query).list();
        return conversationList;
    }

	@Override
	public List<Conversation> getAllConversationsByCustomerId(Long customerId) {

		List<Conversation> newConversationList= new ArrayList<Conversation>();
        String query = "from Conversation where serviceRequest.customer.id="+customerId ;
        @SuppressWarnings("unchecked")
        List<Conversation> conversationList = this.getSessionFactory().getCurrentSession().createQuery(query).list();
		for(Conversation conv:conversationList){
			if(!conv.getMessageList().isEmpty()){
				newConversationList.add(conv);
			}
		}
        return newConversationList;
	}

    public List<Conversation> getAllConversationsByTranslatorId(Long translatorId){
		List<Conversation> newConversationList= new ArrayList<Conversation>();

//    	String query = "from Conversation where translator.id="+translatorId+ "messageList IS NOT EMPTY";
        String query = "from Conversation where translator.id="+translatorId;

        @SuppressWarnings("unchecked")
        List<Conversation> conversationList = this.getSessionFactory().getCurrentSession().createQuery(query).list();
    	for(Conversation conv:conversationList){
			if(!conv.getMessageList().isEmpty()){
				newConversationList.add(conv);
			}
		}
        return newConversationList;
    }

//	@Override
//	public Conversation getConversationFromServiceRequestId(Long servicerequestid) {
//		String query = "from Conversation where serviceRequest.id="+servicerequestid;
//        return (Conversation) this.getSessionFactory().getCurrentSession().createQuery(query).uniqueResult();
//    }

	public Conversation getConversationFromServiceRequestIdAndTranslatorId(Long servicerequestid, Long translatorid){
		String query = "from Conversation where serviceRequest.id="+servicerequestid+"and translator.id="+translatorid;
        return (Conversation) this.getSessionFactory().getCurrentSession().createQuery(query).uniqueResult();
	}

	@Override
	public Conversation getConversationByServiceResponseId(Long servcieresponseid) {
		String query = "from Conversation where serviceResponse.id="+servcieresponseid;
        return (Conversation) this.getSessionFactory().getCurrentSession().createQuery(query).uniqueResult();
	}
	
//    @Override
//    public Integer getAllUnreadMessageByUser(Long id) {
//        List<ServiceRequest> list = getOpenServiceRequestsByUserId(id);
//        int count = 0;
//        for (ServiceRequest sr : list) {
//        	for(Conversation conv : sr.getConversationList()){
//        		for (ChatMessage chatMessage : conv.getMessageList()) {
//                    if (chatMessage.getSenderId() != null && !Objects.equals(chatMessage.getSenderId(), id) && !chatMessage.isRead()) {
//                        count++;
//                    }
//                }
//        	} 
//        }
//        return count;
//    }

	@Override
	public Integer getAllUnreadMessageByUser(Long id) {
		return chatMessageDao.getUnreadMessageById(id).size();
	}

    @Override
    public void markAsReadMessages(Long id, Long conversationId) {
        Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(Conversation.class, "req");
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.add(Restrictions.eq("id", conversationId));
        Conversation conversation = (Conversation) criteria.uniqueResult();

        for (ChatMessage chatMessage : conversation.getMessageList()) {
            if (chatMessage.getRead() || chatMessage.getSenderId() == null || Objects.equals(chatMessage.getSenderId(), id)) {
                continue;
            }

            chatMessage.setRead(true);
            getSessionFactory().getCurrentSession().save(chatMessage);

            notifyMarkAsRead(conversationId, conversation, chatMessage);
        }
    }

    @Override
    public Integer getUnreadMessageByUserIdAndConversationId(Long id, Long conversationId) {
        List<ServiceRequest> list = getOpenServiceRequestsByUserId(id);//TODO: Anallize it
        int count = 0;
        for (ServiceRequest sr : list) {
            if (!Objects.equals(sr.getId(), conversationId)) {
                continue;
            }
            for(Conversation conv: sr.getConversationList()){
            	for (ChatMessage chatMessage : conv.getMessageList()) {
                    if (chatMessage.getSenderId() != null && !Objects.equals(chatMessage.getSenderId(), id) && !chatMessage.getRead()) {
                        count++;
                    }
                }
            }
            
        }
        return count;
    }

    private List<ServiceRequest> getOpenServiceRequestsByUserId(Long id) {
        Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(ServiceRequest.class, "req");
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.createAlias("req.customer", "cust");
        criteria.createAlias("req.translator", "trans");
        criteria.createAlias("req.serviceRequestStatus", "srs");
        LogicalExpression or = Restrictions.or(Restrictions.eq("cust.id", id), Restrictions.eq("trans.id", id));
        criteria.add(Restrictions.and(or, Restrictions.eq("srs.description","OpenService")));
        return (List<ServiceRequest>) criteria.list();
    }

    private void notifyMarkAsRead(Long conversationId, Conversation conversation, ChatMessage chatMessage) {
        ServiceRequest serviceRequest = conversation.getServiceRequest();
        User translator = userService.getByEmail(conversation.getTranslator().getUser().getEmail());
        User customer = userService.getByEmail(serviceRequest.getCustomer().getUser().getEmail());

        realtimeHandler.notifyMarkAsRead(conversationId, chatMessage.getId());
        realtimeHandler.notifyUnreadMessageChanged(translator);
        realtimeHandler.notifyUnreadMessageChanged(customer);
        realtimeHandler.notifyUnreadMessageChanged(translator, chatMessage.getConversation().getId());
        realtimeHandler.notifyUnreadMessageChanged(customer, chatMessage.getConversation().getId());
    }

	@Override
	public ServiceResponse getServiceResponseByConversationId(Long id) {
		Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(Conversation.class);
		criteria.add(Restrictions.eq("serviceResponse.id", id));
		Conversation conversation = (Conversation) criteria.uniqueResult();
		return conversation.getServiceResponse();
	}

}
