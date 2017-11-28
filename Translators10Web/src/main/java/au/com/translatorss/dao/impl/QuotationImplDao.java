package au.com.translatorss.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import au.com.translatorss.bean.Quotation;
import au.com.translatorss.bean.QuotationStandar;
import au.com.translatorss.bean.ServiceRequest;
import au.com.translatorss.bean.ServiceRequestCategory;
import au.com.translatorss.bean.ServiceRequestStatus;
import au.com.translatorss.bean.Translator;
import au.com.translatorss.dao.QuotationDao;
import au.com.translatorss.dao.ServiceRequestStatusDao;

@Repository
public class QuotationImplDao extends GenericDaoImplementation<Quotation, Long>implements QuotationDao {

   
	@Autowired
	private ServiceRequestStatusDao serviceRequestStatusDao;
	
    @SuppressWarnings("unchecked")
    @Override
    public List<Quotation> getListByTranslatorId(Long id, String timeFrame) {
        String query = "from Quotation where translator.id = "+id+" and serviceRequest.timeFrame.description='"+timeFrame+"'";
        return this.getSessionFactory().getCurrentSession().createQuery(query).list();
    }

    /*
    public List<Quotation> getListByTranslatorId(Long translatorid, boolean status){
        Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(Quotation.class);
		criteria.createAlias("serviceRequest", "serviceRequest");
		criteria.createAlias("serviceRequest.serviceRequestStatus", "serviceRequestStatus");
	    criteria.add(Restrictions.eq("translator.id", translatorid));
	    criteria.add(Restrictions.eq("isValid", status));
	    Disjunction or = Restrictions.disjunction();
	    or.add(Restrictions.eq("serviceRequestStatus.description","Quoted"));
	    or.add(Restrictions.eq("serviceRequestStatus.description","Unquoted"));
		criteria.add(or);
	    List<Quotation> quotationList = criteria.list();
		return quotationList;
    }*/

    public List<Quotation> getValidQuotesFromSRQuoted(Long translatorid){
        Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(Quotation.class);
		criteria.createAlias("serviceRequest", "serviceRequest");
		criteria.createAlias("serviceRequest.serviceRequestStatus", "serviceRequestStatus");
	    criteria.add(Restrictions.eq("translator.id", translatorid));
	    criteria.add(Restrictions.eq("isValid", true));
	    criteria.add(Restrictions.eq("serviceRequestStatus.description","Quoted"));
	    List<Quotation> quotationList = criteria.list();
		return quotationList;
    }

    public List<Quotation> getInValidQuotesFromSRUnquotedOrQuoted(Long translatorid){
        Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(Quotation.class);
		criteria.createAlias("serviceRequest", "serviceRequest");
		criteria.createAlias("serviceRequest.serviceRequestStatus", "serviceRequestStatus");
	    criteria.add(Restrictions.eq("translator.id", translatorid));
	    criteria.add(Restrictions.eq("isValid", false));
	    Disjunction or = Restrictions.disjunction();
	    or.add(Restrictions.eq("serviceRequestStatus.description","Quoted"));
	    or.add(Restrictions.eq("serviceRequestStatus.description","Unquoted"));
		criteria.add(or);
	    List<Quotation> quotationList = criteria.list();
		return quotationList;
    }

    @Override
    public List<ServiceRequest> getServiceRequestQuotedFromTranslator(Translator translatorloged, String status) {    
        List<ServiceRequest> serviceRequestList= new ArrayList<ServiceRequest>();      
        String query = "from Quotation where translator.id = "+translatorloged.getId()+"and isValid=true and isAutomatic=true and serviceRequest.serviceRequestStatus.id='2'";

        List<Quotation> quotes =  this.getSessionFactory().getCurrentSession().createQuery(query).list();
        for(Quotation quote: quotes){
        	serviceRequestList.add(quote.getServiceRequest());
        }
        return serviceRequestList;
    }

    @Override
    public Quotation getQuotation(Long serviceRequestId, Long translatorId) {
        String query = "from Quotation where translator.id = "+translatorId+" and serviceRequest.id="+serviceRequestId;
        @SuppressWarnings("unchecked")
        Quotation quote = (Quotation) this.getSessionFactory().getCurrentSession().createQuery(query).uniqueResult();
        return quote;
    }

	@Override
	public List<Quotation> getStandarQuotes(long translatorid, String timeFrame) {
        //String query = "from Quotation where translator.id = "+translatorid+" and isAutomatic=true and serviceRequest.timeFrame.description='"+timeFrame+"' and serviceRequest.serviceRequestStatus.description='Quoted'";
		//List<Quotation> quotes =  this.getSessionFactory().getCurrentSession().createQuery(query).list();
        //return quotes;
		Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(Quotation.class);
		criteria.createAlias("serviceRequest", "serviceRequest");
		criteria.createAlias("serviceRequest.timeFrame", "timeFrame");
		criteria.createAlias("serviceRequest.serviceRequestStatus", "serviceRequestStatus");

	    criteria.add(Restrictions.eq("translator.id", translatorid));
	    criteria.add(Restrictions.eq("timeFrame.description", timeFrame));
	    criteria.add(Restrictions.eq("isAutomatic", true));

	    Disjunction or = Restrictions.disjunction();
	    or.add(Restrictions.eq("serviceRequestStatus.description","Quoted"));
	    or.add(Restrictions.eq("serviceRequestStatus.description","Unquoted"));
	    
		criteria.add(or);
		List<Quotation> quotationList = criteria.list();
		return quotationList;
	}

	@Override
	public List<Quotation> getQuoteListByState(String state, Long id) {
		String query = "from Quotation where serviceRequest.serviceRequestStatus.description='"+state+"' and serviceRequest.customer.id='"+id+"' and isValid=true";
        List<Quotation> quotes =  this.getSessionFactory().getCurrentSession().createQuery(query).list();
        return quotes;
	}

	@Override
	public List<Quotation> getQuotesFromServiceRequestQuotedAndUnquoted() {
		Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(Quotation.class);
		criteria.createAlias("serviceRequest", "serviceRequest");
		criteria.createAlias("serviceRequest.serviceRequestStatus", "serviceRequestStatus");
		
        LogicalExpression or = Restrictions.or(Restrictions.eq("serviceRequestStatus.description", "Quoted"), Restrictions.eq("serviceRequestStatus.description", "UnQuoted"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        criteria.add(Restrictions.and(or));    
		return criteria.list();
	}

	
	public void saveOrUpdateQuotation(Quotation quotation) {	
		saveOrUpdate(quotation);
	}
	
}
