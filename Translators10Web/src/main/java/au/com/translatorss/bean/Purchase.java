package au.com.translatorss.bean;


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * Purchase 
 */
@Entity
@Table(name = "purchase")
public class Purchase implements java.io.Serializable {

	private static final long serialVersionUID = 1146097597800715294L;
	private Long id;
	private PurchaseType purchaseType;
	private Translator translator;
	private Date date;
	private Date expireDate;
	private String paypalTransactionId;
	private BigDecimal value;
	private Integer remaininDays;
//    private Set<PauseSuscription> pauseSuscriptionList = new HashSet<PauseSuscription>();

	
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
	@JoinColumn(name = "purchasetypeid", nullable = false)
	public PurchaseType getPurchasetype() {
		return this.purchaseType;
	}

	public void setPurchasetype(PurchaseType purchasetype) {
		this.purchaseType = purchasetype;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "translatorid", nullable = false)
	public Translator getTranslator() {
		return this.translator;
	}

	public void setTranslator(Translator translator) {
		this.translator = translator;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date", nullable = false, length = 29)
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "expireDate", nullable = false, length = 29)
	public Date getExpireDate() {
		return this.expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	@Column(name = "paypalTransactionId", nullable = false, length = 200)
	public String getPaypalTransactionId() {
		return this.paypalTransactionId;
	}

	public void setPaypalTransactionId(String paypalTransactionId) {
		this.paypalTransactionId = paypalTransactionId;
	}

	@Column(name = "value", nullable = false, precision = 131089, scale = 0)
	public BigDecimal getValue() {
		return this.value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	@Column(name = "remainingdays", nullable = false)
    public Integer getRemaininDays() {
        return remaininDays;
    }

    public void setRemaininDays(Integer remaininDays) {
        this.remaininDays = remaininDays;
    }

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "purchase")
//    public Set<PauseSuscription> getPauseSuscriptionList() {
//        return pauseSuscriptionList;
//    }
//
//    public void setPauseSuscriptionList(Set<PauseSuscription> pauseSuscriptionList) {
//        this.pauseSuscriptionList = pauseSuscriptionList;
//    }

}
