package org.francescobasile.tisano.prove.observer;

import java.util.ArrayList;
import java.util.List;


public class Subscriber implements Subscribable {

    private final List<EventSchema> schemas;

    public Subscriber() {
        this.schemas = new ArrayList<>();
    }

    public List<EventSchema> getSchemas() {
        return schemas;
    }

    @Override
    public void onEvent(EventSchema schema) {
//        this.schemas.add(schema);
    }
}
