package au.com.translatorss.dao;

import au.com.translatorss.bean.AmazonFile;
import au.com.translatorss.bean.AmazonFilePhoto;
import au.com.translatorss.enums.FileType;

import java.util.List;

public interface AmazonFileDao extends GenericDao<AmazonFile, Long> {

    AmazonFile save(AmazonFile amazonFile);

    List<AmazonFile> findByServiceRequestIdAndType(Long serviceRequestId, FileType fileType);

    List<AmazonFile> findByServiceResponseIdAndType(Long serviceResponseId, FileType fileType);

    List findFileNameBySerReqId(String fileName, Long serReqId);

    List<String> findFileNameBySerRespId(String fileName, Long serRespId);

	List<AmazonFile> getAllExpiredFiles();
	
	
}
