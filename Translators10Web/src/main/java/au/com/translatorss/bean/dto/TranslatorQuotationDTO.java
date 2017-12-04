package au.com.translatorss.bean.dto;

public class TranslatorQuotationDTO {

    private long serviceRequestId;
    private long translatorId;
    private long quotationId;
    private String name;
    private String photo;
	private String quote;
    private boolean isNaati;
    private String description;
    private Integer quality;
    private Integer timeDelivery;
    private Integer serviceDescribed;
    
    public TranslatorQuotationDTO(){
        
    }

    public long getQuotationId() {
        return quotationId;
    }

    public void setQuotationId(long quotationId) {
        this.quotationId = quotationId;
    }

    
    public long getTranslatorId() {
        return translatorId;
    }

    public void setTranslatorId(long translatorId) {
        this.translatorId = translatorId;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getQuote() {
        return quote;
    }
    public void setQuote(String quote) {
        this.quote = quote;
    }
    public boolean isNaati() {
        return isNaati;
    }
    public void setNaati(boolean isNaati) {
        this.isNaati = isNaati;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public long getServiceRequestId() {
        return serviceRequestId;
    }

    public void setServiceRequestId(long serviceRequestId) {
        this.serviceRequestId = serviceRequestId;
    }
    

    public Integer getServiceDescribed() {
        return serviceDescribed;
    }

    public void setServiceDescribed(Integer serviceDescribed) {
        this.serviceDescribed = serviceDescribed;
    }
    public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

    public Integer getQuality() {
        return quality;
    }

    public void setQuality(Integer quality) {
        this.quality = quality;
    }

    public Integer getTimeDelivery() {
        return timeDelivery;
    }

    public void setTimeDelivery(Integer timeDelivery) {
        this.timeDelivery = timeDelivery;
    }
}
