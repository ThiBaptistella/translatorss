package au.com.translatorss.utils;

import au.com.translatorss.bean.ChatMessage;
import au.com.translatorss.bean.Conversation;
import org.springframework.util.CollectionUtils;

import java.util.Objects;
import java.util.Set;

public class ConversationUtils {
    public static Integer getCountOfUnreadMessage(Conversation conversation, Long userId) {
        if (conversation == null || CollectionUtils.isEmpty(conversation.getMessageList())) {
            return 0;
        }


        int unreadMessages = 0;
        for (ChatMessage message : conversation.getMessageList()) {
            if (message.getSenderId() != null && !Objects.equals(message.getSenderId(), userId) && !message.getRead()) {
                unreadMessages++;
            }
        }
        return unreadMessages;
    }
    
    public static Integer getCountOfUnreadMessage(Set<Conversation> conversationList, Long userId) {
        int unreadMessages = 0;
        for(Conversation conversation: conversationList){
        	unreadMessages= getCountOfUnreadMessage(conversation, userId);
        }
        return unreadMessages;
    }
}
