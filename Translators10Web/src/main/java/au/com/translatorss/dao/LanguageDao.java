package au.com.translatorss.dao;

import java.util.List;

import au.com.translatorss.bean.Language;
import au.com.translatorss.bean.TranslatorLanguage;

public interface LanguageDao extends GenericDao<Language, Long> {
	
	public List<Language> getAvailableLanguages();
	
}
