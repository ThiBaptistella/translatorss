package au.com.translatorss.bean.dto;

import au.com.translatorss.bean.AmazonFile;

import java.util.Date;

public class AmazonFileDto {
    private Long id;
    private String url;
    private String fileName;
    private String createdBy;
    private Date createdAt;

    public AmazonFileDto(AmazonFile file) {
        this.setCreatedAt(file.getCreatedAt());
        this.setCreatedBy(file.getCreatedBy().getName());
        this.setUrl(file.getUrl());
        this.setFileName(file.getFileName());
        this.setId(file.getId());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
