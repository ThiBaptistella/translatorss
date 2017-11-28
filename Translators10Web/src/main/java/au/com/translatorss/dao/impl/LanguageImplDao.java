package au.com.translatorss.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import au.com.translatorss.bean.Language;
import au.com.translatorss.bean.TranslatorLanguage;
import au.com.translatorss.dao.LanguageDao;

@Repository
public class LanguageImplDao extends GenericDaoImplementation<Language, Long>implements LanguageDao {

	@Override
	public List<Language> getAvailableLanguages() {
		
		return null;
	}

	public List<Language> translatorLanguageDao() {
		// TODO Auto-generated method stub
		return null;
	}

}
