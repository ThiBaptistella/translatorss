package au.com.translatorss.dao.impl;

import org.springframework.stereotype.Repository;

import au.com.translatorss.bean.ServiceRequestFiles;
import au.com.translatorss.dao.ServiceRequestFilesDao;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public class ServiceRequestFilesImplDao extends GenericDaoImplementation<ServiceRequestFiles, Long>
		implements ServiceRequestFilesDao {

}
