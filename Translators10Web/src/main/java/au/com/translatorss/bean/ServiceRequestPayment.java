package au.com.translatorss.bean;


import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

/**
 * Servicerequestpayment 
 */
@Entity
@Table(name = "servicerequestpayment")
public class ServiceRequestPayment implements java.io.Serializable {

	private static final long serialVersionUID = 5451267971893833575L;
	private Long id;
	private ServiceRequest serviceRequest;
	private String paypalTransactionId;
	private BigDecimal value;

	@Id
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    @Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "servicerequesid", nullable = false)
	@Fetch(FetchMode.SELECT)
	public ServiceRequest getServiceRequest() {
		return this.serviceRequest;
	}

	public void setServiceRequest(ServiceRequest servicerequest) {
		this.serviceRequest = servicerequest;
	}

	@Column(name = "paypalTransactionId", nullable = false, length = 200)
	public String getPaypalTransactionId() {
		return this.paypalTransactionId;
	}

	public void setPaypalTransactionId(String paypalTransactionId) {
		this.paypalTransactionId = paypalTransactionId;
	}

	@Column(name = "value", nullable = false, precision = 131089, scale = 0)
	public BigDecimal getValue() {
		return this.value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

}
