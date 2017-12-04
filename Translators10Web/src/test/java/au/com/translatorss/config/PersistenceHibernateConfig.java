package au.com.translatorss.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.LogFactoryImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan({ "au.com.translatorss.preserve.dao" })
@ImportResource("classpath:external-properties.xml")
@EnableTransactionManagement
public class PersistenceHibernateConfig {
    protected Log log = LogFactoryImpl.getLog(this.getClass());

    @Value("${postgres.driver}")
    private String postgresDriver;

    @Value("${postgres.url}")
    private String postgresUrl;

    @Value("${postgres.username}")
    private String postgresUsername;

    @Value("${postgres.password}")
    private String postgresPassword;

    @Value("${postgres.dialect}")
    private String postgresDialect;

    @Value("${postgres.ejb.naming_strategy}")
    private String naming_strategy;

    @Value("${postgres.charset}")
    private String charset;

    @Value("${postgres.show_sql}")
    private String show_sql;

    @Value("${postgres.schema}")
    private String schema;

    @Value("${h2.driver}")
    private String h2Driver;

    @Value("${h2.url}")
    private String h2Url;

    @Value("${h2.username}")
    private String h2Username;

    @Value("${h2.password}")
    private String h2Password;

    @Value("${h2.dialect}")
    private String h2Dialect;

    @Value("${db.type}")
    private String dbType;

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(hibernateDataSource());
        String[] packagesToScan = { "com.starmount.preserve.arts.v6_0_0", "com.starmount.preserve.equate", "com.starmount.preserve.tax" };
        sessionFactory.setPackagesToScan(packagesToScan);
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    @Bean
    public DataSource hibernateDataSource() {
        DataSource dataSource = null;
        if (StringUtils.equals(dbType, "H2")) {

            dataSource = h2DataSource();
        } else {
            dataSource = postGresDataSource();
        }
        log.info("Using " + dbType);
        return dataSource;
    }

    public DataSource postGresDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(postgresDriver);
        dataSource.setUrl(postgresUrl + "/" + schema);
        dataSource.setUsername(postgresUsername);
        dataSource.setPassword(postgresPassword);
        return dataSource;
    }

    public DataSource h2DataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(h2Driver);
        dataSource.setUrl(h2Url);
        dataSource.setUsername(h2Username);
        dataSource.setPassword(h2Password);
        return dataSource;
    }

    @Bean
    public Properties hibernateProperties() {
        Properties properties = null;
        if (StringUtils.equals(dbType, "H2")) {
            properties = h2HibernateProperties();
        } else {
            properties = postgresHibernateProperties();
        }
        return properties;
    }

    public Properties postgresHibernateProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", postgresDialect);
        properties.setProperty("hibernate.ejb.naming_strategy", naming_strategy);
        properties.setProperty("hibernate.connection.charSet", charset);
        properties.setProperty("hibernate.use_sql_comments", show_sql);
        properties.setProperty("hibernate.format_sql", show_sql);
        properties.setProperty("default_schema", schema);
        return properties;
    }

    public Properties h2HibernateProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", h2Dialect);
        properties.setProperty("hibernate.ejb.naming_strategy", naming_strategy);
        properties.setProperty("hibernate.connection.charSet", charset);
        properties.setProperty("hibernate.use_sql_comments", show_sql);
        properties.setProperty("default_schema", schema);
        return properties;
    }

    @Bean(name = "hibernateTransactionManager")
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory().getObject());
        return txManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
