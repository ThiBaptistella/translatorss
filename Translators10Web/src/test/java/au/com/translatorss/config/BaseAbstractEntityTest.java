package au.com.translatorss.config;

import java.io.Serializable;

import org.hibernate.SessionFactory;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * Base class to be inherited by all the Tests within Preserve. This class contains all the
 * configuration to rollback all the crud operations made to the arts database. Also contains all
 * the loaders to be run and initialized before running the tests. If a new Loader is created, it
 * should be placed here, autowired and initialized.
 * @author javierF
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PersistenceHibernateConfig.class })
@TransactionConfiguration(transactionManager = "hibernateTransactionManager", defaultRollback = true)
@Transactional
public abstract class BaseAbstractEntityTest {

   /* @Autowired
    @Qualifier("generic")
    protected PreserveDao hibernateDAO;*/

    @Autowired
    protected SessionFactory sessionFactory;

    /**
     * This method initializes all the loaders. It can be used for initial setup, but each test must
     * define its initialization process, calling explicitly to each loader.
     */
   
/*
    public <T extends Serializable> T saveIfNotExist(final Serializable id, final T anEntity) {
        Serializable entity = hibernateDAO.findById(id, anEntity.getClass());
        if (entity == null) {
            hibernateDAO.saveOrUpdate(anEntity);
        }
        return anEntity;
    }*/

    protected void flushCurrentSession() {
        sessionFactory.getCurrentSession().flush();
        sessionFactory.getCurrentSession().clear();
    }

}
