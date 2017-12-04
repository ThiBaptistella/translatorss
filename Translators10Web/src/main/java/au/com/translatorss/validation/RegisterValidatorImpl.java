package au.com.translatorss.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import au.com.translatorss.bean.BusinessUser;
import au.com.translatorss.bean.Register;
import au.com.translatorss.bean.Translator;
import au.com.translatorss.service.BusinessUserService;
import au.com.translatorss.service.TranslatorSettingsService;

@Service
@Transactional
public class RegisterValidatorImpl implements RegisterValidator {

	@Autowired
	private TranslatorSettingsService translatorService;
	
	@Autowired
	private BusinessUserService businessUserService;
	
	@Override
	public boolean supports(Class<?> paramClass) {
        return Register.class.equals(paramClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "emptyEmail");
        Register register= (Register) target;
        Translator translator2= translatorService.getTranslatorByEmail(register.getEmail(), register.getPassword());
        BusinessUser businessUser= businessUserService.getUserByEmail(register.getEmail(), register.getPassword());
        
        if(translator2!=null || businessUser!=null){      			
            errors.rejectValue("email", "existingEmail");
      	}else{
      		if(!register.getPassword().equals(register.getRepassword())){
                errors.rejectValue("password", "passwordsNotSame");
                errors.rejectValue("repassword", "passwordsNotSame");
      		}
      		
      	}
        
        	
        
	}
}
