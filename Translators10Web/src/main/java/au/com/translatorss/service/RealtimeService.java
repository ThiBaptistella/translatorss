package au.com.translatorss.service;

import au.com.translatorss.bean.ChatMessage;

public interface RealtimeService {

    public void NotifyMessage( ChatMessage chatMessage) throws Throwable;

}
