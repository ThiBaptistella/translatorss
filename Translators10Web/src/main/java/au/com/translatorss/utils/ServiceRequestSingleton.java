package au.com.translatorss.utils;

import au.com.translatorss.bean.ServiceRequest;

import java.util.*;

public  class ServiceRequestSingleton {

    private static ServiceRequestSingleton instance = null;
    private Map <String,List <ServiceRequest>> map = null;

    private ServiceRequestSingleton() {
        map = new HashMap<>();
        map.put("Albanian", new ArrayList<ServiceRequest>());
    }

    public synchronized static ServiceRequestSingleton getInstance() {
        if(instance == null) {
            instance = new ServiceRequestSingleton();
        }
        return instance;
    }

    public synchronized  void addServiceRequest(ServiceRequest sr){
        List<ServiceRequest> serviceRequestList = map.get(sr.getLanguagefrom());
        if(serviceRequestList.size()<10){
            serviceRequestList.add(sr);
        }else{
            Collections.sort(serviceRequestList,new Comparator<ServiceRequest>(){
                @Override
                public int compare(final ServiceRequest sr1,ServiceRequest sr2) {
                    if(sr1.getCreationDate().before(sr2.getCreationDate())){
                        return -1;
                    }else return 1;
                }
            });

            serviceRequestList.remove(serviceRequestList.size()-1);
            serviceRequestList.add(sr);
            System.out.println(sr.getId());
        }
    }

    public synchronized List<ServiceRequest> getServiceRequestList(String language){
        return this.map.get(language);
    }

}
