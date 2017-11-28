package au.com.translatorss.dao;

import au.com.translatorss.bean.Purchase;

public interface PurchaseDao extends GenericDao<Purchase, Long> {

   public Purchase getPurchaseByTranslator(Long translatorId);

}
