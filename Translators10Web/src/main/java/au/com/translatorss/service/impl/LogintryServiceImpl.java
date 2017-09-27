package au.com.translatorss.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import au.com.translatorss.bean.Logintry;
import au.com.translatorss.bean.User;
import au.com.translatorss.dao.LogintryDao;
import au.com.translatorss.service.LogintryService;

@Service
@Transactional
public class LogintryServiceImpl implements LogintryService {

	@Autowired
	private LogintryDao logintryDao;

	public Logintry getByUser(Long user) {
		return logintryDao.getByUser(user);
	}

	public void IncreaseTries(User user) {
		logintryDao.IncreaseTries(user);
	}

	public Long getTriesByUser(Long user) {
		return logintryDao.getTriesByUser(user);
	}

	public void DeleteByUser(Long user) {
		logintryDao.DeleteByUser(user);
	}

}
