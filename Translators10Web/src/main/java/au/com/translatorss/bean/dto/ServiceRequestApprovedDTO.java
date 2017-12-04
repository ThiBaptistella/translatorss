package au.com.translatorss.bean.dto;

public class ServiceRequestApprovedDTO {

	private String month;
	private int amount;
	
	public ServiceRequestApprovedDTO(){
		
	}
	
	public String getMonth() {
		return month;
	}
	
	public void setMonth(String month) {
		this.month = month;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
}
