package au.com.translatorss.service;

import au.com.translatorss.bean.Logintry;
import au.com.translatorss.bean.User;

public interface LogintryService {

	public Logintry getByUser(Long user);

	public void IncreaseTries(User user);

	public Long getTriesByUser(Long user);
	
	public void DeleteByUser(Long user);

}
