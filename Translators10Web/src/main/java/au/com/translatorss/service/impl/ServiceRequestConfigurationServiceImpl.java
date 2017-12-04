package au.com.translatorss.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.com.translatorss.bean.ServiceRequestConfiguration;
import au.com.translatorss.dao.ServiceRequestConfigurationDao;
import au.com.translatorss.service.ServiceRequestConfigurationService;

@Service
@Transactional
public class ServiceRequestConfigurationServiceImpl implements ServiceRequestConfigurationService {

    @Autowired
    private ServiceRequestConfigurationDao serviceRequestConfigurationDao;
    
    @Override
    public ServiceRequestConfiguration getServiceRequestConfiguration() {
        return serviceRequestConfigurationDao.getServiceRequestConfiguration();
    }

    @Override
    public int getServiceRequestMinimunMarket() {
        return serviceRequestConfigurationDao.getServiceRequestConfiguration().getMinimumMarket();
    }

    @Override
    public int getServiceRequestHoursLeft() {
        return serviceRequestConfigurationDao.getServiceRequestConfiguration().getHoursLeft();
    }

	@Override
	public void saveOrUpdate(ServiceRequestConfiguration serviceReqConfig) {
		serviceRequestConfigurationDao.saveOrUpdate(serviceReqConfig);
	}
}
