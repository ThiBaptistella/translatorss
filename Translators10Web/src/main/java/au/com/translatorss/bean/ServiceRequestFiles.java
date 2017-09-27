package au.com.translatorss.bean;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * Servicerequestfiles 
 */
@Entity
@Table(name = "serviceRequestfiles")
public class ServiceRequestFiles implements java.io.Serializable {

	private static final long serialVersionUID = -1610631524672950490L;
	private Long id;
	private ServiceRequest serviceRequest;
	private String url;
	private byte[] file;
	private boolean original;
	private Date creationDae;

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
	@JoinColumn(name = "serviceRequestid", nullable = false)
	public ServiceRequest getServiceRequest() {
		return this.serviceRequest;
	}

	public void setServiceRequest(ServiceRequest serviceRequest) {
		this.serviceRequest = serviceRequest;
	}

	@Column(name = "url", length = 500)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "file")
	public byte[] getFile() {
		return this.file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	@Column(name = "original", nullable = false)
	public boolean getOriginal() {
        return original;
    }

    public void setOriginal(boolean original) {
        this.original = original;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_date", nullable = false, length = 29)
    public Date getCreationDae() {
        return creationDae;
    }

    public void setCreationDae(Date creationDae) {
        this.creationDae = creationDae;
    }
}
