package au.com.translatorss.bean.dto;

public class QuoteSettingDTO {

	private Long translatorId;
	private String driverLic;
    private String birthCertificate;
	private String marriageCertificate;
    private String passport;
    private boolean valid;
	

	public QuoteSettingDTO() {
	}

	public String getBirthCertificate() {
		return birthCertificate;
	}

	public void setBirthCertificate(String birthCertificate) {
		this.birthCertificate = birthCertificate;
	}

	public String getMarriageCertificate() {
		return marriageCertificate;
	}

	public void setMarriageCertificate(String marriageCertificate) {
		this.marriageCertificate = marriageCertificate;
	}

	public Long getTranslatorId() {
		return translatorId;
	}

	public void setTranslatorId(Long translatorId) {
		this.translatorId = translatorId;
	}
	
	public String getDriverLic() {
		return driverLic;
	}
	
	public void setDriverLic(String driverLic) {
		this.driverLic = driverLic;
	}
	
	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}
	
	public boolean getValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}
}
