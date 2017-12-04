package au.com.translatorss.dao;

import au.com.translatorss.bean.BusinessUser;
import au.com.translatorss.bean.User;

public interface BusinessUserDao extends GenericDao<BusinessUser, Long> {

	public BusinessUser getTranslatorByEmail(String userEmail, String password);

    public String getNextSequenceNumber(BusinessUser businessUser) ;
    
	public BusinessUser getBusinessUserByUserId(User user) ;

}
