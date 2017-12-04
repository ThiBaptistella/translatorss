package au.com.translatorss.service;

import java.util.List;

import au.com.translatorss.bean.Rate;
import au.com.translatorss.bean.ServiceRequest;
import au.com.translatorss.bean.Translator;
import au.com.translatorss.bean.User;
import au.com.translatorss.bean.dto.QuoteSettingDTO;

public interface TranslatorSettingsService {

	public void saveTranslator(Translator translator);

	public void updateStandarQuote(Translator translator, QuoteSettingDTO quote, String timeFrame);
	
	public List<Translator> getTranslators();

	public void removeTransltor(long id);

	public void verifyTranslator(long id);

	public Translator getTranslatorById(long id);

	public Translator getTranslatorByEmail(String userEmail, String password);

	public Translator getTranslatorByNatiNumber(String naatiNumber);

	public void pauseTranslator(long id);
	    
    public void rateTranslator(Rate rate);
	
    public List<Rate> getAllTranslatorRates(Translator translator);
    
    public List<Translator> getTranslatorsByStatus(String status);
    
    public List<Translator> getTranslatorByStatusAndLanguage(ServiceRequest serviceRequest);

    public List<Translator> getAvailableTranslatorsWithoutQuotesSetUp(ServiceRequest serviceRequest);

    void manuallyPauseTranslator(Translator translator);

    public void manuallyUnPauseTranslator(Translator translator);

    public void manuallyReactive(Translator translator);
    
    public List<ServiceRequest> getServiceRequestList(long translatorid, String status);

	public Translator getTranslatorByEmail(String email);

	public Translator getTranslatorByUserId(User user);
}
