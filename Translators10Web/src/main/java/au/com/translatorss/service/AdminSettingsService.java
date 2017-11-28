package au.com.translatorss.service;

import au.com.translatorss.bean.Admin;

public interface AdminSettingsService {

	public void saveAdmin(Admin admin);

	public Admin getAdminByEmail(String adminEmail, String password);
}
