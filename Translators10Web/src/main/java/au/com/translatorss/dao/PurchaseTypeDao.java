package au.com.translatorss.dao;

import au.com.translatorss.bean.PurchaseType;

public interface PurchaseTypeDao extends GenericDao<PurchaseType, Long> {

    public PurchaseType findByDescription(String string);

}
