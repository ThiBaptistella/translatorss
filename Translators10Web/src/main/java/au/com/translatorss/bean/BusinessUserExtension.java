package au.com.translatorss.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "businessuser_ext")
public class BusinessUserExtension implements java.io.Serializable{

	private Long id;
	private String nextSequenceNumber;
	private BusinessUser businessUser;
	
	public BusinessUserExtension(){
		
	}
	
	@Id
	@Column(name = "id_bu", unique = true, nullable = false, length = 32)
	public Long getId() {
	   return id;
	}

	public void setId(Long id) {
	   this.id = id;
	}
	
    @Column(name = "sec_num_next", length = 4)
    public String getNextSequenceNumber() {
        return this.nextSequenceNumber;
    }

    public void setNextSequenceNumber(String nextSequenceNumber) {
        this.nextSequenceNumber = nextSequenceNumber;
    }
	
    @MapsId
    @OneToOne
    @JoinColumn(name = "id_bu")
    public BusinessUser getBusinessUser() {
		return businessUser;
	}

	public void setBusinessUser(BusinessUser businessUser) {
		this.businessUser = businessUser;
	}

}
