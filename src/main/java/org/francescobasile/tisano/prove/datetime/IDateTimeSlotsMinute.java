package org.francescobasile.tisano.prove.datetime;

public interface IDateTimeSlotsMinute {
    /**
     * @param slice
     * Restituisce un'istanza di DateTimeSlots nel formato minuti
     */
    void render(Integer slice);

    //TODO: aggiungere altri metodi
}

class DateTimeSlotsMinuteV1 implements IDateTimeSlotsMinute {
    /**
     * @param slice
     * Genera un'istanza della versione 1 ottmizzata per Web
     */
    @Override
    public void render(Integer slice) {
        System.out.println(slice + " (V1) minuti slots");
    }
}

class DateTimeSlotsMinuteV2 implements IDateTimeSlotsMinute {
    @Override
    public void render(Integer slice) {
        System.out.println(slice + " (V2) minuti slots");
    }
}


