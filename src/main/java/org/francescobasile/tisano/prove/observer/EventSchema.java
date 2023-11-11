package org.francescobasile.tisano.prove.observer;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class EventSchema implements Serializable {
    private EventType eventType;
    private LocalDateTime dateTime;
    private Long messageVersion;
    private Payload payload;

    public EventSchema() {
    }

    public EventSchema(EventType eventType) {
        this.eventType = eventType;
    }

    public EventSchema(EventType eventType, Payload payload) {
        this.eventType = eventType;
        this.payload = payload;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Long getMessageVersion() {
        return messageVersion;
    }

    public void setMessageVersion(Long messageVersion) {
        this.messageVersion = messageVersion;
    }

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }

    private String makeFieldName(Object value) {
        //TODO: passare anche l'oggetto contenitore
        String attributo = null;
        try {
            final Field[] declaredFields = this.getClass().getDeclaredFields();
            for (Field field : declaredFields) {
                if (field.get(this) == value) {
                    attributo = field.getName();
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return attributo;
    }

    public static class Payload {
        private Subject soggetto;
        private Set<Attribute> attributeSet;

        public Payload(Subject soggetto) {
            this.soggetto = soggetto;
            this.attributeSet = new HashSet<>();
        }

        public Subject getSoggetto() {
            return soggetto;
        }

        public Set<Attribute> getAttributeSet() {
            return this.attributeSet;
        }

        public void addAttribute(Attribute attribute) {
            this.attributeSet.add(attribute);
        }

        public void removeAttribute(Attribute attribute) {
            this.attributeSet.remove(attribute);
        }

        public static class Attribute {
            protected String name;
            private Object prevValue;
            protected Object actualValue;

            public Attribute(String name, Object prevValue, Object actualValue) {
                this.name = name;
                this.prevValue = prevValue;
                this.actualValue = actualValue;
            }

            public String getName() {
                return name;
            }

            public Object getPrevValue() {
                return prevValue;
            }

            public Object getActualValue() {
                return actualValue;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setPrevValue(Object prevValue) {
                this.prevValue = prevValue;
            }

            public void setActualValue(Object actualValue) {
                this.actualValue = actualValue;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof Attribute attribute)) return false;
                return Objects.equals(getName(), attribute.getName());
            }

            @Override
            public int hashCode() {
                return Objects.hash(getName());
            }
        }

    }

    public static class Subject {
        private final String entityClass;

        public Subject(String entityClass) {
            this.entityClass = entityClass;
        }

        public String getEntityClass() {
            return entityClass;
        }

    }

    public enum EventType {
        OBSERVATION,
        TIMESCHEDULE,
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof EventSchema that)) return false;
//        return getEventType() == that.getEventType() && Objects.equals(getMessageVersion(), that.getMessageVersion());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getEventType(), getMessageVersion());
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EventSchema that)) return false;
        return getEventType() == that.getEventType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEventType());
    }

    public static EventSchema buildSchemaForPublisher(EventType type, Object subject, String attributoNome, Object prevValue, Object actualValue) {
        EventSchema eventSchema = new EventSchema();
        eventSchema.setEventType(type);
        Payload payload1 = new Payload(new Subject(subject.getClass().getSimpleName()));
        payload1.addAttribute(new Payload.Attribute(
                attributoNome,
                prevValue,
                actualValue
        ));
        eventSchema.setPayload(payload1);
        return eventSchema;
    }

    public static EventSchema buildSchemaForSubscriber(EventType type) {
        return new EventSchema((type));
    }
}
