package au.com.translatorss.controller.translator;

import au.com.translatorss.bean.Language;
import au.com.translatorss.bean.Purchase;
import au.com.translatorss.bean.PurchaseType;
import au.com.translatorss.bean.Translator;
import au.com.translatorss.controller.LanguageEditor;
import au.com.translatorss.controller.PaypalController;
import au.com.translatorss.service.*;
import au.com.translatorss.validation.TranslatorValidator;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.RelatedResources;
import com.paypal.api.payments.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@SessionAttributes("languageList")
public class TranslatorController {

	private static final Logger logger = LoggerFactory.getLogger(TranslatorController.class);
	
	@Autowired
	private TranslatorSettingsService translatorService;

	private Translator translator;

	@Autowired
    private TranslatorValidator translatorValidator;
	
    @Autowired
	private LanguageService languageService;
	
    @Autowired
    private EmailService emailService;
    
    @Autowired
    private PurchaseTypeService purchaseTypeService;
   
    @Autowired
    private PurchaseService purchaseService;
    
    @Autowired
    private PaypalController paypalController;
    
    @InitBinder("translatorForm")
    public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Language.class, new LanguageEditor(languageService.getAllLanguages()));
		binder.addValidators(translatorValidator);
    }
  
    @RequestMapping("/choseSuscription")
    public void choseSuscription(@RequestParam("type") Long type,@RequestParam("suscriptionvalue") Long suscriptionvalue, @RequestParam(required = false, value = "isDonation") Boolean isDonation, RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response)throws Exception {
        logger.info("Welcome BusinessUserDashboardController: choseSuscription");
        String guid = UUID.randomUUID().toString().replaceAll("-", "");
        
        HttpSession session = request.getSession();
        Translator translator = (Translator) session.getAttribute("loggedInUser");
	    session.setAttribute("loggedInUser", translator);

        paypalController.checkoutSaleSuscription(type, suscriptionvalue, isDonation, "/suscription/" +type+"/"+ suscriptionvalue + "/?guid=", "/chose/payment_cancel/" + suscriptionvalue + "/?guid=", guid, request, response);
    }
	
	@RequestMapping(value = "/suscription/{type}/{value}")
    public String suscription(@PathVariable("type") long id, @PathVariable("value") BigDecimal value, HttpSession session,Model model, HttpServletRequest request) {
        logger.info("Welcome TranslatorDashboardController: suscription");
        
        Payment paypalPayment = null;
        try {
            paypalPayment = paypalController.payment(request);
        } catch (Exception exc) {
            // TODO: Handle wrong guid case properly
            exc.printStackTrace();
        }
        
        List<Transaction> transactions = paypalPayment.getTransactions();
        String saleId=null;
        if (transactions.size() > 0) {
            List<RelatedResources> relRes = transactions.get(0).getRelatedResources();
            if (relRes.size() > 0) {
                 saleId = relRes.get(0).getSale().getId();
                 
            }
        }
        
        Purchase purchase = new Purchase(); 
        PurchaseType purchaseType = purchaseTypeService.findById(id);

        //Translator translator = (Translator) session.getAttribute("translatorComplete");

        Translator translator = (Translator) session.getAttribute("loggedInUser");
        translatorService.saveTranslator(translator);
        emailService.sendMessage("Welcome to Translatorss", "Hi, Thank you for registering after Nati verification you will be able to receive jobs", translator.getUser().getEmail());

        Date currentDate = new Date();
        purchase.setDate(currentDate);
        purchase.setPaypalTransactionId(saleId);

        Date finishDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(finishDate);
        int daysAfter = purchaseType.getQuantityDays();

        c.add(Calendar.DATE, daysAfter);
        finishDate = c.getTime();

        purchase.setExpireDate(finishDate);

        purchase.setPurchasetype(purchaseType);
        purchase.setValue(value);
        purchase.setTranslator(translator);
        purchase.setRemaininDays(purchaseType.getQuantityDays());

        if(translator.getExpireSuscriptionDate()==null){
        	translator.setExpireSuscriptionDate(finishDate);
        	translator.setRemaininDays(purchaseType.getQuantityDays());
        }else{
        	translator.setRemaininDays(translator.getRemaininDays()+purchaseType.getQuantityDays());
        	Date currentFinishDate= translator.getExpireSuscriptionDate();
            Calendar cal= Calendar.getInstance();
            cal.setTime(currentFinishDate);
            cal.add(Calendar.DATE,purchaseType.getQuantityDays());
            
            Date newfinishdate= cal.getTime();
        	translator.setExpireSuscriptionDate(newfinishdate);
        }
        translator.getTranslatorStatusFlags().setValidSuscription(true);
        translatorService.saveTranslator(translator);
        purchaseService.savePurchase(purchase);
		//model.addAttribute("message", "Welcome "+ translator.getUser().getName());
        session.setAttribute("loggedInUser", translator);

		return "redirect:/indexTranslatorIni";
    }

	@RequestMapping("/logeo")
    public String logeo(RedirectAttributes redirectAttributes,HttpSession session,Model model, HttpServletRequest request, HttpServletResponse response)throws Exception {
        Translator tranlator=(Translator)session.getAttribute("loggedInUser");
		session.setAttribute("message", "Welcome "+tranlator.getUser().getName());
        session.setAttribute("loggedInUser", translator);
		model.addAttribute("translatorForm", translator);	
		return "redirect:indexTranslatorIni";
    }
	
	
	public List<Language> initializeProfiles() {
		List<Language> languageList = languageService.getAllLanguages();
        return languageList;
    }
	
}
