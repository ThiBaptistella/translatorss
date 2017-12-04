package au.com.translatorss.bean;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

/**
 * Serviceresponse 
 */
@Entity
@Table(name = "serviceresponse")
public class ServiceResponse implements java.io.Serializable {

	private static final long serialVersionUID = -6675082750151112237L;
	private Long id;
	private ServiceResponseStatus serviceResponseStatus;
	private Translator translator;
	private String description;
	private Date creationDate;
	private Date updateDate;
	private Set<ServiceResponseFiles> serviceResponseFiles = new HashSet<ServiceResponseFiles>();
	private Set<AmazonFile> amazonFiles = new HashSet<AmazonFile>();

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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "serviceResponseStatusid", nullable = false)
	public ServiceResponseStatus getServiceResponseStatus() {
		return this.serviceResponseStatus;
	}

	public void setServiceResponseStatus(ServiceResponseStatus serviceResponseStatus) {
		this.serviceResponseStatus = serviceResponseStatus;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "translatorid", nullable = false)
	public Translator getTranslator() {
		return this.translator;
	}

	public void setTranslator(Translator translator) {
		this.translator = translator;
	}

	@Column(name = "description", nullable = false)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creationDate", nullable = false, length = 29)
	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updateDate", nullable = false, length = 29)
	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "serviceResponse", orphanRemoval=true)
	public Set<ServiceResponseFiles> getServiceResponseFiles() {
		return this.serviceResponseFiles;
	}

	public void setServiceResponseFiles(Set<ServiceResponseFiles> serviceResponseFiles) {
		this.serviceResponseFiles = serviceResponseFiles;
	}

	@OneToMany(mappedBy = "serviceResponse", orphanRemoval=true)
	public Set<AmazonFile> getAmazonFiles() {
		return amazonFiles;
	}

	public void setAmazonFiles(Set<AmazonFile> amazonFiles) {
		this.amazonFiles = amazonFiles;
	}

}
