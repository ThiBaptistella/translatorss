package au.com.translatorss.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import au.com.translatorss.bean.Quotation;
import au.com.translatorss.bean.ServiceRequest;
import au.com.translatorss.bean.Translator;

public interface QuotationDao extends GenericDao<Quotation, Long> {

    public List<Quotation> getListByTranslatorId(Long id, String timeFrame);
    
    //public List<Quotation> getListByTranslatorId(Long id, boolean status);

    public List<Quotation> getValidQuotesFromSRQuoted(Long translatorid);

    public List<Quotation> getInValidQuotesFromSRUnquotedOrQuoted(Long translatorid);
    
    public List<ServiceRequest> getServiceRequestQuotedFromTranslator(Translator translatorloged, String status);
    
    public Quotation getQuotation(Long serviceRequestId, Long translatorId);

	public List<Quotation> getStandarQuotes(long translatorid, String timeFrame);

	public List<Quotation> getQuoteListByState(String state, Long id);

	public List<Quotation> getQuotesFromServiceRequestQuotedAndUnquoted();

	public void saveOrUpdateQuotation(Quotation quotation) ;
}
