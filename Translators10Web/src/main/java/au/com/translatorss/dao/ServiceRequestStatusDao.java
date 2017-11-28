package au.com.translatorss.dao;

import au.com.translatorss.bean.ServiceRequestStatus;

public interface ServiceRequestStatusDao extends GenericDao<ServiceRequestStatus, Integer> {

    public ServiceRequestStatus findByDescription(String string);

}
