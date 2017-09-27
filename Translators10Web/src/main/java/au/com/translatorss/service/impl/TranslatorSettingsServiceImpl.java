package au.com.translatorss.service.impl;

import au.com.translatorss.bean.*;
import au.com.translatorss.bean.dto.QuoteSettingDTO;
import au.com.translatorss.dao.RateDao;
import au.com.translatorss.dao.ServiceRequestCategoryDao;
import au.com.translatorss.dao.ServiceRequestDao;
import au.com.translatorss.dao.TimeFrameDao;
import au.com.translatorss.dao.TranslatorDao;
import au.com.translatorss.service.EmailService2;
import au.com.translatorss.service.PurchaseService;
import au.com.translatorss.service.QuotationStandarService;
import au.com.translatorss.service.TranslatorQuotationService;
import au.com.translatorss.service.TranslatorSettingsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Service
@Transactional
public class TranslatorSettingsServiceImpl implements TranslatorSettingsService {

	private static final Logger logger = LoggerFactory.getLogger(TranslatorSettingsServiceImpl.class);

	@Autowired
	private TranslatorDao translatorImplDao;

	@Autowired
	private TranslatorQuotationService quotationService;
	
	@Autowired
	private RateDao rateDao;
	
    @Autowired
    private EmailService2 emailService; 
    
    @Autowired
    private PurchaseService purchaseService;
    
    @Autowired
    private ServiceRequestDao serviceRequestDao;
    
    @Autowired
    private ServiceRequestCategoryDao serviceRequestCategoryDao;
    
    @Autowired
    private TimeFrameDao timeFrameDao;
    
 	@Autowired
	private QuotationStandarService quotationStandarService;
    
	public void saveTranslator(Translator translator){
		logger.info("Welcome TranslatorSettingsServiceImpl.");
		List<ServiceRequestCategory> categoryList = serviceRequestCategoryDao.getAutomaticCategories();
		List<TimeFrame> timeFrameList = timeFrameDao.getAll();
		
		for(ServiceRequestCategory category: categoryList){
			for(TimeFrame timeFrame: timeFrameList){
				QuotationStandar quotationStandar = new QuotationStandar();
				quotationStandar.setCategory(category);
				quotationStandar.setTimeFrame(timeFrame);
				quotationStandar.setValue(0);
				quotationStandar.setValid(false);
				quotationStandar.setTranslator(translator);
				quotationStandar.setUpdateDate(new Date());
				translator.getStandarValuesList().add(quotationStandar);
			}
		}
		
		this.translatorImplDao.saveOrUpdate(translator);
	}

	@Override
	public void updateStandarQuote(Translator translator, QuoteSettingDTO QuoteSetting, String timeFrame) {
 		 translatorImplDao.saveOrUpdate(translator);
 		 quotationStandarService.updateQuotes(timeFrame, QuoteSetting, translator);
	}
	
	public List<Translator> getTranslators() {
		return translatorImplDao.getAll();
	}

	public void removeTransltor(long id) {
		translatorImplDao.remove(id);
	}

	public Translator getTranslatorById(long id) {
		return translatorImplDao.getTranslatorById(id);
	}
	
	public void verifyTranslator(long id){
		Translator translator = this.getTranslatorById(id);
		translator.setStatus("Active");
		translator.getTranslatorStatusFlags().setInactiveCancelled(false);
		translator.getTranslatorStatusFlags().setInactiveRefunded(false);
		translator.getTranslatorStatusFlags().setManualyPaused(false);
		translator.getTranslatorStatusFlags().setNatyExtiryDate(false);
		translator.getTranslatorStatusFlags().setNatyVerified(true);
		translator.getTranslatorStatusFlags().setValidSuscription(true);
		translatorImplDao.saveOrUpdate(translator);
	}
	
	
	
	public Translator getTranslatorByEmail(String userEmail, String password) {
		return translatorImplDao.getTranslatorByEmail(userEmail,password);
	}

	
	public Translator getTranslatorByNatiNumber(String naatiNumber) {
		return translatorImplDao.getTranslatorByNatiNumber(naatiNumber);
	}

	@Override
	public void pauseTranslator(long id) {
		Translator translator = this.getTranslatorById(id);
		translator.setStatus("Paused");
		translatorImplDao.saveOrUpdate(translator);		
	}

	@Override
    public void manuallyPauseTranslator(Translator translator) {
        translator.setStatus("Paused");
        translator.getTranslatorStatusFlags().setManualyPaused(true);
        translatorImplDao.saveOrUpdate(translator); 
        List<Quotation> quotationList = quotationService.getQuotationListFromTranslator(translator.getId(), true);    
        for(Quotation quotation: quotationList){
            quotation.setIsValid(false);
            quotationService.saveOrUpdate(quotation);
        }
        
    }
	
    @Override
    public void rateTranslator(Rate rate) {
        rateDao.saveOrUpdate(rate);        
    }

    @Override
    public List<Rate> getAllTranslatorRates(Translator translator) {
        return rateDao.getAllTranslatorRates(translator);
    }

    @Override
    public List<Translator> getTranslatorsByStatus(String status) {
        return translatorImplDao.getTranslatorsByStatus(status);
    }

    @Override
    public void manuallyUnPauseTranslator(Translator translator) {
        //verify if translator has remaining suscription days
        //recalcular la fecha
        if(translator.getRemaininDays()>0 && translator.getNatyExpiration().after(new Date())){
            translator.setStatus("Active");
            translator.getTranslatorStatusFlags().setManualyPaused(false);
            translatorImplDao.saveOrUpdate(translator);

            List<Quotation> quoteList= quotationService.getQuotationListFromTranslator(translator.getId(),false);
            for(Quotation quotation: quoteList){
            	quotation.setIsValid(true);
            	quotationService.saveOrUpdate(quotation);
                emailService.sendEmailNewQuoteFromTranslator(quotation.getServiceRequest().getCustomer().getUser().getEmail(),quotation.getServiceRequest().getCustomer().getUser().getName(), quotation.getValue().toString(), quotation.getServiceRequest().getId().toString());
            }
        }   
    }

    @Override
    public void manuallyReactive(Translator translator) {
        Purchase purchase = purchaseService.getPurchaseByTranslator(translator.getId());
        if("Inactive".equals(translator.getStatus()) && purchase.getRemaininDays()>0 && translator.getNatyExpiration().after(new Date())){
            translator.setStatus("Active");
            translator.getTranslatorStatusFlags().setManualyPaused(false);
            translatorImplDao.saveOrUpdate(translator);
        }   
    }

    @Override
    public List<ServiceRequest> getServiceRequestList(long translatorid, String status) {
        return serviceRequestDao.getServiceRequestList(translatorid,status);
    }

	@Override
	public Translator getTranslatorByEmail(String email) {
		return translatorImplDao.getTranslatorByEmail(email);
	}

	@Override
	public List<Translator> getTranslatorByStatusAndLanguage(ServiceRequest serviceRequest) {
		return translatorImplDao.getAvailableTranslatorsByLanguages(serviceRequest);
	}

	@Override
	public List<Translator> getAvailableTranslatorsWithoutQuotesSetUp(ServiceRequest serviceRequest) {
		return translatorImplDao.getAvailableTranslatorsWithoutQuotesSetUp(serviceRequest);
	}

	@Override
	public Translator getTranslatorByUserId(User user) {
		return translatorImplDao.getTranslatorUserById(user);
	}
}
