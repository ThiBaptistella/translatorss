package au.com.translatorss.bean;

import au.com.translatorss.enums.FileType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "amazon_files_photo")
public class AmazonFilePhoto{

	private long id;
    private String key;
    private String url;
    private Date createdAt = new Date();
    private User createdBy;
    private String fileName;
    private FileType fileType;
    
    public AmazonFilePhoto(){}
    
	public AmazonFilePhoto(String key, String url, Date createdAt, User createdBy, String fileName, FileType fileType) {
		this.key = key;
		this.url = url;
		this.createdAt = createdAt;
		this.createdBy = createdBy;
		this.fileName = fileName;
		this.fileType = fileType;
	}

	@Id
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

    @Column(name = "key", nullable = false, length = 200)
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

    @Column(name = "url", nullable = false, length = 1000)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

    @Column(name = "created_at")
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@ManyToOne
	@JoinColumn(name = "created_by", nullable = false)
	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "file_name", nullable = false)
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
	public FileType getFileType() {
		return fileType;
	}

	public void setFileType(FileType fileType) {
		this.fileType = fileType;
	}
}
