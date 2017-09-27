package au.com.translatorss.validation;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartFile;

import au.com.translatorss.bean.dto.ServiceRequestDTO;

@Service
@Transactional
public class CreateServiceRequestValidatorImpl implements CreateServiceRequestValidator{

    @Override
    public boolean supports(Class<?> paramClass) {
        return ServiceRequestDTO.class.equals(paramClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ServiceRequestDTO serviceRequestDTO = (ServiceRequestDTO) target;

        if(serviceRequestDTO.getFiles()==null){
            errors.reject("entityNotNull");
        }
        else{
        
        if (!StringUtils.isNotBlank(serviceRequestDTO.getLanguagefrom())) {
            errors.rejectValue("languagefrom", "languagefrom");
        }

        if (!StringUtils.isNotBlank(serviceRequestDTO.getDescription())) {
            errors.rejectValue("description", "description");
        }

        if (!StringUtils.isNotBlank(serviceRequestDTO.getServiceRequestCategory())) {
            errors.rejectValue("serviceRequestCategory", "serviceRequestCategory");
        }

        if (!StringUtils.isNotBlank(serviceRequestDTO.getTimeFrame())) {
            errors.rejectValue("timeFrame", "timeFrame");
        }

        List<MultipartFile> files = serviceRequestDTO.getFiles();
        for(MultipartFile multipleFile: files){
            if( multipleFile.isEmpty()){
                errors.rejectValue("files", "filesEmpty");
            }
        }
        }
    }
}
