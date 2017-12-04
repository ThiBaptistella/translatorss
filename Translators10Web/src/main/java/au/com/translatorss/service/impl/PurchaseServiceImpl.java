package au.com.translatorss.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.com.translatorss.bean.Purchase;
import au.com.translatorss.dao.PurchaseDao;
import au.com.translatorss.service.PurchaseService;

@Service
@Transactional
public class PurchaseServiceImpl implements PurchaseService {

    private static final Logger logger = LoggerFactory.getLogger(PurchaseServiceImpl.class);
    
    @Autowired
    private PurchaseDao purchaseDao;
    
    @Override
    public void savePurchase(Purchase Purchase) {
        purchaseDao.saveOrUpdate(Purchase);
    }

    @Override
    public Purchase getPurchaseByTranslator(Long translatorId) {
        return purchaseDao.getPurchaseByTranslator(translatorId);
    }

}
