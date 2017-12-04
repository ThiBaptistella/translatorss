package au.com.translatorss.validation;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;

import au.com.translatorss.bean.BusinessUser;
import au.com.translatorss.bean.dto.QuotationConfigurationDTO;
import au.com.translatorss.bean.dto.QuotationDTO;

@Service
@Transactional
public class QuotationDTOValidatorImpl implements QuotationDTOValidator {

    @Override
    public boolean supports(Class<?> clazz) {
        return QuotationConfigurationDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        QuotationConfigurationDTO quotationDTO = (QuotationConfigurationDTO)target;
        
        if(!StringUtils.isNumeric(quotationDTO.getMediumAcademicTranscript())){
            errors.rejectValue("mediumAcademicTranscript", "fieldFomat");
        }
        if(!StringUtils.isNumeric(quotationDTO.getMediumBirthDeath())){
            errors.rejectValue("mediumBirthDeath", "fieldFomat");
        }
        if(!StringUtils.isNumeric(quotationDTO.getMediumBusinessDocument())){
            errors.rejectValue("mediumBusinessDocument", "fieldFomat");  
        }
        if(!StringUtils.isNumeric(quotationDTO.getMediumDriverLic())){
            errors.rejectValue("mediumDriverLic", "fieldFomat");
        }

        
        if(!StringUtils.isNumeric(quotationDTO.getRelaxedAcademicTranscript())){
            errors.rejectValue("relaxedAcademicTranscript", "fieldFomat");
        }
        if(!StringUtils.isNumeric(quotationDTO.getRelaxedBirthDeath())){
            errors.rejectValue("relaxedBirthDeath", "fieldFomat");
        }
        if(!StringUtils.isNumeric(quotationDTO.getRelaxedBusinessDocument())){
            errors.rejectValue("relaxedBusinessDocument", "fieldFomat");
        }
        if(!StringUtils.isNumeric(quotationDTO.getRelaxedDriverLic())){
            errors.rejectValue("relaxedDriverLic", "fieldFomat");
        }
        
        
        if(!StringUtils.isNumeric(quotationDTO.getUrgentAcademicTranscript())){
            errors.rejectValue("urgentAcademicTranscript", "fieldFomat");
        }
        if(!StringUtils.isNumeric(quotationDTO.getUrgentBirthDeath())){
            errors.rejectValue("urgentBirthDeath", "fieldFomat");
        }
        if(!StringUtils.isNumeric(quotationDTO.getUrgentBusinessDocument())){
            errors.rejectValue("urgentBusinessDocument", "fieldFomat");
        }
        if(!StringUtils.isNumeric(quotationDTO.getUrgenDriverLic())){
            errors.rejectValue("urgenDriverLic", "fieldFomat");
        }
        
    }

}
