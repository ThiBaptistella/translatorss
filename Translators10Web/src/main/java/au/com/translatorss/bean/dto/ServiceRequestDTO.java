package au.com.translatorss.bean.dto;

import au.com.translatorss.bean.Invoice;
import au.com.translatorss.bean.ServiceRequestFiles;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class ServiceRequestDTO {

    private Long id;
	private String fullName;
	private String serviceRequestCategory;
	private String languagefrom;
	private String languageTo;
	private String timeFrame;
	private String description;
	private Boolean hardcopy;
	private List<MultipartFile> files;
	private String url;
	private Boolean update;
	private String status;
	private String translatorName;
	private Date finishDate;
	private Date createDate;
	private Date paidDate;
	private String finishQuoteDate;
	private Integer countOfUnreadMessages;
	private String clientName;
	private BigDecimal quote;
	private String invoiceUrl;
	private List<AmazonFileDto> amazonList;
	private RateDTO rateDto;


	public ServiceRequestDTO(){ }

	public RateDTO getRateDto() {
		return rateDto;
	}

	public void setRateDto(RateDTO rateDto) {
		this.rateDto = rateDto;
	}


	public List<AmazonFileDto> getAmazonList() {
		return amazonList;
	}

	public void setAmazonList(List<AmazonFileDto> amazonList) {
		this.amazonList = amazonList;
	}

	public String getFinishQuoteDate() {
		return finishQuoteDate;
	}

	public void setFinishQuoteDate(String finishQuoteDate) {
		this.finishQuoteDate = finishQuoteDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


	public String getServiceRequestCategory() {
		return serviceRequestCategory;
	}
	
	public void setServiceRequestCategory(String serviceRequestCategory) {
		this.serviceRequestCategory = serviceRequestCategory;
	}
	
	public String getLanguagefrom() {
		return languagefrom;
	}
	
	public void setLanguagefrom(String languagefrom) {
		this.languagefrom = languagefrom;
	}
	
	public String getLanguageTo() {
		return languageTo;
	}
	
	public void setLanguageTo(String languageTo) {
		this.languageTo = languageTo;
	}
	
	public String getTimeFrame() {
		return timeFrame;
	}
	
	public void setTimeFrame(String timeFrame) {
		this.timeFrame = timeFrame;
	}
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Boolean getHardcopy() {
		return hardcopy;
	}
	
	public void setHardcopy(Boolean hardcopy) {
		this.hardcopy = hardcopy;
	}
	
	public List<MultipartFile> getFiles() {
		return files;
	}
	
	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}
	
	public String getFullName() {
		return fullName;
	}
	
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
    public Boolean getUpdate() {
        return update;
    }
    
    public void setUpdate(Boolean update) {
        this.update = update;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTranslatorName() {
        return translatorName;
    }

    public void setTranslatorName(String translatorName) {
        this.translatorName = translatorName;
    }
    
	public Date getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}

	public Integer getCountOfUnreadMessages() {
		return countOfUnreadMessages;
	}

	public void setCountOfUnreadMessages(Integer countOfUnreadMessages) {
		this.countOfUnreadMessages = countOfUnreadMessages;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public BigDecimal getQuote() {
		return quote;
	}

	public void setQuote(BigDecimal quote) {
		this.quote = quote;
	}

	public String getInvoiceUrl() {
		return invoiceUrl;
	}

	public void setInvoiceUrl(String invoiceUrl) {
		this.invoiceUrl = invoiceUrl;
	}
	
	public Date getPaidDate() {
		return paidDate;
	}

	public void setPaidDate(Date paidDate) {
		this.paidDate = paidDate;
	}
}
