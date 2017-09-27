package au.com.translatorss.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.com.translatorss.bean.ServiceRequestPayment;
import au.com.translatorss.dao.ServiceRequestPaymentDao;
import au.com.translatorss.service.ServiceRequestPaymentService;

@Service
@Transactional
public class ServiceRequestPaymentServicieImpl implements ServiceRequestPaymentService{

	@Autowired
	private ServiceRequestPaymentDao serviceRequestPaymentDao;

	@Override
	public List<ServiceRequestPayment> getAll() {
		return serviceRequestPaymentDao.getAll();
	}

	@Override
	public List<ServiceRequestPayment> getServiceRequestPaymentApproved() {
		return serviceRequestPaymentDao.serviceRequestListByState("Approved");
	}
	
	
}
