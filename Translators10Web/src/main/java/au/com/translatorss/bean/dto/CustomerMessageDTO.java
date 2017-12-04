package au.com.translatorss.bean.dto;

import java.util.Date;

public class CustomerMessageDTO {

    private String sender;
    private Date update;
    private String message;

    public CustomerMessageDTO(){
        
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Date getUpdate() {
        return update;
    }

    public void setUpdate(Date update) {
        this.update = update;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
