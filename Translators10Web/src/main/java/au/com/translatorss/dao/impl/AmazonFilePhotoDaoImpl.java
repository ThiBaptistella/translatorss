package au.com.translatorss.dao.impl;

import java.io.Serializable;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import au.com.translatorss.bean.AmazonFilePhoto;
import au.com.translatorss.bean.ChatMessage;
import au.com.translatorss.bean.User;
import au.com.translatorss.dao.AmazonFilePhotoDao;

@Repository
@Transactional
public class AmazonFilePhotoDaoImpl extends GenericDaoImplementation<AmazonFilePhoto, Long> implements AmazonFilePhotoDao{

	@Override
	public AmazonFilePhoto savePhoto(AmazonFilePhoto amazonFilePhoto) {
    	Serializable save = getSessionFactory().getCurrentSession().save(amazonFilePhoto);
        currentSession().flush();
        amazonFilePhoto.setId((Long) save);
        return amazonFilePhoto;
	}

	@Override
	public AmazonFilePhoto getAmazonFilePhotoByUserId(User user) {
			Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(AmazonFilePhoto.class, "amazonFilePhoto");
	        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
	        criteria.add(Restrictions.eq("createdBy", user));
	        return  (AmazonFilePhoto) criteria.uniqueResult();
	}

}
