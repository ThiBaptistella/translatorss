package au.com.translatorss.dao.impl;

import org.springframework.stereotype.Repository;

import au.com.translatorss.bean.ServiceRequestConfiguration;
import au.com.translatorss.dao.ServiceRequestConfigurationDao;

@Repository
public class ServiceRequestConfigurationDaoImpl extends GenericDaoImplementation<ServiceRequestConfiguration, Long> implements ServiceRequestConfigurationDao{

    @Override
    public ServiceRequestConfiguration getServiceRequestConfiguration() {
        try {
            String query = "from ServiceRequestConfiguration serviceRequestConfiguration where id=1 ";
            
            @SuppressWarnings("unchecked")
            ServiceRequestConfiguration serviceRequestList = (ServiceRequestConfiguration) this.getSessionFactory().getCurrentSession().createQuery(query).uniqueResult();
            return serviceRequestList;
        } catch (RuntimeException re) {
            throw re;
        }
    }

}
