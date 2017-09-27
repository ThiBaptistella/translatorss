package au.com.translatorss.bean;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Customer
 */
@Entity
@Table(name = "customer")
@Inheritance(strategy = InheritanceType.JOINED)
public class Customer implements java.io.Serializable {

    private static final long serialVersionUID = 3696195133818349742L;
    private Long id;
    private Date creationdate;
    private Date updatedate;
    private String phone;
    private String address;
    private User user = new User();
    private Set<ServiceRequest> serviceRequestList;

    @Id
    @SequenceGenerator(name = "customer_sequence", sequenceName = "customerId_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_sequence")
    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creationdate", length = 29)
    public Date getCreationdate() {
        return this.creationdate;
    }

    public void setCreationdate(Date creationdate) {
        this.creationdate = creationdate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updatedate", length = 29)
    public Date getUpdatedate() {
        return this.updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    @Column(name = "phone", nullable = false, length = 50)
    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name = "address", nullable = false, length = 300)
    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    public Set<ServiceRequest> getServiceRequestList() {
        return serviceRequestList;
    }

    public void setServiceRequestList(Set<ServiceRequest> serviceRequestList) {
        this.serviceRequestList = serviceRequestList;
    }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
