package au.com.translatorss.dao.impl;

import au.com.translatorss.bean.BusinessUser;
import au.com.translatorss.bean.Translator;
import au.com.translatorss.bean.User;
import au.com.translatorss.dao.BusinessUserDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class BusinessUserImplDao extends GenericDaoImplementation<BusinessUser, Long> implements BusinessUserDao {

	@Override
	public BusinessUser getTranslatorByEmail(String userEmail, String password) {
		Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(BusinessUser.class, "bu");
        criteria.createAlias("bu.user", "user");
        criteria.add(Restrictions.eq("user.email", userEmail));
	    criteria.add(Restrictions.eq("user.password", password));
        return (BusinessUser) criteria.uniqueResult();
	}

    public String getNextSequenceNumber(BusinessUser businessUser) {
        // Retrieve the next sequence number
        String nextSequenceNumber = businessUser.getBusinessUserExtension().getNextSequenceNumber();
        // Increment the sequence number.
        int sequenceNumberIncrement = Integer.parseInt(nextSequenceNumber);
        sequenceNumberIncrement++;
        
        // Apply roll over if needed
        if (applyRollover(sequenceNumberIncrement)) {
            sequenceNumberIncrement = 0;
        }
        
        String incrementedSequenceNumber = String.format("%04d", sequenceNumberIncrement);
        businessUser.getBusinessUserExtension().setNextSequenceNumber(incrementedSequenceNumber);

        //  Save the incremented sequence number.
        this.update(businessUser);

        return nextSequenceNumber;
    }

    public boolean applyRollover(int sequenceNumber) {
        if (sequenceNumber > 9999) {
            return true;
        }
        return false;
    }

	@Override
	public BusinessUser getBusinessUserByUserId(User user) {
		Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(BusinessUser.class, "BusinessUser");
        criteria.add(Restrictions.eq("BusinessUser.user", user));
        return (BusinessUser) criteria.uniqueResult();
	}

}
