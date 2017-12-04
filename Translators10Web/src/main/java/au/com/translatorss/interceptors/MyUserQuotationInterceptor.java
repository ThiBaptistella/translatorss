package au.com.translatorss.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class MyUserQuotationInterceptor extends HandlerInterceptorAdapter{

    private static final Logger logger = LoggerFactory.getLogger(MyUserQuotationInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws Exception {
        logger.info("In preHandle: MyUserQuotationInterceptor");
        if (request.getSession().getAttribute("loggedInUser") == null) {
            response.sendRedirect("/translatorss");
            return false;
        }else{
            if (request.getSession().getAttribute("serviceRequestid") == null) {
                response.sendRedirect("/translatorss/pendingActions");
                return false;
            }
        }
        return true;
    }
    
}
