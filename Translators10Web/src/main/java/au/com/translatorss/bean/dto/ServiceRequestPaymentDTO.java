package au.com.translatorss.bean.dto;

import java.math.BigDecimal;

public class ServiceRequestPaymentDTO {

	private String paypalId;
	private BigDecimal amount;
	private String currency; 
	private String user;
	

	private Long serviceRequestId;
	
	public ServiceRequestPaymentDTO(){
		
	}
	
	public String getPaypalId() {
		return paypalId;
	}

	public void setPaypalId(String paypalId) {
		this.paypalId = paypalId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Long getServiceRequestId() {
		return serviceRequestId;
	}

	public void setServiceRequestId(Long serviceRequestId) {
		this.serviceRequestId = serviceRequestId;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	
}
