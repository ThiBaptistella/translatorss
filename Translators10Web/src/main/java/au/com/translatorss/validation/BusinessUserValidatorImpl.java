package au.com.translatorss.validation;

import au.com.translatorss.bean.BusinessUser;
import au.com.translatorss.bean.User;
import au.com.translatorss.bean.dto.BusinessUserDTO;
import au.com.translatorss.service.BusinessUserService;
import au.com.translatorss.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;

@Service
@Transactional
public class BusinessUserValidatorImpl implements BusinessUserValidator {

	@Autowired
	private UserService userService;

	@Autowired
	private BusinessUserService businessUserService;

    @Override
    public boolean supports(Class<?> paramClass) {
        return BusinessUserDTO.class.equals(paramClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
    	BusinessUserDTO businessUserDTO = (BusinessUserDTO) target;
		if(!userService.isAvailableEmailAndExcludeUser(businessUserDTO.getEmail(), businessUserDTO.getId())){
			errors.rejectValue("email", "emailUsed");
		}
    }
}
