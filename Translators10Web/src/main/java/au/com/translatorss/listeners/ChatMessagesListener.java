package au.com.translatorss.listeners;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostInsertEventListener;
import org.hibernate.event.spi.PostUpdateEvent;
import org.hibernate.event.spi.PostUpdateEventListener;
import org.hibernate.event.spi.SaveOrUpdateEvent;
import org.hibernate.event.spi.SaveOrUpdateEventListener;
import org.hibernate.persister.entity.EntityPersister;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import au.com.translatorss.bean.ChatMessage;
import au.com.translatorss.bean.Conversation;
import au.com.translatorss.bean.Quotation;
import au.com.translatorss.bean.ServiceResponse;
import au.com.translatorss.bean.ServiceResponseFiles;

@Component
public class ChatMessagesListener implements PostInsertEventListener{

	private static final long serialVersionUID = 1L;
	private HashMap<String, Object[]> emitters = new HashMap<String, Object[]>();
	
    public SseEmitter reCreateEmitter(String guid, Long conversationtId) {
        emitters.remove(guid);
        // Sets timeout for SSE connection. It looks like this setting is skipped by Spring.
        // So make sure to configure time for async connections in SpringWebConfig.configureAsyncSupport
        SseEmitter emiter = new SseEmitter(100000L);
        emitters.put(guid, new Object[]{conversationtId, emiter});
        return emiter;
    }
	
    private List<SseEmitter> getEmittersByConversationId(Long conversationtId) {
        List<SseEmitter> result = new ArrayList<SseEmitter>();
        for (Object[] objs : emitters.values()) {
            if (objs[0] == conversationtId) {
                result.add((SseEmitter) objs[1]);
            }
        }
        return result;
    }
    
    
	@Override
	public void onPostInsert(PostInsertEvent event) {
        handleInsert(event.getEntity(), true);
	}

	@Override
	public boolean requiresPostCommitHanding(EntityPersister persister) {
		return false;
	}

	private void handleInsert(Object instance, boolean b) {
		if (instance instanceof ChatMessage) {
			ChatMessage message = ((ChatMessage) instance);
            Long conversationId = message.getConversation().getId();
            List<SseEmitter> emitters = getEmittersByConversationId(conversationId);
            if (emitters.size() > 0) {
            	
            	
                for (int i = 0; i < emitters.size(); i++) {
                    SseEmitter emitter = emitters.get(i);
                    try {
                        	emitter.send((SseEmitter.event().data("ms_id:" + message.getId() +"|ms_sd:" + message.getSender()+"|ms_ms:" + message.getMessage()+"|ms_dt:" + message.getDate()).name("message").comment("This is test event").id(UUID.randomUUID().toString()).reconnectTime(500)
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
