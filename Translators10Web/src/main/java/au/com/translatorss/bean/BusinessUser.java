package au.com.translatorss.bean;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Businessuser 
 */
@Entity
@Table(name = "businessuser")
@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
public class BusinessUser extends Customer implements java.io.Serializable {

	private static final long serialVersionUID = -4881501726052203574L;
	
	private String acnabn;
			
	private BusinessUserExtension businessUserExtension;
	
	@Column(name = "acnabn", nullable = false, length = 50)
	public String getAcnabn() {
		return this.acnabn;
	}

	public void setAcnabn(String acnabn) {
		this.acnabn = acnabn;
	}

	@OneToOne(fetch = FetchType.EAGER, cascade = { CascadeType.ALL }, targetEntity = BusinessUserExtension.class)
    @PrimaryKeyJoinColumn
	public BusinessUserExtension getBusinessUserExtension() {
		return businessUserExtension;
	}

	public void setBusinessUserExtension(BusinessUserExtension businessUserExtension) {
		this.businessUserExtension = businessUserExtension;
	}
	
}
