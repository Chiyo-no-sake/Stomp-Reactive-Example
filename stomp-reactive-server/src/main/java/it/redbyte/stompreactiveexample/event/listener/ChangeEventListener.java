package it.redbyte.stompreactiveexample.event.listener;

import it.redbyte.stompreactiveexample.event.HeroChangeEvent;
import it.redbyte.stompreactiveexample.config.liveWebSocket.ChangeEvent;
import it.redbyte.stompreactiveexample.config.liveWebSocket.ChangeEventListenerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.Map;

@Configuration
public class ChangeEventListener extends ChangeEventListenerConfigurer {

    @Autowired
    public ChangeEventListener(SimpMessagingTemplate messagingTemplate) {
        super(messagingTemplate);
    }

    @Override
    public void configureChangeEventMappings(Map<Class<? extends ChangeEvent>, String> changeEventMappings) {
        changeEventMappings.put(HeroChangeEvent.class, "/topic/heroes/updates");
    }
}
