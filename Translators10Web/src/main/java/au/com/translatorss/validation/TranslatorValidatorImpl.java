package au.com.translatorss.validation;

import au.com.translatorss.bean.Translator;
import au.com.translatorss.bean.User;
import au.com.translatorss.service.TranslatorSettingsService;
import au.com.translatorss.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;

@Service
@Transactional
public class TranslatorValidatorImpl implements TranslatorValidator {

    @Autowired
    private TranslatorSettingsService translatorService;

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> paramClass) {
        return Translator.class.equals(paramClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Translator translator = (Translator) target;
//        if(!userService.isAvailableEmailAndExcludeUser(translator.getUser().getEmail(), translator.getUser().getId())){
//            errors.rejectValue("email", "emailUsed");
//        }
        if(userService.isExistEmail(translator.getUser().getEmail())){
            errors.rejectValue("user.email", "emailUsed");
        }
    }
}
