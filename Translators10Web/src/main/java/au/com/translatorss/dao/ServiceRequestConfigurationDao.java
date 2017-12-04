package au.com.translatorss.dao;

import au.com.translatorss.bean.ServiceRequestConfiguration;

public interface ServiceRequestConfigurationDao {

    public ServiceRequestConfiguration getServiceRequestConfiguration();

	public void saveOrUpdate(ServiceRequestConfiguration serviceReqConfig);
}
