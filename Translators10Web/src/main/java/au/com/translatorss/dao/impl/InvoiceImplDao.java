package au.com.translatorss.dao.impl;

import au.com.translatorss.bean.Invoice;
import au.com.translatorss.dao.InvoiceDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Service
@Transactional
public class InvoiceImplDao extends GenericDaoImplementation<Invoice, Long> implements InvoiceDao {

    @Override
    public Invoice save(Invoice invoice) {
        Serializable save = this.getSessionFactory().getCurrentSession().save(invoice);
        currentSession().flush();
        invoice.setId((Long) save);
        return invoice;
    }

    @Override
    public Invoice findInvoiceByQuotationId(Long quotationId){
        Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(Invoice.class, "inv");
        criteria.createAlias("inv.quotation", "quo");
        criteria.add(Restrictions.eq("quo.id", quotationId));
        return (Invoice) criteria.uniqueResult();
    }

    @Override
    public void updateInv(Invoice invoice) {
        this.getSessionFactory().getCurrentSession().saveOrUpdate(invoice);
        currentSession().flush();
    }
}
