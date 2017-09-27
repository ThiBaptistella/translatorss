package au.com.translatorss.dao.impl;

import org.springframework.stereotype.Repository;

import au.com.translatorss.bean.RefundRequest;
import au.com.translatorss.dao.RefundRequestDao;

@Repository
public class RefundRequestImplDao extends GenericDaoImplementation<RefundRequest, Long>implements RefundRequestDao {

}
