package au.com.translatorss.bean;


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
 * Refundrequest 
 */
@Entity
@Table(name = "refundrequest")
public class RefundRequest implements java.io.Serializable {

	private static final long serialVersionUID = -5017015638888588475L;
	private Long id;
	private Transfer transfer;
	private Date creationDate;
	private String description;

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "quotationid", nullable = false)
	public Transfer getTransfer() {
		return this.transfer;
	}

	public void setTransfer(Transfer transfer) {
		this.transfer = transfer;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creationdate", nullable = false, length = 29)
	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationdate) {
		this.creationDate = creationdate;
	}

	@Column(name = "description", nullable = false, length = 500)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


}
