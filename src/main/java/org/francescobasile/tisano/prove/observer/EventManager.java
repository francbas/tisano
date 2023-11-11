package org.francescobasile.tisano.prove.observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventManager implements IEventManger {
    private final Map<EventSchema.EventType, List<Subscribable>> schemaSubscribers;
    private PublishManager publishManager;

    public EventManager() {
        this.schemaSubscribers = new HashMap<>();
    }

    public EventManager(PublishManager publishManager) {
        this();
        this.publishManager = publishManager;
    }

    @Override
    public void subscribe(Subscribable subscribable, EventSchema.EventType eventType) {
        List<Subscribable> subscribers = this.schemaSubscribers.getOrDefault(eventType, new ArrayList<>());
        subscribers.add(subscribable);
        this.schemaSubscribers.put(eventType, subscribers);
    }

    @Override
    public void unSubscribe(Subscribable subscribable, EventSchema.EventType eventType) {
        List<Subscribable> subscribers = this.schemaSubscribers.getOrDefault(eventType, new ArrayList<>());
        if (subscribers.isEmpty()) {
            this.schemaSubscribers.remove(eventType);
            return;
        }
        subscribers.remove(subscribable);
        this.schemaSubscribers.put(eventType, subscribers);
    }

    @Override
    public void publish(EventSchema schema) {
        List<Subscribable> subscribers = this.schemaSubscribers.getOrDefault(schema.getEventType(), new ArrayList<>());
        if (subscribers.isEmpty()) return;
        for (Subscribable subscriber : subscribers) {
            subscriber.onEvent(schema);
        }
    }

    @Override
    public void register(Publishable publisher) {
        this.publishManager.addPublisher(publisher);
    }

    @Override
    public void unregister(Publishable publisher) {
        this.publishManager.removePublisher(publisher);
    }
}
