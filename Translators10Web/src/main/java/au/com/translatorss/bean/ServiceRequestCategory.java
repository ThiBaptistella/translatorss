package au.com.translatorss.bean;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Servicerequestcategory 
 */
@Entity
@Table(name = "servicerequestcategory")
public class ServiceRequestCategory implements java.io.Serializable {

	private static final long serialVersionUID = -6710798214280718669L;
	private Integer id;
	private String description;
	private boolean defaultPrice;

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "description", nullable = false, length = 300)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "defaultprice", nullable = false)
	public boolean getDefaultPrice() {
		return this.defaultPrice;
	}

	public void setDefaultPrice(boolean defaultprice) {
		this.defaultPrice = defaultprice;
	}
}
