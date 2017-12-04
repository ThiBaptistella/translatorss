package au.com.translatorss.aspect;


import java.util.List;
import java.util.Set;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import au.com.translatorss.bean.Quotation;
import au.com.translatorss.bean.ServiceRequest;
import au.com.translatorss.service.CustomerServiceRequestService;
import au.com.translatorss.service.ServiceRequestStatusService;


@Component
@Aspect
public class QuotationAspect {

	@Autowired
    private ServiceRequestStatusService serviceRequestStatusService;
	 
	@Autowired
	private CustomerServiceRequestService serviceRequestService;
	 
	//@Around(value = "execution(* au.com.translatorss.service.impl.QuotationServiceImpl.saveOrUpdate(..)) && args(quotation)")
	@Around(value = "execution(* au.com.translatorss.dao.impl.QuotationImplDao.saveOrUpdateQuotation(..)) && args(quotation)")
    public Object saveOrUpdateMessage(ProceedingJoinPoint pjp, Quotation quotation) throws Throwable {
        Object proceed = pjp.proceed();
      
        ServiceRequest serviceRequest = quotation.getServiceRequest();
        if(hasEmptyActiveQuotes(serviceRequest)) {
        	serviceRequest.setServiceRequestStatus(serviceRequestStatusService.findByDescription("Unquoted"));
        	serviceRequestService.saveOrUpdate(serviceRequest);
        }else {
        	serviceRequest.setServiceRequestStatus(serviceRequestStatusService.findByDescription("Quoted"));
        	serviceRequestService.saveOrUpdate(serviceRequest);
        }
        return proceed;
    }
	
	private boolean hasEmptyActiveQuotes(ServiceRequest serviceRequest) {
		Set<Quotation> quotationList=serviceRequest.getQuotationList();
		for(Quotation quotation:quotationList) {
			if(quotation.getIsValid()) {
				return false;
			}
		}
		return true;
	}
}
