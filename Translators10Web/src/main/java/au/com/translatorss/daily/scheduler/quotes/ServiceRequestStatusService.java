package au.com.translatorss.daily.scheduler.quotes;

import org.springframework.scheduling.annotation.Scheduled;
import au.com.translatorss.bean.ServiceRequest;
import au.com.translatorss.dao.ServiceRequestStatusDao;
import au.com.translatorss.service.CustomerServiceRequestService;
import au.com.translatorss.service.EmailService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

//@Component
public class ServiceRequestStatusService {

    public ServiceRequestStatusService(){}
    
    @Autowired
    private CustomerServiceRequestService serviceRequestService;
    
    @Autowired
    private ServiceRequestStatusDao serviceRequestStatusDao;
    
    @Autowired
    private EmailService2 emailService;

   // @Scheduled(cron="*/60 * * * * *")
    public void serviceRequestStatusCheck() {
        List<ServiceRequest> quotedServiceRequestList = serviceRequestService.getServiceRequestByState("Unquoted");
        System.out.println("Reading all service Request "+ new Date());
        for(ServiceRequest serviceRequest: quotedServiceRequestList){
                if(expiredQuote(serviceRequest.getFinishQuoteSelection())){
                    serviceRequest.setServiceRequestStatus(serviceRequestStatusDao.findByDescription("Expired"));
                    serviceRequestService.saveOrUpdate(serviceRequest);
                    emailService.sendEmailServiceRequestExpired(serviceRequest.getCustomer().getUser().getEmail(),serviceRequest.getCustomer().getUser().getName(), serviceRequest.getId().toString());
                }
       }
        
        List<ServiceRequest> openServiceServiceRequestList = serviceRequestService.getServiceRequestByState("OpenService");
        for(ServiceRequest serviceRequest: openServiceServiceRequestList){
            if(expiredQuote(serviceRequest.getFinishDate())){
                serviceRequest.setServiceRequestStatus(serviceRequestStatusDao.findByDescription("Expired"));
                serviceRequestService.saveOrUpdate(serviceRequest);
                emailService.sendEmailToCustomerServiceRequestExpired(serviceRequest.getCustomer().getUser().getEmail(), serviceRequest.getCustomer().getUser().getName(), serviceRequest.getId().toString());
            }
        }
        
    }
    

    private boolean expiredQuote(Date expirationDate) {
    	Date today = new Date();
    	return expirationDate.before(today);
    }  
}
