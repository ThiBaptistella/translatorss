package au.com.translatorss.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.com.translatorss.bean.Admin;
import au.com.translatorss.dao.AdminSettingDao;
import au.com.translatorss.service.AdminSettingsService;

@Service
@Transactional
public class AdminSettingServiceImpl implements AdminSettingsService{

	@Autowired
	private AdminSettingDao admindao;

	@Override
	public void saveAdmin(Admin admin) {	
	}

	@Override
	public Admin getAdminByEmail(String adminEmail, String password) {
		return admindao.getAdminByEmail(adminEmail, password);
	}

	
}
