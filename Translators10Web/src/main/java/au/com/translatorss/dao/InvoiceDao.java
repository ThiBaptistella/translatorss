package au.com.translatorss.dao;

import au.com.translatorss.bean.Invoice;

public interface InvoiceDao extends GenericDao<Invoice, Long>{
    Invoice save(Invoice invoice);

    Invoice findInvoiceByQuotationId(Long quotationId);

    void updateInv(Invoice invoice);
}
