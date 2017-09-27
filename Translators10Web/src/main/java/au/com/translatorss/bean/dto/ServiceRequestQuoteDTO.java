package au.com.translatorss.bean.dto;

import java.math.BigDecimal;
import java.util.Date;

public class ServiceRequestQuoteDTO {

	private Long translatorid;
	private String translatorName;
	private String translatorStatus;
	private Long customerID;
	private Long serviceRequestID;
	private Date date;
	private Date timeLeftCloseQuote;
	private String serviceRequestStatus;
	private Date timeLefToFinishAssignment;
	private BigDecimal quote;
	private String customerName;
	private String category;
	private String timeFrame;
	private boolean hardcopy;
	private String origenLanguage;
	private String description;

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getTranslatorid() {
		return translatorid;
	}
	public void setTranslatorid(Long translatorid) {
		this.translatorid = translatorid;
	}
	public String getTranslatorName() {
		return translatorName;
	}
	public void setTranslatorName(String translatorName) {
		this.translatorName = translatorName;
	}
	public String getTranslatorStatus() {
		return translatorStatus;
	}
	public void setTranslatorStatus(String translatorStatus) {
		this.translatorStatus = translatorStatus;
	}
	public Long getCustomerID() {
		return customerID;
	}
	public void setCustomerID(Long customerID) {
		this.customerID = customerID;
	}
	public Long getServiceRequestID() {
		return serviceRequestID;
	}
	public void setServiceRequestID(Long serviceRequestID) {
		this.serviceRequestID = serviceRequestID;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getTimeLeftCloseQuote() {
		return timeLeftCloseQuote;
	}
	public void setTimeLeftCloseQuote(Date timeLeftCloseQuote) {
		this.timeLeftCloseQuote = timeLeftCloseQuote;
	}
	public String getServiceRequestStatus() {
		return serviceRequestStatus;
	}
	public void setServiceRequestStatus(String serviceRequestStatus) {
		this.serviceRequestStatus = serviceRequestStatus;
	}
	public Date getTimeLefToFinishAssignment() {
		return timeLefToFinishAssignment;
	}
	public void setTimeLefToFinishAssignment(Date timeLefToFinishAssignment) {
		this.timeLefToFinishAssignment = timeLefToFinishAssignment;
	}
	public BigDecimal getQuote() {
		return quote;
	}
	public void setQuote(BigDecimal quote) {
		this.quote = quote;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getTimeFrame() {
		return timeFrame;
	}
	public void setTimeFrame(String timeFrame) {
		this.timeFrame = timeFrame;
	}
	public boolean isHardcopy() {
		return hardcopy;
	}
	public void setHardcopy(boolean hardcopy) {
		this.hardcopy = hardcopy;
	}
	public String getOrigenLanguage() {
		return origenLanguage;
	}
	public void setOrigenLanguage(String origenLanguage) {
		this.origenLanguage = origenLanguage;
	}
}
