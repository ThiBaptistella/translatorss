package au.com.translatorss.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import au.com.translatorss.bean.PurchaseType;
import au.com.translatorss.bean.ServiceRequestStatus;
import au.com.translatorss.dao.PurchaseTypeDao;

@Repository
public class PurchaseTypeImplDao extends GenericDaoImplementation<PurchaseType, Long>implements PurchaseTypeDao {

    @Override
    public PurchaseType findByDescription(String status) {
        Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(PurchaseType.class);
        criteria.add(Restrictions.eq("description", status));
        PurchaseType purchaseType=(PurchaseType)criteria.uniqueResult();
        return purchaseType;
    }

}
