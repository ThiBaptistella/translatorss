package au.com.translatorss.dao.impl;

import au.com.translatorss.bean.Admin;
import au.com.translatorss.dao.AdminSettingDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class AdminSettingDaoImpl extends GenericDaoImplementation<Admin, Long> implements AdminSettingDao {

	@Override
	public void saveAdmin(Admin admin) {

	}

	@Override
	public Admin getAdminByEmail(String adminEmail, String password) {
		Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(Admin.class, "admin");
		criteria.createAlias("admin.user", "user");
	    criteria.add(Restrictions.eq("user.email", adminEmail));
	    criteria.add(Restrictions.eq("user.password", password));
		return (Admin) criteria.uniqueResult();
	}

}
