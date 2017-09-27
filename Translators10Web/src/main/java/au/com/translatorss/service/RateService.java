package au.com.translatorss.service;

import au.com.translatorss.bean.Rate;
import au.com.translatorss.bean.ServiceRequest;
import au.com.translatorss.bean.Translator;

import java.util.List;

public interface RateService {

    public List<Rate> getAllTranslatorRates(Translator translator);

    public Rate getRateByServiceRequest(ServiceRequest servicerequest);
}
