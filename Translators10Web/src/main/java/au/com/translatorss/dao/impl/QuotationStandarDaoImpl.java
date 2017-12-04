package au.com.translatorss.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import au.com.translatorss.bean.QuotationStandar;
import au.com.translatorss.bean.ServiceRequest;
import au.com.translatorss.bean.Translator;
import au.com.translatorss.dao.QuotationStandarDao;

@Repository
public class QuotationStandarDaoImpl extends GenericDaoImplementation<QuotationStandar, Long> implements QuotationStandarDao{

    public List<Translator> getAvailableTranslatorsWithoutQuotesSetUp(ServiceRequest serviceRequest) {
		Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(QuotationStandar.class);
		criteria.createAlias("timeFrame", "timeFrame");
		criteria.createAlias("category", "category");
		criteria.createAlias("translator", "translator");
	    criteria.createAlias("translator.languageList", "language");
        criteria.add(Restrictions.eq("language.description", serviceRequest.getLanguagefrom()));    
	    criteria.add(Restrictions.eq("timeFrame", serviceRequest.getTimeFrame()));
	    criteria.add(Restrictions.eq("category", serviceRequest.getServiceRequestCategory()));
	    criteria.add(Restrictions.eq("valid",false));
	    criteria.add(Restrictions.eq("translator.status","Active"));
       // criteria.setProjection(Projections.property("translator"));

	    List<Translator> translatorList = new ArrayList<Translator>();
	    @SuppressWarnings("unchecked")
        List<QuotationStandar> quoteationList = criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	    for(QuotationStandar quote:quoteationList){
			translatorList.add(quote.getTranslator());
		}
		return translatorList;
    }

    public List<Translator> getAvailableTranslatorsByLanguages(ServiceRequest serviceRequest) {
        Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(QuotationStandar.class);
        criteria.createAlias("translator.languageList", "language");
        criteria.createAlias("translator", "translator");

        criteria.add(Restrictions.eq("language.description", serviceRequest.getLanguagefrom().trim()));
        criteria.add(Restrictions.eq("translator.status", "Active"));
        
        criteria.add(Restrictions.eq("category", serviceRequest.getServiceRequestCategory()));
        criteria.add(Restrictions.eq("timeFrame", serviceRequest.getTimeFrame()));
        criteria.add(Restrictions.eq("valid", true));
        //criteria.setProjection(Projections.property("translator"));

        @SuppressWarnings("unchecked")
        List<QuotationStandar> quoteationList = criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        List<Translator> transList = new ArrayList<Translator>();
        for(QuotationStandar quote: quoteationList){
        	transList.add(quote.getTranslator());
        }
        return transList;
    }

	@Override
	public List<QuotationStandar> getByTimeFrame(String timeFrame, Long translatorId) {
		Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(QuotationStandar.class);
		criteria.createAlias("timeFrame", "timeFrame");
	    criteria.add(Restrictions.eq("timeFrame.description", timeFrame));
		criteria.add(Restrictions.eq("translator.id", translatorId));
		List<QuotationStandar> quoteationStandarList = criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

		return quoteationStandarList;
	}

	@Override
	public QuotationStandar getByTimeFrameCategory(String category, String timeFrame, Long translatorid) {
		Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(QuotationStandar.class);
		criteria.createAlias("timeFrame", "timeFrame");
		criteria.createAlias("category", "category");
	    criteria.add(Restrictions.eq("timeFrame.description", timeFrame));
	    criteria.add(Restrictions.eq("category.description", category));
		criteria.add(Restrictions.eq("translator.id", translatorid));

		List<QuotationStandar> quoteationList = criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

		return quoteationList.get(0);
	}

	@Override
	public List<QuotationStandar> getAllByTranslator(Long translatorid) {
		Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(QuotationStandar.class);
		criteria.add(Restrictions.eq("translator.id", translatorid));
		return criteria.list();
	}

}
