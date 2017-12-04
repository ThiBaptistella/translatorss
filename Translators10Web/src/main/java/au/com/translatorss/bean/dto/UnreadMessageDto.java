package au.com.translatorss.bean.dto;

public class UnreadMessageDto {

    private Integer count;
    private Long conversationId;

    public UnreadMessageDto(Long conversationId, Integer count) {
        this.count = count;
        this.conversationId = conversationId;
    }

    public Long getConversationId() {
        return conversationId;
    }

    public Integer getCount() {
        return count;
    }
}
