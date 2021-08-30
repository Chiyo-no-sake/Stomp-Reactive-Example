package it.redbyte.stompreactiveexample.event;

import it.redbyte.stompreactiveexample.model.entities.Hero;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

@ToString
public abstract class HeroChangeEvent extends ApplicationEvent implements ChangeEvent {
    public HeroChangeEvent(Hero source) {
        super(source);
    }

    @Override
    public Hero getSource() {
        return (Hero)super.getSource();
    }
}
