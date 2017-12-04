package au.com.translatorss.realtime;


public class Message {
    private Object content;
    private MessageType type;

    public Message(Object content, MessageType type) {
        this.content = content;
        this.type = type;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public Object getContent() {
        return content;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }
}
