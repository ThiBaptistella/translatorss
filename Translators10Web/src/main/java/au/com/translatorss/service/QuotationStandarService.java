package au.com.translatorss.service;

import java.util.List;

import au.com.translatorss.bean.QuotationStandar;
import au.com.translatorss.bean.ServiceRequest;
import au.com.translatorss.bean.Translator;
import au.com.translatorss.bean.dto.QuoteSettingDTO;

public interface QuotationStandarService {

	public void updateQuotes(String timeFrame, QuoteSettingDTO quoteDTO, Translator translator);

	public List<QuotationStandar> getByTimeFrame(String timeFrame, Long  translatorid);

	public QuotationStandar getByTimeFrameCategory(String category,String timeFrame, Long  translatorid);

	public List<QuotationStandar> getAllByTranslator(Long  translatorid);

	public void saveOrUpdate(QuotationStandar quote);

	public List<Translator> getAvailableTranslatorsByLanguages(ServiceRequest mapServiceRequestFromDTO);
}
