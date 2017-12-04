package au.com.translatorss.controller.translator;

import java.util.List;

import javax.servlet.http.HttpSession;
import java.util.*;

import au.com.translatorss.bean.*;
import au.com.translatorss.controller.BaseController;
import au.com.translatorss.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import au.com.translatorss.bean.dto.*;
import au.com.translatorss.bean.dto.QuoteSettingDTO;

@Controller
@PreAuthorize("hasAnyAuthority('TRANSLATOR')")
public class QuoteSettingsController extends BaseController{

        private static final Logger logger = LoggerFactory.getLogger(QuoteSettingsController.class);

        @Autowired
        private TranslatorSettingsService translatorService;
        
        @Autowired
        private TranslatorQuotationService translatorQuotationService;
        
    	@Autowired
    	private AmazonFilePhotoService amazonFilePhotoService;

    	@Autowired
    	private QuotationStandarService quotesStandarService;
    	
        @Autowired
        private ChatMessageService chatMessageService;

		@Autowired
		private UserService userService;

        @InitBinder("urgentQuoteForm")
        public void initBinder(WebDataBinder binder) {
        }

	    @RequestMapping(value = "/quoteSettings")
	    public String urgentQuotesettings(HttpSession session, Model model) {
	           logger.info("Welcome TranslatorDashboardController: settings");
	            
	        	Translator translatorloged = (Translator) session.getAttribute("loggedInUser");
	        	
	        	List<QuotationStandar> urgentList=quotesStandarService.getByTimeFrame("Urgent", translatorloged.getId());
	        	QuoteSettingDTO urgent =getCategoryValues(urgentList);

	        	List<QuotationStandar> mediumList=quotesStandarService.getByTimeFrame("Medium", translatorloged.getId());
	        	QuoteSettingDTO medium =getCategoryValues(mediumList);

	        	List<QuotationStandar> relaxedList=quotesStandarService.getByTimeFrame("Relaxed", translatorloged.getId());
	        	QuoteSettingDTO relaxed =getCategoryValues(relaxedList);
	        	
	            model.addAttribute("urgentQuoteForm", urgent);
	            model.addAttribute("mediumQuoteForm", medium);
	            model.addAttribute("relaxedQuoteForm",relaxed);
	            model.addAttribute("translatorName", translatorloged.getUser().getName());
	           
	            List<ChatMessage> messageList= chatMessageService.getUnreadMessageByCustomerId(translatorloged.getUser().getId());
				List<ChatMessageDTO> dtoList = getMessagesDTO(new HashSet(messageList));


				model.addAttribute("unreadMessageList", dtoList);
	    		model.addAttribute("newMessagesNumber", messageList.size());
	            
	            
	            AmazonFilePhoto photoView = amazonFilePhotoService.getAmazonFilePhotoByUserId(translatorloged.getUser());
	            if(photoView ==null){
	    			model.addAttribute("photoUrl", "resources/assets/layouts/layout2/img/avatar.png");
	    		}else{
	    			model.addAttribute("photoUrl", photoView.getUrl());
	    		}
	            return "/translatorDashboard/quoteSettings";
	    }

		@RequestMapping(value = "/disableValue/{timeFrame}", method = RequestMethod.GET)
	   	public void disableUrgentValue( @PathVariable("timeFrame") String timeFrame,HttpSession session) {
	        Translator translatorloged = (Translator) session.getAttribute("loggedInUser");
	        translatorQuotationService.disableStandarQuotations(translatorloged.getId(),timeFrame);
	   		//return "redirect:quoteSettings";
	   	}
	    
	    @RequestMapping(value = "/enableValue/{timeFrame}", method = RequestMethod.GET)
	   	public void enableUrgentValue(@PathVariable("timeFrame") String timeFrame,HttpSession session) {
	        Translator translatorloged = (Translator) session.getAttribute("loggedInUser");
	        translatorQuotationService.enableStandarQuotations(translatorloged.getId(),timeFrame);
	   	}
	    
