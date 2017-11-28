package au.com.translatorss.bean.dto;

import java.util.Date;

public class BusinessUserDTO {

    private String email;
    private String paypalid;
	private Date creationdate;
    private Date updatedate;
    private String fullname;
    private String preferedname;
    private String phone;
    private String address;
    private String password;
	private Long id;

	public BusinessUserDTO(){
        
    }

    public String getPreferedname() {
        return preferedname;
    }

    public void setPreferedname(String preferedname) {
        this.preferedname = preferedname;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreationdate() {
        return creationdate;
    }

    public void setCreationdate(Date creationdate) {
        this.creationdate = creationdate;
    }

    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
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
    
    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPaypalid() {
		return paypalid;
	}

	public void setPaypalid(String paypalid) {
		this.paypalid = paypalid;
	}
}
