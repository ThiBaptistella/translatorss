package au.com.translatorss.daily.scheduler.quotes;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import au.com.translatorss.bean.AmazonFile;
import au.com.translatorss.service.AmazonService;

@Component
public class FilesManagment {

	@Autowired
	private AmazonService amazonService;
	
	public FilesManagment() {
		
	}
	
    @Scheduled(cron="0 0 0 ? * 1/5")
	public void FilesManager() {
		List<AmazonFile> amazonFileList = amazonService.getAllExpiredFiles();
		for(AmazonFile amazonFile : amazonFileList) {
			amazonService.deleteFile(amazonFile);
		}
	}
    
 
}
