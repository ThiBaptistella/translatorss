package au.com.translatorss.dao;

import java.util.List;

import au.com.translatorss.bean.BusinessUser;
import au.com.translatorss.bean.ServiceRequest;
import au.com.translatorss.bean.Translator;

public interface ServiceRequestDao extends GenericDao<ServiceRequest, Long> {

   public List<ServiceRequest> getServiceRequestFromBusinessUSerId(Long id, String status);

   public List<ServiceRequest> getServiceRequestFromTranslatorId(Long id, String status);
   
   public List<ServiceRequest> getNotStandarServiceRequestAvailableForTranslator(Translator translatorloged) ;

   public List<ServiceRequest> getStandarsServiceRequestAvailableForTransalator(Translator translator, String timeFrame);
   
   public List<ServiceRequest> getStandarsServiceRequestAvailableForTransalator(Translator translator);

   public List<ServiceRequest> getServiceRequestByState(String status);

   public List<ServiceRequest> getServiceRequestList(long translatorid, String status);

   void save(ServiceRequest serviceRequest);
    
   public List<ServiceRequest> getServiceRequestFromBusinessUser(BusinessUser businessUser, List<String> statusList);

   public List<ServiceRequest> getServiceRequestFromTranslator(Translator translator, List<String> statusList) ;

}
