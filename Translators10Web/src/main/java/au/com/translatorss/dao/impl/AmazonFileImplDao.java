package au.com.translatorss.dao.impl;

import au.com.translatorss.bean.AmazonFile;
import au.com.translatorss.dao.AmazonFileDao;
import au.com.translatorss.enums.FileType;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

import static org.hibernate.criterion.Restrictions.*;


@Repository
@Transactional
public class AmazonFileImplDao extends GenericDaoImplementation<AmazonFile, Long> implements AmazonFileDao {

    @Override
    public AmazonFile save(AmazonFile amazonFile) {
        Serializable save = getSessionFactory().getCurrentSession().save(amazonFile);
        currentSession().flush();
        amazonFile.setId((Long) save);
        return amazonFile;
    }

    
    @Override
    public List<AmazonFile> findByServiceRequestIdAndType(Long serviceRequestId, FileType fileType) {
        Criteria criteria = currentSession().createCriteria(AmazonFile.class, "af");
        criteria.createAlias("af.serviceRequest", "req");
        criteria.add(eq("req.id", serviceRequestId));
        criteria.add(eq("af.fileType", fileType));
        return criteria.list();
    }

    @Override
    public List<AmazonFile> findByServiceResponseIdAndType(Long serviceResponseId, FileType fileType) {
        Criteria criteria = currentSession().createCriteria(AmazonFile.class, "af");
        criteria.createAlias("af.serviceResponse", "resp");
        criteria.add(eq("resp.id", serviceResponseId));
        criteria.add(eq("af.fileType", fileType));
        return criteria.list();
    }

    @Override
    public List<String> findFileNameBySerReqId(String fileName, Long serReqId) {
        Criteria criteria = currentSession().createCriteria(AmazonFile.class, "af")
                .setProjection(Projections.projectionList()
                        .add(Projections.property("fileName"), "fileName"));
        criteria.createAlias("af.serviceRequest", "req");
        criteria.add(Restrictions.like("af.fileName", fileName, MatchMode.ANYWHERE));
        criteria.add(Restrictions.eq("req.id", serReqId));

        return criteria.list();
    }

    @Override
    public List<String> findFileNameBySerRespId(String fileName, Long serRespId) {
        Criteria criteria = currentSession().createCriteria(AmazonFile.class, "af")
                .setProjection(Projections.projectionList()
                        .add(Projections.property("fileName"), "fileName"));
        criteria.createAlias("af.serviceResponse", "resp");
        criteria.add(Restrictions.like("af.fileName", fileName, MatchMode.ANYWHERE));
        criteria.add(Restrictions.eq("resp.id", serRespId));

        return criteria.list();
    }

	
}
