package au.com.translatorss.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

public class PauseSuscription {

    private Long id;
    private Date startDate;
    private Date finishDate;
    private String reason;
    private Purchase purchase;
    
    @Id
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "startdate", length = 29)
    public Date getStartDate() {
        return startDate;
    }
    
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "finishdate", length = 29)
    public Date getFinishDate() {
        return finishDate;
    }
    
    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }
    
    @Column(name = "reason", unique = true, nullable = false)
    public String getReason() {
        return reason;
    }
    
    public void setReason(String reason) {
        this.reason = reason;
    }
    
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "purchaseid", nullable = false)
//    public Purchase getPurchase() {
//        return purchase;
//    }
//    public void setPurchase(Purchase purchase) {
//        this.purchase = purchase;
//    }
}
