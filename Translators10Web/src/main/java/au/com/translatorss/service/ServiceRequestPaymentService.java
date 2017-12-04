package au.com.translatorss.service;

import java.util.List;

import au.com.translatorss.bean.ServiceRequestPayment;

public interface ServiceRequestPaymentService {

	public List<ServiceRequestPayment> getAll();

	public List<ServiceRequestPayment> getServiceRequestPaymentApproved();

	public List<ServiceRequestPayment> getServiceRequestPaymentCancelled();

}
