package au.com.translatorss.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class UserQuotationInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(UserQuotationInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler) throws Exception {
        logger.info("In preHandle");
        if (request.getSession().getAttribute("loggedInUser") == null) {
            response.sendRedirect("/translatorss");
            return false;
        }
//        if(request.getSession().getAttribute("serviceRequest") == null){
//            response.sendRedirect("/translatorss/pendingActions");
//            return false;
//        }
        return true;
    }
    
}
