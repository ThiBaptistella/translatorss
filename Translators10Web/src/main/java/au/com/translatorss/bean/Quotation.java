package au.com.translatorss.bean;





import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * Quotation 
 */
@Entity
@Table(name = "quotation")
public class Quotation implements java.io.Serializable {

	private static final long serialVersionUID = 2418188043811877317L;
	private Long id;
	private ServiceRequest serviceRequest;
	private Translator translator;
	private BigDecimal value;
	private Boolean isAutomatic;
    private Boolean isValid;

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
	
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Translator.class, cascade = {})   
    @JoinColumn(name = "translatorid", nullable = false)
	public Translator getTranslator() {
		return this.translator;
	}

	public void setTranslator(Translator translator) {
		this.translator = translator;
	}

	@Column(name = "value", nullable = false, precision = 131089, scale = 0)
	public BigDecimal getValue() {
		return this.value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	@Column(name = "valid")
    public Boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }
    
    @Column(name = "automatic")
    public Boolean getIsAutomatic() {
        return isAutomatic;
    }

    public void setIsAutomatic(Boolean isAutomatic) {
        this.isAutomatic = isAutomatic;
    }
}
