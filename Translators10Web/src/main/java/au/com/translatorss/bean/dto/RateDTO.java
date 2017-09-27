package au.com.translatorss.bean.dto;

public class RateDTO {
    
    private int customerId;
    private int translatorId;
    private int translatorComunication;
    private int serviceDescribed;
    private int wouldRecomend;
    private String feedback;
    
    public RateDTO(){
        
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getTranslatorId() {
        return translatorId;
    }

    public void setTranslatorId(int translatorId) {
        this.translatorId = translatorId;
    }

    public int getTranslatorComunication() {
        return translatorComunication;
    }

    public void setTranslatorComunication(int translatorComunication) {
        this.translatorComunication = translatorComunication;
    }

    public int getServiceDescribed() {
        return serviceDescribed;
    }

    public void setServiceDescribed(int serviceDescribed) {
        this.serviceDescribed = serviceDescribed;
    }

    public int getWouldRecomend() {
        return wouldRecomend;
    }

    public void setWouldRecomend(int wouldRecomend) {
        this.wouldRecomend = wouldRecomend;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
