package au.com.translatorss.controller;

import au.com.translatorss.bean.*;
import au.com.translatorss.bean.dto.BusinessUserDTO;
import au.com.translatorss.bean.dto.ChatMessageDTO;
import au.com.translatorss.bean.dto.TranslatorQuotationDTO;
import au.com.translatorss.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

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
        dto.setFullname(businessUserLogger.getFullname());
        dto.setPhone(businessUserLogger.getPhone());
        dto.setId(businessUserLogger.getUser().getId());
        dto.setPreferedname(businessUserLogger.getUser().getName());
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

            dto.setDateFormat(dateInString);
            dto.setDate(message.getDate());
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


/*	@ExceptionHandler
	public ModelAndView errorHandler(HttpServletRequest request, Exception exception) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("Exception", exception);
		mav.addObject("headMessage", "System Unavailable.");
		mav.addObject("bodyMessage", "There was an unexpected error, Please try login again or contact us!.");
		mav.addObject("returnButtom", true);
		mav.setViewName("errorPages/SystemDown");
		return mav;
	}*/

    protected void populateMediaRating(TranslatorQuotationDTO dto, Translator translator) {
        quoteService.populateMediaRating(dto, translator);
    }
}
