package au.com.translatorss.dao.impl;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import au.com.translatorss.bean.BusinessUser;
import au.com.translatorss.bean.ServiceRequestApprovedRegister;
import au.com.translatorss.bean.Translator;
import au.com.translatorss.dao.ServiceRequestApprovedRegisterDao;

@Repository
@Transactional
public class ServiceRequestApprovedRegisterDaoImpl extends GenericDaoImplementation<ServiceRequestApprovedRegister, Long> implements ServiceRequestApprovedRegisterDao{

	@Override
	public List<ServiceRequestApprovedRegister> getApprovedSRByCustomer(BusinessUser businessUser) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -4);
		Date date = cal.getTime();
		Query query = this.getSessionFactory().getCurrentSession().createQuery("from ServiceRequestApprovedRegister serviceRequestAproved where serviceRequest.customer.id="+businessUser.getId()+" and approvedDate >:date");
		query.setParameter("date", date);
		@SuppressWarnings("unchecked")
        List<ServiceRequestApprovedRegister> serviceRequestApprovedRegister= query.list();
		Collections.sort(serviceRequestApprovedRegister);
		return serviceRequestApprovedRegister;
	}

	@Override
	public List<ServiceRequestApprovedRegister> getApprovedSRByTranslator(Translator translator) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		Date date = cal.getTime();
		
		return null;
	}
	
}
