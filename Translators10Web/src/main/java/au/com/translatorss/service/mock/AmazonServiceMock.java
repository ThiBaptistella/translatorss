package au.com.translatorss.service.mock;

import java.io.InputStream;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.com.translatorss.bean.AmazonFile;
import au.com.translatorss.bean.ServiceRequest;
import au.com.translatorss.bean.ServiceResponse;
import au.com.translatorss.bean.User;
import au.com.translatorss.enums.FileType;
import au.com.translatorss.service.AmazonService;

//@Service
//@Component
//@Transactional
public class AmazonServiceMock implements AmazonService{

	@Override
	public List<AmazonFile> findByServiceRequestIdAndType(Long serviceRequestId, FileType type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AmazonFile> findByServiceResponseIdAndType(Long serviceResponseId, FileType type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AmazonFile saveServiceRequestFile(ServiceRequest serviceRequest, String fileName, InputStream inputStream,
			String contentType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AmazonFile saveServiceResponseFile(ServiceResponse serviceResponse,User createdBy, String fileName, InputStream is,
			String contentType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AmazonFile saveInvoice(ServiceRequest sr, User createdBy, String fileName, InputStream is) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteFile(AmazonFile amazonFile) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteFile(String key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<AmazonFile> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AmazonFile> getAllExpiredFiles() {
		// TODO Auto-generated method stub
		return null;
	}

}
