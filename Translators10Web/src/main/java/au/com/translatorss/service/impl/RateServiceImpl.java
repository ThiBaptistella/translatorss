package au.com.translatorss.service.impl;

import au.com.translatorss.bean.Rate;
import au.com.translatorss.bean.ServiceRequest;
import au.com.translatorss.bean.Translator;
import au.com.translatorss.dao.RateDao;
import au.com.translatorss.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RateServiceImpl implements RateService{

    @Autowired
    private RateDao rateDao;

    public List<Rate> getAllTranslatorRates(Translator translator){
        return null;
    }

    public Rate getRateByServiceRequest(ServiceRequest servicerequest){
        return rateDao.getRateByServiceRequest(servicerequest);
    }
}