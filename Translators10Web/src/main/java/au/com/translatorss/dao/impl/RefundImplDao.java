package au.com.translatorss.dao.impl;

import org.springframework.stereotype.Repository;

import au.com.translatorss.bean.Refund;
import au.com.translatorss.dao.RefundDao;

@Repository
public class RefundImplDao extends GenericDaoImplementation<Refund, Long>implements RefundDao {

}
