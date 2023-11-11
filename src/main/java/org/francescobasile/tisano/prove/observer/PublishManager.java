package org.francescobasile.tisano.prove.observer;

import java.util.ArrayList;
import java.util.List;

public class PublishManager {
    private List<Publishable> publishers;

    public PublishManager() {
        this.publishers = new ArrayList<>();
    }

    public void addPublisher(Publishable publishable) {
        this.publishers.add(publishable);
    }

    public void removePublisher(Publishable publishable) {
        this.publishers.remove(publishable);
    }

}
