package au.com.translatorss.validation;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;

import au.com.translatorss.bean.BusinessUser;
import au.com.translatorss.bean.Translator;
import au.com.translatorss.bean.User;
import au.com.translatorss.bean.dto.PasswordDTO;
import au.com.translatorss.bean.dto.QuotationConfigurationDTO;
import au.com.translatorss.service.BusinessUserService;
import au.com.translatorss.service.TranslatorSettingsService;
import au.com.translatorss.service.UserService;


@Service
@Transactional
public class PasswordChangeRequestValidatorImpl implements PasswordChangeRequestValidator{

    @Autowired
    private BusinessUserService businessUserService;
    
    @Autowired
	private TranslatorSettingsService translatorService;
    
	@Autowired
    private UserService userService;
    
    @Override
    public boolean supports(Class<?> clazz) {
        return PasswordDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PasswordDTO passwordDTO = (PasswordDTO)target;

        User user = userService.getCurrentUserOrNull();
        
        if(!user.getPassword().equals(passwordDTO.getCurrentPassword())){
        	errors.rejectValue("currentPassword", "incorrectPassword");
        }
//        if(!StringUtils.isNotBlank(passwordDTO.getCurrentPassword())){
//            errors.rejectValue("currentPassword", "fieldEmpty");
//        }
//        
//        if(!StringUtils.isNotBlank(passwordDTO.getNewPassword())){
//            errors.rejectValue("newPassword", "fieldEmpty");
//        }
//        
//        if(!StringUtils.isNotBlank(passwordDTO.getConfirmNewPassword())){
//            errors.rejectValue("confirmNewPassword", "fieldEmpty");
//        }
//        
//        if(StringUtils.isNotBlank(passwordDTO.getConfirmNewPassword()) &&  StringUtils.isNotBlank(passwordDTO.getNewPassword()) && StringUtils.isNotBlank(passwordDTO.getCurrentPassword())){        
//            if(!passwordDTO.getNewPassword().equals(passwordDTO.getConfirmNewPassword())){
//                errors.rejectValue("confirmNewPassword", "passwordsNotSame");
//            }
//            
//            if(passwordDTO.getType().equals("businessuser")){
//            	BusinessUser businessUser = businessUserService.getUserByEmail(passwordDTO.getEmail(), passwordDTO.getCurrentPassword());
//                if(businessUser==null){
//                    errors.rejectValue("confirmNewPassword", "notUserFound");
//                }
//            }
//            if(passwordDTO.getType().equals("translator")){
//            	Translator translator = translatorService.getTranslatorByEmail(passwordDTO.getEmail(), passwordDTO.getCurrentPassword());
//                if(translator==null){
//                    errors.rejectValue("currentPassword", "incorrectPassword");
//                }
//            }
//        }
    }

}
