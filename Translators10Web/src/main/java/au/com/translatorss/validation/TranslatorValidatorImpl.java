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

//        User user = translatorDTO.getUser();
//        if (user == null || StringUtils.isEmpty(user.getEmail())) {
//            errors.reject("user.name", "fieldEmpty");
//        } else if (StringUtils.isEmpty(user.getPassword())) {
//            errors.reject("user.password", "fieldEmpty");
//        } else if (translatorDTO.getId() != null && userService.isExistEmail(user.getEmail())) {
//            errors.rejectValue("naatiNumber", "naatiNumberUsed");
//        } else if (translatorService.getTranslatorByNatiNumber(translatorDTO.getNaatiNumber()) != null) {
//            errors.rejectValue("email", "emailUsed");
//        }

        if(!userService.isAvailableEmailAndExcludeUser(translator.getUser().getEmail(), translator.getUser().getId())){
            errors.rejectValue("email", "emailUsed");
        }
    }
}
