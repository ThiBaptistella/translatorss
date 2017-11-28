package au.com.translatorss.service.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import au.com.translatorss.bean.AmazonFile;
import au.com.translatorss.bean.AmazonFileCSV;
import au.com.translatorss.bean.User;
import au.com.translatorss.dao.AmazonCSVFileDao;
import au.com.translatorss.enums.FileType;
import au.com.translatorss.service.AmazonCSVFileService;

@Service
@Transactional
public class AmazonCSVFileServiceImpl implements AmazonCSVFileService{

	private static List<String> folders = new ArrayList<String>();
	private AmazonS3 amazonS3;
	private static final String SLASH = "/";
	private final String BUCKET_NAME;
	
	@Autowired
    private AmazonCSVFileDao dao;
	
	@Inject
	public AmazonCSVFileServiceImpl(@Value("${aws.s3.bucket}") String bucketName, AmazonS3 amazonS3) {
	        this.amazonS3 = amazonS3;
	        BUCKET_NAME = bucketName;

	        if (!amazonS3.doesBucketExist(bucketName)) createBucket(bucketName);

	        checkAndCreateFolders();
	}
	    
	
	@Override
	public AmazonFileCSV getAmazonFileCSVByUserId(User user) {
		return this.dao.getAmazonFileCSVByUserId(user);
	}

	@Override
	public AmazonFileCSV saveCSV(User user, String fileName, InputStream is, String contentType) {
		
		FileType type;
		
		if("Paid".equals(contentType)){
			 type = FileType.PAYMENT_TRANSLATOR_FILE;
		}else {
			 type = FileType.REFUND_CUSTOMER_FILE;
		}
		
	    String key = String.format("%s/%d/%s", type.name(), user.getId(), fileName);
	    checkAndCreateFolder(type, user.getId());
	     
	    String url = saveFileOnAmazon(is, contentType, key);
	    AmazonFileCSV amazonFile = new AmazonFileCSV(key, url,new Date(),user, fileName, type);
	    return dao.saveCSV(amazonFile);
	}

	@Override
	public void deleteFile(AmazonFile amazonFile) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteFile(String key) {
		// TODO Auto-generated method stub
		
	}

	
	private String saveFileOnAmazon(InputStream is, String contentType, String key) {
	        ObjectMetadata metadata = new ObjectMetadata();
	        metadata.setContentType(contentType);
	        PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKET_NAME, key, is, metadata).withCannedAcl(CannedAccessControlList.PublicRead);
	        amazonS3.putObject(putObjectRequest);
	        return amazonS3.getUrl(BUCKET_NAME, key).toExternalForm();
	}
		
	private void checkAndCreateFolder(FileType type, Long id) {
	        String folder = type.name() + SLASH + String.valueOf(id) + SLASH;
	        if (!folders.contains(folder)) {
	            createFolder(folder);
	        }
	}
		
	private void createFolder(String folderName) {
		        ObjectMetadata metadata = new ObjectMetadata();
		        metadata.setContentLength(0);
		        InputStream emptyContent = new ByteArrayInputStream(new byte[0]);
		        PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKET_NAME,
		                folderName, emptyContent, metadata);
		        amazonS3.putObject(putObjectRequest);
		        folders.add(folderName);
	}
		 
	private void checkAndCreateFolders() {
		        ObjectListing objectListing = amazonS3.listObjects(new ListObjectsRequest().withBucketName(BUCKET_NAME).withDelimiter(SLASH));
		        folders.addAll(objectListing.getCommonPrefixes());

		        for (FileType fileType : FileType.values()) {
		            String name = fileType.name() + SLASH;
		            if (!folders.contains(name)) {
		                createFolder(name);
		            } else {
		                folders.addAll(listKeysInDirectory(fileType.name()));
		            }
		        }
	}

	private List<String> listKeysInDirectory(String prefix) {
		        String delimiter = SLASH;
		        if (!prefix.endsWith(delimiter)) {
		            prefix += delimiter;
		        }

		        ListObjectsRequest listObjectsRequest = new ListObjectsRequest()
		                .withBucketName(BUCKET_NAME)
		                .withPrefix(prefix)
		                .withDelimiter(delimiter);
		        ObjectListing objects = amazonS3.listObjects(listObjectsRequest);
		        return objects.getCommonPrefixes();
	}
		 
	private void createBucket(String bucketName) {
		     amazonS3.createBucket(bucketName);
	}

	@Override
	public BigInteger getPaymentNextSecuence() {
		return dao.getPaymentNextSecuence();
	}

	@Override
	public BigInteger getRefundNextSecuence() {
		return dao.getRefundNextSecuence();
	}


	@Override
	public List<AmazonFileCSV> getAll() {
		// TODO Auto-generated method stub
		return dao.getAll();
	}

}
