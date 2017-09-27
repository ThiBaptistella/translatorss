package au.com.translatorss.bean.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class TranslatorDTO {

	private Long id;
	private Long userId;
	private Integer remainingDays;
	private Long deliveryTime;
	private Long customerService;
	private Long quality;
    private String paypalClientId;
    private String name;
    private String fullName;
    private String phone;
    private String address;
    private String email;
    private String password;
    private String naatiNumber;
    private String status;
    private String abn_number;
	private String abn_name;
	private String abn_address;
	private String preferedName;
    private Date natyExpiration; 
    private String language;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    //flags
    private boolean natyVerified; 
    private boolean natyExtiryDate; 
    private boolean validSuscription;
    private boolean manualyPaused ;
    private boolean inactiveCancelled; 
    private boolean inactiveRefunded ;

    
    public TranslatorDTO(){
        
    }
    
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
    
    public boolean getNatyVerified() {
        return natyVerified;
    }

    public void setNatyVerified(boolean natyVerified) {
        this.natyVerified = natyVerified;
    }

    public boolean getNatyExtiryDate() {
        return natyExtiryDate;
    }

    public void setNatyExtiryDate(boolean natyExtiryDate) {
        this.natyExtiryDate = natyExtiryDate;
    }

    public boolean getValidSuscription() {
        return validSuscription;
    }

    public void setValidSuscription(boolean validSuscription) {
        this.validSuscription = validSuscription;
    }

    public boolean getManualyPaused() {
        return manualyPaused;
    }

    public void setManualyPaused(boolean manualyPaused) {
        this.manualyPaused = manualyPaused;
    }

    public boolean getInactiveCancelled() {
        return inactiveCancelled;
    }

    public void setInactiveCancelled(boolean inactiveCancelled) {
        this.inactiveCancelled = inactiveCancelled;
    }

    public boolean getInactiveRefunded() {
        return inactiveRefunded;
    }

    public void setInactiveRefunded(boolean inactiveRefunded) {
        this.inactiveRefunded = inactiveRefunded;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPaypalClientId() {
        return paypalClientId;
    }

    public void setPaypalClientId(String paypalClientId) {
        this.paypalClientId = paypalClientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @DateTimeFormat(iso = ISO.DATE)
    public Date getNatyExpiration() {
        return natyExpiration;
    }

    public void setNatyExpiration(Date natyExpiration) {
        this.natyExpiration = natyExpiration;
    }

    public String getNaatiNumber() {
        return naatiNumber;
    }

    public void setNaatiNumber(String naatiNumber) {
        this.naatiNumber = naatiNumber;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
 
    public Integer getRemainingDays() {
        return remainingDays;
    }

    public void setRemainingDays(Integer remainingDays) {
        this.remainingDays = remainingDays;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public String getAbn_number() {
		return abn_number;
	}

	public void setAbn_number(String abn_number) {
		this.abn_number = abn_number;
	}

    public String getAbn_name() {
		return abn_name;
	}

	public void setAbn_name(String abn_name) {
		this.abn_name = abn_name;
	}
	
	public String getAbn_address() {
		return abn_address;
	}

	public void setAbn_address(String abn_address) {
		this.abn_address = abn_address;
	}

	public String getPreferedName() {
		return preferedName;
	}

	public void setPreferedName(String preferedName) {
		this.preferedName = preferedName;
	}

	public Long getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Long deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public Long getCustomerService() {
		return customerService;
	}

	public void setCustomerService(Long customerService) {
		this.customerService = customerService;
	}

	public Long getQuality() {
		return quality;
	}

	public void setQuality(Long quality) {
		this.quality = quality;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
}