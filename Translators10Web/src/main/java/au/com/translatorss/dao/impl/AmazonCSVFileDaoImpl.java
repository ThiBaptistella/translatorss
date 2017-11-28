package au.com.translatorss.dao.impl;

import java.io.Serializable;
import java.math.BigInteger;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import au.com.translatorss.bean.AmazonFileCSV;
import au.com.translatorss.bean.AmazonFilePhoto;
import au.com.translatorss.bean.User;
import au.com.translatorss.dao.AmazonCSVFileDao;

@Repository
@Transactional
public class AmazonCSVFileDaoImpl extends GenericDaoImplementation<AmazonFileCSV, Long> implements AmazonCSVFileDao{

	@Override
	public AmazonFileCSV saveCSV(AmazonFileCSV amazonFileCSV) {
		Serializable save = getSessionFactory().getCurrentSession().save(amazonFileCSV);
        currentSession().flush();
        amazonFileCSV.setId((Long) save);
        return amazonFileCSV;
	}

	@Override
	public AmazonFileCSV getAmazonFileCSVByUserId(User user) {
		Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(AmazonFileCSV.class, "amazonFileCSV");
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.add(Restrictions.eq("createdBy", user));
        return  (AmazonFileCSV) criteria.uniqueResult();
	}

	@Override
	public BigInteger getPaymentNextSecuence() {
		SQLQuery query = this.getSessionFactory().getCurrentSession().createSQLQuery("select nextval('payment_file_secuence')");
		BigInteger nextValue = ((BigInteger)query.uniqueResult());
		return nextValue;
	}

	@Override
	public BigInteger getRefundNextSecuence() {
		SQLQuery query = this.getSessionFactory().getCurrentSession().createSQLQuery("select nextval('refund_file_secuence')");
		BigInteger nextValue = ((BigInteger)query.uniqueResult());
		return nextValue;
	}
}
