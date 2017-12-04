package au.com.translatorss.validation;

import au.com.translatorss.bean.dto.BusinessUserDTO;
import au.com.translatorss.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;

import au.com.translatorss.bean.Translator;
import au.com.translatorss.bean.User;
import au.com.translatorss.bean.dto.TranslatorDTO;
import au.com.translatorss.service.BusinessUserService;
import au.com.translatorss.service.TranslatorSettingsService;

@Service
@Transactional
public class TranslatorValidatorSettingsImpl implements TranslatorValidatorSettings {

	@Autowired
	private TranslatorSettingsService translatorService;

    @Autowired
    private UserService userService;
    
    @Override
	public boolean supports(Class<?> paramClass) {
		return TranslatorDTO.class.equals(paramClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		TranslatorDTO translatorDTO= (TranslatorDTO) target;
		Translator translator = (Translator)translatorService.getTranslatorById(translatorDTO.getId());

		if(!userService.isAvailableEmailAndExcludeUser(translatorDTO.getEmail(), translator.getUser().getId())){
			errors.rejectValue("email", "emailUsed");
		}
	}
}
