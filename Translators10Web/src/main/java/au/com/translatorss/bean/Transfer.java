package au.com.translatorss.bean;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Transfer 
 */
@Entity
@Table(name = "transfer")
public class Transfer implements java.io.Serializable {

	private static final long serialVersionUID = 5803047526876541016L;
	private Long id;
	private ServiceRequestPayment serviceRequestPayment;
	private Date creationDate;
	private Boolean status;
	private String paypalTransactionId;
	private RefundRequest refundRequest;

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "serviceRequestPaymentid", nullable = false)
	public ServiceRequestPayment getServiceRequestPayment() {
		return this.serviceRequestPayment;
	}

	public void setServiceRequestPayment(ServiceRequestPayment serviceRequestPayment) {
		this.serviceRequestPayment = serviceRequestPayment;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creationdate", nullable = false, length = 29)
	public Date getCreationdate() {
		return this.creationDate;
	}

	public void setCreationdate(Date creationdate) {
		this.creationDate = creationdate;
	}

	@Column(name = "status", nullable = false)
	public Boolean isStatus() {
		return this.status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	@Column(name = "paypaltransactionid", nullable = false, length = 200)
	public String getPaypaltransactionid() {
		return this.paypalTransactionId;
	}

	public void setPaypaltransactionid(String paypaltransactionid) {
		this.paypalTransactionId = paypaltransactionid;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "transfer")
	public RefundRequest getRefundRequest() {
		return this.refundRequest;
	}

	public void setRefundRequest(RefundRequest refundRequest) {
		this.refundRequest = refundRequest;
	}

}
