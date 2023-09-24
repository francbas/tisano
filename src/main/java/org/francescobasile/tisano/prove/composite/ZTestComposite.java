package org.francescobasile.tisano.prove.composite;

import java.time.LocalDateTime;

public class ZTestComposite {
    public static void main(String[] args) {

        Specializzazione specializzazione = new Specializzazione("Otorinolaringoiatria");
        Medico medico1 = new Medico("Dott. Otorino Guido");
        Medico medico2 = new Medico("Dott. Orecchioni Carlo");
        TimeSlot timeSlot1 = new TimeSlot(LocalDateTime.now());
        TimeSlot timeSlot2 = new TimeSlot(LocalDateTime.of(2023, 9, 27, 8, 0, 0));
        TimeSlot timeSlot3 = new TimeSlot(LocalDateTime.of(2023, 10, 2, 10, 30, 0));
        TimeSlot timeSlot4 = new TimeSlot(LocalDateTime.of(2023, 11, 16, 14, 55, 0));
        TimeSlot timeSlot5 = new TimeSlot(LocalDateTime.of(2023, 12, 9, 18, 0, 0));

        ResourceComposite<Specializzazione> specializzazioneResourceComposite = new ResourceComposite<>(new SpecializzazioneCompositeContent(specializzazione));
        ResourceComposite<Medico> medicoResourceComposite1 = new ResourceComposite<>(new MedicoCompositeContent(medico1));
        ResourceComposite<Medico> medicoResourceComposite2 = new ResourceComposite<>(new MedicoCompositeContent(medico2));
        ResourceComposite<TimeSlot> timeSlotResourceComposite1 = new ResourceComposite<>(new TimeSlotCompositeContent(timeSlot1));
        ResourceComposite<TimeSlot> timeSlotResourceComposite2 = new ResourceComposite<>(new TimeSlotCompositeContent(timeSlot2));
        ResourceComposite<TimeSlot> timeSlotResourceComposite3 = new ResourceComposite<>(new TimeSlotCompositeContent(timeSlot3));
        ResourceComposite<TimeSlot> timeSlotResourceComposite4 = new ResourceComposite<>(new TimeSlotCompositeContent(timeSlot4));
        ResourceComposite<TimeSlot> timeSlotResourceComposite5 = new ResourceComposite<>(new TimeSlotCompositeContent(timeSlot5));

        medicoResourceComposite1.attachChild(timeSlotResourceComposite1);
        medicoResourceComposite1.attachChild(timeSlotResourceComposite2);
        medicoResourceComposite2.attachChild(timeSlotResourceComposite3);
        medicoResourceComposite2.attachChild(timeSlotResourceComposite4);
        medicoResourceComposite2.attachChild(timeSlotResourceComposite5);

        specializzazioneResourceComposite.attachChild(medicoResourceComposite1);
        specializzazioneResourceComposite.attachChild(medicoResourceComposite2);

        medicoResourceComposite1.traverse();
    }
}
