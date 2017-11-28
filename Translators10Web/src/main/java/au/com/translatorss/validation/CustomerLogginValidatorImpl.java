package au.com.translatorss.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;

import au.com.translatorss.bean.BusinessUser;
import au.com.translatorss.bean.Loggin;
import au.com.translatorss.service.BusinessUserService;

@Service
@Transactional
public class CustomerLogginValidatorImpl implements CustomerLogginValidator {

	@Autowired
	private BusinessUserService businessUserService;
	
	@Override
	public boolean supports(Class<?> paramClass) {
		return Loggin.class.equals(paramClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		  Loggin Loggin= (Loggin) target;
	      BusinessUser user= businessUserService.getUserByEmail(Loggin.getEmail(), Loggin.getPassword());
	      if(user==null){
	            errors.rejectValue("email", "languagefrom");
	      }
	}

}
