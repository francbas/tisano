package org.francescobasile.tisano.prove.composite;

import org.francescobasile.tisano.entity.Medico;
import org.francescobasile.tisano.entity.Specializzazione;
import org.francescobasile.tisano.entity.TimeSlot;
import org.francescobasile.tisano.prove.LocalEntityRepository;

import java.time.LocalDateTime;


public class ZTest {
    public static void main(String[] args) {

        // generazione delle entities
        Specializzazione specializzazione = new Specializzazione("Cardiologia");

        Medico m1 = new Medico("Dott. Otorino Guido");
        Medico m2 = new Medico("Dott. Orecchioni Carlo");

        TimeSlot ts1 = new TimeSlot(LocalDateTime.now(), LocalDateTime.now().minusDays(0));
        TimeSlot ts2 = new TimeSlot(LocalDateTime.now(), LocalDateTime.now().minusDays(1));
        TimeSlot ts3 = new TimeSlot(LocalDateTime.now(), LocalDateTime.now().minusDays(10));
        TimeSlot ts4 = new TimeSlot(LocalDateTime.now(), LocalDateTime.now().minusDays(100));
        TimeSlot ts5 = new TimeSlot(LocalDateTime.now(), LocalDateTime.now().minusDays(1000));

        // generazione dei nodi contenitore
        Composite specializzazioneNode1 = new Composite(new SpecializzazioneContainer(specializzazione), "compositeSpecializzazione1");
////
        Composite medicoNode1 = new Composite(new MedicoContainer(m1), "compositeMedicoNode1");
        Composite medicoNode2 = new Composite(new MedicoContainer(m2), "compositeMedicoNode2");
        Composite timeSlotNode1 = new Composite(new TimeSlotContainer(ts1), "compositeTimeSlotNode1");
        Composite timeSlotNode2 = new Composite(new TimeSlotContainer(ts2), "compositeTimeSlotNode2");
        Composite timeSlotNode3 = new Composite(new TimeSlotContainer(ts3), "compositeTimeSlotNode3");
        Composite timeSlotNode4 = new Composite(new TimeSlotContainer(ts4), "compositeTimeSlotNode4");
        Composite timeSlotNode5 = new Composite(new TimeSlotContainer(ts5), "compositeTimeSlotNode5");
////        // creazione dell'albero nodi/foglia
        specializzazioneNode1.attachChild(medicoNode1);
        specializzazioneNode1.attachChild(medicoNode2);
        medicoNode1.attachChild(timeSlotNode1);
        medicoNode1.attachChild(timeSlotNode2);
        medicoNode2.attachChild(timeSlotNode3);
        medicoNode2.attachChild(timeSlotNode4);
        medicoNode2.attachChild(timeSlotNode5);
        LocalEntityRepository.save(specializzazioneNode1);
//        LocalEntityRepository.close();

//        specializzazioneNode1.traverse();

        Composite specializzazioneNodoTest = LocalEntityRepository.find(Composite.class, 1);

        specializzazioneNodoTest.traverse();
        System.out.println(medicoNode1.isLeaf());
////        medicoNode1.traverse();
//        LocalEntityRepository.save(m1);
//        LocalEntityRepository.save(m1);
//        LocalEntityRepository.save(m2);
    }
}

