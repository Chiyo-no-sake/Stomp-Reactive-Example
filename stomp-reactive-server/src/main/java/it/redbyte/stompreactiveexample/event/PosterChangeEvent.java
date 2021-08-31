package it.redbyte.stompreactiveexample.event;

import it.redbyte.stompreactiveexample.config.liveWebSocket.ChangeEvent;
import it.redbyte.stompreactiveexample.config.liveWebSocket.ChangeEventType;
import it.redbyte.stompreactiveexample.model.dto.PosterText;

public class PosterChangeEvent extends ChangeEvent {

    public PosterChangeEvent(PosterText source) {
        super(source);
    }

    @Override
    public PosterText getSource() {
        return (PosterText)super.getSource();
    }

    @Override
    public ChangeEventType getChangeType() {
        return ChangeEventType.UPDATED;
    }
}
