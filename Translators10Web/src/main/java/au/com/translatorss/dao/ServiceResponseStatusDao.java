package au.com.translatorss.dao;

import au.com.translatorss.bean.ServiceResponseStatus;

public interface ServiceResponseStatusDao extends GenericDao<ServiceResponseStatus, Integer> {

    public ServiceResponseStatus findByDescription(String status);
	
}
