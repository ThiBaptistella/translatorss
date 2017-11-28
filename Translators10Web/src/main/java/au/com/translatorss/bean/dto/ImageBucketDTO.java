package au.com.translatorss.bean.dto;

import org.springframework.web.multipart.MultipartFile;

public class ImageBucketDTO {

		private MultipartFile file;
     
	    public MultipartFile getFile() {
	        return file;
	    }
	 
	    public void setFile(MultipartFile file) {
	        this.file = file;
	    }
}
