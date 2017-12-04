package au.com.translatorss.controller.translator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import au.com.translatorss.bean.Language;
import au.com.translatorss.bean.dto.QuotationConfigurationDTO;
import au.com.translatorss.service.TranslatorSettingsService;
import au.com.translatorss.validation.QuotationDTOValidator;
import au.com.translatorss.validation.TranslatorValidator;

@Controller
public class TranslatorControllerQuotConfig {

    @Autowired
    private QuotationDTOValidator quotationDTOValidator;
    
    @InitBinder("quotationConfForm")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(quotationDTOValidator);
    }
}
