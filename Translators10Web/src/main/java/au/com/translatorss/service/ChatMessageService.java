package au.com.translatorss.service;

import java.util.List;

import au.com.translatorss.bean.ChatMessage;

public interface ChatMessageService {

    public void saveOrUpdate(ChatMessage entity);

    public List<ChatMessage> getUnreadMessageByCustomerId(Long customerid);

}
