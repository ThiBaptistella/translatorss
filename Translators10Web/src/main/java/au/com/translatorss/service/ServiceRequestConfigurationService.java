package au.com.translatorss.service;

import au.com.translatorss.bean.ServiceRequestConfiguration;

public interface ServiceRequestConfigurationService {

    public ServiceRequestConfiguration getServiceRequestConfiguration();
    
    public int getServiceRequestMinimunMarket();
    
    public int getServiceRequestHoursLeft();
    
    public void saveOrUpdate(ServiceRequestConfiguration serviceReqConfig) ;

}
