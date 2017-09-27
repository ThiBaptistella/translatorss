package au.com.translatorss.service.impl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import au.com.translatorss.bean.AmazonFile;
import au.com.translatorss.bean.ServiceRequest;
import au.com.translatorss.bean.ServiceResponse;
import au.com.translatorss.bean.User;
import au.com.translatorss.dao.AmazonFileDao;
import au.com.translatorss.enums.FileType;
import au.com.translatorss.service.AmazonService;

@Service
@Component
@Transactional
public class AmazonServiceImpl implements AmazonService {

    private static final String SLASH = "/";
    private AmazonS3 amazonS3;
    private final String BUCKET_NAME;
    private static List<String> folders = new ArrayList<String>();

    @Autowired
    private AmazonFileDao dao;

    @Override
    public List<AmazonFile> findByServiceRequestIdAndType(Long serviceRequestId, FileType type) {
        return dao.findByServiceRequestIdAndType(serviceRequestId, type);
    }

    @Override
    public List<AmazonFile> findByServiceResponseIdAndType(Long serviceResponseId, FileType type) {
        return dao.findByServiceResponseIdAndType(serviceResponseId, type);
    }

//    public AmazonServiceImpl() {
//        BUCKET_NAME = "translatorss-storage-files"; 
//        AWSCredentials credentials = new BasicAWSCredentials("AKIAIVXDJDOPB7WBWBJQ", "oKKkULGcwXdbhkJnRkJ3W7wypFOYwrjyAahkqGYT");
//		AmazonS3 s3client = new AmazonS3Client(credentials);
//        if (!amazonS3.doesBucketExist(BUCKET_NAME)) createBucket(BUCKET_NAME);
//        checkAndCreateFolders();
//    }

    @Inject
    public AmazonServiceImpl(@Value("${aws.s3.bucket}") String bucketName, AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
        BUCKET_NAME = bucketName;

        if (!amazonS3.doesBucketExist(bucketName)) createBucket(bucketName);

        checkAndCreateFolders();
    }
    
    @Override
    public AmazonFile saveServiceRequestFile(ServiceRequest serviceRequest, final String fileName, InputStream is, String contentType) {
        FileType type = FileType.SERVICE_REQUEST;
        List<String> fileNameBySerReqId = dao.findFileNameBySerReqId(fileName, serviceRequest.getId());
        String newFileName = fileName;
        for (int i = 0; fileNameBySerReqId.contains(newFileName); i++) {
            newFileName = String.format("%d_%s", i, fileName);
        }

        String key = String.format("%s/%d/%s", type.name(), serviceRequest.getId(), newFileName);
        checkAndCreateFolder(type, serviceRequest.getId());
        String url = saveFileOnAmazon(is, contentType, key);

        AmazonFile amazonFile = new AmazonFile(newFileName, key, type, url, serviceRequest.getCustomer().getUser());

        amazonFile.setServiceRequest(serviceRequest);

        return dao.save(amazonFile);
    }

   
    
    @Override
    public AmazonFile saveServiceResponseFile(ServiceResponse serviceResponse, final String fileName, InputStream is, String contentType) {
        FileType type = FileType.SERVICE_RESPONSE;
        List<String> fileNameBySerReqId = dao.findFileNameBySerRespId(fileName, serviceResponse.getId());
        String newFileName = fileName;
        for (int i = 0; fileNameBySerReqId.contains(newFileName); i++) {
            newFileName = String.format("%d_%s", i, fileName);
        }
        String key = String.format("%s/%d/%s", type.name(), serviceResponse.getId(), newFileName);

        checkAndCreateFolder(type, serviceResponse.getId());
        String url = saveFileOnAmazon(is, contentType, key);

        AmazonFile amazonFile = new AmazonFile(newFileName, key, type, url, serviceResponse.getTranslator().getUser());
        amazonFile.setServiceResponse(serviceResponse);

        return dao.save(amazonFile);
    }

    @Override
    public AmazonFile saveInvoice(ServiceRequest sr, User createdBy, String fileName, InputStream is) {
        FileType type = FileType.INVOICE;
        String contentType = "application/pdf";
        String key = String.format("%s/%s", type.name(), fileName);

        String url = saveFileOnAmazon(is, contentType, key);

        AmazonFile amazonFile = new AmazonFile(fileName, key, type, url, sr.getCustomer().getUser());

        amazonFile.setServiceRequest(sr);
        return dao.save(amazonFile);
    }

    private String saveFileOnAmazon(InputStream is, String contentType, String key) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(contentType);
        PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKET_NAME, key, is, metadata).withCannedAcl(CannedAccessControlList.PublicRead);
        amazonS3.putObject(putObjectRequest);
        return amazonS3.getUrl(BUCKET_NAME, key).toExternalForm();
    }

    @Override
    public void deleteFile(AmazonFile amazonFile) {
        amazonS3.deleteObject(new DeleteObjectRequest(BUCKET_NAME, amazonFile.getKey()));
    }

    @Override
    public void deleteFile(String key) {
        amazonS3.deleteObject(new DeleteObjectRequest(BUCKET_NAME, key));
    }

    public File convert(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
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

	
}
