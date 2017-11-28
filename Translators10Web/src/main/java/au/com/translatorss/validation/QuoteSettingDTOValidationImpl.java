package au.com.translatorss.validation;

import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;

import au.com.translatorss.bean.dto.QuoteSettingDTO;

public class QuoteSettingDTOValidationImpl implements QuotationDTOValidator{

	@Override
	public boolean supports(Class<?> clazz) {
		return QuoteSettingDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		QuoteSettingDTO quoteSettingDTO = (QuoteSettingDTO) target;

		 if (!StringUtils.isNotBlank(quoteSettingDTO.getMarriageCertificate())) {
	            errors.rejectValue("fullname", "fieldEmpty");
	     }
		 if (!StringUtils.isNotBlank(quoteSettingDTO.getBirthCertificate())) {
	            errors.rejectValue("fullname", "fieldEmpty");
	     }
		 
	}

}
