package au.com.translatorss.dao.impl;

import au.com.translatorss.dao.GenericDao;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.transaction.Transactional;

@SuppressWarnings("unchecked")
public abstract class GenericDaoImplementation<E, K extends Serializable> implements GenericDao<E, K> {
	
	@Autowired
	private SessionFactory sessionFactory;

	private static final Logger logger = Logger.getLogger(GenericDaoImplementation.class);

	
	protected Class<? extends E> daoType;

	@SuppressWarnings("rawtypes")
	public GenericDaoImplementation() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		daoType = (Class) pt.getActualTypeArguments()[0];
	}

	protected Session currentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void add(E entity) {
		currentSession().save(entity);
	}

	@Override
	public void saveOrUpdate(E entity) {	
		logger.info("Welcome GenericDaoImplementation.");
		currentSession().saveOrUpdate(entity);
		currentSession().flush();
	}

	@Override
	public void update(E entity) {
		currentSession().merge(entity);
	}

	@Override
	public void remove(E entity) {
		currentSession().delete(entity);
	}

	@Override
	public E find(K key) {
		return (E) currentSession().get(daoType, key);
	}

	@Override
	public List<E> getAll() {
		return currentSession().createCriteria(daoType).list();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
