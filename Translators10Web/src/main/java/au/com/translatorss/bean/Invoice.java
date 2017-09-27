package au.com.translatorss.bean;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Invoice {
    private Long id;

    private ServiceRequest serviceRequest;

    private Date createdAt = new Date();

    private AmazonFile file;

    public Invoice() {
    }

    public Invoice(ServiceRequest serviceRequest) {
        this.serviceRequest = serviceRequest;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_invoice_id")
    @SequenceGenerator(sequenceName = "seq_invoice_id", name = "seq_invoice_id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "created_at", nullable = false)
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @OneToOne
    @JoinColumn(name = "service_request_id", nullable = false, unique = true)
    public ServiceRequest getServiceRequest() {
        return serviceRequest;
    }

    public void setServiceRequest(ServiceRequest serviceRequest) {
        this.serviceRequest = serviceRequest;
    }

    @ManyToOne
    @JoinColumn(name = "file_id", nullable = false)
    public AmazonFile getFile() {
        return file;
    }

    public void setFile(AmazonFile file) {
        this.file = file;
    }
}
