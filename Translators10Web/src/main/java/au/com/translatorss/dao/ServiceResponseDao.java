package au.com.translatorss.dao;

import au.com.translatorss.bean.ServiceResponse;

public interface ServiceResponseDao extends GenericDao<ServiceResponse, Long> {

	ServiceResponse getServiceResponseByConversationId(Long conversationid);

   // public ServiceResponse getServiceResponseByServiceRequestId(Long id);

}
