package au.com.translatorss.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import au.com.translatorss.bean.Quotation;
import au.com.translatorss.bean.ServiceRequest;
import au.com.translatorss.bean.ServiceRequestPayment;
import au.com.translatorss.dao.ServiceRequestPaymentDao;

@Repository
public class ServiceRequestPaymentImplDao extends GenericDaoImplementation<ServiceRequestPayment, Long> implements ServiceRequestPaymentDao {

//	@Override
//	public List<ServiceRequestPayment> serviceRequestListByState(String string) {
////        Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(ServiceRequestPayment.class);
////        criteria.add(Restrictions.eq("serviceRequest.description", "Approved"));
////		return criteria.list();
//		List<ServiceRequestPayment> servcieRequestPaymentList=(List<ServiceRequestPayment>)getAll();
//		List<ServiceRequestPayment> returnservcieRequestPaymentList= new ArrayList<ServiceRequestPayment>();
//		
//		for( ServiceRequestPayment servcieRequestPayment: servcieRequestPaymentList){
//			ServiceRequest servReq= servcieRequestPayment.getServiceRequest();
//			System.out.println("serviceRequestid: "+servReq.getId()+ "serviceRequestStaus:"+servReq.getServiceRequestStatus().getDescription() );
//			if(servReq.getServiceRequestStatus().getDescription().equals("Approved")){
//				returnservcieRequestPaymentList.add(servcieRequestPayment);
//			}
//		}
//		return returnservcieRequestPaymentList;
//	}

	@Override
	public List<ServiceRequestPayment> serviceRequestListByState(String string) {
        Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(ServiceRequestPayment.class);
        criteria.createAlias("serviceRequest", "serviceRequest");
		criteria.createAlias("serviceRequest.serviceRequestStatus", "serviceRequestStatus");
        
		criteria.add(Restrictions.eq("serviceRequestStatus.description", string));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}
	
	
}
