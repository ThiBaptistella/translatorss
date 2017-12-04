package au.com.translatorss.dao.impl;

import au.com.translatorss.bean.ServiceRequest;
import au.com.translatorss.bean.Translator;
import au.com.translatorss.bean.User;
import au.com.translatorss.dao.TranslatorDao;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TranslatorImplDao extends GenericDaoImplementation<Translator, Long> implements TranslatorDao {

	public void remove(long id) {
		Session session = this.getSessionFactory().getCurrentSession();
		Translator p = (Translator) session.load(Translator.class, new Long(id));
		if (null != p) {
			session.delete(p);
		}
	}

	public Translator getTranslatorById(long id) {
		Session session = this.getSessionFactory().getCurrentSession();
		Translator p = (Translator) session.load(Translator.class, new Long(id));
		return p;
	}

	public Translator getTranslatorByEmail(String userEmail, String password) {
		Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(Translator.class, "translator");
        criteria.createAlias("translator.user", "user");
        criteria.add(Restrictions.eq("user.email", userEmail));
	    criteria.add(Restrictions.eq("user.password", password));
	    Translator translator = (Translator) criteria.uniqueResult();
		return translator;
	}

	public Translator getTranslatorByNatiNumber(String naatiNumber) {
		Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(Translator.class);
	    criteria.add(Restrictions.eq("naatiNumber", naatiNumber));
	    Translator translator = (Translator) criteria.uniqueResult();
		return translator;
	}

    public List<Translator> getAll() {
        String query = "from Translator";
        
        @SuppressWarnings("unchecked")
        List<Translator> translatorList = this.getSessionFactory().getCurrentSession().createQuery(query).list();
        return translatorList;
    }

    //TODO:VER EN DETALLE
    public List<Translator> getAvailableTranslatorsByLanguages(ServiceRequest serviceRequest) {
        Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(Translator.class, "translator");
        criteria.createAlias("translator.languageList", "language");
        criteria.add(Restrictions.eq("language.description", serviceRequest.getLanguagefrom().trim()));
        criteria.add(Restrictions.eq("status", "Active"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        //falta terminar
        @SuppressWarnings("unchecked")
        List<Translator> translatorList = criteria.list();
        return  translatorList;
    }

    
    
    
    
    //Improve this query using hibernate anotations or hql
    public List<Translator> getAvailableTranslatorsByLanguages2(ServiceRequest serviceRequest) {
        Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(Translator.class, "translator");
        criteria.createAlias("translator.languageList", "language");
        criteria.add(Restrictions.eq("language.description", serviceRequest.getLanguagefrom().trim()));
        criteria.add(Restrictions.eq("status", "Active"));
 
        //driver license filter
        if("Drivers License".equals(serviceRequest.getServiceRequestCategory().getDescription()) && "Urgent".equals(serviceRequest.getTimeFrame().getDescription())){
             criteria.add(Restrictions.ne("translator.driverLicenseUrgent", 0));
        }
        if("Drivers License".equals(serviceRequest.getServiceRequestCategory().getDescription()) && "Medium".equals(serviceRequest.getTimeFrame().getDescription())){
            criteria.add(Restrictions.ne("translator.driverLicenseMedium", 0));
        }
        if("Drivers License".equals(serviceRequest.getServiceRequestCategory().getDescription()) && "Relaxed".equals(serviceRequest.getTimeFrame().getDescription())){
            criteria.add(Restrictions.ne("translator.driverLicenseRelaxed", 0));
        }

        //bi_dea_mar_cer
        if("Birth, Death or Marriage Certificate".equals(serviceRequest.getServiceRequestCategory().getDescription()) && "Urgent".equals(serviceRequest.getTimeFrame().getDescription())){
             criteria.add(Restrictions.ne("translator.birthDeathCertificateUrgent", 0));
        }
        if("Birth, Death or Marriage Certificate".equals(serviceRequest.getServiceRequestCategory().getDescription()) && "Medium".equals(serviceRequest.getTimeFrame().getDescription())){
            criteria.add(Restrictions.ne("translator.birthDeathCertificateMedium", 0));
        }
        if("Birth, Death or Marriage Certificate".equals(serviceRequest.getServiceRequestCategory().getDescription()) && "Relaxed".equals(serviceRequest.getTimeFrame().getDescription())){
            criteria.add(Restrictions.ne("translator.birthDeathCertificateRelaxed", 0));
        }

        //aca_trans
        if("Academic Records / Transcripts".equals(serviceRequest.getServiceRequestCategory().getDescription()) && "Urgent".equals(serviceRequest.getTimeFrame().getDescription())){
            criteria.add(Restrictions.ne("translator.academicTranscriptUrgent", 0));
        }
        if("Academic Records / Transcripts".equals(serviceRequest.getServiceRequestCategory().getDescription()) && "Medium".equals(serviceRequest.getTimeFrame().getDescription())){
           criteria.add(Restrictions.ne("translator.academicTranscriptMedium", 0));
        }
        if("Academic Records / Transcripts".equals(serviceRequest.getServiceRequestCategory().getDescription()) && "Relaxed".equals(serviceRequest.getTimeFrame().getDescription())){
           criteria.add(Restrictions.ne("translator.academicTranscriptRelaxed", 0));
        }

        //business_doc
        if("Business Documents".equals(serviceRequest.getServiceRequestCategory().getDescription()) && "Urgent".equals(serviceRequest.getTimeFrame().getDescription())){
            criteria.add(Restrictions.ne("translator.businessDocumentUrgent", 0));
        }
        if("Business Documents".equals(serviceRequest.getServiceRequestCategory().getDescription()) && "Medium".equals(serviceRequest.getTimeFrame().getDescription())){
           criteria.add(Restrictions.ne("translator.businessDocumentMedium", 0));
        }
        if("Business Documents".equals(serviceRequest.getServiceRequestCategory().getDescription()) && "Relaxed".equals(serviceRequest.getTimeFrame().getDescription())){
           criteria.add(Restrictions.ne("translator.businessDocumentRelaxed", 0));
        }
        @SuppressWarnings("unchecked")
        List<Translator> translatorList = criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        return  translatorList;
    }

    public List<Translator> getAvailableTranslatorsWithoutQuotesSetUp(ServiceRequest serviceRequest) {
    	Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(Translator.class, "translator");
        criteria.createAlias("translator.languageList", "language");
        criteria.add(Restrictions.eq("language.description", serviceRequest.getLanguagefrom().trim()));
        criteria.add(Restrictions.eq("status", "Active"));        
        
        @SuppressWarnings("unchecked")
        List<Translator> translatorList = criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        return translatorList;
    }
    
    //Improve this query using hibernate anotations or hql
//    public List<Translator> getAvailableTranslatorsWithoutQuotesSetUp(ServiceRequest serviceRequest) {
//        Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(Translator.class, "translator");
//        criteria.createAlias("translator.languageList", "language");
//        criteria.add(Restrictions.eq("language.description", serviceRequest.getLanguagefrom().trim()));
//        criteria.add(Restrictions.eq("status", "Active"));
// 
//        //driver license filter
//        if("Drivers License".equals(serviceRequest.getServiceRequestCategory().getDescription()) && "Urgent".equals(serviceRequest.getTimeFrame().getDescription())){
//             criteria.add(Restrictions.eq("translator.driverLicenseUrgent", 0));
//        }
//        if("Drivers License".equals(serviceRequest.getServiceRequestCategory().getDescription()) && "Medium".equals(serviceRequest.getTimeFrame().getDescription())){
//            criteria.add(Restrictions.eq("translator.driverLicenseMedium", 0));
//        }
//        if("Drivers License".equals(serviceRequest.getServiceRequestCategory().getDescription()) && "Relaxed".equals(serviceRequest.getTimeFrame().getDescription())){
//            criteria.add(Restrictions.eq("translator.driverLicenseRelaxed", 0));
//        }
//
//        //bi_dea_mar_cer
//        if("Birth, Death or Marriage Certificate".equals(serviceRequest.getServiceRequestCategory().getDescription()) && "Urgent".equals(serviceRequest.getTimeFrame().getDescription())){
//             criteria.add(Restrictions.eq("translator.birthDeathCertificateUrgent", 0));
//        }
//        if("Birth, Death or Marriage Certificate".equals(serviceRequest.getServiceRequestCategory().getDescription()) && "Medium".equals(serviceRequest.getTimeFrame().getDescription())){
//            criteria.add(Restrictions.eq("translator.birthDeathCertificateMedium", 0));
//        }
//        if("Birth, Death or Marriage Certificate".equals(serviceRequest.getServiceRequestCategory().getDescription()) && "Relaxed".equals(serviceRequest.getTimeFrame().getDescription())){
//            criteria.add(Restrictions.eq("translator.birthDeathCertificateRelaxed", 0));
//        }
//
//        //aca_trans
//        if("Academic Records / Transcripts".equals(serviceRequest.getServiceRequestCategory().getDescription()) && "Urgent".equals(serviceRequest.getTimeFrame().getDescription())){
//            criteria.add(Restrictions.eq("translator.academicTranscriptUrgent", 0));
//        }
//        if("Academic Records / Transcripts".equals(serviceRequest.getServiceRequestCategory().getDescription()) && "Medium".equals(serviceRequest.getTimeFrame().getDescription())){
//           criteria.add(Restrictions.eq("translator.academicTranscriptMedium", 0));
//        }
//        if("Academic Records / Transcripts".equals(serviceRequest.getServiceRequestCategory().getDescription()) && "Relaxed".equals(serviceRequest.getTimeFrame().getDescription())){
//           criteria.add(Restrictions.eq("translator.academicTranscriptRelaxed", 0));
//        }
//
//        //business_doc
//        if("Business Documents".equals(serviceRequest.getServiceRequestCategory().getDescription()) && "Urgent".equals(serviceRequest.getTimeFrame().getDescription())){
//            criteria.add(Restrictions.eq("translator.businessDocumentUrgent", 0));
//        }
//        if("Business Documents".equals(serviceRequest.getServiceRequestCategory().getDescription()) && "Medium".equals(serviceRequest.getTimeFrame().getDescription())){
//           criteria.add(Restrictions.eq("translator.businessDocumentMedium", 0));
//        }
//        if("Business Documents".equals(serviceRequest.getServiceRequestCategory().getDescription()) && "Relaxed".equals(serviceRequest.getTimeFrame().getDescription())){
//           criteria.add(Restrictions.eq("translator.businessDocumentRelaxed", 0));
//        }
//        @SuppressWarnings("unchecked")
//        List<Translator> translatorList = criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
//        return translatorList;
//    }
    
    @Override
    public List<Translator> getTranslatorsByLanguages(ServiceRequest serviceRequest) {
        Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(Translator.class, "translator");
        criteria.createAlias("translator.languageList", "language");
        criteria.add(Restrictions.eq("language.description", serviceRequest.getLanguagefrom().trim()));
        criteria.add(Restrictions.eq("status", "Active"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        @SuppressWarnings("unchecked")
        List<Translator> translatorList = criteria.list();
        return  translatorList;
    }

    @Override
    public List<Translator> getTranslatorsByStatus(String status) {
        Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(Translator.class, "translator");
        criteria.add(Restrictions.eq("status", "Active"));

        @SuppressWarnings("unchecked")
        List<Translator> translatorList = criteria.list();
        return  translatorList;
    }

	@Override
	public Translator getTranslatorByEmail(String email) {
        Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(Translator.class, "translator");
        criteria.createAlias("translator.user", "user");
        criteria.add(Restrictions.eq("user.email", email));
        return (Translator) criteria.uniqueResult();
	}

	@Override
	public Translator getTranslatorUserById(User user) {
		Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(Translator.class, "translator");
        criteria.add(Restrictions.eq("translator.user", user));
        return (Translator) criteria.uniqueResult();
	}
}
