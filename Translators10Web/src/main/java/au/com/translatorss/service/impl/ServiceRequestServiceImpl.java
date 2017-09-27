package au.com.translatorss.service.impl;

import au.com.translatorss.bean.*;
import au.com.translatorss.bean.dto.ServiceRequestDTO;
import au.com.translatorss.dao.AmazonFileDao;
import au.com.translatorss.dao.QuotationDao;
import au.com.translatorss.dao.ServiceRequestDao;
import au.com.translatorss.dao.ServiceRequestFilesDao;
import au.com.translatorss.enums.FileType;
import au.com.translatorss.service.AmazonService;
import au.com.translatorss.service.CustomerServiceRequestService;
import au.com.translatorss.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ServiceRequestServiceImpl implements CustomerServiceRequestService{

	private static final Logger logger = LoggerFactory.getLogger(ServiceRequestServiceImpl.class);

	@Autowired
	private ServiceRequestDao serviceRequestDao;
	
	@Autowired
	private ServiceRequestFilesDao serviceRequestFilesDao;
	
	@Autowired
	private QuotationDao quotationDao;

    @Autowired
    private AmazonService amazonService;

    @Override
    public void saveOrUpdate(ServiceRequest entity) {
        serviceRequestDao.saveOrUpdate(entity);
    }
	
	@Override
	public void saveOrUpdate(ServiceRequest serviceRequest, ServiceRequestDTO serviceRequestDTO) {
		serviceRequestDao.saveOrUpdate(serviceRequest);
        saveFiles(serviceRequest, serviceRequestDTO.getFiles());
    }

    @Override
    public void saveWithoutFiles(ServiceRequest serviceRequest, ServiceRequestDTO serviceRequestDTO) {
        serviceRequestDao.save(serviceRequest);
    }

    @Override
    public List<AmazonFile> saveFiles(ServiceRequest serviceRequest, List<MultipartFile> files) {
        List<AmazonFile> amazonFiles = new ArrayList<AmazonFile>();
        for (MultipartFile file : files) {
            try {
                amazonService.saveServiceRequestFile(serviceRequest, file.getOriginalFilename(), file.getInputStream(), file.getContentType());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return amazonFiles;
    }

	@Override
	public void add(ServiceRequest entity) {
		
	}

	@Override
	public void update(ServiceRequest entity) {
		serviceRequestDao.update(entity);
	}

	@Override
	public void remove(ServiceRequest entity) throws Exception {
	   serviceRequestDao.remove(entity);
        for (AmazonFile file : entity.getAmazonFiles()) {
            amazonService.deleteFile(file);
        }
	}

	@Override
	public ServiceRequest find(long key) {
		return serviceRequestDao.find(key);
	}

	@Override
	public List<ServiceRequest> getAll() {
		return serviceRequestDao.getAll();
	}
	
	public List<ServiceRequest> getServiceRequestByState(String status){
	    return serviceRequestDao.getServiceRequestByState(status);
	}
	
	public static void delete(File file) throws IOException{
	 
	        if(file.isDirectory()){
	            //directory is empty, then delete it
	            if(file.list().length==0){
	               file.delete();
	               System.out.println("Directory is deleted : "+ file.getAbsolutePath());
	            }else{
	               //list all the directory contents
	               String files[] = file.list();
	               for (String temp : files) {
	                  //construct the file structure
	                  File fileDelete = new File(file, temp);
	                  //recursive delete
	                 delete(fileDelete);
	               }
	               //check the directory again, if empty then delete it
	               if(file.list().length==0){
	                 file.delete();
	                 System.out.println("Directory is deleted : "+ file.getAbsolutePath());
	               }
	            } 
	        }else{
	            //if file, then delete it
	            file.delete();
	            System.out.println("File is deleted : " + file.getAbsolutePath());
	        }
	    }

	@Override
    public List<ServiceRequest> getServiceRequestAvailableForTranslator(Translator translatorloged) {
	    List<ServiceRequest> serviceRequestList;
	    serviceRequestList = serviceRequestDao.getNotStandarServiceRequestAvailableForTranslator(translatorloged);
	    serviceRequestList.addAll(serviceRequestDao.getStandarsServiceRequestAvailableForTransalator(translatorloged));
	    return serviceRequestList;
    }
	
    private List<ServiceRequest> getFromLanguages(List<Language> languageList) { 
        List<ServiceRequest> serviceRequestReturn= new ArrayList<ServiceRequest>();
        List<ServiceRequest> ServiceRequestList = serviceRequestDao.getAll();
        for(ServiceRequest serviceRequest: ServiceRequestList){
            String languageFrom= serviceRequest.getLanguagefrom().trim();
            String languageTo= serviceRequest.getLanguageTo().trim();
           if(languageListTranslatorContains(languageList,languageFrom, languageTo)){
               serviceRequestReturn.add(serviceRequest);
           }
        }
        return serviceRequestReturn;
    }

    private boolean languageListTranslatorContains(List<Language> languageList, String languageFrom,
            String languageTo) {
        boolean containsFrom=false;
        boolean containsTo=false;

        for(Language language:languageList){
            if(language.getDescription().equals(languageFrom)){
                containsFrom=true;
            }else if(language.getDescription().equals(languageTo)){
                containsTo=true;
            }
        }
        return containsFrom && containsTo;
    }

    @Override
    public List<ServiceRequest> getServiceRequestWithoutQuote(Translator translatorloged,
            List<ServiceRequest> serviceRequestList) {
        List<ServiceRequest> newServiceRequestList = new ArrayList<ServiceRequest>();
        
        for(ServiceRequest serviceRequest: serviceRequestList){
            if((serviceRequest.IsStandar())&&(!serviceRequest.hasQuoteFrom(translatorloged.getId()))){
                newServiceRequestList.add(serviceRequest);
            }
        }
        return newServiceRequestList;
    }

    @Override
    public List<ServiceRequest> getServiceRequestFromBusinessUser(BusinessUser businessUser,String status) {
        return serviceRequestDao.getServiceRequestFromBusinessUSerId(businessUser.getId(),status);
    }

    @Override
    public List<ServiceRequest> getServiceRequestFromTanslator(Long id, String status) {
        return serviceRequestDao.getServiceRequestFromTranslatorId(id,status);
    }

    @Override
    public List<ServiceRequest> getServiceRequestQuotedFromTranslator(Translator translatorloged, String status) {
       return quotationDao.getServiceRequestQuotedFromTranslator(translatorloged, status);
    }

    @Override
    public List<ServiceRequest> getServiceRequestStandartWithoutQuote(Translator translator) {
        return this.getServiceRequestWithoutQuote(translator, getAll());
    }
    
    public ServiceRequestFiles getServiceRequestFile(long id){
        return serviceRequestFilesDao.find(id);
    }

    @Override
    public void saveServiceRequestFile(ServiceRequestFiles serviceRequestFile) {
        serviceRequestFilesDao.saveOrUpdate(serviceRequestFile);    
    }

    @Override
	public List<ServiceRequest> getServiceRequestFromTranslator(Long translatorid, String status) {
		return serviceRequestDao.getServiceRequestFromTranslatorId(translatorid, status);
	}
}
