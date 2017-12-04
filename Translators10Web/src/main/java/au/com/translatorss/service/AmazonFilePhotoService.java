package au.com.translatorss.service;

import java.io.InputStream;

import au.com.translatorss.bean.AmazonFile;
import au.com.translatorss.bean.AmazonFilePhoto;
import au.com.translatorss.bean.User;

public interface AmazonFilePhotoService {

    AmazonFilePhoto getAmazonFilePhotoByUserId(User user);
	
    AmazonFilePhoto savePhoto(User createdBy, String fileName, InputStream is, String contentType);

    void deleteFile(AmazonFile amazonFile);

    void deleteFile(String key);
}
