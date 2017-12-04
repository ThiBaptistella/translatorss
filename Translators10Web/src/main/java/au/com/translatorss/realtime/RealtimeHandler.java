package au.com.translatorss.realtime;

import au.com.translatorss.bean.*;
import au.com.translatorss.bean.dto.ChatMessageDTO;
import au.com.translatorss.service.AmazonFilePhotoService;
import au.com.translatorss.service.ConversationService;
import au.com.translatorss.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class RealtimeHandler {

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @Autowired
    private ConversationService conversationService;

    @Autowired
    private UserService userService;

    @Autowired
    private AmazonFilePhotoService amazonFilePhotoService;

    private static final Logger LOGGER = LogManager.getLogger(RealtimeHandler.class);

    //@Around(value = "execution(* au.com.translatorss.service.impl.ChatMessageServiceImpl.saveOrUpdate(..)) && args(chatMessage)")
    @Around(value = "execution(* au.com.translatorss.dao.impl.ChatMessageDaoImpl.persistMessage(..)) && args(message)")
    public Object saveOrUpdateMessage(ProceedingJoinPoint pjp, ChatMessage message) throws Throwable {

        Object proceed = pjp.proceed();
        Conversation conversation = message.getConversation();
        ServiceRequest serviceRequest = conversation.getServiceRequest();

        conversation.setLastMessage(message.getMessage());
        conversation.setSender(message.getSender());
        conversation.setUpdated(message.getDate());
        conversationService.saveOrUpdate(conversation);

        User customer = userService.getByEmail(serviceRequest.getCustomer().getUser().getEmail());
        User translator = userService.getByEmail(message.getConversation().getTranslator().getUser().getEmail());

        saveMessageInChat(message);
        notifyUnreadMessageChanged(customer);
        notifyUnreadMessageChanged(translator);
        notifyUnreadMessageChanged(translator, message.getConversation().getId());
        notifyUnreadMessageChanged(customer, message.getConversation().getId());

        return proceed;
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

    //todo move to dto
    private static class UnreadMessageDto {
        private Integer count;
        private Long conversationId;

        private UnreadMessageDto(Long conversationId, Integer count) {
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

    //todo move in ChatMessageDTO
    private ChatMessageDTO mapToDto(ChatMessage message) {

        User user = this.userService.getById(message.getSenderId());
        AmazonFilePhoto photo = amazonFilePhotoService.getAmazonFilePhotoByUserId(user);
        ChatMessageDTO dto = new ChatMessageDTO();
        if (photo != null) {
            dto.setPhotoUrl(photo.getUrl());
        } else {
            dto.setPhotoUrl("resources/assets/layouts/layout2/img/avatar.png");
        }
        dto.setRead(message.getRead());
        dto.setDate(message.getDate());
        dto.setConversationid(message.getConversation().getId());
        dto.setSender(message.getSender());
        dto.setMessage(message.getMessage());
        dto.setId(message.getId());
        return dto;
    }
}
