package au.com.translatorss.dao;

import java.util.List;

import au.com.translatorss.bean.ChatMessage;

public interface ChatMessageDao extends GenericDao<ChatMessage, Long>{

	public List<ChatMessage> getUnreadMessageById(Long id);
	
}
