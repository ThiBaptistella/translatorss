package au.com.translatorss.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import au.com.translatorss.bean.ServiceRequestStatus;
import au.com.translatorss.dao.ServiceRequestStatusDao;

@Repository
@Transactional
public class ServiceRequestStatusImplDao extends GenericDaoImplementation<ServiceRequestStatus, Integer>
		implements ServiceRequestStatusDao {

    @Override
    public ServiceRequestStatus findByDescription(String status) {
        Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(ServiceRequestStatus.class);
        criteria.add(Restrictions.eq("description", status));
        ServiceRequestStatus serviceRequestStatus=(ServiceRequestStatus)criteria.uniqueResult();
        return serviceRequestStatus;
    }

}
