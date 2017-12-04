package au.com.translatorss.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Repository;

import au.com.translatorss.bean.Purchase;
import au.com.translatorss.dao.PurchaseDao;

@Repository
public class PurchaseImplDao extends GenericDaoImplementation<Purchase, Long>implements PurchaseDao {

//    @Override
//    public Purchase getPurchaseByTranslator(Long translatorId) {
//        String query = "from Purchase where translator.id = "+translatorId;
//        return (Purchase) this.getSessionFactory().getCurrentSession().createQuery(query).uniqueResult();
//    }
    
    @Override
    public Purchase getPurchaseByTranslator(Long translatorid){
    	Purchase purchase= (Purchase) getSessionFactory().getCurrentSession()
    	.createQuery("from Purchase where translator.id = "+translatorid+"and expireDate > :today")
    	.setTimestamp("today", new Date())
    	.uniqueResult();
    	return purchase;
    }
}
