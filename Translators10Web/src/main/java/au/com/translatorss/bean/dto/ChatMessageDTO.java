package au.com.translatorss.bean.dto;

import java.util.Date;


public class ChatMessageDTO implements Comparable<ChatMessageDTO>{

    private long id;
    private String sender;
    private String message;
    private Date date;
    private String DateFormat;
	private Long conversationid;
    private String photoUrl;
    private boolean isRead;
    
    public ChatMessageDTO(){
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getConversationid() {
        return conversationid;
    }

    public void setConversationid(Long conversationid) {
        this.conversationid = conversationid;
    }

    @Override
    public int compareTo(ChatMessageDTO o) {
        if(this.date.before(o.getDate())){
            return -1;
        }
        if(this.date.after(o.getDate())){
            return 1;
        }
        return 0;
    }


    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	
	public String getDateFormat() {
		return DateFormat;
	}

	public void setDateFormat(String dateFormat) {
		DateFormat = dateFormat;
	}

}
