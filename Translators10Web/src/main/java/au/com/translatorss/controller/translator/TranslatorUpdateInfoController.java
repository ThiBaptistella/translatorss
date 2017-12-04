package au.com.translatorss.controller.translator;


import au.com.translatorss.bean.AmazonFilePhoto;
import au.com.translatorss.bean.ChatMessage;
import au.com.translatorss.bean.Translator;
import au.com.translatorss.bean.User;
import au.com.translatorss.bean.dto.ChatMessageDTO;
import au.com.translatorss.bean.dto.PasswordDTO;
import au.com.translatorss.bean.dto.TranslatorDTO;
import au.com.translatorss.service.AmazonFilePhotoService;
import au.com.translatorss.service.ChatMessageService;
import au.com.translatorss.service.TranslatorSettingsService;
import au.com.translatorss.service.UserService;
import au.com.translatorss.validation.PasswordChangeRequestValidator;
import au.com.translatorss.validation.TranslatorValidatorSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@PreAuthorize("hasAnyAuthority('TRANSLATOR')")
public class TranslatorUpdateInfoController {

    @Autowired
    private ChatMessageService chatMessageService;

    @Autowired
    private TranslatorSettingsService translatorService;

    @Autowired
    private UserService userService;

    @Autowired
    private TranslatorValidatorSettings translatorValidatorSettings;

    @Autowired
    private PasswordChangeRequestValidator passwordChangeRequestValidator;
    
    @Autowired
    private AmazonFilePhotoService amazonFilePhotoService;

    @InitBinder("updateTranslatorForm")
    protected void initTranslatorBinder(WebDataBinder binder) {
        binder.addValidators(translatorValidatorSettings);
    }

    @InitBinder("passwordDTOForm")
    protected void initPasswordBinder(WebDataBinder binder){
     binder.addValidators(passwordChangeRequestValidator);
    }

    @RequestMapping(value = "/updateTranslator")
    public String updateTranslator(HttpSession session, Model model, @ModelAttribute("updateTranslatorForm") @Validated TranslatorDTO translatorDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        Translator translatorloged = (Translator) session.getAttribute("loggedInUser");
        if (result.hasErrors()) {
            PasswordDTO password = new PasswordDTO();
            password.setEmail(translatorloged.getUser().getEmail());
            model.addAttribute("passwordDTOForm", password);
            model.addAttribute("updateTranslatorForm", translatorDTO);
            model.addAttribute("translatorName", translatorloged.getUser().getName());
            List<ChatMessage> messageList= chatMessageService.getUnreadMessageByCustomerId(translatorloged.getUser().getId());
            List<ChatMessageDTO> dtoList = getMessagesDTO(new HashSet(messageList));

            model.addAttribute("unreadMessageList", dtoList);
            model.addAttribute("newMessagesNumber", messageList.size());
			model.addAttribute("info",100);

            return "translatorDashboard/settings";
        }

        translatorloged.setPaypalClientId(translatorDTO.getPaypalClientId());
        translatorloged.getUser().setName(translatorDTO.getPreferedName());
        translatorloged.setFullname(translatorDTO.getFullName());
        translatorloged.setPhone(translatorDTO.getPhone());
        translatorloged.getUser().setEmail(translatorDTO.getEmail());
        translatorloged.setAbn_name(translatorDTO.getAbn_name());
        translatorloged.setAbn_number(translatorDTO.getAbn_number());
        translatorloged.setAddress(translatorDTO.getAddress());
        translatorService.saveTranslator(translatorloged);
    	session.setAttribute("messageDisplay", "Data Changed Successfuly.");
		session.setAttribute("info",100);
		
        redirectAttributes.addFlashAttribute("message", "Your Personal Details have been updated");
        return "redirect:/settings";
    }


    @RequestMapping(value = "/updateTranslatorPassword", method = RequestMethod.POST)
    public String updateBusinessUserPassword(HttpSession session,@ModelAttribute("passwordDTOForm") @Validated PasswordDTO passwordDTO,BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        Translator translatorloged = (Translator) session.getAttribute("loggedInUser");

        if (result.hasErrors()) {
            model.addAttribute("translatorName", translatorloged.getUser().getName());
            TranslatorDTO translator = new TranslatorDTO();
            translator.setAddress(translatorloged.getAddress());
            translator.setEmail(translatorloged.getUser().getEmail());
            translator.setName(translatorloged.getUser().getName());
            translator.setPaypalClientId(translatorloged.getPaypalClientId());
            translator.setPhone(translatorloged.getPhone());
            translator.setPassword(translatorloged.getUser().getPassword());
            model.addAttribute("updateTranslatorForm", translator);
            List<ChatMessage> messageList= chatMessageService.getUnreadMessageByCustomerId(translatorloged.getUser().getId());
            List<ChatMessageDTO> dtoList = getMessagesDTO(new HashSet(messageList));

            model.addAttribute("unreadMessageList", messageList);
            model.addAttribute("newMessagesNumber", messageList.size());
			model.addAttribute("info",300);

            return "translatorDashboard/settings";
        }
        Translator translator = translatorService.getTranslatorByEmail(passwordDTO.getEmail(), passwordDTO.getCurrentPassword());
        translator.getUser().setPassword(passwordDTO.getConfirmNewPassword());
        translatorService.saveTranslator(translator);

        User user = userService.getByEmail(translator.getUser().getEmail());
        user.setPassword(translator.getUser().getPassword());
        userService.saveOrUpdate(user);

        model.addAttribute("translatorName", translatorloged.getUser().getName());
        translatorloged.getUser().setPassword(passwordDTO.getConfirmNewPassword());
        PasswordDTO password = new PasswordDTO();
        password.setEmail(translator.getUser().getEmail());
        model.addAttribute("passwordDTOForm", password);
        session.setAttribute("messageDisplay", "Password Changed Successfuly.");
		session.setAttribute("info", 300);
        return "redirect:/settings";
    }

    private List<ChatMessageDTO> getMessagesDTO(Set<ChatMessage> messageList) {
        List<ChatMessageDTO> messageListDTO = new ArrayList<ChatMessageDTO>();
        for (ChatMessage message : messageList) {
            ChatMessageDTO dto = new ChatMessageDTO();
            dto.setId(message.getId());
            dto.setDate(message.getDate());
            dto.setMessage(message.getMessage());
            dto.setSender(message.getSender());
            dto.setRead(message.getRead());
            dto.setConversationid(message.getConversation().getId());
            User user= userService.getById(message.getSenderId());
            AmazonFilePhoto photo=amazonFilePhotoService.getAmazonFilePhotoByUserId(user);
            if(photo!=null){
                dto.setPhotoUrl(photo.getUrl());
            }else{
                dto.setPhotoUrl("resources/assets/layouts/layout2/img/avatar.png");
            }

            messageListDTO.add(dto);
        }
        Collections.sort(messageListDTO);
        return messageListDTO;
    }

}
