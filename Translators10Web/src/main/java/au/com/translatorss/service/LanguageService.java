package au.com.translatorss.service;

import java.util.List;

import au.com.translatorss.bean.Language;

public interface LanguageService {

	public List<Language> getAllLanguages();
	
	public List<String> getAllLanguagesNames() ;
	
	public List<Language> getAvailableLanguages();
	
	public Language find(Long key) ;
	
}
