package au.com.translatorss.daily.scheduler.suscriptions;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import au.com.translatorss.bean.Purchase;
import au.com.translatorss.bean.Translator;
import au.com.translatorss.service.PurchaseService;
import au.com.translatorss.service.TranslatorSettingsService;

//@Component
public class SuscriptionsStatusService {

    @Autowired
    private TranslatorSettingsService translatorService;
    
    @Autowired
    private PurchaseService purchaseService;
    
    //@Scheduled(cron="0 0 12 * * ?")
    public void suscriptionVerification(){
        System.out.println("Reading all Translators "+ new Date());

        List<Translator> translatorList = translatorService.getTranslatorsByStatus("Active");
        Date today = new Date();
        for(Translator translator: translatorList){
            Date natyExpiration = translator.getNatyExpiration();

            if(natyExpiration.before(today)){
                translator.setStatus("Paused");
                translator.getTranslatorStatusFlags().setNatyExtiryDate(true);
            }

            if(translator.getRemaininDays()==0){
                translator.setStatus("Paused");
                translator.getTranslatorStatusFlags().setValidSuscription(false);
            }else{
            	translator.setRemaininDays(translator.getRemaininDays()-1);
            }
            translatorService.saveTranslator(translator);
        }
    }    
}
