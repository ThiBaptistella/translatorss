package au.com.translatorss.controller.businessuser;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpSession;

import au.com.translatorss.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import au.com.translatorss.bean.AmazonFilePhoto;
import au.com.translatorss.bean.BusinessUser;
import au.com.translatorss.bean.ChatMessage;
import au.com.translatorss.bean.dto.BusinessUserDTO;
import au.com.translatorss.bean.dto.ChatMessageDTO;
import au.com.translatorss.bean.dto.PasswordDTO;
import au.com.translatorss.service.AmazonFilePhotoService;
import au.com.translatorss.service.BusinessUserService;
import au.com.translatorss.service.ChatMessageService;
import au.com.translatorss.validation.BusinessUserValidator;
import au.com.translatorss.validation.PasswordChangeRequestValidator;

@Controller
@PreAuthorize("hasAnyAuthority('CLIENT')")
public class BusinessUserUpdateInfoController extends BaseController{

	private static final Logger logger = LoggerFactory.getLogger(BusinessUserUpdateInfoController.class);

	@Autowired
	private ChatMessageService chatMessageService;
	
	@Autowired
	private AmazonFilePhotoService amazonFilePhotoService;
	
	@Autowired
	private BusinessUserService businessUserService;
	
	@Autowired
	private BusinessUserValidator businessUserValidator;
	
	@Autowired
	private PasswordChangeRequestValidator passwordChangeRequestValidator;
	
	
	@InitBinder("businessUserForm")
	public void businessUserForm(WebDataBinder binder) {
	    binder.addValidators(businessUserValidator);
	}
	
    @InitBinder("passwordDTOForm")
    protected void initPasswordBinder(WebDataBinder binder){
     binder.addValidators(passwordChangeRequestValidator);
    }
	
	/*@RequestMapping(value = "/userSettings/{field}")
	public String userSettings(HttpSession session, @ModelAttribute("businessUserForm") BusinessUserDTO invalidBusinessUser,@PathVariable("field") String field, Model model) {
		logger.info("Welcome BusinessUserDashboardController: userSettings");
		BusinessUser businessUser = (BusinessUser) session.getAttribute("loggedInUser");
		BusinessUserDTO businessUserDTO = getBusinessUserDTO(businessUser);
		getDataLoad(model, businessUser, businessUserDTO);
		session.setAttribute("field", field);
		return "redirect:/userSettings";
	}*/

	@RequestMapping(value = "/userSettings")
	public String userSettings(HttpSession session, @ModelAttribute("businessUserForm") BusinessUserDTO invalidBusinessUser, Model model) {
		logger.info("Welcome BusinessUserDashboardController: userSettings");
		BusinessUser businessUser = (BusinessUser) session.getAttribute("loggedInUser");
		BusinessUserDTO businessUserDTO = getBusinessUserDTO(businessUser);
		Integer info = (Integer) session.getAttribute("info");
		if(info==null) {
			model.addAttribute("info",100);
			//String messageDisplay = (String) session.getAttribute("messageDisplay");
			//model.addAttribute("messageDisplay", messageDisplay);
		}else {
			model.addAttribute("info",info);
			String messageDisplay = (String) session.getAttribute("messageDisplay");
			model.addAttribute("messageDisplay", messageDisplay);
		}
		getDataLoad(model, businessUser, businessUserDTO);
		return "/customerDashboard/userSettings";
	}
	
	
	@RequestMapping(value = "/updateBusinessUser", method = RequestMethod.POST)
	public String processUser(HttpSession session,@ModelAttribute("businessUserForm") @Validated BusinessUserDTO businessUserDTO, BindingResult result, Model model) {
		logger.info("Welcome BusinessUserDashboardController: processUser");
		BusinessUser businessUser = (BusinessUser) session.getAttribute("loggedInUser");
		if (result.hasErrors()) {
			getDataLoad(model,businessUser, businessUserDTO);
			model.addAttribute("info",100);
			return "/customerDashboard/userSettings";
		}
		businessUser.setAddress(businessUserDTO.getAddress());
		businessUser.setPhone(businessUserDTO.getPhone());
		businessUser.getUser().setEmail(businessUserDTO.getEmail());
		businessUser.getUser().setName(businessUserDTO.getPreferedname());
		businessUser.setFullname(businessUserDTO.getFullname());
		businessUser.setUpdatedate(new Date());
		businessUserService.saveOrUpdate(businessUser);
		session.setAttribute("messageDisplay", "Data Changed Successfuly.");
		session.setAttribute("info",100);

		return "redirect:userSettings";
	}
	
	@RequestMapping(value = "/updateBusinessUserPassword", method = RequestMethod.POST)
	public String updateBusinessUserPassword(HttpSession session,@ModelAttribute("passwordDTOForm") @Validated PasswordDTO passwordDTO, BindingResult result, Model model) {
		BusinessUser businessUser = (BusinessUser) session.getAttribute("loggedInUser");
		if (result.hasErrors()) {
			BusinessUserDTO businessUserDTO = getBusinessUserDTO(businessUser);
			
			List<ChatMessage> messageList= chatMessageService.getUnreadMessageByCustomerId(businessUser.getUser().getId());
			List<ChatMessageDTO> dtoList = this.getMessagesDTO(new HashSet(messageList));
			model.addAttribute("businessUserForm",businessUserDTO);
			model.addAttribute("unreadMessageList", dtoList);
			model.addAttribute("newMessagesNumber", messageList.size());
			model.addAttribute("info","info1");
			AmazonFilePhoto photoView =amazonFilePhotoService.getAmazonFilePhotoByUserId(businessUser.getUser());
			model.addAttribute("info",300);

			if(photoView ==null){
				model.addAttribute("photoUrl", "resources/assets/layouts/layout2/img/avatar.png");
			}else{
				model.addAttribute("photoUrl", photoView.getUrl());
			}
			
			return "/customerDashboard/userSettings";	
		}
		BusinessUser businesUser = businessUserService.getUserByEmail(passwordDTO.getEmail(),passwordDTO.getCurrentPassword());
		businesUser.getUser().setPassword(passwordDTO.getConfirmNewPassword());
		businessUserService.saveOrUpdate(businesUser);
		session.setAttribute("messageDisplay", "Password Changed Successfuly.");
		session.setAttribute("info", 300);
		return "redirect:userSettings";
	}

	private void getDataLoad(Model model, BusinessUser businessUser, BusinessUserDTO businessUserDTO){
		PasswordDTO password = new PasswordDTO();
		password.setEmail(businessUser.getUser().getEmail());
		List<ChatMessage> messageList= chatMessageService.getUnreadMessageByCustomerId(businessUser.getUser().getId());
		List<ChatMessageDTO> dtoList = this.getMessagesDTO(new HashSet(messageList));
		model.addAttribute("passwordDTOForm", password);
		model.addAttribute("businessUserForm",businessUserDTO);
		model.addAttribute("unreadMessageList", dtoList);
		model.addAttribute("newMessagesNumber", messageList.size());
		AmazonFilePhoto photoView =amazonFilePhotoService.getAmazonFilePhotoByUserId(businessUser.getUser());
		if(photoView ==null){
			model.addAttribute("photoUrl", "resources/assets/layouts/layout2/img/avatar.png");
		}else{
			model.addAttribute("photoUrl", photoView.getUrl());
		}
	}
}
