package org.francescobasile.tisano.prove.datetime;

public class DateTimeSlotsComponents {
    IDateTimeSlotsMinute timeSlotsMinute;
    IDateTimeSlotsHour slotsHour;
    IDateTimeSlotsDay timeSlotsDay;

    public DateTimeSlotsComponents(IDateTimeSlotsMinute timeSlotsMinute, IDateTimeSlotsHour slotsHour, IDateTimeSlotsDay timeSlotsDay) {
        this.timeSlotsMinute = timeSlotsMinute;
        this.slotsHour = slotsHour;
        this.timeSlotsDay = timeSlotsDay;
    }

    public IDateTimeSlotsMinute getTimeSlotsMinute() {
        return timeSlotsMinute;
    }

    public IDateTimeSlotsHour getSlotsHour() {
        return slotsHour;
    }

    public IDateTimeSlotsDay getTimeSlotsDay() {
        return timeSlotsDay;
    }
}
