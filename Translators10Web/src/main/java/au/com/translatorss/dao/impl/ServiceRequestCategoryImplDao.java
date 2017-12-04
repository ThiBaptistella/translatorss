package au.com.translatorss.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import au.com.translatorss.bean.ServiceRequestCategory;
import au.com.translatorss.dao.ServiceRequestCategoryDao;

@Repository
@Transactional
public class ServiceRequestCategoryImplDao extends GenericDaoImplementation<ServiceRequestCategory, Integer>
		implements ServiceRequestCategoryDao {

    @Override
    public ServiceRequestCategory findByDescription(String serviceRequestCategory) {
        Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(ServiceRequestCategory.class);
        criteria.add(Restrictions.eq("description", serviceRequestCategory));
        ServiceRequestCategory serviceRequestCategory2=(ServiceRequestCategory)criteria.uniqueResult();
        return serviceRequestCategory2;
    }

	@Override
	public List<ServiceRequestCategory> getAutomaticCategories() {
        Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(ServiceRequestCategory.class);
        criteria.add(Restrictions.eq("defaultPrice", true));
        return criteria.list();
	}

}
