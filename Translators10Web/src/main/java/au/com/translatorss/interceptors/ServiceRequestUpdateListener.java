package au.com.translatorss.interceptors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.event.spi.SaveOrUpdateEvent;
import org.hibernate.event.spi.SaveOrUpdateEventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import au.com.translatorss.bean.Quotation;
import au.com.translatorss.bean.ServiceRequest;

@Component
public class ServiceRequestUpdateListener implements SaveOrUpdateEventListener {

	private static final long serialVersionUID = 1L;
	private HashMap<String, Object[]> emitters = new HashMap<String, Object[]>();

//  TODO: Move the methods below into a separate class after other functionality of SSE usage will appear in application
  public SseEmitter reCreateEmitter(String guid, Long serviceRequestId) {
      emitters.remove(guid);
      // Sets timeout for SSE connection. It looks like this setting is skipped by Spring.
      // So make sure to configure time for async connections in SpringWebConfig.configureAsyncSupport
      SseEmitter emiter = new SseEmitter(100000L);
      emitters.put(guid, new Object[]{serviceRequestId, emiter});
      return emiter;
  }
	
  private List<SseEmitter> getEmittersByServiceRequestId(Long serviceRequestId) {
      List<SseEmitter> result = new ArrayList<SseEmitter>();
      for (Object[] objs : emitters.values()) {
          if (objs[0] == serviceRequestId) {
              result.add((SseEmitter) objs[1]);
          }
      }
      return result;
  }
  
	@Override
	public void onSaveOrUpdate(SaveOrUpdateEvent event) throws HibernateException {
		Object instance = event.getEntity();
        if (instance instanceof ServiceRequest) {
            ServiceRequest serviceRequest = ((ServiceRequest) instance);
            Long serviceRequestId = serviceRequest.getId();
            List<SseEmitter> emitters = getEmittersByServiceRequestId(serviceRequestId);
            if (emitters.size() > 0) {
                for (int i = 0; i < emitters.size(); i++) {
                    SseEmitter emitter = emitters.get(i);
                    try { 
                        emitter.send((SseEmitter.event().data(
                        		"sr_id:"+serviceRequest.getId()+
                        		"|sr_status:"+serviceRequest.getServiceRequestStatus().getDescription()).
                                name("message").comment("This is test event")
                                .id(UUID.randomUUID().toString()).reconnectTime(500)
                        ));
                        // Reconnect time: client reconnects in this time after SSE connection is expired/lost
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                        emitters.remove(i);
                        i--;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } 
        }
	}

}
