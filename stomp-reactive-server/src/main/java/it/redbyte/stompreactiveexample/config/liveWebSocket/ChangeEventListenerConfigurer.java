package it.redbyte.stompreactiveexample.config.liveWebSocket;

import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.HashMap;
import java.util.Map;

public abstract class ChangeEventListenerConfigurer implements ApplicationListener<ChangeEvent> {
    private final SimpMessagingTemplate messagingTemplate;
    private final Map<Class<? extends ChangeEvent>, String> mappings;

    public ChangeEventListenerConfigurer(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
        mappings = new HashMap<>();
        configureChangeEventMappings(mappings);
    }

    @Override
    public void onApplicationEvent(ChangeEvent event) {
        this.mappings.keySet().parallelStream()
                .filter(key -> key.isAssignableFrom(event.getClass()))
                .forEach(key -> {
                    this.messagingTemplate.convertAndSend(this.mappings.get(key), ChangeEventMessage.fromEvent(event));
                });
    }

    public abstract void configureChangeEventMappings(Map<Class<? extends ChangeEvent>, String> changeEventMappings);
}
