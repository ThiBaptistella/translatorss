package au.com.translatorss.security.configuration;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import au.com.translatorss.bean.Loggin;
import au.com.translatorss.bean.User;
import au.com.translatorss.bean.dto.CurrentUser;
import au.com.translatorss.bean.dto.ServiceRequestDTO;
import au.com.translatorss.enums.Role;
import au.com.translatorss.service.LogintryService;

public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Autowired
	LogintryService logintryService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		HttpSession session = request.getSession();

		CurrentUser currentuser = (CurrentUser) authentication.getPrincipal();
		User user = currentuser.getUser();
		logintryService.DeleteByUser(user.getId());

		session.setAttribute("username", user.getName());
		GrantedAuthority authority = (GrantedAuthority) authentication.getAuthorities().toArray()[0];

		Loggin loggin = new Loggin();
		loggin.setEmail(user.getEmail().replaceAll("\\s+",""));
		loggin.setPassword(user.getPassword().replaceAll("\\s+",""));
		session.setAttribute("loggin", loggin);

		ServiceRequestDTO serviceRequestDTO = (ServiceRequestDTO)session.getAttribute("servcieRequestLead");

		if (authority.getAuthority().equals(Role.ADMIN.name())) {
			response.sendRedirect("/adminloggin2");
		}else if(authority.getAuthority().equals(Role.TRANSLATOR.name())){
			response.sendRedirect("/translatorloggin");
		}else if(authority.getAuthority().equals(Role.CLIENT.name())&& serviceRequestDTO!=null){
			response.sendRedirect("/loginBusinessUser");
		}else if(authority.getAuthority().equals(Role.CLIENT.name()) && serviceRequestDTO==null){
			response.sendRedirect("/customerloggin");
		}
	}
}