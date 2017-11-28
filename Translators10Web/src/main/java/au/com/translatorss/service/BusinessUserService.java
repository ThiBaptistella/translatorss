package au.com.translatorss.service;

import java.util.List;

import au.com.translatorss.bean.BusinessUser;
import au.com.translatorss.bean.User;

public interface BusinessUserService{

	public void saveOrUpdate(BusinessUser entity);

	public List<BusinessUser> getAll();

	public BusinessUser get(Long id);

	public	void add(BusinessUser entity);

	public	void update(BusinessUser entity);

	public	void remove(BusinessUser entity);
	
	public BusinessUser getUserByEmail(String userEmail, String password);
	
	public BusinessUser getBusinessUSerByUserId(User user) ;


}
