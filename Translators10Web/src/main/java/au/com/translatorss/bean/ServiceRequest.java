package au.com.translatorss.bean;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

/**
 * Servicerequest
 */
@Entity
@Table(name = "servicerequest")
public class ServiceRequest implements java.io.Serializable {

    private static final long serialVersionUID = 4379883079538477504L;
    private Long id;
    private BusinessUser customer;
    private ServiceRequestStatus serviceRequestStatus;
    private ServiceRequestCategory serviceRequestCategory;
    private String languagefrom;
    private String languageTo;
    private TimeFrame timeFrame;
    private String description;
    private Boolean hardcopy;
    private Date creationDate;
    private Date finishQuoteSelection;
    private Date finishDate;
    private Set<Quotation> quotationList = new HashSet<Quotation>(0);
    private Set<ServiceRequestFiles> serviceRequestFiles = new HashSet<ServiceRequestFiles>(0);
    private Set<Conversation> conversationList = new HashSet<Conversation>(0);
    private Translator translator;
    private ServiceRequestPayment serviceRequestPayment;
    private Invoice invoice;
    private List<AmazonFile> amazonFiles = new ArrayList<AmazonFile>();
//    private ServiceResponse serviceResponse;

	@Id
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customerid", nullable = false)
    public BusinessUser getCustomer() {
        return this.customer;
    }

    public void setCustomer(BusinessUser customer) {
        this.customer = customer;
    }

   // @Column(name = "timeframeid", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "timeframeid", nullable = false)
    public TimeFrame getTimeFrame() {
        return this.timeFrame;
    }

    public void setTimeFrame(TimeFrame timeFrame) {
        this.timeFrame = timeFrame;
    }

    @Column(name = "description", nullable = false)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creationdate", nullable = false, length = 29)
    public Date getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(Date creationdate) {
        this.creationDate = creationdate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "finishdate", nullable = false, length = 29)
    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "servicerequeststatusid")
    public ServiceRequestStatus getServiceRequestStatus() {
        return serviceRequestStatus;
    }

    public void setServiceRequestStatus(ServiceRequestStatus status) {
        this.serviceRequestStatus = status;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "servicerequestcategoryid")
    public ServiceRequestCategory getServiceRequestCategory() {
        return serviceRequestCategory;
    }

    public void setServiceRequestCategory(ServiceRequestCategory serviceRequestCategory) {
        this.serviceRequestCategory = serviceRequestCategory;
    }

    @Column(name = "language_from", nullable = false)
    public String getLanguagefrom() {
        return languagefrom;
    }

    public void setLanguagefrom(String languagefrom) {
        this.languagefrom = languagefrom;
    }

    @Column(name = "language_to", nullable = false)
    public String getLanguageTo() {
        return languageTo;
    }

    public void setLanguageTo(String languageTo) {
        this.languageTo = languageTo;
    }

    @Column(name = "hardcopy", nullable = false)
    public Boolean getHardcopy() {
        return hardcopy;
    }

    public void setHardcopy(Boolean hardcopy) {
        this.hardcopy = hardcopy;
    }

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "serviceRequest", orphanRemoval=true)
    public Set<Quotation> getQuotationList() {
        return quotationList;
    }

    public void setQuotationList(Set<Quotation> quotationList) {
        this.quotationList = quotationList;
    }

//    public ServiceResponse getServiceResponse() {
//		return serviceResponse;
//	}
//
//    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "serviceRequest", orphanRemoval=true)
//	public void setServiceResponse(ServiceResponse serviceResponse) {
//		this.serviceResponse = serviceResponse;
//	}
    
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "serviceRequest", orphanRemoval=true)
    public Set<Conversation> getConversationList() {
        return conversationList;
    }

    public void setConversationList(Set<Conversation> conversationList) {
        this.conversationList = conversationList;
    }

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "serviceRequest")
    public Set<ServiceRequestFiles> getServiceRequestFiles() {
        return serviceRequestFiles;
    }

    public void setServiceRequestFiles(Set<ServiceRequestFiles> serviceRequestFiles) {
        this.serviceRequestFiles = serviceRequestFiles;
    }


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "translatorid")
    public Translator getTranslator() {
      return this.translator;
    }
    
    public void setTranslator(Translator translator) {
      this.translator = translator;
    }
    
  //@OneToOne(fetch = FetchType.LAZY, mappedBy = "servicerequest")
    @OneToOne(mappedBy="serviceRequest", cascade = CascadeType.ALL)
    @Fetch(FetchMode.SELECT)
    public ServiceRequestPayment getServiceRequestPayment() {
      return this.serviceRequestPayment;
    }

    public void setServiceRequestPayment(ServiceRequestPayment serviceRequestPayment) {
    	this.serviceRequestPayment = serviceRequestPayment;
    }

    public boolean hasQuoteFrom(Long id) {
        for(Quotation quotation: this.quotationList){
            if(quotation.getTranslator().getId().equals(id)){
                return true;
            }
        }
        return false;
    }
 
    public boolean IsStandar(){
        return this.serviceRequestCategory.getDefaultPrice();
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updatedate", nullable = false, length = 29)
	public Date getFinishQuoteSelection() {
		return finishQuoteSelection;
	}

	public void setFinishQuoteSelection(Date finishQuoteSelection) {
		this.finishQuoteSelection = finishQuoteSelection;
	}

	@OneToOne(mappedBy = "serviceRequest")
    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "serviceRequest")
    public List<AmazonFile> getAmazonFiles() {
        return amazonFiles;
    }

    public void setAmazonFiles(List<AmazonFile> amazonFiles) {
        this.amazonFiles = amazonFiles;
    }
}
