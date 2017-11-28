package au.com.translatorss.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import au.com.translatorss.bean.Conversation;

public class MessageInterceptor extends HandlerInterceptorAdapter{

    private static final Logger logger = LoggerFactory.getLogger(MessageInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler) throws Exception {
        logger.info("In preHandle: MessageInterceptor");
        if (request.getSession().getAttribute("loggedInUser") == null) {
            response.sendRedirect("/translatorss");
            return false;
        }else{
            Conversation conversation = (Conversation) request.getSession().getAttribute("conversation");
            if(conversation != null && conversation.getId()==null){
                postHandle(request, response, handler, null);
                return false;
            }
        }
        return true;
    }
    
    @Override
    public void postHandle(HttpServletRequest request,HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("In postHandle: MessageInterceptor");
        if(modelAndView.getModelMap().containsKey("conversation")){
            Conversation conversation = (Conversation) modelAndView.getModelMap().get("conversation");
//            if(status.equals("SUCCESS!")){
//                 status = "Authentication " + status;
//                                modelAndView.getModelMap().put("status",status);
//             }
        }
    }
}
