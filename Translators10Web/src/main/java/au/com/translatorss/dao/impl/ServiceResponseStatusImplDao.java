package au.com.translatorss.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import au.com.translatorss.bean.ServiceRequestStatus;
import au.com.translatorss.bean.ServiceResponseStatus;
import au.com.translatorss.dao.ServiceResponseStatusDao;

@Repository
@Transactional
public class ServiceResponseStatusImplDao extends GenericDaoImplementation<ServiceResponseStatus, Integer>
		implements ServiceResponseStatusDao {

	
	@Override
    public ServiceResponseStatus findByDescription(String status) {
        Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(ServiceResponseStatus.class);
        criteria.add(Restrictions.eq("description", status));
        ServiceResponseStatus serviceRequestStatus=(ServiceResponseStatus)criteria.uniqueResult();
        return serviceRequestStatus;
    }
}
