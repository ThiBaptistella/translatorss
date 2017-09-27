package au.com.translatorss.bean.dto;

import java.util.Date;

public class ServiceRequestCSDTO extends ServiceRequestDTO{

	private Long translatorId;
	private Long customerId;
	private String preferedName;
	private String translatorStatus;
	private Date creationDate;
	private Date finishDate;
	private String description;
	
	public Long getTranslatorId() {
		return translatorId;
	}
	public void setTranslatorId(Long translatorId) {
		this.translatorId = translatorId;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public String getPreferedName() {
		return preferedName;
	}
	public void setPreferedName(String preferedName) {
		this.preferedName = preferedName;
	}
	public String getTranslatorStatus() {
		return translatorStatus;
	}
	public void setTranslatorStatus(String translatorStatus) {
		this.translatorStatus = translatorStatus;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public Date getFinishDate() {
		return finishDate;
	}

	@Override
	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}
}
