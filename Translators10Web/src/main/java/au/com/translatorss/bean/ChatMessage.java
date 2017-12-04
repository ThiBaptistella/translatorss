package au.com.translatorss.bean;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "message")
public class ChatMessage {

    private Long id;
    private String sender;
    private Long senderId;
    private Long receiverId;
    private String message;
    private Date date;
    private Conversation conversation;
    private boolean read = false;

	public ChatMessage(){
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

    @Column(name = "sender", unique = true, nullable = false)
    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    @Column(name = "message", unique = true, nullable = false, length = 500)
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date", length = 29)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "conversationid", nullable = false)
    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }

//    @Column(name = "is_read", columnDefinition = "BOOLEAN default TRUE")
//    public boolean isRead() {
//        return isRead;
//    }
//
//    public void setRead(boolean read) {
//        isRead = read;
//    }

    @Column(name = "senderId")
    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    @Column(name = "is_read", columnDefinition = "BOOLEAN default TRUE")
	public boolean getRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
	}

    @Column(name = "receiverId")
	public Long getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(Long receiverId) {
		this.receiverId = receiverId;
	}

}
