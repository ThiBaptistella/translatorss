package au.com.translatorss.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import au.com.translatorss.bean.ServiceRequest;

import au.com.translatorss.bean.Rate;
import au.com.translatorss.bean.Translator;
import au.com.translatorss.dao.RateDao;

@Repository
public class RateImplDao extends GenericDaoImplementation<Rate, Long>implements RateDao {

    @Override
    public List<Rate> getAllTranslatorRates(Translator translator) {
        String query = "from Rate where translator.id = "+translator.getId();
        return this.getSessionFactory().getCurrentSession().createQuery(query).list();
    }

    public Rate getRateByServiceRequest(ServiceRequest servicerequest){
        Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(Rate.class, "rate");
        criteria.add(Restrictions.eq("serviceRequest", servicerequest));
        return (Rate)criteria.uniqueResult();
    }

}
