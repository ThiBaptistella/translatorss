package au.com.translatorss.service.impl;

import au.com.translatorss.bean.Invoice;
import au.com.translatorss.dao.InvoiceDao;
import au.com.translatorss.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceDao invoiceDao;

    @Override
    public Invoice save(Invoice invoice) {
        return invoiceDao.save(invoice);
    }

    @Override
    public void update(Invoice invoice) {
        invoiceDao.updateInv(invoice);
    }
}
