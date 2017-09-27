package au.com.translatorss.bean.dto;

import java.math.BigDecimal;

public class ServiceRequestPaymentDTO {

	private String paypalTransaction;
	private Long serviceRequestId;
	private BigDecimal amount;
	private String customer;
	private String translator;
	
	public ServiceRequestPaymentDTO(){
		
	}

	public String getPaypalTransaction() {
		return paypalTransaction;
	}

	public void setPaypalTransaction(String paypalTransaction) {
		this.paypalTransaction = paypalTransaction;
	}

	public Long getServiceRequestId() {
		return serviceRequestId;
	}

	public void setServiceRequestId(Long serviceRequestId) {
		this.serviceRequestId = serviceRequestId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getTranslator() {
		return translator;
	}

	public void setTranslator(String translator) {
		this.translator = translator;
	}
}
