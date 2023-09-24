package org.francescobasile.tisano.prove.composite;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeSlot {
    private final LocalDateTime localDateTime;
    private final int slice;

    public TimeSlot(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
        this.slice = 0;
    }

    public int getSlice() {
        return slice;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    @Override
    public String toString() {
        return "TimeSlot{" +
                "localDateTime=" + localDateTime +
                ", slice=" + slice +
                '}';
    }
}

class TimeSlotCompositeContent implements ICompositeContent<TimeSlot> {

    private final TimeSlot timeSlot;

    public TimeSlotCompositeContent(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    public TimeSlotCompositeContent(LocalDateTime localDateTime) {
        this.timeSlot = new TimeSlot(localDateTime);
    }

    @Override
    public TimeSlot getContent() {
        return this.timeSlot;
    }

    @Override
    public void processContent() {
        System.out.println(this.timeSlot.getLocalDateTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }
}
