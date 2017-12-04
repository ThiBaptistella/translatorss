package au.com.translatorss.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "servicerequestconfiguration")
public class ServiceRequestConfiguration {

    private Long id;
    private int minimumMarket;
    private int hoursLeft;
    private int standarQuoteVealue;
    
   

	public ServiceRequestConfiguration(){}

    @Id
    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "minimunmarket", unique = true, nullable = false)
    public int getMinimumMarket() {
        return minimumMarket;
    }

    public void setMinimumMarket(int minimumMarket) {
        this.minimumMarket = minimumMarket;
    }
    
    @Column(name = "hoursleft", unique = true, nullable = false)
    public int getHoursLeft() {
        return hoursLeft;
    }

    public void setHoursLeft(int hoursLeft) {
        this.hoursLeft = hoursLeft;
    }

    
    @Column(name = "standar_quote_value", unique = true, nullable = false)
    public int getStandarQuoteVealue() {
		return standarQuoteVealue;
	}

	public void setStandarQuoteVealue(int standarQuoteVealue) {
		this.standarQuoteVealue = standarQuoteVealue;
	}
}
