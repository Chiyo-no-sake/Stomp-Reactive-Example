package it.redbyte.stompreactiveexample.model.dto;

import it.redbyte.stompreactiveexample.event.ChangeEventType;
import it.redbyte.stompreactiveexample.event.HeroChangeEvent;
import it.redbyte.stompreactiveexample.model.entities.Hero;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HeroChangeEventMessage {
    private Hero subject;
    private ChangeEventType changeType;

    public static HeroChangeEventMessage fromEvent(HeroChangeEvent event){
        return HeroChangeEventMessage.builder()
                .subject(event.getSource())
                .changeType(event.getChangeType())
                .build();
    }
}
