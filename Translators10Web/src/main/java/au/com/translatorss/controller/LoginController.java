package au.com.translatorss.controller;

import au.com.translatorss.bean.*;
import au.com.translatorss.bean.dto.BusinessUserDTO;
import au.com.translatorss.service.AdminSettingsService;
import au.com.translatorss.service.BusinessUserService;
import au.com.translatorss.service.LanguageService;
import au.com.translatorss.service.TranslatorSettingsService;
import au.com.translatorss.validation.CustomerLogginValidator;
import au.com.translatorss.validation.TranslatorLogginValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private TranslatorSettingsService translatorService;

	@Autowired
    private TranslatorLogginValidator logginValidator;

	@Autowired
    private CustomerLogginValidator customerValidator;

	@Autowired
    private LanguageService languageService;

	@Autowired
	private BusinessUserService businessUserService;

	@Autowired
	private AdminSettingsService adminSettingsService;

	@InitBinder("translatorloggin")
	public void initBinder(WebDataBinder binder) {
			binder.addValidators(logginValidator);
    }

	@InitBinder("customerloggin")
	public void initBinder2(WebDataBinder binder) {
			binder.addValidators(customerValidator);
    }

//	@RequestMapping(value = "/login", method = RequestMethod.GET)
//    public String viewLogin(Map<String, Object> model) {
//		Translator translator = new Translator();
//        model.put("userForm", translator);
//        return "LoginForm";
//    }
//

	@RequestMapping(value = "/translatorloggin")
	public String processTranslator(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
			logger.info("Welcome LoginController: TRANSLATOR");
			Loggin Loggin = (Loggin) session.getAttribute("loggin");
			Translator translator = translatorService.getTranslatorByEmail(Loggin.getEmail(),Loggin.getPassword());
			model.addAttribute("translatorForm", translator);	
			model.addAttribute("translatorForm2", new Translator());
			//model.addAttribute("message", "Welcome "+ translator.getUser().getName());
		    redirectAttributes.addFlashAttribute("translatorForm", translator);
		    session.setAttribute("loggedInUser", translator);
		    //redirectAttributes.addFlashAttribute("message", "Welcome "+ translator.getUser().getName());
		    return "redirect:dashboard";
	}

	@RequestMapping(value = "/customerloggin")
	public String customerloggin(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
		logger.info("Welcome LoginController: CUSTOMER");		
		Loggin Loggin = (Loggin) session.getAttribute("loggin");
		BusinessUser BusinessUser = businessUserService.getUserByEmail(Loggin.getEmail(),Loggin.getPassword());	
        session.setAttribute("loggedInUser", BusinessUser);
        return "redirect:indexUserIni";
	}

//    @RequestMapping(value = "/adminloggin", method = RequestMethod.GET)
//	public String adminloggin(HttpSession session,Model model) {
//    	logger.info("Welcome LoginController: ADMIN");
//		Loggin Loggin = (Loggin) session.getAttribute("loggin");
//
//		model.addAttribute("adminloginForm", new Loggin());	
//		return "adminDashboard/AdminLoggin";
//	}
 
    /***VER EN DETALLE**/
	@RequestMapping(value = "/adminloggin2")
	public String adminloggin2(HttpSession session, Model model) {
		logger.info("Welcome LoginController: ADMIN");
		Loggin Loggin = (Loggin) session.getAttribute("loggin");
		Admin admin = adminSettingsService.getAdminByEmail(Loggin.getEmail(), Loggin.getPassword());
		session.setAttribute("loggedInAdmin", admin);
		return "redirect:/translatorList";
	}

}
