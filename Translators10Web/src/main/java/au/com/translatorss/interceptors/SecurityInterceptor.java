package au.com.translatorss.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class SecurityInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(SecurityInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		logger.info("In preHandle");
		String loginUrl = request.getRequestURL().toString();
		if (request.getSession().getAttribute("loggedInUser") == null) {
			response.sendRedirect("/translatorss");
			return false;
		}
		return true;
	}
}
