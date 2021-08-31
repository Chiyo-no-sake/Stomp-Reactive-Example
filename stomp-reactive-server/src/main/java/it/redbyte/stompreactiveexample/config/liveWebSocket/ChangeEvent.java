package it.redbyte.stompreactiveexample.config.liveWebSocket;

import org.springframework.context.ApplicationEvent;

public abstract class ChangeEvent extends ApplicationEvent {
    public ChangeEvent(Object source) {
        super(source);
    }

    public abstract ChangeEventType getChangeType();
}
