package au.com.translatorss.bean.dto;

public class QuoteSettingDTO {

	private Long translatorId;
	private String driverLic;
    private String birthDeath;
    private String academicTranscript;
    private String passport;
    private boolean valid;
	

	public QuoteSettingDTO() {
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
	
	public String getBirthDeath() {
		return birthDeath;
	}
	
	public void setBirthDeath(String birthDeath) {
		this.birthDeath = birthDeath;
	}
	
	public String getAcademicTranscript() {
		return academicTranscript;
	}
	
	public void setAcademicTranscript(String academicTranscript) {
		this.academicTranscript = academicTranscript;
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
