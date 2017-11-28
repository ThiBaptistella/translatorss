package au.com.translatorss.bean.dto;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import au.com.translatorss.bean.ServiceRequestFiles;

public class ServiceRequestHomeDTO {

	private long id;
	private String fullName;
	private String serviceRequestCategory;
	private String languagefrom;
	private String languageTo;
	private String timeFrame;
	private String description;
	private Boolean hardcopy;
	private String url;
	private Boolean update;
	private String status;
	private String translatorName;
	private Date finishDate;
	private String finishQuoteDate;
	
	
	
	public ServiceRequestHomeDTO() {
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
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
	public String getFinishQuoteDate() {
		return finishQuoteDate;
	}
	public void setFinishQuoteDate(String finishQuoteDate) {
		this.finishQuoteDate = finishQuoteDate;
	}
	
}
