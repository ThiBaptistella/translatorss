package au.com.translatorss.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Logintry {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "userid")
	private User user;

	private Long tries;

	public Logintry() {
		// TODO Auto-generated constructor stub
	}

	public Logintry(User user) {
		this.user = user;
		tries = 1l;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getTries() {
		return tries;
	}

	public void setTries(Long tries) {
		this.tries = tries;
	}

	public void increaseTries() {
		this.tries = this.tries + 1;
	}

}
