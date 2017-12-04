package au.com.translatorss.bean.dto;


import au.com.translatorss.bean.Quotation;
import au.com.translatorss.bean.ServiceRequest;
import au.com.translatorss.bean.Translator;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.util.Date;

public class InvoicePdfDto {

    private String naatiNumber;
    private String invoiceNum;
    private String customerDetails;
    private Date invoiceDate;
    private String description;
    private Long qty = 1L;
    private BigDecimal rate;
    private BigDecimal price;
    private String translatorDetails;


    public InvoicePdfDto(Quotation quotation) {
        ServiceRequest serviceRequest = quotation.getServiceRequest();
        Translator translator = serviceRequest.getTranslator();
        this.translatorDetails = StringUtils.trim(translator.getUser().getName());
        this.naatiNumber = translator.getNaatiNumber();
        this.customerDetails = String.format("%s, %s", StringUtils.trim(serviceRequest.getCustomer().getUser().getName()), serviceRequest.getCustomer().getAddress());
        this.description = serviceRequest.getDescription();
        this.rate = quotation.getValue();
        this.price = quotation.getValue();
    }

    public InvoicePdfDto(TranslatorQuotationDTO quotation, ServiceRequest serviceRequest) {
        Translator translator = serviceRequest.getTranslator();
        this.translatorDetails = StringUtils.trim(translator.getUser().getName());
        this.naatiNumber = translator.getNaatiNumber();
        this.customerDetails = String.format("%s, %s", StringUtils.trim(serviceRequest.getCustomer().getUser().getName()), serviceRequest.getCustomer().getAddress());
        this.description = serviceRequest.getDescription();
        this.rate = new BigDecimal(quotation.getQuote());
        this.price = new BigDecimal(quotation.getQuote());
    }

    public void setTranslatorDetails(String translatorDetails) {
        this.translatorDetails = translatorDetails;
    }

    public void setNaatiNumber(String naatiNumber) {
        this.naatiNumber = naatiNumber;
    }

    public void setInvoiceNum(String invoiceNum) {
        this.invoiceNum = invoiceNum;
    }

    public void setCustomerDetails(String customerDetails) {
        this.customerDetails = customerDetails;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public String getTranslatorDetails() {
        return translatorDetails;
    }

    public String getNaatiNumber() {
        return naatiNumber;
    }

    public String getInvoiceNum() {
        return invoiceNum;
    }

    public String getCustomerDetails() {
        return customerDetails;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public String getDescription() {
        return description;
    }

    public Long getQty() {
        return qty;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public BigDecimal getPrice() {
        return price;
    }
}