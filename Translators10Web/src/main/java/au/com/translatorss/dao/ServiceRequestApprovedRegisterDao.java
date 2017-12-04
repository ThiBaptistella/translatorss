package au.com.translatorss.dao;

import java.util.List;

import au.com.translatorss.bean.BusinessUser;
import au.com.translatorss.bean.ServiceRequestApprovedRegister;
import au.com.translatorss.bean.Translator;
import au.com.translatorss.bean.dto.ServiceRequestApprovedDTO;

public interface ServiceRequestApprovedRegisterDao extends GenericDao<ServiceRequestApprovedRegister, Long>{

	public List<ServiceRequestApprovedRegister> getApprovedSRByCustomer(BusinessUser businessUser);
	
	public List<ServiceRequestApprovedRegister> getApprovedSRByTranslator(Translator translator);
}
