package org.francescobasile.tisano.prove.datetime;

public interface IDateTimeSlotsDay {
    public void render(Integer slice);
}

class DateTimeSlotsDayV1 implements IDateTimeSlotsDay {
    @Override
    public void render(Integer slice) {
        System.out.println(slice + " (V1) giorni slots");
    }
}

class DateTimeSlotsDayV2 implements IDateTimeSlotsDay {
    @Override
    public void render(Integer slice) {
        System.out.println(slice + " (V2) giorni slots");
    }
}
