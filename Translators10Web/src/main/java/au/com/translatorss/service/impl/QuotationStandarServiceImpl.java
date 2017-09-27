package au.com.translatorss.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.com.translatorss.bean.QuotationStandar;
import au.com.translatorss.bean.ServiceRequest;
import au.com.translatorss.bean.Translator;
import au.com.translatorss.bean.dto.QuoteSettingDTO;
import au.com.translatorss.dao.QuotationStandarDao;
import au.com.translatorss.service.QuotationStandarService;

@Service
@Transactional
public class QuotationStandarServiceImpl implements QuotationStandarService {

	@Autowired
	private QuotationStandarDao quotationStandarDao;

	@Override
	public void updateQuotes(String timeFrame, QuoteSettingDTO quoteDTO, Translator translator) {
		List<QuotationStandar> list = quotationStandarDao.getByTimeFrame(timeFrame, quoteDTO.getTranslatorId());
		for(QuotationStandar quotestandar:list ){
			if(quotestandar.getCategory().getDescription().equals("Birth, Death or Marriage Certificate")){
				quotestandar.setValue(new Integer(quoteDTO.getAcademicTranscript()));
				if(translator.getStatus().equals("Active")){
					quotestandar.setValid(true);
				}else{
					quotestandar.setValid(false);
				}
			}
			if(quotestandar.getCategory().getDescription().equals("Passport")){
				quotestandar.setValue(new Integer(quoteDTO.getPassport()));
				if(translator.getStatus().equals("Active")){
					quotestandar.setValid(true);
				}else{
					quotestandar.setValid(false);
				}
			}
			if(quotestandar.getCategory().getDescription().equals("Drivers License")){
				quotestandar.setValue(new Integer(quoteDTO.getDriverLic()));
				if(translator.getStatus().equals("Active")){
					quotestandar.setValid(true);
				}else{
					quotestandar.setValid(false);
				}
			}
			if(quotestandar.getCategory().getDescription().equals("Academic Records / Transcripts")){
				quotestandar.setValue(new Integer(quoteDTO.getAcademicTranscript()));
				if(translator.getStatus().equals("Active")){
					quotestandar.setValid(true);
				}else{
					quotestandar.setValid(false);
				}
			}
			quotationStandarDao.saveOrUpdate(quotestandar);
		}
	}

	@Override
	public List<QuotationStandar> getByTimeFrame(String timeFrame, Long translator) {
		List<QuotationStandar> list = quotationStandarDao.getByTimeFrame(timeFrame, translator);
		return list;
	}

	@Override
	public void saveOrUpdate(QuotationStandar quote) {
		quotationStandarDao.saveOrUpdate(quote);
	}

	@Override
	public QuotationStandar getByTimeFrameCategory(String category, String timeFrame, Long translatorid) {
		return quotationStandarDao.getByTimeFrameCategory(category,timeFrame,translatorid);
	}

	@Override
	public List<QuotationStandar> getAllByTranslator(Long translatorid) {
		// TODO Auto-generated method stub
		return quotationStandarDao.getAllByTranslator(translatorid);
	}

	@Override
	public List<Translator> getAvailableTranslatorsByLanguages(ServiceRequest mapServiceRequestFromDTO) {
		// TODO Auto-generated method stub
		return quotationStandarDao.getAvailableTranslatorsByLanguages(mapServiceRequestFromDTO);
	}

}
