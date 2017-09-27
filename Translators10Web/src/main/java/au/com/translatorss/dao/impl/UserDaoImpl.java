package au.com.translatorss.dao.impl;

import au.com.translatorss.bean.User;
import au.com.translatorss.dao.UserDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends GenericDaoImplementation<User, Long> implements UserDao {


    @Override
    public User getByEmail(String email) {
        Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("email", email));
        User user = (User) criteria.uniqueResult();
        return user;
    }

    @Override
    public User getById(Long id) {
        Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("id", id));
        User user = (User) criteria.uniqueResult();
        return user;
    }

    @Override
    public Long getUserIdByEmail(String email) {
        Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(User.class)
                .setProjection(Projections.property("id"));
        criteria.add(Restrictions.sqlRestriction("TRIM(email) = ?", email.trim(), StringType.INSTANCE));
        return (Long) criteria.uniqueResult();
    }

    @Override
    public boolean isExistEmail(String email) {
        Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(User.class)
                .setProjection(Projections.rowCount());
        criteria.add(Restrictions.eq("email", email));
        long count = (Long) criteria.uniqueResult();
        return count != 0;
    }

    @Override
    public boolean isAvailableEmailAndExcludeUser(String email, Long id) {
        Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("email", email));
        criteria.add(Restrictions.not(Restrictions.eq("id", id)));
        User count = (User) criteria.uniqueResult();
        return (count==null);
    }

}