	    @RequestMapping(value = "/urgentUpdateQuotationConfig", method = RequestMethod.POST)
	    public String urgentUpdateQuotationConf(HttpSession session,@ModelAttribute("urgentQuoteForm") QuoteSettingDTO quoteSetting, BindingResult result, Model model) {
	            Translator translatorloged = (Translator) session.getAttribute("loggedInUser");
	            quoteSetting.setTranslatorId(translatorloged.getId());
	            translatorService.updateStandarQuote(translatorloged, quoteSetting, "Urgent");
	            translatorQuotationService.updateQuotations(translatorloged, quoteSetting,"Urgent");	
	            List<QuotationStandar> urgentList =quotesStandarService.getByTimeFrame("Urgent", translatorloged.getId());
	            List<QuotationStandar> relaxedList = quotesStandarService.getByTimeFrame("Relaxed", translatorloged.getId());
	            List<QuotationStandar> mediumList = quotesStandarService.getByTimeFrame("Medium",translatorloged.getId());
	        	QuoteSettingDTO urgent = getCategoryValues(urgentList);
	        	QuoteSettingDTO medium = getCategoryValues(mediumList);
	        	QuoteSettingDTO relaxed = getCategoryValues(relaxedList);

	            model.addAttribute("urgentQuoteForm", urgent);
	            model.addAttribute("urgentvalid",urgent.getValid());
	            model.addAttribute("mediumQuoteForm", medium);
	            model.addAttribute("relaxedQuoteForm", relaxed);

	            model.addAttribute("translatorid", translatorloged.getId());
	            AmazonFilePhoto photoView = amazonFilePhotoService.getAmazonFilePhotoByUserId(translatorloged.getUser());
	            if(photoView ==null){
	    			model.addAttribute("photoUrl", "resources/assets/layouts/layout2/img/avatar.png");
	    		}else{
	    			model.addAttribute("photoUrl", photoView.getUrl());
	    		}
	            
	            List<ChatMessage> messageList= chatMessageService.getUnreadMessageByCustomerId(translatorloged.getId());
	    		model.addAttribute("unreadMessageList", messageList);
	    		model.addAttribute("newMessagesNumber", messageList.size());
	        	return "translatorDashboard/quoteSettings";
	     }
	    
	    @RequestMapping(value = "/mediumUpdateQuotationConfig", method = RequestMethod.POST)
	    public String mediumQuotationConf(HttpSession session,@ModelAttribute("mediumQuoteForm") QuoteSettingDTO quoteSetting, BindingResult result, Model model) {
	        Translator translatorloged = (Translator) session.getAttribute("loggedInUser");
	        quoteSetting.setTranslatorId(translatorloged.getId());

            translatorService.updateStandarQuote(translatorloged, quoteSetting, "Medium");
            translatorQuotationService.updateQuotations(translatorloged,quoteSetting,"Medium");

			List<QuotationStandar> urgentList =quotesStandarService.getByTimeFrame("Urgent", translatorloged.getId());
			List<QuotationStandar> relaxedList = quotesStandarService.getByTimeFrame("Relaxed", translatorloged.getId());
			List<QuotationStandar> mediumList = quotesStandarService.getByTimeFrame("Medium",translatorloged.getId());
			QuoteSettingDTO urgent = getCategoryValues(urgentList);
			QuoteSettingDTO medium = getCategoryValues(mediumList);
			QuoteSettingDTO relaxed = getCategoryValues(relaxedList);

			model.addAttribute("urgentQuoteForm", urgent);
			model.addAttribute("urgentvalid",urgent.getValid());

			model.addAttribute("mediumQuoteForm", medium);
			model.addAttribute("relaxedQuoteForm", relaxed);

	        AmazonFilePhoto photoView = amazonFilePhotoService.getAmazonFilePhotoByUserId(translatorloged.getUser());
            if(photoView ==null){
    			model.addAttribute("photoUrl", "resources/assets/layouts/layout2/img/avatar.png");
    		}else{
    			model.addAttribute("photoUrl", photoView.getUrl());
    		}
            
            List<ChatMessage> messageList= chatMessageService.getUnreadMessageByCustomerId(translatorloged.getId());
    		model.addAttribute("unreadMessageList", messageList);
    		model.addAttribute("newMessagesNumber", messageList.size());
	       // return "translatorDashboard/quoteSettings";
	    return "redirect:quoteSettings";
        }
	    
