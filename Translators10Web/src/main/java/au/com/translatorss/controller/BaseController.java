package au.com.translatorss.controller;

import au.com.translatorss.bean.*;
import au.com.translatorss.bean.dto.BusinessUserDTO;
import au.com.translatorss.bean.dto.ChatMessageDTO;
import au.com.translatorss.bean.dto.TranslatorQuotationDTO;
import au.com.translatorss.service.*;
import org.springframework.beans.factory.annotation.Autowired;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private AmazonFilePhotoService amazonFilePhotoService;

    @Autowired
    private TranslatorQuotationService quoteService;

    protected BusinessUserDTO getBusinessUserDTO(BusinessUser businessUserLogger) {
        BusinessUserDTO dto= new BusinessUserDTO();
        dto.setAddress(businessUserLogger.getAddress());
        dto.setEmail(businessUserLogger.getUser().getEmail());
        dto.setFullname(businessUserLogger.getUser().getName());
        dto.setPhone(businessUserLogger.getPhone());
        dto.setId(businessUserLogger.getUser().getId());
        return dto;
    }

    protected List<ChatMessageDTO> getMessagesDTO(Set<ChatMessage> messageList) {
        ArrayList messageListDTO = new ArrayList();
        for (ChatMessage message : messageList) {
            ChatMessageDTO dto = new ChatMessageDTO();
            dto.setId(message.getId().longValue());

            String pattern ="yyyy-MM-dd HH:mm";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String dateInString = simpleDateFormat.format(message.getDate());

            try {
                dto.setDate(simpleDateFormat.parse(dateInString));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            dto.setMessage(message.getMessage());
            dto.setSender(message.getSender());
            dto.setRead(message.getRead());
            dto.setConversationid(message.getConversation().getId());
            User user = userService.getById(message.getSenderId());
            AmazonFilePhoto photo = amazonFilePhotoService.getAmazonFilePhotoByUserId(user);
            if (photo != null) {
                dto.setPhotoUrl(photo.getUrl());
            } else {
                dto.setPhotoUrl("resources/assets/layouts/layout2/img/avatar.png");
            }
            messageListDTO.add(dto);
        }
        Collections.sort(messageListDTO);
        return messageListDTO;
    }

    protected void populateMediaRating(TranslatorQuotationDTO dto, Translator translator) {
        quoteService.populateMediaRating(dto, translator);
    }
}
