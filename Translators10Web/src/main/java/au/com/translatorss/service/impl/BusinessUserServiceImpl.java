package au.com.translatorss.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import au.com.translatorss.bean.BusinessUser;
import au.com.translatorss.bean.Translator;
import au.com.translatorss.bean.User;
import au.com.translatorss.bean.dto.ServiceRequestDTO;
import au.com.translatorss.dao.BusinessUserDao;
import au.com.translatorss.service.BusinessUserService;

@Service
@Transactional
public class BusinessUserServiceImpl implements BusinessUserService{

	private static final Logger logger = LoggerFactory.getLogger(BusinessUserServiceImpl.class);
	
	@Autowired
	private BusinessUserDao businessUserDao;
	
	@Override
	public void saveOrUpdate(BusinessUser entity) {
		businessUserDao.saveOrUpdate(entity);
	}

	@Override
	public List<BusinessUser> getAll() {
		return businessUserDao.getAll();
	}

	@Override
	public BusinessUser get(Long id) {
		return businessUserDao.find(id);
	}

	@Override
	public void add(BusinessUser entity) {
		businessUserDao.add(entity);
	}

	@Override
	public void update(BusinessUser entity) {
		businessUserDao.update(entity);
	}

	@Override
	public void remove(BusinessUser entity) {
		businessUserDao.remove(entity);
	}

	@Override
	public BusinessUser getUserByEmail(String userEmail, String password) {
		return businessUserDao.getTranslatorByEmail(userEmail,password);
	}
	
	@Override
	public BusinessUser getBusinessUSerByUserId(User user) {
		return businessUserDao.getBusinessUserByUserId(user);
	}
}
