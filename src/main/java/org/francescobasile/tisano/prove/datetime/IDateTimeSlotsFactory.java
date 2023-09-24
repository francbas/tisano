package org.francescobasile.tisano.prove.datetime;

public interface IDateTimeSlotsFactory {

    /**
     * @return Una istanza di IDateTimeSlots in minuti. Ciascuna factory implementa la propria versione
     */
    IDateTimeSlotsMinute createDateTimeSlotsMinute();

    /**
     * @return Una istanza di IDateTimeSlots in ore. Ciascuna factory implementa la propria versione
     */
    IDateTimeSlotsHour createDateTimeSlotsHour();

    /**
     * @return Una istanza di IDateTimeSlots in giorni. Ciascuna factory implementa la propria versione
     */
    IDateTimeSlotsDay createDateTimeSlotsDay();
}

/**
 * @author francesco.basile
 * Implementazione dell'interfaccia Factory Abstract per creazione di DateTimSlots concreto in formato minuti.</br>
 * Aggiungere testo descrittivo per la versione V1 (ad es: V1 è la versione piu utile per la rappresentazione in template)
 */
class DateTimeSlotsV1Factory implements IDateTimeSlotsFactory {
    /**
     * {@inheritDoc}
     *
     * @return Restituisce una istanza di {@link DateTimeSlotsMinuteV1}
     * @see DateTimeSlotsMinuteV1#render(Integer)
     */
    @Override
    public IDateTimeSlotsMinute createDateTimeSlotsMinute() {
        return new DateTimeSlotsMinuteV1();
    }

    /**
     * {@inheritDoc}
     *
     * @return Restituisce una istanza di {@link DateTimeSlotsHourV1}
     * @see DateTimeSlotsHourV1#render(Integer)
     */
    @Override
    public IDateTimeSlotsHour createDateTimeSlotsHour() {
        return new DateTimeSlotsHourV1();
    }

    @Override
    public IDateTimeSlotsDay createDateTimeSlotsDay() {
        return new DateTimeSlotsDayV1();
    }

}

class DateTimeSlotsV2Factory implements IDateTimeSlotsFactory {
    @Override
    public IDateTimeSlotsMinute createDateTimeSlotsMinute() {
        return new DateTimeSlotsMinuteV2();
    }

    @Override
    public IDateTimeSlotsHour createDateTimeSlotsHour() {
        return new DateTimeSlotsHourV2();
    }

    @Override
    public IDateTimeSlotsDay createDateTimeSlotsDay() {
        return new DateTimeSlotsDayV2();
    }

}
