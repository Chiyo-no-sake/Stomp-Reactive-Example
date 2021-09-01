package it.redbyte.stompreactiveexample.config;

import it.redbyte.springlivestomp.ChangeEventListenerConfigurer;
import it.redbyte.springlivestomp.ChangeEventMappings;
import it.redbyte.stompreactiveexample.event.HeroChangeEvent;
import it.redbyte.stompreactiveexample.event.PosterChangeEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Configuration
public class ChangeEventListenerConfig extends ChangeEventListenerConfigurer {

    @Autowired
    public ChangeEventListenerConfig(SimpMessagingTemplate messagingTemplate) {
        super(messagingTemplate);
    }

    @Override
    public void configureChangeEventMappings(ChangeEventMappings changeEventMappings) {
        // this maps events of type HeroChangeEvent.class to 2 endpoints:
        // "/topic/heroes/updates"
        // "/topic/heroes/{heroID}/updates"
        // both of the endpoints will be available for subscription by the client
        // ATTENTION! in order to send messages to client, you must enable broker for the path.
        // See WebSocketConfig.
        changeEventMappings
                .mapEvent(HeroChangeEvent.class)
                    .toEndpoint("/topic/heroes/updates")
                    .toEndpoint((heroChangeEvent -> String.format("/topic/heroes/%s/updates", heroChangeEvent.getSource().getId())))
                    .and()
                .mapEvent(PosterChangeEvent.class)
                    .toEndpoint("/topic/poster/updates");
    }
}
