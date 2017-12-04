package au.com.translatorss.controller;

import java.beans.PropertyEditorSupport;
import java.util.List;

import au.com.translatorss.bean.Language;

public class LanguageEditor extends PropertyEditorSupport{

	private List<Language> languageList;

	public LanguageEditor(List<Language> languageList){
		this.languageList=languageList;
	}
	
	@Override
    public void setAsText(String id) {
		for(Language language:languageList){
			if(language.getId().toString().equals(id)){
		        this.setValue(language);
			}
		}
    }
}
