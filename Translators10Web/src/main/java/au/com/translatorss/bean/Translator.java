package au.com.translatorss.bean;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import javax.persistence.*;
import java.util.*;

/**
 * Translator
 */
@Entity
@Table(name = "translator")
public class Translator implements java.io.Serializable {

	private static final long serialVersionUID = -892550799336418558L;
	private Long id;
	private String paypalClientId;
	private String naatiNumber;
	private String phone;
	private String address;
	private String status;
	private Set<Rate> rates ;
	private List<Language> languageList = new ArrayList<>(0);
	private Set<Purchase> purchaseList = new HashSet<Purchase>(0);
	private Set<Conversation> conversationList = new HashSet<Conversation>(0);
	private Set<QuotationStandar> standarValuesList = new HashSet<QuotationStandar>(0);
	private TranslatorStatusFlags translatorStatusFlags;
	private User user = new User();
	private Date natyExpiration;
	private Date expireSuscriptionDate;
	private Integer remaininDays;
	private String abn_name;
	private String abn_number;
	private String abn_address;
	private String fullname;



	@Id
    @SequenceGenerator(name = "translator_sequence", sequenceName = "translatorId_secuence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "translator_sequence")
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "paypalClientId", length = 200)
	public String getPaypalClientId() {
		return this.paypalClientId;
	}

	public void setPaypalClientId(String paypalClientId) {
		this.paypalClientId = paypalClientId;
	}

	@Column(name = "naatiNumber", nullable = false, length = 50)
	public String getNaatiNumber() {
		return this.naatiNumber;
	}

	public void setNaatiNumber(String naatiNumber) {
		this.naatiNumber = naatiNumber;
	}

	@Column(name = "phone", nullable = false, length = 50)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "address", nullable = false, length = 300)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "status", nullable = false, length = 50)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinTable(name = "translator_language", joinColumns = { @JoinColumn(name = "translator_id") }, inverseJoinColumns = { @JoinColumn(name = "language_id") })
	public List<Language> getLanguageList() {
		return languageList;
    }

	public void setLanguageList(List<Language> languageList) {
		this.languageList = languageList;
	}

    @OneToOne(fetch = FetchType.EAGER, cascade = { CascadeType.ALL }, targetEntity = TranslatorStatusFlags.class)
    @JoinColumns({ @JoinColumn(name = "statusflagid" /*transTabla*/, referencedColumnName = "id"/*statusTable*/) })
	public TranslatorStatusFlags getTranslatorStatusFlags() {
        return translatorStatusFlags;
    }

    public void setTranslatorStatusFlags(TranslatorStatusFlags translatorStatusFlags) {
        this.translatorStatusFlags = translatorStatusFlags;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(iso = ISO.DATE)
    @Column(name = "natyexpiration")
    public Date getNatyExpiration() {
        return natyExpiration;
    }

    public void setNatyExpiration(Date natyExpiration) {
        this.natyExpiration = natyExpiration;
    }

    public boolean ContainsLanguage(String languageTo) {
        for(Language language:languageList){
            if(language.getDescription().equals(languageTo)){
                return true;
            }
        }
        return false;
    }


    public boolean IsAvailable() {
        if("Paused".equals(status)){
            return false;
        }
        return true;
    }

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "translator")
	public Set<Rate> getRates() {
		return rates;
	}

	public void setRates(Set<Rate> rates) {
		this.rates = rates;
	}

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "translator")
	public Set<Purchase> getPurchaseList() {
		return purchaseList;
	}

	public void setPurchaseList(Set<Purchase> purchaseList) {
		this.purchaseList = purchaseList;
	}

	@Column(name = "remainingdays", nullable = false)
	public Integer getRemaininDays() {
		return remaininDays;
	}

	public void setRemaininDays(Integer remaininDays) {
		this.remaininDays = remaininDays;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "expiresuscriptiondate", nullable = false, length = 29)
	public Date getExpireSuscriptionDate() {
		return expireSuscriptionDate;
	}

	public void setExpireSuscriptionDate(Date expireSuscriptionDate) {
		this.expireSuscriptionDate = expireSuscriptionDate;
	}

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "translator", orphanRemoval=true)
	public Set<Conversation> getConversationList() {
		return conversationList;
	}

	public void setConversationList(Set<Conversation> conversationList) {
		this.conversationList = conversationList;
	}

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Column(name = "abn_name", nullable = false)
	public String getAbn_name() {
		return abn_name;
	}

	public void setAbn_name(String abn_name) {
		this.abn_name = abn_name;
	}
	
	@Column(name = "abn_number", nullable = false)
	public String getAbn_number() {
		return abn_number;
	}

	public void setAbn_number(String abn_number) {
		this.abn_number = abn_number;
	}

	@Column(name = "fullname", nullable = false)
	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "translator", orphanRemoval=true)
    public Set<QuotationStandar> getStandarValuesList() {
		return standarValuesList;
	}

	public void setStandarValuesList(Set<QuotationStandar> standarValuesList) {
		this.standarValuesList = standarValuesList;
	}

	@Column(name = "abn_address")
	public String getAbn_address() {
		return abn_address;
	}

	public void setAbn_address(String abn_address) {
		this.abn_address = abn_address;
	}
}
