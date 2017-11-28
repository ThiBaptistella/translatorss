package au.com.translatorss.dao;

import java.math.BigInteger;
import java.util.List;

import au.com.translatorss.bean.AmazonFileCSV;
import au.com.translatorss.bean.User;

public interface AmazonCSVFileDao {

	AmazonFileCSV saveCSV(AmazonFileCSV amazonFilePhoto);

	AmazonFileCSV getAmazonFileCSVByUserId(User id);
	
	BigInteger getPaymentNextSecuence();
	
	BigInteger getRefundNextSecuence();
	
	List<AmazonFileCSV> getAll();

}
