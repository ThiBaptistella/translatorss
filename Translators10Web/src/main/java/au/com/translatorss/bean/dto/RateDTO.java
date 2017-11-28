package au.com.translatorss.bean.dto;

public class RateDTO {

    private int servieRequestId;
    private int customerId;
    private int translatorId;
    private Integer timeDelivery;
	private Integer service;
	private Integer quality;
    private String feedback;


    public RateDTO(){
        
    }

    public int getServieRequestId() {
        return servieRequestId;
    }

    public void setServieRequestId(int servieRequestId) {
        this.servieRequestId = servieRequestId;
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

    /*
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
    }*/

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

	public Integer getTimeDelivery() {
		return timeDelivery;
	}

	public void setTimeDelivery(Integer timeDelivery) {
		this.timeDelivery = timeDelivery;
	}

	public Integer getService() {
		return service;
	}

	public void setService(Integer service) {
		this.service = service;
	}

	public Integer getQuality() {
		return quality;
	}

	public void setQuality(Integer quality) {
		this.quality = quality;
	}
}
