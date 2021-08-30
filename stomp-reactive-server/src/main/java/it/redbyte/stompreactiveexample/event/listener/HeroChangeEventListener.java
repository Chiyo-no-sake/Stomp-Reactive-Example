package it.redbyte.stompreactiveexample.event.listener;

import it.redbyte.stompreactiveexample.event.HeroChangeEvent;
import it.redbyte.stompreactiveexample.model.dto.HeroChangeEventMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class HeroChangeEventListener implements ApplicationListener<HeroChangeEvent> {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private static final Logger logger = LoggerFactory.getLogger(HeroChangeEventListener.class);

    @Autowired
    public HeroChangeEventListener(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @Override
    public void onApplicationEvent(HeroChangeEvent event) {
        logger.info("Sending event to client: {}", event);
        simpMessagingTemplate.convertAndSend("/topic/heroes/updates", HeroChangeEventMessage.fromEvent(event));
    }
}
