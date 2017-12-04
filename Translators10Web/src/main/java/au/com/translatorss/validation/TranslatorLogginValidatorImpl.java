package au.com.translatorss.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import au.com.translatorss.bean.Loggin;
import au.com.translatorss.bean.Translator;
import au.com.translatorss.service.TranslatorSettingsService;


@Service
@Transactional
public class TranslatorLogginValidatorImpl implements TranslatorLogginValidator{

	@Autowired
	private TranslatorSettingsService translatorService;
	
	@Override
	public boolean supports(Class<?> paramClass) {
        return Loggin.class.equals(paramClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		  Loggin Loggin= (Loggin) target;
	      Translator translator= translatorService.getTranslatorByEmail(Loggin.getEmail(), Loggin.getPassword());
	      if(translator==null){
	            errors.rejectValue("email", "languagefrom");
	      }
	}
}
