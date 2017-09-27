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
import javax.persistence.Version;

/**
 * Changerequest 
 */
@Entity
@Table(name = "changerequest")
public class ChangeRequest implements java.io.Serializable {

	private static final long serialVersionUID = 8233649716547542007L;
	private Long id;
	private Date timestamp;
	private ServiceResponse ServiceResponse;
	private String description;

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Version
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "timestamp", nullable = false, length = 13)
	public Date getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "serviceResponseId", nullable = false)
	public ServiceResponse getServiceResponse() {
		return this.ServiceResponse;
	}

	public void setServiceResponse(ServiceResponse ServiceResponse) {
		this.ServiceResponse = ServiceResponse;
	}

	@Column(name = "description", nullable = false)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
