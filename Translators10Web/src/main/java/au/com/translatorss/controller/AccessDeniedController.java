package au.com.translatorss.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AccessDeniedController {

	// for 403 access denied page
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String accesssDenied() {
		ModelAndView model = new ModelAndView();
		model.addObject("msg","You do not have permission to access this page!");
		model.setViewName("403");
		return "restricAccess";
	}
}
