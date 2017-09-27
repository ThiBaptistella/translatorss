package au.com.translatorss.bean;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * Rate 
 */
@Entity
@Table(name = "rate")
public class Rate implements java.io.Serializable {

	private static final long serialVersionUID = -3411530701216874347L;
	private Long id;
	private Customer customer;
	private Translator translator;
	private Integer timeDelivery;
	private Integer serviceAsDescribed;
	private Integer quality;
	private String feedback;
	private ServiceRequest serviceRequest;

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
	@JoinColumn(name = "customerid", nullable = false)
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "translatorid", nullable = false)
	public Translator getTranslator() {
		return this.translator;
	}

	public void setTranslator(Translator translator) {
		this.translator = translator;
	}

	@Column(name = "serviceAsDescribed", nullable = false)
	public Integer getServiceAsDescribed() {
		return this.serviceAsDescribed;
	}

	public void setServiceAsDescribed(Integer serviceAsDescribed) {
		this.serviceAsDescribed = serviceAsDescribed;
	}

	@Column(name = "feedback", nullable = false)
	public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }


	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "servicerequestid", nullable = false)
	public ServiceRequest getServiceRequest() {
		return serviceRequest;
	}

	public void setServiceRequest(ServiceRequest serviceRequest) {
		this.serviceRequest = serviceRequest;
	}

	@Column(name = "timedelivery", nullable = false)
	public Integer getTimeDelivery() {
		return timeDelivery;
	}

	public void setTimeDelivery(Integer timeDelivery) {
		this.timeDelivery = timeDelivery;
	}

	@Column(name = "quality", nullable = false)
	public Integer getQuality() {
		return quality;
	}

	public void setQuality(Integer quality) {
		this.quality = quality;
	}
}
