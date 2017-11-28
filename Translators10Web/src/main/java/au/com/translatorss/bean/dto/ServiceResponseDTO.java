package au.com.translatorss.bean.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class ServiceResponseDTO {

    private long id;
    private List<MultipartFile> files;

    
    public ServiceResponseDTO(){}
    
    public List<MultipartFile> getFiles() {
        return files;
    }
    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
}
