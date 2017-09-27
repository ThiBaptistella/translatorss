
package au.com.translatorss.service;

import au.com.translatorss.bean.AmazonFile;
import au.com.translatorss.bean.ServiceRequest;
import au.com.translatorss.bean.ServiceResponse;
import au.com.translatorss.bean.User;
import au.com.translatorss.enums.FileType;

import java.io.InputStream;
import java.util.List;

public interface AmazonService {

    List<AmazonFile> findByServiceRequestIdAndType(Long serviceRequestId, FileType type);

    List<AmazonFile> findByServiceResponseIdAndType(Long serviceResponseId, FileType type);

    AmazonFile saveServiceRequestFile(ServiceRequest serviceRequest, String fileName, InputStream inputStream, String contentType);

    AmazonFile saveServiceResponseFile(ServiceResponse serviceResponse, String fileName, InputStream is, String contentType);

    AmazonFile saveInvoice(ServiceRequest sr, User createdBy, String fileName, InputStream is);
    
    void deleteFile(AmazonFile amazonFile);

    void deleteFile(String key);
    
}
