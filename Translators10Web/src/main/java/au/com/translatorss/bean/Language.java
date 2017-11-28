package au.com.translatorss.bean;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Language 
 */
@Entity
@Table(name = "language")
public class Language implements java.io.Serializable {

	private static final long serialVersionUID = 6945428659836229211L;
	private Integer id;
	private String description;


	@Id
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "description", nullable = false, length = 150)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


}
