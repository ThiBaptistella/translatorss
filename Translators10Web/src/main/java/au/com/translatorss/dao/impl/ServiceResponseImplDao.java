package au.com.translatorss.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import au.com.translatorss.bean.Conversation;
import au.com.translatorss.bean.Logintry;
import au.com.translatorss.bean.ServiceResponse;
import au.com.translatorss.dao.ServiceResponseDao;

@Repository
public class ServiceResponseImplDao extends GenericDaoImplementation<ServiceResponse, Long>
		implements ServiceResponseDao {

    @Override
    public ServiceResponse getServiceResponseByConversationId(Long id) {
    	Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(Conversation.class);
		criteria.add(Restrictions.eq("serviceResponse.id", id));
		return (ServiceResponse) criteria.uniqueResult();
    }
}
