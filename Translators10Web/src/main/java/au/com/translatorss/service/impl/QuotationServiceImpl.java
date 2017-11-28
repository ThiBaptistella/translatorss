package au.com.translatorss.service.impl;

import au.com.translatorss.bean.Quotation;
import au.com.translatorss.bean.QuotationStandar;
import au.com.translatorss.bean.Rate;
import au.com.translatorss.bean.ServiceRequest;
import au.com.translatorss.bean.Translator;
import au.com.translatorss.bean.dto.QuoteSettingDTO;
import au.com.translatorss.bean.dto.ServiceRequestDTO;
import au.com.translatorss.bean.dto.TranslatorQuotationDTO;
import au.com.translatorss.dao.*;
import au.com.translatorss.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class QuotationServiceImpl implements TranslatorQuotationService {

    @Autowired
    private QuotationDao quotationDao;

    /*@Autowired
    private QuotationService quotationService;*/
    
    @Autowired
    private TranslatorDao translatorImplDao;

    @Autowired
    private EmailService2 emailService2;

    @Autowired
    private EmailService emailService;
    
    @Autowired
    private CustomerServiceRequestService serviceRequestService;
    
    @Autowired
    private ServiceRequestStatusDao serviceRequestStatusDao;
    
    @Autowired
    private ServiceRequestConfigurationService serviceRequestConfigurationService;
    
    @Autowired
    private TimeFrameDao timeFrameDao;

    @Autowired
    private ServiceRequestCategoryDao serviceRequestCategoryDao;
    
    @Autowired
    private TranslatorSettingsService translatorService;
    
    @Autowired
	private QuotationStandarService quotesStandarService;
    
    @Override
    public void saveOrUpdate(Quotation quotation) {
        quotationDao.saveOrUpdateQuotation(quotation);
    }

    @Override
    public void add(Quotation entity) {
    }

    @Override
    public void update(Quotation quotationUpdate) {
        Quotation quotation = quotationDao.getQuotation(quotationUpdate.getServiceRequest().getId(), quotationUpdate.getTranslator().getId());
        if(quotation != null){
        	if(quotationUpdate.getValue().longValue()!=0){
        		quotation.setIsValid(true);
                quotation.setValue(quotationUpdate.getValue());
        	}else{
        		quotation.setIsValid(false);
        	}
        	saveOrUpdate(quotation);
        }else{
        	quotationDao.saveOrUpdate(quotationUpdate);
        }
    }

    @Override
    public void remove(Quotation entity) throws Exception {
    }

    @Override
    public Quotation find(long key) {
        return quotationDao.find(key);
    }
    
    @Override
	public void disableStandarQuotations(Long translatorid,String timeFrame) {

    	List<Quotation> quotationList = quotationDao.getStandarQuotes(translatorid, timeFrame);
        for (Quotation quotation : quotationList) {
        	 quotation.setIsValid(false);
        	 saveOrUpdate(quotation);
             //generateNewQuotations(translator);
        }

        List<QuotationStandar> list =quotesStandarService.getByTimeFrame(timeFrame, translatorid);
        for(QuotationStandar quote: list){
        	quote.setValid(false);
        	quotesStandarService.saveOrUpdate(quote);
        }
	}
    
    @Override
	public void enableStandarQuotations(Long translatorid,String timeFrame) {
        System.out.println("enable");
    	List<Quotation> quotationList = quotationDao.getStandarQuotes(translatorid, timeFrame);
        for (Quotation quotation : quotationList) {
	       	 quotation.setIsValid(true);
	       	 saveOrUpdate(quotation);
        }
        
        List<QuotationStandar> list =quotesStandarService.getByTimeFrame(timeFrame, translatorid);
        for(QuotationStandar quote: list){
        	quote.setValid(true);
        	quotesStandarService.saveOrUpdate(quote);
        }
        
      	Translator translator = this.translatorService.getTranslatorById(translatorid);
        generateNewQuotations(translator,timeFrame);
	}

    
    @Override
    public void updateQuotations(Translator translator,QuoteSettingDTO quoteSetting, String timeFrame) {
     //   update current quotations
        List<Quotation> quotationList = quotationDao.getListByTranslatorId(translator.getId(), timeFrame);
        for (Quotation quotation : quotationList) {
            ServiceRequest serviceRequest = quotation.getServiceRequest();
            if (serviceRequest.getServiceRequestCategory().getDescription().equals("Drivers License")) {
                 quotation.setValue(new BigDecimal(quoteSetting.getDriverLic()));
                 quotation.setIsAutomatic(true);
            } else if (serviceRequest.getServiceRequestCategory().getDescription().equals("Marriage Certificate")) {
                 quotation.setValue(new BigDecimal(quoteSetting.getMarriageCertificate()));
                 quotation.setIsAutomatic(true);
            } else if (serviceRequest.getServiceRequestCategory().getDescription().equals("Birth Certificate")) {
                 quotation.setValue(new BigDecimal(quoteSetting.getBirthCertificate()));
                 quotation.setIsAutomatic(true);
            } else if (serviceRequest.getServiceRequestCategory().getDescription().equals("Passport")){  
                 quotation.setValue(new BigDecimal(quoteSetting.getPassport()));
                 quotation.setIsAutomatic(true);
            }
            saveOrUpdate(quotation);
        }
        generateNewQuotations(translator,timeFrame);
    }

    public void generateNewQuotations(Translator translator,String timeFrame) {
        //Quiero todos los serviceRequest que en sus quotations no este este translator
    	List<QuotationStandar> quotes=quotesStandarService.getAllByTranslator(translator.getId());
        List<ServiceRequest> serviceRequestList = serviceRequestService.getServiceRequestStandartWithoutQuote(translator,timeFrame);
        for(ServiceRequest serviceRequest :serviceRequestList){
            Quotation quotation = new Quotation();
            quotation.setTranslator(translator);
            quotation.setServiceRequest(serviceRequest);
            quotation.setIsValid(true);
            quotation.setIsAutomatic(true);
            for(QuotationStandar quote:quotes){
            	if( quote.getTimeFrame().getDescription().equals(serviceRequest.getTimeFrame().getDescription()) &&quote.getCategory().getDescription().equals(serviceRequest.getServiceRequestCategory().getDescription())){
                  quotation.setValue(new BigDecimal(quote.getValue()));
            	}
            }
            this.emailService2.sendEmailNewQuoteFromTranslator(serviceRequest.getCustomer().getUser().getEmail(), serviceRequest.getCustomer().getFullname(), translator.getFullname(), quotation.getValue().toString(), serviceRequest.getId().toString());
            saveOrUpdate(quotation);   
        }
    }

    @Override
    public List<Quotation> getQuotationList(ServiceRequest serviceRequest) {
        List<Quotation> quotationList = new ArrayList<Quotation>();
        if (serviceRequest.getQuotationList().isEmpty()) {
            return generateQuotations(serviceRequest);
        }
        for(Quotation quotation: serviceRequest.getQuotationList()){
        	Quotation realQuote = quotationDao.find(quotation.getId());
            if(realQuote.getIsValid()){
                quotationList.add(realQuote);
            }
        }
        return quotationList;
    }

    private List<Quotation> generateQuotations(ServiceRequest serviceRequest) {
        List<Quotation> quotations = new ArrayList<Quotation>();
        if (serviceRequest.IsStandar()) {
            List<Translator> translatorList = quotesStandarService.getAvailableTranslatorsByLanguages(serviceRequest);
            for (Translator translator : translatorList) {
                Quotation quotation = new Quotation();
                quotation.setServiceRequest(serviceRequest);
                quotation.setTranslator(translator);
                quotation.setIsValid(true);
                quotation.setIsAutomatic(true);
                quotation.setValue(getQuotation(translator, serviceRequest.getServiceRequestCategory().getDescription(),serviceRequest.getTimeFrame().getDescription()));
                saveOrUpdate(quotation);
                quotations.add(quotation);
            }
        }
        
        if(quotations.size()>0){
            serviceRequest.setServiceRequestStatus(serviceRequestStatusDao.findByDescription("Quoted"));
            serviceRequestService.update(serviceRequest);
        }

        int minimunMarket = serviceRequestConfigurationService.getServiceRequestMinimunMarket();
        if (quotations.size() < minimunMarket || quotations.isEmpty()) {
            List<Translator> translatorList = translatorImplDao.getTranslatorsByLanguages(serviceRequest);
            for (Translator translator : translatorList) {
                emailService.sendMessage("Job proposal", "You have a job proposal that fit your skill",
                        translator.getUser().getEmail());
            }
        }
        return quotations;
    }

    private BigDecimal getQuotation(Translator translator, String serviceRequestCategory, String timeFrame) {
    	QuotationStandar standarQuote = quotesStandarService.getByTimeFrameCategory(serviceRequestCategory,timeFrame, translator.getId());
    	return BigDecimal.valueOf(standarQuote.getValue());
    }

    @Override
    public List<Quotation> getValidQuotesFromSRQuoted(Long translatorid) {
       // return quotationDao.getListByTranslatorId(translatorid, status);
    	return quotationDao.getValidQuotesFromSRQuoted(translatorid);
    }

    @Override
    public List<Quotation> getInValidQuotesFromSRUnquotedOrQuoted(Long translatorid){
    	return quotationDao.getInValidQuotesFromSRUnquotedOrQuoted(translatorid);

    }

    
	@Override
	public List<TranslatorQuotationDTO> getQuotationArrayDTO(ServiceRequestDTO serviceRequestDTO) throws IllegalStateException, IOException {
		 List<Translator> translatorList = quotesStandarService.getAvailableTranslatorsByLanguages(mapServiceRequestFromDTO(serviceRequestDTO));
	  	 List<TranslatorQuotationDTO> list = new ArrayList<TranslatorQuotationDTO>();
	  	 int quotationIdSecuense=1;
	     for(Translator translator:translatorList){
	              TranslatorQuotationDTO dto = new TranslatorQuotationDTO();
	              dto.setQuotationId(quotationIdSecuense);
	              dto.setNaati(true);
	              dto.setName(translator.getUser().getName());
	              dto.setTranslatorId(translator.getId());
	              BigDecimal quote = getQuotation(translator, serviceRequestDTO.getServiceRequestCategory(), serviceRequestDTO.getTimeFrame());
				  if(quote!=null){
					  dto.setQuote(quote.toString());
		              list.add(dto);
		              populateMediaRating(dto,translator);    
		              quotationIdSecuense++;
				  }
	        }
		return list;
	}


    public void populateMediaRating(TranslatorQuotationDTO dto, Translator translator) {
        List<Rate> rateList = translatorService.getAllTranslatorRates(translator);
        int rateSize = rateList.size();
        if (rateSize == 0) {
            rateSize = 1;
        }
        int quality = 0;
        int serviceDescribed = 0;
        int time = 0;
        for (Rate rate : rateList) {
            quality += rate.getQuality();
            serviceDescribed += rate.getServiceAsDescribed();
            time += rate.getTimeDelivery();
        }
        dto.setTimeDelivery(time / rateSize);
        dto.setServiceDescribed(serviceDescribed / rateSize);
        dto.setQuality(quality / rateSize);
    }



    private ServiceRequest mapServiceRequestFromDTO(ServiceRequestDTO serviceRequestDTO)throws IllegalStateException, IOException {
        ServiceRequest serviceRequest = new ServiceRequest();
        Date requestCreationDate = new Date();
        serviceRequest.setCreationDate(requestCreationDate);
        serviceRequest.setDescription(serviceRequestDTO.getDescription());
        serviceRequest.setHardcopy(serviceRequestDTO.getHardcopy());
        String languageFrom = serviceRequestDTO.getLanguagefrom().trim();
        serviceRequest.setLanguagefrom(languageFrom);
        serviceRequest.setLanguageTo("English");
        serviceRequest.setServiceRequestCategory(serviceRequestCategoryDao.findByDescription(serviceRequestDTO.getServiceRequestCategory()));
        serviceRequest.setServiceRequestStatus(serviceRequestStatusDao.findByDescription("Unquoted"));
        serviceRequest.setTimeFrame(timeFrameDao.findByDescription(serviceRequestDTO.getTimeFrame()));
        serviceRequest.setFinishQuoteSelection(getFinishDateSelectionQuote(serviceRequestDTO.getTimeFrame()));
        serviceRequest.setFinishDate(new Date()); //will be completed after the customer selects the candidate
        return serviceRequest;
    }
	
    public Date getFinishDateSelectionQuote(String timeFrame) {
        Date finishDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(finishDate);
        int percentajeToApplyBefore = serviceRequestConfigurationService.getServiceRequestHoursLeft();//Gives me a percentage
        float timerBeforeSelect= 0;

        if ("Urgent".equals(timeFrame)) {//24hs--8
             timerBeforeSelect = (float) (percentajeToApplyBefore*24)/100;
        }
        if ("Medium".equals(timeFrame)) {//72hs--24
            timerBeforeSelect = (float) (percentajeToApplyBefore*72)/100;
        }
        if ("Relaxed".equals(timeFrame)) {////167--56
            timerBeforeSelect = (float) (percentajeToApplyBefore*167)/100;
        }
        int hourpart= (int) Math.abs(timerBeforeSelect);
        int minutespart =  (int) Math.abs((timerBeforeSelect-hourpart)*100);
        c.add(Calendar.HOUR, hourpart);
        c.add(Calendar.MINUTE, minutespart);
        finishDate = c.getTime();
        return finishDate;
    }

	@Override
	public Quotation getQuoteFromServiceRequestAndTranslator(Long servicerequestid, Long translatorid) {
		return quotationDao.getQuotation(servicerequestid, translatorid);
	}

	@Override
	public List<Quotation> getQuoteListByState(String state, Long id) {
		return quotationDao.getQuoteListByState(state,id);
	}

	@Override
	public List<Quotation> getQuotesFromServiceRequestQuotedAndUnquoted() {
		return quotationDao.getQuotesFromServiceRequestQuotedAndUnquoted();
	}

	@Override
	public List<Quotation> getQuotationListFromTranslator(long translatorid, boolean status) {
		// TODO Auto-generated method stub
		return null;
	}

  
}
