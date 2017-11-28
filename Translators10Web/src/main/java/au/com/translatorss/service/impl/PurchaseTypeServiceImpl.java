package au.com.translatorss.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.com.translatorss.bean.PurchaseType;
import au.com.translatorss.dao.PurchaseTypeDao;
import au.com.translatorss.service.PurchaseTypeService;

@Service
@Transactional
public class PurchaseTypeServiceImpl implements PurchaseTypeService {

    private static final Logger logger = LoggerFactory.getLogger(PurchaseTypeServiceImpl.class);

    @Autowired
    private PurchaseTypeDao purchaseTypeDao;

    @Override
    public PurchaseType findById(long  id) {
        return purchaseTypeDao.find(id);
    }
}
