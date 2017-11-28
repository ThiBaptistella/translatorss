package au.com.translatorss.dao;

import java.util.List;

import au.com.translatorss.bean.QuotationStandar;
import au.com.translatorss.bean.ServiceRequest;
import au.com.translatorss.bean.Translator;

public interface QuotationStandarDao extends GenericDao<QuotationStandar, Long>{

	List<QuotationStandar> getByTimeFrame(String timeFrame, Long translatorId);

	QuotationStandar getByTimeFrameCategory(String category, String timeFrame, Long translatorid);

	List<QuotationStandar> getAllByTranslator(Long translatorid);
	
    List<Translator> getAvailableTranslatorsWithoutQuotesSetUp(ServiceRequest serviceRequest) ;
    
    List<Translator> getAvailableTranslatorsByLanguages(ServiceRequest serviceRequest) ;


}
