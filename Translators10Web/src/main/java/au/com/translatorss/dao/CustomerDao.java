package au.com.translatorss.dao;

import au.com.translatorss.bean.Customer;

public interface CustomerDao extends GenericDao<Customer, Long> {
    Customer getByEmail(String email);
}
