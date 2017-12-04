package au.com.translatorss.bean;


import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Refund 
 */
@Entity
@Table(name = "refund")
public class Refund implements java.io.Serializable {

	private static final long serialVersionUID = -2398816068376217489L;
	private Long id;
	private RefundRequest refundRequest;
	private BigDecimal value;
	private Date creationDate;

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "refundRequestid", nullable = false)
	public RefundRequest getRefundRequest() {
		return this.refundRequest;
	}

	public void setRefundRequest(RefundRequest refundRequest) {
		this.refundRequest = refundRequest;
	}

	@Column(name = "value", nullable = false, precision = 131089, scale = 0)
	public BigDecimal getValue() {
		return this.value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creationDate", nullable = false, length = 29)
	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

}
