package au.com.translatorss.bean;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Translatorlanguage 
 */
@Entity
@Table(name = "translator_language")
public class TranslatorLanguage implements java.io.Serializable {

	private static final long serialVersionUID = 7301415032108097027L;
	private Long id;
	private Language language;
	private Translator translator;

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "language_id", nullable = false)
	public Language getLanguage() {
		return this.language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "translator_id", nullable = false)
	public Translator getTranslator() {
		return this.translator;
	}

	public void setTranslator(Translator translator) {
		this.translator = translator;
	}

}
