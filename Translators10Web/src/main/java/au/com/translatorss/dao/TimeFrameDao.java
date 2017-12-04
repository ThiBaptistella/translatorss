package au.com.translatorss.dao;

import au.com.translatorss.bean.TimeFrame;

public interface TimeFrameDao extends GenericDao<TimeFrame, Integer> {

   public TimeFrame findByDescription(String timeFrame);

}
