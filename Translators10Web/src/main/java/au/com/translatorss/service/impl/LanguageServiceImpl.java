package au.com.translatorss.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.com.translatorss.bean.Language;
import au.com.translatorss.bean.TranslatorLanguage;
import au.com.translatorss.dao.TranslatorLanguageDao;
import au.com.translatorss.dao.impl.LanguageImplDao;
import au.com.translatorss.service.LanguageService;

@Service
@Transactional
public class LanguageServiceImpl implements LanguageService {

	@Autowired
	private LanguageImplDao languageImplDao;

	@Autowired
	private TranslatorLanguageDao translatorLanguageDao;

	public List<String> getAllLanguagesNames() {
		List<String> languagesNames = new ArrayList<String>();
		for (Language language : getAllLanguages()) {
			languagesNames.add(language.getDescription());
		}
		return languagesNames;
	}
	
	public Language find(Long key) {
		return languageImplDao.find(key);
	}

	public List<Language> getAvailableLanguages(){
		return translatorLanguageDao.getAvailableLanguages();
	}

	@Override
	public List<Language> getAllLanguages() {
		return languageImplDao.getAll();
	}

	public void save(Language language){
		this.languageImplDao.saveOrUpdate(language);
	}

}
