package it.redbyte.stompreactiveexample.event;

import it.redbyte.stompreactiveexample.config.liveWebSocket.ChangeEventType;
import it.redbyte.stompreactiveexample.model.entities.Hero;
import lombok.ToString;

@ToString
public class HeroUpdatedEvent extends HeroChangeEvent {

    public HeroUpdatedEvent(Hero source) {
        super(source);
    }

    @Override
    public ChangeEventType getChangeType() {
        return ChangeEventType.UPDATED;
    }
}
