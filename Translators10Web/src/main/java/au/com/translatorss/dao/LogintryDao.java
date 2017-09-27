package au.com.translatorss.dao;

import au.com.translatorss.bean.Logintry;
import au.com.translatorss.bean.User;

public interface LogintryDao extends GenericDao<Logintry, Long> {

	public Logintry getByUser(Long user);

	public void IncreaseTries(User user);

	public Long getTriesByUser(Long user);

	public void DeleteByUser(Long user);

}
