package au.com.translatorss.dao;

import java.util.List;

import au.com.translatorss.bean.Rate;
import au.com.translatorss.bean.ServiceRequest;
import au.com.translatorss.bean.Translator;

public interface RateDao extends GenericDao<Rate, Long> {

    public List<Rate> getAllTranslatorRates(Translator translator);

    public Rate getRateByServiceRequest(ServiceRequest servicerequest);
}
