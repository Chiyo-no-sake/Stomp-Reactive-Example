package it.redbyte.stompreactiveexample.event;

import it.redbyte.stompreactiveexample.config.liveWebSocket.ChangeEvent;
import it.redbyte.stompreactiveexample.model.entities.Hero;
import lombok.ToString;

@ToString
public abstract class HeroChangeEvent extends ChangeEvent {
    public HeroChangeEvent(Hero source) {
        super(source);
    }

    @Override
    public Hero getSource() {
        return (Hero)super.getSource();
    }
}
