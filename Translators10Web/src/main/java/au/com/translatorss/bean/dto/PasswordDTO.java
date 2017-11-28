package au.com.translatorss.bean.dto;

public class PasswordDTO {

    private String email;
    private String currentPassword;
    private String newPassword;
    private String confirmNewPassword;
    private String type;
    
    public PasswordDTO(){
        
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmNewPassword() {
        return confirmNewPassword;
    }

    public void setConfirmNewPassword(String confirmNewPassword) {
        this.confirmNewPassword = confirmNewPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
