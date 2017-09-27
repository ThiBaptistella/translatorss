package au.com.translatorss.controller.translator.ajax;

import au.com.translatorss.bean.Translator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;


@Controller
public class AjaxController {
	
//	   @Autowired
//	   private TranslatorSettingsService translatorService;
	
	   @RequestMapping(value = "/ajaxtest/{password}", method = RequestMethod.GET)
	   @ResponseBody
	   public String changeEmailValidator(HttpSession session, @PathVariable("password") String password) {
	        Translator translator=(Translator)session.getAttribute("loggedInUser");
	        if(translator.getUser().getPassword().equals(password)){
	        	return "true";
	        }else{
	        	return "false";
	        }
	    }
}
