package it.redbyte.stompreactiveexample.event;

import it.redbyte.stompreactiveexample.model.entities.Hero;
import lombok.ToString;

@ToString
public class HeroCreatedEvent extends HeroChangeEvent {
    public HeroCreatedEvent(Hero source) {
        super(source);
    }

    @Override
    public ChangeEventType getChangeType() {
        return ChangeEventType.CREATED;
    }
}
