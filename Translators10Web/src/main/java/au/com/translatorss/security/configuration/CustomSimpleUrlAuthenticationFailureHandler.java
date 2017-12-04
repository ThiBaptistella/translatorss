package au.com.translatorss.security.configuration;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import au.com.translatorss.bean.ServiceRequest;
import au.com.translatorss.bean.dto.ServiceRequestDTO;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;

public class CustomSimpleUrlAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	private String url;
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	private String fromusernamekey = "email";

	public CustomSimpleUrlAuthenticationFailureHandler(String url) {
		this.url = url;
	}

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authenticationException) throws IOException, ServletException {

		if (authenticationException instanceof SessionAuthenticationException) {
			request.getSession().setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION,
					new SessionAuthenticationException("You have logged in somewhere else also"));
		} else {
			saveException(request, authenticationException);
		}
		String username = request.getParameter(fromusernamekey);
		HttpSession session = request.getSession();
		ServiceRequestDTO  serviceRequest = (ServiceRequestDTO ) session.getAttribute("servcieRequestLead");
		if(serviceRequest!=null){
			String redirectURl = url + "businessUserForm";
			redirectStrategy.sendRedirect(request,response,redirectURl);
		}else {
			String redirectUrl = url + "login";
			logger.info("Login Was Not Successful");
			redirectStrategy.sendRedirect(request, response, redirectUrl);
		}
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}

	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}

	public String getFromusernamekey() {
		return fromusernamekey;
	}

	public void setFromusernamekey(String fromusernamekey) {
		this.fromusernamekey = fromusernamekey;
	}
}