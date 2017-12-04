package au.com.translatorss.controller;

import au.com.translatorss.service.LogintryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class AccessDeniedController {

	@Autowired
	private LogintryService logintryService;

	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String accesssDenied() {
		ModelAndView model = new ModelAndView();
		model.addObject("msg","You do not have permission to access this page!");
		model.setViewName("403");
		return "restricAccess";
	}

	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@RequestMapping(value = "/unlockUser/{userid}", method = RequestMethod.GET)
	public String unlockUser(@PathVariable("userid") long userid, Model model, HttpSession session) {
		logintryService.DeleteByUser(userid);
		return "redirect:/customerList";
	}

}
