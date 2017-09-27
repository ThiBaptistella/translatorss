package au.com.translatorss.configuration;

import au.com.translatorss.interceptors.QuotationUpdateListener;
import au.com.translatorss.interceptors.ServiceRequestUpdateListener;
import au.com.translatorss.listeners.ChatMessagesListener;

import org.hibernate.SessionFactory;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class HibernateEventWiring {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private QuotationUpdateListener quotationListener;

    @Autowired
    private ServiceRequestUpdateListener serviceRequestUpdateListener;
    
    @Autowired
    private ChatMessagesListener chatMessageListener;
    
    @PostConstruct
    public void registerListeners() {
        EventListenerRegistry registry = ((SessionFactoryImpl) sessionFactory).getServiceRegistry().getService(EventListenerRegistry.class);
        registry.getEventListenerGroup(EventType.SAVE_UPDATE).appendListeners(serviceRequestUpdateListener);
        registry.getEventListenerGroup(EventType.POST_UPDATE).appendListener(quotationListener);
        registry.getEventListenerGroup(EventType.POST_INSERT).appendListener(quotationListener);
        registry.getEventListenerGroup(EventType.POST_INSERT).appendListener(chatMessageListener);
    }
}