package it.redbyte.stompreactiveexample.event.listener;

import it.redbyte.stompreactiveexample.config.liveWebSocket.ChangeEventListenerConfigurer;
import it.redbyte.stompreactiveexample.config.liveWebSocket.ChangeEventMappings;
import it.redbyte.stompreactiveexample.event.HeroChangeEvent;
import it.redbyte.stompreactiveexample.event.PosterChangeEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Configuration
public class ChangeEventListener extends ChangeEventListenerConfigurer {

    @Autowired
    public ChangeEventListener(SimpMessagingTemplate messagingTemplate) {
        super(messagingTemplate);
    }

    @Override
    public void configureChangeEventMappings(ChangeEventMappings changeEventMappings) {
        changeEventMappings
                .mapEvent(HeroChangeEvent.class)
                    .toEndpoint("/topic/heroes/updates")
                    .toEndpoint((heroChangeEvent -> String.format("/topic/heroes/%s/updates", heroChangeEvent.getSource().getId())))
                    .and()
                .mapEvent(PosterChangeEvent.class)
                    .toEndpoint("/topic/poster/updates");
    }
}
