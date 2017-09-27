package au.com.translatorss.dao;

import java.util.List;

import au.com.translatorss.bean.Language;
import au.com.translatorss.bean.TranslatorLanguage;

public interface TranslatorLanguageDao extends GenericDao<TranslatorLanguage, Integer> {

	public List<Language> getAvailableLanguages();

}
