package it.redbyte.stompreactiveexample.event;

import it.redbyte.springlivestomp.ChangeEvent;
import it.redbyte.springlivestomp.ChangeEventType;
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
