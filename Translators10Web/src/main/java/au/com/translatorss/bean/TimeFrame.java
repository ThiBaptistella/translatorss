package au.com.translatorss.bean;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Timeframe 
 */
@Entity
@Table(name = "timeFrame")
public class TimeFrame implements java.io.Serializable {

	private static final long serialVersionUID = 1076430488288947493L;
	private Integer id;
	private String description;
	private String shortDescription;
	private Integer timeFrame;

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "description", nullable = false, length = 500)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "shortDescription", nullable = false, length = 100)
	public String getShortDescription() {
		return this.shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	@Column(name = "timeFrame", nullable = false)
	public Integer getTimeFrame() {
		return this.timeFrame;
	}

	public void setTimeFrame(Integer timeFrame) {
		this.timeFrame = timeFrame;
	}

}
