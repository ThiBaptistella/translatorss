package au.com.translatorss.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "aprovedservicerequest")
public class ServiceRequestApprovedRegister implements Comparable<ServiceRequestApprovedRegister> {

	private Long id;
	private ServiceRequest serviceRequest;
	private Date approvedDate;
	
	public ServiceRequestApprovedRegister(){}
	
	@Id
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    @Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "servicerequestid", nullable = false)
	@Fetch(FetchMode.SELECT)
	public ServiceRequest getServiceRequest() {
		return serviceRequest;
	}
	
	public void setServiceRequest(ServiceRequest serviceRequest) {
		this.serviceRequest = serviceRequest;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creationdate", nullable = false, length = 29)
	public Date getApprovedDate() {
		return approvedDate;
	}
	
	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}
	
	@Override
	public int compareTo(ServiceRequestApprovedRegister o) {
	  return getApprovedDate().compareTo(o.getApprovedDate());
	}
}
