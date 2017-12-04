package au.com.translatorss.dao.impl;

import au.com.translatorss.bean.Customer;
import au.com.translatorss.dao.CustomerDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerImplDao extends GenericDaoImplementation<Customer, Long> implements CustomerDao {

    @Override
    public Customer getByEmail(String email) {
        Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(Customer.class, "cust");
        criteria.createAlias("cust.user", "user");
        criteria.add(Restrictions.eq("user.email", email));
        return (Customer) criteria.uniqueResult();
    }
}
