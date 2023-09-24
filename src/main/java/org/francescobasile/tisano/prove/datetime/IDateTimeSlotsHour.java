package org.francescobasile.tisano.prove.datetime;

public interface IDateTimeSlotsHour {
    public void render(Integer slice);
}


class DateTimeSlotsHourV1 implements IDateTimeSlotsHour {
    @Override
    public void render(Integer slice) {
        System.out.println(slice + " (V1) ore slots");
    }
}

class DateTimeSlotsHourV2 implements IDateTimeSlotsHour {
    @Override
    public void render(Integer slice) {
        System.out.println(slice + " (V2) ore slots");
    }
}
