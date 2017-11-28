package au.com.translatorss.bean;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Purchasetype 
 */
@Entity
@Table(name = "purchasetype")
public class PurchaseType implements java.io.Serializable {

	private static final long serialVersionUID = 5833290720263719195L;
	private Long id;
	private String description;
	private String ShortDescription;
	private Integer quantityDays;

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "description", nullable = false, length = 200)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "ShortDescription", nullable = false, length = 50)
	public String getShortDescription() {
		return this.ShortDescription;
	}

	public void setShortDescription(String ShortDescription) {
		this.ShortDescription = ShortDescription;
	}

	@Column(name = "quantityDays", nullable = false)
	public Integer getQuantityDays() {
		return this.quantityDays;
	}

	public void setQuantityDays(Integer quantityDays) {
		this.quantityDays = quantityDays;
	}



}
