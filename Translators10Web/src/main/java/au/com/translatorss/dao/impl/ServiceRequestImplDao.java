package au.com.translatorss.dao.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import au.com.translatorss.bean.BusinessUser;
import au.com.translatorss.bean.Language;
import au.com.translatorss.bean.QuotationStandar;
import au.com.translatorss.bean.ServiceRequest;
import au.com.translatorss.bean.ServiceRequestCategory;
import au.com.translatorss.bean.ServiceResponseStatus;
import au.com.translatorss.bean.Translator;
import au.com.translatorss.bean.User;
import au.com.translatorss.dao.ServiceRequestCategoryDao;
import au.com.translatorss.dao.ServiceRequestDao;
import au.com.translatorss.service.QuotationStandarService;
import au.com.translatorss.service.impl.ServiceRequestServiceImpl;

@Repository
public class ServiceRequestImplDao extends GenericDaoImplementation<ServiceRequest, Long> implements ServiceRequestDao {

	@Autowired
	private QuotationStandarService quotesStandarService;
	
	@Autowired
	private ServiceRequestCategoryDao serviceRequestCategoryDao;
	
	private static final Logger logger = LoggerFactory.getLogger(ServiceRequestImplDao.class);

	
	
    public List<ServiceRequest> getServiceRequestFromBusinessUSerId(Long id, String status) {
        try {
            String query = "from ServiceRequest serviceRequest where customerid =" + id + "and serviceRequestStatus.description='" + status + "'";
            @SuppressWarnings("unchecked")
            List<ServiceRequest> serviceRequestList = this.getSessionFactory().getCurrentSession().createQuery(query).list();
            return serviceRequestList;
        } catch (RuntimeException re) {
            throw re;
        }
    }

    
    public List<ServiceRequest> getServiceRequestFromBusinessUser(BusinessUser businessUser, List<String> statusList) {
        Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(ServiceRequest.class);
        criteria.createAlias("serviceRequestStatus", "status");
        criteria.add(Restrictions.in("status.description", statusList));
        criteria.add(Restrictions.eq("customer", businessUser));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return criteria.list();
    }
    
    public List<ServiceRequest> getServiceRequestFromTranslator(Translator translator, List<String> statusList) {
        Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(ServiceRequest.class);
        criteria.createAlias("serviceRequestStatus", "status");
        criteria.add(Restrictions.in("status.description", statusList));
        criteria.add(Restrictions.eq("translator", translator));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return criteria.list();
    }
    
    
    public List<ServiceRequest> getAll() {
        try {
            String query = "from ServiceRequest";

            @SuppressWarnings("unchecked")
            List<ServiceRequest> serviceRequestList = this.getSessionFactory().getCurrentSession().createQuery(query).list();
            return serviceRequestList;
        } catch (RuntimeException re) {
            throw re;
        }
    }

    public List<ServiceRequest> getServiceRequestFromTranslatorId(Long id, String status) {
        try {
            String query =
                    "from ServiceRequest serviceRequest where translatorid =" + id + "and serviceRequestStatus.description='" + status + "'";

            @SuppressWarnings("unchecked")
            List<ServiceRequest> serviceRequestList= this.getSessionFactory().getCurrentSession().createQuery(query).list();
            return serviceRequestList;
        } catch (RuntimeException re) {
            throw re;
        }
    }

    /*
    public List<ServiceRequest> getStandarsServiceRequestAvailableForTransalator(Translator translatorloged){
        String languageSet = "('";
        for (Language language : translatorloged.getLanguageList()) {
            languageSet = languageSet.concat(language.getDescription() + "','");
        }
        languageSet = languageSet.concat(")");
        languageSet = languageSet.replace("',')", "')");

        List<QuotationStandar>  quoteList=quotesStandarService.getAllByTranslator(translatorloged.getId());
        String quoteSet = "('";
        for(QuotationStandar quote: quoteList){
        	quoteSet = quoteSet.concat(quote.getCategory().getDescription()+"','");
        }
        quoteSet = quoteSet.concat(")");
        quoteSet = quoteSet.replace("',')", "')");
        
        List<ServiceRequest> newServiceRequestList = new ArrayList<ServiceRequest>();
        try {
            String query = "from ServiceRequest serviceRequest where serviceRequestStatus.description in ('Unquoted','Quoted')"; // first filter by state                                                                                      
            query = query.concat("and languagefrom in " + languageSet); // second filter by language      
            query = query.concat("and serviceRequestCategory.description in "+quoteSet);
            
            @SuppressWarnings("unchecked")
            List<ServiceRequest> serviceRequestList= this.getSessionFactory().getCurrentSession().createQuery(query).list();
        	} catch (RuntimeException re) {
	            throw re;
	        }
	        return newServiceRequestList;
    }*/

