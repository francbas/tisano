package org.francescobasile.tisano.prove.observer;

public class Publisher implements Publishable {

    private EventManager eventManager;
    private final Boolean publishable;

    public Publisher(EventManager eventManager) {
        this.eventManager = eventManager;
        this.publishable = true;
    }

    public EventManager getEventManager() {
        return eventManager;
    }

    @Override
    public void publish(EventSchema schema) {
        eventManager.publish(schema);
    }

    @Override
    public void setEventManager(EventManager eventManager) {
        this.eventManager = eventManager;
    }

    @Override
    public boolean isPublishable() {
        return this.publishable;
    }
}
