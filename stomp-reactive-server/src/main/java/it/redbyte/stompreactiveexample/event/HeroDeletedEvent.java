package it.redbyte.stompreactiveexample.event;

import it.redbyte.springlivestomp.ChangeEventType;
import it.redbyte.stompreactiveexample.model.entities.Hero;
import lombok.ToString;

@ToString
public class HeroDeletedEvent extends HeroChangeEvent{

    public HeroDeletedEvent(Hero source) {
        super(source);
    }

    @Override
    public ChangeEventType getChangeType() {
        return ChangeEventType.DELETED;
    }
}
