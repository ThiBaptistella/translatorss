package au.com.translatorss.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "translatorstatusflags")
public class TranslatorStatusFlags {

    private Long id;
    private boolean natyVerified; 
    private boolean natyExtiryDate; 
    private boolean validSuscription;
    private boolean manualyPaused ;
    private boolean inactiveCancelled; 
    private boolean inactiveRefunded;
    
    public TranslatorStatusFlags(){
        
    }

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

    @Column(name = "naty_verified", nullable = false)
    public boolean getNatyVerified() {
        return natyVerified;
    }

    public void setNatyVerified(boolean natyVerified) {
        this.natyVerified = natyVerified;
    }

    @Column(name = "naty_extiry_date", nullable = false)
    public boolean getNatyExtiryDate() {
        return natyExtiryDate;
    }

    public void setNatyExtiryDate(boolean natyExtiryDate) {
        this.natyExtiryDate = natyExtiryDate;
    }

    @Column(name = "valid_suscription", nullable = false)
    public boolean getValidSuscription() {
        return validSuscription;
    }

    public void setValidSuscription(boolean validSuscription) {
        this.validSuscription = validSuscription;
    }

    @Column(name = "manualy_paused", nullable = false)
    public boolean getManualyPaused() {
        return manualyPaused;
    }

    public void setManualyPaused(boolean manualyPaused) {
        this.manualyPaused = manualyPaused;
    }

    @Column(name = "incactive_cancelled", nullable = false)
    public boolean getInactiveCancelled() {
        return inactiveCancelled;
    }

    public void setInactiveCancelled(boolean inactiveCancelled) {
        this.inactiveCancelled = inactiveCancelled;
    }

    @Column(name = "inactive_refunded", nullable = false)
    public boolean getInactiveRefunded() {
        return inactiveRefunded;
    }

    public void setInactiveRefunded(boolean inactiveRefunded) {
        this.inactiveRefunded = inactiveRefunded;
    }
}