    public List<ServiceRequest> getStandarsServiceRequestAvailableForTransalator(Translator translator){
    	List<String> languageList= new ArrayList<String>();
    	languageList.add(translator.getLanguageList().get(0).getDescription());
    	Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(ServiceRequest.class);
        criteria.add(Restrictions.in("languagefrom",languageList));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.createAlias("serviceRequestStatus", "serviceRequestStatus");
        Criterion rest1= Restrictions.and(Restrictions.eq("serviceRequestStatus.description", "Unquoted"));
	    Criterion rest2= Restrictions.and(Restrictions.eq("serviceRequestStatus.description", "Quoted"));
	    criteria.add(Restrictions.or(rest1, rest2));
        
    	List<ServiceRequestCategory> categoriesToInclude= serviceRequestCategoryDao.getAutomaticCategories();
        criteria.add(Restrictions.in("serviceRequestCategory", categoriesToInclude));
    	return criteria.list();
    }
    
    /*This method doesnt filter if the sr has a quote from the translator*/
	@Override
	public List<ServiceRequest> getStandarsServiceRequestAvailableForTransalator(Translator translator,String timeFrame) {
		List<String> languageList= new ArrayList<String>();
    	languageList.add(translator.getLanguageList().get(0).getDescription());
    	Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(ServiceRequest.class);
        criteria.add(Restrictions.in("languagefrom",languageList));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.createAlias("serviceRequestStatus", "serviceRequestStatus");
		criteria.createAlias("timeFrame", "timeFrame");
		criteria.add(Restrictions.eq("timeFrame.description", timeFrame));
		
        Criterion rest1= Restrictions.and(Restrictions.eq("serviceRequestStatus.description", "Unquoted"));
	    Criterion rest2= Restrictions.and(Restrictions.eq("serviceRequestStatus.description", "Quoted"));
	    criteria.add(Restrictions.or(rest1, rest2));
        
    	List<ServiceRequestCategory> categoriesToInclude= serviceRequestCategoryDao.getAutomaticCategories();
        criteria.add(Restrictions.in("serviceRequestCategory", categoriesToInclude));
    	return criteria.list();
	}
    
    
    public List<ServiceRequest> getNotStandarServiceRequestAvailableForTranslator(Translator translator){
    	List<String> languageList= new ArrayList<String>();
    	languageList.add(translator.getLanguageList().get(0).getDescription());
    	Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(ServiceRequest.class);
        criteria.add(Restrictions.in("languagefrom",languageList));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.createAlias("serviceRequestStatus", "serviceRequestStatus");
        Criterion rest1= Restrictions.and(Restrictions.eq("serviceRequestStatus.description", "Unquoted"));
	    Criterion rest2= Restrictions.and(Restrictions.eq("serviceRequestStatus.description", "Quoted"));
	    criteria.add(Restrictions.or(rest1, rest2));
        
    	List<ServiceRequestCategory> categoriesToExclude= serviceRequestCategoryDao.getAutomaticCategories();
        criteria.add(Restrictions.not(Restrictions.in("serviceRequestCategory", categoriesToExclude)));
    	return criteria.list();
    }

    @Override
    public List<ServiceRequest> getServiceRequestByState(String status) {
        try {
            String query = "from ServiceRequest serviceRequest where serviceRequestStatus.description='" + status + "'";

            @SuppressWarnings("unchecked")
            List<ServiceRequest> serviceRequestList = this.getSessionFactory().getCurrentSession().createQuery(query).list();
            return serviceRequestList;
        } catch (RuntimeException re) {
            throw re;
        }
    }

    @Override
    public List<ServiceRequest> getServiceRequestList(long translatorid, String status) {
        try {
            String query = "from ServiceRequest serviceRequest where serviceRequestStatus.description='" + status + "' and translator.id="+ translatorid;

            @SuppressWarnings("unchecked")
            List<ServiceRequest> serviceRequestList = this.getSessionFactory().getCurrentSession().createQuery(query).list();
            return serviceRequestList;
        } catch (RuntimeException re) {
            throw re;
        }
    }

    @Override
    public void save(ServiceRequest serviceRequest) {
        Serializable save = currentSession().save(serviceRequest);
        currentSession().flush();
        serviceRequest.setId((Long)save);
    }



}
