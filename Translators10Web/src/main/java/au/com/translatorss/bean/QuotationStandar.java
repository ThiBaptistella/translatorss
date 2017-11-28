package au.com.translatorss.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name = "quotationstandar")
public class QuotationStandar implements java.io.Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
    private Translator translator;
	private ServiceRequestCategory category;
	private TimeFrame timeFrame;
    private Integer value;
    private boolean valid;
    private Date updateDate;
    

	public QuotationStandar(){}

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
	
    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "translatorid", nullable = false)
	public Translator getTranslator() {
		return translator;
	}

	public void setTranslator(Translator translator) {
		this.translator = translator;
	}

	 @ManyToOne(fetch = FetchType.EAGER)
	 @JoinColumn(name = "servicerequestcategoryid")	
	 public ServiceRequestCategory getCategory() {
		return category;
	}

	public void setCategory(ServiceRequestCategory category) {
		this.category = category;
	}

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "timeframeid", nullable = false)
	public TimeFrame getTimeFrame() {
		return timeFrame;
	}

	public void setTimeFrame(TimeFrame timeFrame) {
		this.timeFrame = timeFrame;
	}

    @Column(name = "value", unique = true, nullable = false)
	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

    @Column(name = "valid", unique = true, nullable = false)
	public boolean getValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
}
