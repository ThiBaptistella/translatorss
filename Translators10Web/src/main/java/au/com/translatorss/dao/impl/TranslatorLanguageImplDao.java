package au.com.translatorss.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import au.com.translatorss.bean.Language;
import au.com.translatorss.bean.TranslatorLanguage;
import au.com.translatorss.dao.TranslatorLanguageDao;

@Repository
public class TranslatorLanguageImplDao extends GenericDaoImplementation<TranslatorLanguage, Integer>
		implements TranslatorLanguageDao {

	@Override
	public List<Language> getAvailableLanguages() {
	
		Criteria query = this.getSessionFactory().getCurrentSession()
			    .createCriteria(TranslatorLanguage.class)
			    .createAlias("translator", "trans")
			    .add(Restrictions.eq("trans.status","Active"))

			    .setProjection(Projections.projectionList().add(Projections.groupProperty("language"), "language"))
			    .setResultTransformer(Transformers.aliasToBean(TranslatorLanguage.class));
		List<TranslatorLanguage> list =  query.list();
		List<Language> languageList= new ArrayList();
		
		for(TranslatorLanguage tranLang: list){
			languageList.add(tranLang.getLanguage());
		}
		return languageList;
	}

}
