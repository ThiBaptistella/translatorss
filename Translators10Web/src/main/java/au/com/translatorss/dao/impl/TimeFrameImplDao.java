package au.com.translatorss.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import au.com.translatorss.bean.TimeFrame;
import au.com.translatorss.dao.TimeFrameDao;

@Repository
@Transactional
public class TimeFrameImplDao extends GenericDaoImplementation<TimeFrame, Integer>implements TimeFrameDao {

    
    @Override
    public TimeFrame findByDescription(String timeFrame) {
        Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(TimeFrame.class);
        criteria.add(Restrictions.eq("description", timeFrame));
        TimeFrame timeFrame2=(TimeFrame)criteria.uniqueResult();
        return timeFrame2;

    }
}
