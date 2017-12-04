package au.com.translatorss.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class ServiceRequestInterceptor extends HandlerInterceptorAdapter{

    private static final Logger logger = LoggerFactory.getLogger(ServiceRequestInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler) throws Exception {
        logger.info("In preHandle: ServiceRequestInterceptor");
        if (request.getSession().getAttribute("loggedInUser") == null) {
            response.sendRedirect("/translatorss");
            return false;
        }
        return true;
    }
    
    
}