	    @RequestMapping(value = "/relaxedUpdateQuotationConfig", method = RequestMethod.POST)
	    public String relaxedQuotationConf(HttpSession session,@ModelAttribute("relaxedQuoteForm") QuoteSettingDTO quoteSetting, BindingResult result, Model model) {
	         Translator translatorloged = (Translator) session.getAttribute("loggedInUser");
	         quoteSetting.setTranslatorId(translatorloged.getId());
	         translatorService.updateStandarQuote(translatorloged, quoteSetting, "Relaxed");
	         translatorQuotationService.updateQuotations(translatorloged,quoteSetting,"Relaxed");

	         Translator translator = (Translator) session.getAttribute("loggedInUser");

			 List<QuotationStandar> urgentList =quotesStandarService.getByTimeFrame("Urgent", translatorloged.getId());
			 List<QuotationStandar> relaxedList = quotesStandarService.getByTimeFrame("Relaxed", translatorloged.getId());
			 List<QuotationStandar> mediumList = quotesStandarService.getByTimeFrame("Medium",translatorloged.getId());
			 QuoteSettingDTO urgent = getCategoryValues(urgentList);
			 QuoteSettingDTO medium = getCategoryValues(mediumList);
			 QuoteSettingDTO relaxed = getCategoryValues(relaxedList);

			 model.addAttribute("urgentQuoteForm", urgent);
			 model.addAttribute("urgentvalid",urgent.getValid());

			 model.addAttribute("mediumQuoteForm", medium);
			 model.addAttribute("relaxedQuoteForm", relaxed);
	         model.addAttribute("translatorName", translator.getUser().getName());
	         AmazonFilePhoto photoView = amazonFilePhotoService.getAmazonFilePhotoByUserId(translatorloged.getUser());
	            if(photoView ==null){
	    			model.addAttribute("photoUrl", "resources/assets/layouts/layout2/img/avatar.png");
	    		}else{
	    			model.addAttribute("photoUrl", photoView.getUrl());
	    		}
	            List<ChatMessage> messageList= chatMessageService.getUnreadMessageByCustomerId(translatorloged.getId());
	    		model.addAttribute("unreadMessageList", messageList);
	    		model.addAttribute("newMessagesNumber", messageList.size());
	         return "translatorDashboard/quoteSettings";
	    }

		private QuoteSettingDTO getCategoryValues(List<QuotationStandar> urgentList) {
			QuoteSettingDTO quote =new QuoteSettingDTO();

			for(QuotationStandar quoteStandar:urgentList){
				if(quoteStandar.getCategory().getDescription().equals("Birth Certificate")){
					quote.setBirthCertificate(quoteStandar.getValue().toString());
				}else if(quoteStandar.getCategory().getDescription().equals("Passport")){
					quote.setPassport(quoteStandar.getValue().toString());
				}else if(quoteStandar.getCategory().getDescription().equals("Marriage Certificate")){
					quote.setMarriageCertificate(quoteStandar.getValue().toString());
				}else if(quoteStandar.getCategory().getDescription().equals("Drivers License")){
					quote.setDriverLic(quoteStandar.getValue().toString());
				}
				quote.setValid(quoteStandar.getValid());
			}

			return quote;
		}

//	private List<ChatMessageDTO> getMessagesDTO(Set<ChatMessage> messageList) {
//		List<ChatMessageDTO> messageListDTO = new ArrayList<ChatMessageDTO>();
//		for (ChatMessage message : messageList) {
//			ChatMessageDTO dto = new ChatMessageDTO();
//			dto.setId(message.getId());
//			dto.setDate(message.getDate());
//			dto.setMessage(message.getMessage());
//			dto.setSender(message.getSender());
//			dto.setRead(message.getRead());
//			dto.setConversationid(message.getConversation().getId());
//			User user= userService.getById(message.getSenderId());
//			AmazonFilePhoto photo=amazonFilePhotoService.getAmazonFilePhotoByUserId(user);
//			if(photo!=null){
//				dto.setPhotoUrl(photo.getUrl());
//			}else{
//				dto.setPhotoUrl("resources/assets/layouts/layout2/img/avatar.png");
//			}
//
//			messageListDTO.add(dto);
//		}
//		Collections.sort(messageListDTO);
//		return messageListDTO;
//	}
}
