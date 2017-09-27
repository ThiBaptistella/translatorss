package au.com.translatorss.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import au.com.translatorss.bean.Logintry;
import au.com.translatorss.bean.User;
import au.com.translatorss.dao.LogintryDao;

@Repository
public class LogintryDaoImpl extends GenericDaoImplementation<Logintry, Long> implements LogintryDao {

	@Override
	public Logintry getByUser(Long user) {
		Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(Logintry.class);
		criteria.add(Restrictions.eq("user.id", user));
		return (Logintry) criteria.uniqueResult();
	}

	@Override
	public void IncreaseTries(User user) {
		Logintry entity = getByUser(user.getId());
		if (entity == null) {
			entity = new Logintry(user);
		} else {
			entity.increaseTries();
		}
		saveOrUpdate(entity);

	}

	@Override
	public Long getTriesByUser(Long user) {
		Logintry entity = getByUser(user);
		return entity == null ? 0l : entity.getTries();
	}

	@Override
	public void DeleteByUser(Long user) {
		Logintry entity = getByUser(user);
		if (entity != null) {
			remove(entity);
		}
	}
}
