package org.francescobasile.tisano.prove.observer;

public interface Publishable {
    void publish(EventSchema eventSchema);

    void setEventManager(EventManager eventManager);

    boolean isPublishable();
}
