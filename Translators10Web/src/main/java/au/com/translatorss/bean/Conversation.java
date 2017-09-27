package au.com.translatorss.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


@Entity
@Table(name = "conversation")
public class Conversation implements java.io.Serializable{

    private static final long serialVersionUID = 2418188043811877317L;
    private Long id;
    private String sender;
    private String lastMessage;
    private Date updated;
    private ServiceRequest serviceRequest;
    private Translator translator;
    private Set<ChatMessage> messageList = new HashSet<ChatMessage>();
    private ServiceResponse serviceResponse;
    
    
    public Conversation(){
        
    }

	@Id
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator = "generator")
    @Column(name = "conversation_id", unique = true, nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    @Column(name = "sender", nullable = false, length = 50)
    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    @Column(name = "lastMessage", nullable = false, length = 50)
    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated", nullable = false, length = 29)
    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "conversation")
    public Set<ChatMessage> getMessageList() {
        return messageList;
    }

    public void setMessageList(Set<ChatMessage> messageList) {
        this.messageList = messageList;
    }

    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "serviceRequestid", nullable = false)
    public ServiceRequest getServiceRequest() {
        return serviceRequest;
    }

    public void setServiceRequest(ServiceRequest serviceRequest) {
        this.serviceRequest = serviceRequest;
    }

    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "translatorid", nullable = false)
	public Translator getTranslator() {
		return translator;
	}

	public void setTranslator(Translator translator) {
		this.translator = translator;
	}

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval=true)
    @JoinColumn(name = "serviceresponseid")
	public ServiceResponse getServiceResponse() {
		return serviceResponse;
	}

	public void setServiceResponse(ServiceResponse serviceResponse) {
		this.serviceResponse = serviceResponse;
	}
}
