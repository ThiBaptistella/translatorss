package au.com.translatorss.service;

import au.com.translatorss.bean.ServiceRequestStatus;

public interface ServiceRequestStatusService {

    public ServiceRequestStatus findByDescription(String string);

}
