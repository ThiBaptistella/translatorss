package au.com.translatorss.service;

import java.util.List;

import au.com.translatorss.bean.BusinessUser;
import au.com.translatorss.bean.ServiceRequest;
import au.com.translatorss.bean.ServiceRequestApprovedRegister;
import au.com.translatorss.bean.Translator;
import au.com.translatorss.bean.dto.ServiceRequestApprovedDTO;

public interface ServiceRequestApprovedRegisterService extends GenericService<ServiceRequestApprovedRegister, Long>{

	public void registerApprovedSR(ServiceRequest serviceRequest);

	public List<ServiceRequestApprovedDTO> getApprovedSRByCustomer(BusinessUser businessUser);

	public List<ServiceRequestApprovedDTO> getApprovedSRByTranslator(Translator translator);

}
