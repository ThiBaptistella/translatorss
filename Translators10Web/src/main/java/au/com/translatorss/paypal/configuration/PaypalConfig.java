package au.com.translatorss.paypal.configuration;

import javax.servlet.http.HttpServletRequest;

public class PaypalConfig {

    private String authToken;
    private String posturl;
    private String business;
    private String returnurl;
    private String cancelurl;
    private String cmd;
    
    
    public String getAuthToken() {
        return authToken;
    }
    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
    public String getPosturl() {
        return posturl;
    }
    public void setPosturl(String posturl) {
        this.posturl = posturl;
    }
    public String getBusiness() {
        return business;
    }
    public void setBusiness(String business) {
        this.business = business;
    }
    public String getReturnurl() {
        return returnurl;
    }
    public void setReturnurl(String returnurl) {
        this.returnurl = returnurl;
    }
    public String getCancelurl() {
        return cancelurl;
    }
    public void setCancelurl(String cancelurl) {
        this.cancelurl = cancelurl;
    }
    public String getCmd() {
        return cmd;
    }
    public void setCmd(String cmd) {
        this.cmd = cmd;
    }
    
    public PaypalConfig getConfig(HttpServletRequest request){
        PaypalConfig pc = new PaypalConfig();
        try{
            pc.authToken = request.getServletContext().getInitParameter("authtoken");
            pc.posturl = request.getServletContext().getInitParameter("posturl");
            pc.business = request.getServletContext().getInitParameter("business");
            pc.returnurl = request.getServletContext().getInitParameter("returnurl");
        }catch(Exception ex){
            pc=null;
        }
        return pc;
    }
    
}
