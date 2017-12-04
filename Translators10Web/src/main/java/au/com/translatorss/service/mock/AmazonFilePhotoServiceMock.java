package au.com.translatorss.service.mock;

import java.io.InputStream;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.com.translatorss.bean.AmazonFile;
import au.com.translatorss.bean.AmazonFilePhoto;
import au.com.translatorss.bean.User;
import au.com.translatorss.service.AmazonFilePhotoService;

//@Service
//@Component
//@Transactional
public class AmazonFilePhotoServiceMock implements AmazonFilePhotoService {

	@Override
	public AmazonFilePhoto getAmazonFilePhotoByUserId(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AmazonFilePhoto savePhoto(User createdBy, String fileName, InputStream is, String contentType) {
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

}
