package au.com.translatorss.service;

import au.com.translatorss.bean.AmazonFile;
import au.com.translatorss.bean.ServiceResponse;
import au.com.translatorss.bean.ServiceResponseFiles;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ServiceResponseService {

    public ServiceResponse find(long key);

    void saveOrUpdate(ServiceResponse entity);

    public void saveOrUpdate(ServiceResponseFiles serviceResponseFile);

    //public ServiceResponse getServiceResponseByServiceRequestId(Long id);

	public ServiceResponseFiles getServiceResponseById(long id);

	public void remove(ServiceResponse entity) throws Exception;

	public ServiceResponse getServiceResponseByConversationId(Long conversationid);

    List<AmazonFile> saveFiles(ServiceResponse serviceResponse, List<MultipartFile> files);
}
