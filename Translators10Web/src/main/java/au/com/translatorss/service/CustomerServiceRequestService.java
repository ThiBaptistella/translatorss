package au.com.translatorss.service;

import au.com.translatorss.bean.*;
import au.com.translatorss.bean.dto.ServiceRequestDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CustomerServiceRequestService {

    public void saveOrUpdate(ServiceRequest entity);

	public void saveOrUpdate(ServiceRequest entity, ServiceRequestDTO serviceRequestDTO);

    List<AmazonFile> saveFiles(ServiceRequest serviceRequest, List<MultipartFile> files);

    public void add(ServiceRequest entity);

	public void update(ServiceRequest entity);

	public void remove(ServiceRequest entity) throws Exception;

	public ServiceRequest find(long key);

	public List<ServiceRequest> getAll();

    public List<ServiceRequest> getServiceRequestWithoutQuote(Translator translatorloged, List<ServiceRequest> serviceRequestList);
    
    /*History*/	
    public List<ServiceRequest> getServiceRequestFromBusinessUser(BusinessUser businessUSer, String string);
    public List<ServiceRequest> getServiceRequestFromTranslator(Long translatorid, String string);
    /**/
    
    public List<ServiceRequest> getServiceRequestAvailableForTranslator(Translator translatorloged);

    public List<ServiceRequest> getServiceRequestFromTanslator(Long translatorlogedId, String string);

    public List<ServiceRequest> getServiceRequestQuotedFromTranslator(Translator translatorloged, String status);

    public List<ServiceRequest> getServiceRequestStandartWithoutQuote(Translator translator);
    
    public List<ServiceRequest> getServiceRequestByState(String status);
    
    public ServiceRequestFiles getServiceRequestFile(long id);
    
    public void saveServiceRequestFile(ServiceRequestFiles serviceRequestFile);

    void saveWithoutFiles(ServiceRequest serviceRequest, ServiceRequestDTO serviceRequestDTO);
}
