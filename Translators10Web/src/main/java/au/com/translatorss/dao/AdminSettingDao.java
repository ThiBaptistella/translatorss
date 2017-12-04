package au.com.translatorss.dao;

import au.com.translatorss.bean.Admin;

public interface AdminSettingDao {

	public void saveAdmin(Admin admin);

	public Admin getAdminByEmail(String adminEmail, String password);

}
