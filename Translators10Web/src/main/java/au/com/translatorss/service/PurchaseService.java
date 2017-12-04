package au.com.translatorss.service;

import au.com.translatorss.bean.Purchase;

public interface PurchaseService {

    public void savePurchase(Purchase Purchase);

    public Purchase getPurchaseByTranslator(Long translatorId);
}
