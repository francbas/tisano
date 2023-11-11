package org.francescobasile.tisano.prove.observer;

import java.util.List;
import java.util.stream.Collectors;

public class SchemaQueryService {

    public static List<EventSchema> findByType(List<EventSchema> schemas, EventSchema.EventType type) {
        return schemas.stream().filter(
                s -> s.getEventType()
                        .name()
                        .contains(type.name())
        ).collect(Collectors.toList());
    }

    public static Boolean containsSubjectClass(EventSchema schema, String className) {
        return schema.getPayload().getSoggetto().getEntityClass().contains(className);
    }

    public static Boolean containsAttributeName(EventSchema schema, String attributeName) {
        boolean present = false;
        return schema.getPayload().getAttributeSet()
                .contains(new EventSchema.Payload.Attribute(attributeName, null, null));
    }

    public static EventSchema.Payload.Attribute getAttributeByName(EventSchema schema, String name) {
        EventSchema.Payload.Attribute attribute;
        attribute = schema.getPayload().getAttributeSet().stream().filter(
                a -> a.getName().equals(name)
        ).findFirst().orElseThrow();
        return attribute;
    }

}
