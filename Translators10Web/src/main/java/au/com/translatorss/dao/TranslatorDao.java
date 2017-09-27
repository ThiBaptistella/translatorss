package au.com.translatorss.dao;

import java.util.List;

import au.com.translatorss.bean.ServiceRequest;
import au.com.translatorss.bean.Translator;
import au.com.translatorss.bean.User;

public interface TranslatorDao extends GenericDao<Translator, Long> {

	void remove(long id);

	public Translator getTranslatorById(long id);

	public Translator getTranslatorByEmail(String userEmail, String password);

	public Translator getTranslatorByNatiNumber(String naatiNumber);

    public List<Translator> getAvailableTranslatorsByLanguages(ServiceRequest serviceRequest);

    public List<Translator> getTranslatorsByLanguages(ServiceRequest serviceRequest);

    public List<Translator> getAvailableTranslatorsWithoutQuotesSetUp(ServiceRequest serviceRequest);
 
    public List<Translator> getTranslatorsByStatus(String status);

	public Translator getTranslatorByEmail(String email);

	Translator getTranslatorUserById(User user);

}
