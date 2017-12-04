package au.com.translatorss.validation;

import au.com.translatorss.bean.dto.BusinessUserDTO;
import au.com.translatorss.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;

@Service
@Transactional
public class BusinessUserHomeValidatorImpl implements BusinessUserHomeValidation {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> paramClass) {
        return BusinessUserDTO.class.equals(paramClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        BusinessUserDTO businessUserDTO = (BusinessUserDTO) target;
        if(userService.isExistEmail(businessUserDTO.getEmail())){
            errors.rejectValue("email", "emailUsed");
        }
    }
}
