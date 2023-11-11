package org.francescobasile.tisano.prove.observer;

public interface Triggable {
    void trigger(EventSchema schema);

    boolean isTriggable();

}
