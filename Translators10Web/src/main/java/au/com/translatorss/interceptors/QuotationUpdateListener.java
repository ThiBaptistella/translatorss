package au.com.translatorss.interceptors;

import au.com.translatorss.bean.Quotation;
import au.com.translatorss.bean.Rate;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostInsertEventListener;
import org.hibernate.event.spi.PostUpdateEvent;
import org.hibernate.event.spi.PostUpdateEventListener;
import org.hibernate.persister.entity.EntityPersister;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.*;

@Component
public class QuotationUpdateListener implements PostUpdateEventListener, PostInsertEventListener {


    private static final long serialVersionUID = 1L;
    private HashMap<String, Object[]> emitters = new HashMap<String, Object[]>();

    //    TODO: Move the methods below into a separate class after other functionality of SSE usage will appear in application
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

    private HashMap<String, Integer> populateMediaRating(Quotation quotation) {
        HashMap<String, Integer> rating = new HashMap<String, Integer>();
        Set<Rate> rateList = quotation.getTranslator().getRates();

        int rateSize = rateList.size();
        if (rateSize == 0) {
            rateSize = 1;
        }
        int quality = 0;
        int serviceDescribed = 0;
        int time = 0;
        for (Rate rate : rateList) {
            quality += rate.getQuality();
            serviceDescribed += rate.getServiceAsDescribed();
            time += rate.getTimeDelivery();
        }
        rating.put("wouldRecomend", Math.round(quality / rateSize));
        rating.put("serviceDescribed", Math.round(serviceDescribed / rateSize));
        rating.put("comunication", Math.round(time / rateSize));

        return rating;
    }






    @Override
    public void onPostInsert(PostInsertEvent event) {
        handleInsertOrUpdate(event.getEntity(), true);
    }

    @Override
    public void onPostUpdate(PostUpdateEvent event) {
        handleInsertOrUpdate(event.getEntity(), false);
    }

    @Override
    public boolean requiresPostCommitHanding(EntityPersister entityPersister) {
        return false;
    }

    private void handleInsertOrUpdate(Object instance, boolean isInsert) {
        if (instance instanceof Quotation) {
            Quotation quotation = ((Quotation) instance);
            HashMap<String, Integer> rating = populateMediaRating(quotation);
            Long serviceRequestId = quotation.getServiceRequest().getId();
            List<SseEmitter> emitters = getEmittersByServiceRequestId(serviceRequestId);
            if (emitters.size() > 0) {
                for (int i = 0; i < emitters.size(); i++) {
                    SseEmitter emitter = emitters.get(i);
                    try {
                        emitter.send((SseEmitter.event().data(
                                "q_name:" + quotation.getTranslator().getUser().getName() +
                                        "|q_wr:" + rating.get("wouldRecomend") +
                                        "|q_sd:" + rating.get("serviceDescribed") +
                                        "|q_com:" + rating.get("comunication") +
                                        "|q_id:" + quotation.getId() +
                                        "|q_val:" + quotation.getValue() +
                                        "|q_valid:" + quotation.getIsValid() +
                                        "|q_is_insert:" + isInsert).
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
