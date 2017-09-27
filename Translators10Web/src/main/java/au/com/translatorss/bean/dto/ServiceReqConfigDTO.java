package au.com.translatorss.bean.dto;

public class ServiceReqConfigDTO {

	public int getMinquote() {
		return minquote;
	}
	
	public ServiceReqConfigDTO(){
		
	}
	public void setMinquote(int minquote) {
		this.minquote = minquote;
	}
	public int getTimeper() {
		return timeper;
	}
	public void setTimeper(int timeper) {
		this.timeper = timeper;
	}
	private int minquote;
	private int timeper;
}
