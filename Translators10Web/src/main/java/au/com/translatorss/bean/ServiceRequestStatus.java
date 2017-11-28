package au.com.translatorss.bean;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Servicerequeststatus 
 */
@Entity
@Table(name = "serviceRequeststatus")
public class ServiceRequestStatus implements java.io.Serializable {

	private static final long serialVersionUID = 4147868891224875067L;
	private Integer id;
	private String description;
	private String shortDescription;

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "description", nullable = false, length = 200)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "shortDescription", nullable = false, length = 50)
	public String getShortDescription() {
		return this.shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}



}
