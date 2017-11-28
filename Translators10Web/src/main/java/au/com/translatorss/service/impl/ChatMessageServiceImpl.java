package au.com.translatorss.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.com.translatorss.bean.ChatMessage;
import au.com.translatorss.dao.ChatMessageDao;
import au.com.translatorss.service.ChatMessageService;

@Service
@Transactional
public class ChatMessageServiceImpl implements ChatMessageService {

    @Autowired
    private ChatMessageDao chatMessageDao;

    @Override
    public void saveOrUpdate(ChatMessage chatMessage) {
        chatMessageDao.persistMessage(chatMessage);
    }

	@Override
	public List<ChatMessage> getUnreadMessageByCustomerId(Long customerid) {
		return chatMessageDao.getUnreadMessageById(customerid);
	}

}
