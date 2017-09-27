package au.com.translatorss.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import au.com.translatorss.bean.ChatMessage;
import au.com.translatorss.dao.ChatMessageDao;

@Repository
public class ChatMessageDaoImpl extends GenericDaoImplementation<ChatMessage, Long>  implements ChatMessageDao {

	@Override
	public List<ChatMessage> getUnreadMessageById(Long id){
        Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(ChatMessage.class, "mess");
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.add(Restrictions.eq("read", false));
        criteria.add(Restrictions.eq("receiverId",id));
        return  criteria.list();
	}
}
