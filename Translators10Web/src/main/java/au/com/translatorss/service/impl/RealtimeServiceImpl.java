package au.com.translatorss.service.impl;

import au.com.translatorss.bean.*;
import au.com.translatorss.bean.dto.*;
import au.com.translatorss.realtime.Message;
import au.com.translatorss.realtime.MessageType;
import au.com.translatorss.realtime.Notification;
import au.com.translatorss.realtime.RealtimeHandler;
import au.com.translatorss.service.AmazonFilePhotoService;
import au.com.translatorss.service.ConversationService;
import au.com.translatorss.service.RealtimeService;
import au.com.translatorss.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RealtimeServiceImpl implements RealtimeService {

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @Autowired
    private ConversationService conversationService;

    @Autowired
    private UserService userService;

    @Autowired
    private AmazonFilePhotoService amazonFilePhotoService;

    private static final Logger LOGGER = LogManager.getLogger(RealtimeHandler.class);

    public void NotifyMessage( ChatMessage chatMessage) throws Throwable {
        Conversation conversation = chatMessage.getConversation();
        ServiceRequest serviceRequest = conversation.getServiceRequest();

        conversation.setLastMessage(chatMessage.getMessage());
        conversation.setSender(chatMessage.getSender());
        conversation.setUpdated(chatMessage.getDate());
        conversationService.saveOrUpdate(conversation);

        User customer = userService.getByEmail(serviceRequest.getCustomer().getUser().getEmail());
        User translator = userService.getByEmail(chatMessage.getConversation().getTranslator().getUser().getEmail());

        saveMessageInChat(chatMessage);
        notifyUnreadMessageChanged(customer);
        notifyUnreadMessageChanged(translator);
        notifyUnreadMessageChanged(translator, chatMessage.getConversation().getId());
        notifyUnreadMessageChanged(customer, chatMessage.getConversation().getId());
    }

    public void saveMessageInChat(ChatMessage message) {
        ChatMessageDTO dto = mapToDto(message);

        Notification notification = new Notification();
        notification.setContent(dto);
        notification.setType(MessageType.MESSAGE_CREATED);
        notification.setDestination("/chatMessage/" + dto.getConversationid());
        convertAndSend(notification);
    }

    public void notifyUnreadMessageChanged(User user) {
        Notification notification = new Notification();

        Integer unreadMessages = conversationService.getAllUnreadMessageByUserId(userService.getUserIdByRole(user));
        notification.setContent(unreadMessages);
        notification.setDestination("/countUnreadMessages");
        notification.setUser(user.getEmail());
        convertAndSend(notification);
    }

    public void notifyUnreadMessageChanged(User user, Long conversationId) {
        Notification notification = new Notification();

        Integer unreadMessages = conversationService.getUnreadMessageByUserIdAndConversationId(userService.getUserIdByRole(user), conversationId);
        notification.setContent(new UnreadMessageDto(conversationId, unreadMessages));
        notification.setDestination("/unreadMessages");
        notification.setUser(user.getEmail());
        convertAndSend(notification);
    }

    public void notifyMarkAsRead(Long conversationId, Long messageId) {
        Notification notification = new Notification();
        notification.setContent(messageId);
        notification.setType(MessageType.MARK_AS_READ);
        notification.setDestination("/chatMessage/" + conversationId);
        convertAndSend(notification);
    }

    private void convertAndSend(Notification notification) {
        Message message = new Message(notification.getContent(), notification.getType());
        String user = notification.getUser();
        String destination = notification.getDestination();

        LOGGER.info(String.format("Send real-time message to %s", destination));

        if (StringUtils.isBlank(user))
            messagingTemplate.convertAndSend(destination, message);

        else messagingTemplate.convertAndSendToUser(user, destination, message);
    }

    //todo move in ChatMessageDTO
    private ChatMessageDTO mapToDto(ChatMessage message) {

        User user = this.userService.getById(message.getSenderId());
        AmazonFilePhoto photo = amazonFilePhotoService.getAmazonFilePhotoByUserId(user);
        ChatMessageDTO dto = new ChatMessageDTO();
        dto.setRead(message.getRead());
        dto.setDate(message.getDate());
        dto.setConversationid(message.getConversation().getId());
        dto.setSender(message.getSender());
        dto.setMessage(message.getMessage());
        dto.setId(message.getId());
        dto.setPhotoUrl(photo.getUrl());
        return dto;
    }

}
