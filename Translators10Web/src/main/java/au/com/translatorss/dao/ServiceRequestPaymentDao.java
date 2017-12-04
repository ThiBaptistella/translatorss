package au.com.translatorss.dao;

import java.util.List;

import au.com.translatorss.bean.ServiceRequestPayment;

public interface ServiceRequestPaymentDao extends GenericDao<ServiceRequestPayment, Long> {

	public List<ServiceRequestPayment> serviceRequestListByState(String string);

}
