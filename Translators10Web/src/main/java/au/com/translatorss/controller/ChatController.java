package au.com.translatorss.controller;

import au.com.translatorss.bean.ChatMessage;
import au.com.translatorss.bean.Conversation;
import au.com.translatorss.bean.User;
import au.com.translatorss.bean.dto.ChatMessageDTO;
import au.com.translatorss.enums.Role;
import au.com.translatorss.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;

@Controller
public class ChatController {

    @Autowired
    private UserService userService;

    @Autowired
    private ConversationService conversationService;

    @Autowired
    private ChatMessageService chatMessageService;

    @Autowired
    private RealtimeService realtimeService;

    @Autowired
    private EmailService2 emailService2;
    
    @PreAuthorize("hasAnyAuthority('CLIENT', 'TRANSLATOR')")
    @RequestMapping(value={"/{conversationId}/markAsRead"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseStatus(HttpStatus.OK)
    public void markAsRead(@PathVariable("conversationId") Long conversationID) {
        conversationService.messagesMarkAsRead(userService.getCurrentUser(), conversationID);
    }

    @PreAuthorize("hasAnyAuthority('CLIENT', 'TRANSLATOR')")
    @RequestMapping(value={"/countOfUnreadMessages"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public Integer countOfUnreadMessages() {
        Long userId = userService.getUserIdByRole(userService.getCurrentUser());
        return conversationService.getAllUnreadMessageByUserId(userId);
    }

    @PreAuthorize("hasAnyAuthority('CLIENT', 'TRANSLATOR')")
    @RequestMapping(value={"/sbmtMessage"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public ChatMessageDTO submitMessageRest(@RequestBody ChatMessageDTO message) throws Throwable {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setSender(message.getSender());
        User currentUser = userService.getCurrentUserOrNull();
        if (currentUser != null) {
            chatMessage.setSenderId(userService.getUserIdByRole(currentUser));
        }
        Date date = new Date();
        chatMessage.setDate(new Timestamp(date.getTime()));
        chatMessage.setMessage(message.getMessage());
        Conversation conversation = conversationService.get(message.getConversationid());
        chatMessage.setConversation(conversation);

        if (currentUser.getRole() == Role.CLIENT) {
            chatMessage.setReceiverId(conversation.getTranslator().getUser().getId());
           emailService2.sendEmailToTranslatorMessageSentByCustomer(conversation.getTranslator().getUser().getEmail(),
        		    conversation.getTranslator().getFullname(), 
        		    conversation.getServiceRequest().getCustomer().getFullname(), message.getMessage(), conversation.getServiceRequest().getId().toString());
        } else {
            chatMessage.setReceiverId(conversation.getServiceRequest().getCustomer().getUser().getId());
            emailService2.sendEmailToCustomerMessageSentByTranslator(conversation.getServiceRequest().getCustomer().getUser().getEmail(),
            		conversation.getServiceRequest().getCustomer().getFullname(), 
            		conversation.getTranslator().getFullname(), message.getMessage(), conversation.getServiceRequest().getId().toString());
        }

        conversationService.messagesMarkAsRead(currentUser, conversation.getId());
        chatMessageService.saveOrUpdate(chatMessage);

       // realtimeService.NotifyMessage(chatMessage);
        return message;
    }

}
