package au.com.translatorss.dao.impl;

import org.springframework.stereotype.Repository;

import au.com.translatorss.bean.ChangeRequest;
import au.com.translatorss.dao.ChangeRequestDao;

@Repository
public class ChangeRequestImplDao extends GenericDaoImplementation<ChangeRequest, Long>implements ChangeRequestDao {

}
