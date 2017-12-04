package au.com.translatorss.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;

import javax.servlet.http.HttpSession;

import au.com.translatorss.service.UserService;
import org.springframework.http.ResponseEntity;
import au.com.translatorss.bean.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.http.MediaType;
import  org.springframework.http.HttpStatus;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import au.com.translatorss.bean.BusinessUser;
import au.com.translatorss.bean.Language;
import au.com.translatorss.bean.Loggin;
import au.com.translatorss.bean.dto.BusinessUserDTO;
import au.com.translatorss.bean.dto.ServiceRequestDTO;
import au.com.translatorss.bean.dto.TranslatorQuotationDTO;
import au.com.translatorss.dao.ServiceRequestCategoryDao;
import au.com.translatorss.dao.ServiceRequestStatusDao;
import au.com.translatorss.dao.TimeFrameDao;
import au.com.translatorss.dao.TranslatorDao;
import au.com.translatorss.service.LanguageService;
import au.com.translatorss.service.ServiceRequestConfigurationService;
import au.com.translatorss.service.TranslatorQuotationService;
import au.com.translatorss.validation.CreateServiceRequestValidator;
import au.com.translatorss.validation.RegisterValidator;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
public class RegisterController {

	private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

	@Autowired
	private LanguageService languageService;

    @Autowired
    private CreateServiceRequestValidator createServiceRequestValidator;
	
    @Autowired
    private TranslatorQuotationService quotationService;

	@Autowired
	private UserService userService;

    @Value("${donation.value}")
    private String donationValue;
    
    @InitBinder("serviceRequestDTO")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(createServiceRequestValidator);
    }

	@RequestMapping(value = "/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	/* ResponseEntity representa una respuesta HTTP */
	public ResponseEntity<Collection<User>> getUsers() {
	    List<User> userList = userService.findAll();
		return new ResponseEntity<Collection<User>>(userList, HttpStatus.OK);
	}

	@RequestMapping(value = "serviceRequestProcesorHome",method = RequestMethod.POST)
	public String serviceRequestProcesorHome(@ModelAttribute("serviceRequestDTO")@Validated ServiceRequestDTO serviceRequestDTO,BindingResult result, Model model,HttpSession session) throws IllegalStateException, IOException{
		logger.info("Welcome RegisterController: businessUser");
		if (result.hasErrors()) {
			model.addAttribute("serviceRequestDTO", serviceRequestDTO);
			model.addAttribute("languageList", initializeProfiles());
			return "home";
		}
		BusinessUser usinessUser = new BusinessUser();
		session.setAttribute("servcieRequestLead", serviceRequestDTO);
		List<MultipartFile> files = serviceRequestDTO.getFiles();
		List<byte[]> bytes = new ArrayList<byte[]>();
		for (MultipartFile file : files) {
			bytes.add(file.getBytes());
		}
		session.setAttribute("files", bytes);
		model.addAttribute("businessUserForm", usinessUser);
		List<TranslatorQuotationDTO> quoteList= quotationService.getQuotationArrayDTO(serviceRequestDTO);
		if(!quoteList.isEmpty()){
			session.setAttribute("translatorQuoteList",quoteList);
			model.addAttribute("translatorQuoteList", quoteList);
			model.addAttribute("donationValue", donationValue);
			return "HomeQuotes";
		}else{
			return "redirect:/businessUserForm";
		}
	}

	@RequestMapping(value = "/businessUserForm")
	public String businessUserForm(HttpSession session, Model model) {
		logger.info("Welcome BusinessUserDashboardController: businessUserForm");
		BusinessUserDTO businessUserDTO = new BusinessUserDTO();
		Loggin loggin = new Loggin();
		model.addAttribute("businessUserForm", businessUserDTO);
		model.addAttribute("loginSRForm", loggin);
		return "/businessUserForm";
	}

	public List<Language> initializeProfiles() {
		List<Language> languageList = languageService.getAvailableLanguages();
		return languageList;
	}
}
