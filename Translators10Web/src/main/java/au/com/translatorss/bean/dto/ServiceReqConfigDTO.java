package au.com.translatorss.bean.dto;

public class ServiceReqConfigDTO {

	private int minquote;
	private int timeper;
	private int quoteValue;

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
	
	public int getQuoteValue() {
		return quoteValue;
	}

	public void setQuoteValue(int quoteValue) {
		this.quoteValue = quoteValue;
	}
}
