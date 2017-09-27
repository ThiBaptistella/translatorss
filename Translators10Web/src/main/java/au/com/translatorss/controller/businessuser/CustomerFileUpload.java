package au.com.translatorss.controller.businessuser;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class CustomerFileUpload {

	private List<MultipartFile> customerFiles;
	 
    public List<MultipartFile> getFiles() {
        return customerFiles;
    }
 
    public void setFiles(List<MultipartFile> files) {
        this.customerFiles = files;
    }
	
}
