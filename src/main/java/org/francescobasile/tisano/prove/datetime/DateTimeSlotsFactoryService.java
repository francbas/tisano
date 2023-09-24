package org.francescobasile.tisano.prove.datetime;

public class DateTimeSlotsFactoryService {

    public static DateTimeSlotsComponents createDateTimeSlots(IDateTimeSlotsFactory factory) {
        return new DateTimeSlotsComponents(factory.createDateTimeSlotsMinute(), factory.createDateTimeSlotsHour(), factory.createDateTimeSlotsDay());
    }

}