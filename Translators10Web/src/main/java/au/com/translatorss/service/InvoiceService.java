package au.com.translatorss.service;

import au.com.translatorss.bean.Invoice;

public interface InvoiceService {
    Invoice save(Invoice invoice);

    void update(Invoice invoice);
}
