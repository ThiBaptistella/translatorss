package au.com.translatorss.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.com.translatorss.bean.ServiceRequestStatus;
import au.com.translatorss.dao.ServiceRequestStatusDao;
import au.com.translatorss.service.ServiceRequestStatusService;

@Service
@Transactional
public class ServiceRequestStatusServiceImpl implements ServiceRequestStatusService{

    @Autowired
    private ServiceRequestStatusDao serviceRequestStatusDao;

    @Override
    public ServiceRequestStatus findByDescription(String string) {
        return serviceRequestStatusDao.findByDescription(string);
    }
}
