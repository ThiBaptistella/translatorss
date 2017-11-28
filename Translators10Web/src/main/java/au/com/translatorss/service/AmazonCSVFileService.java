package au.com.translatorss.service;

import java.io.InputStream;
import java.math.BigInteger;
import java.util.List;

import au.com.translatorss.bean.AmazonFile;
import au.com.translatorss.bean.AmazonFileCSV;
import au.com.translatorss.bean.User;

public interface AmazonCSVFileService {

	 	AmazonFileCSV getAmazonFileCSVByUserId(User user);
		
	    AmazonFileCSV saveCSV(User createdBy, String fileName, InputStream is, String contentType);

	    void deleteFile(AmazonFile amazonFile);

	    void deleteFile(String key);
	    
		BigInteger getPaymentNextSecuence();

		BigInteger getRefundNextSecuence();

		List<AmazonFileCSV> getAll();

}
