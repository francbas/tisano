package org.francescobasile.tisano.prove.datetime;

import java.util.Scanner;

public class TestSlots {
    public static void main(String[] args) {
        int choice;

        Scanner in;
        in = new Scanner(System.in);
        System.out.println("Inserire timeslot:");
        choice = in.nextInt();
// passare istanza di IDateTimeSlotsFactory al service per avere generare gli slots specific
//        qui è possibile inserire statement condizionale per decidere quale versione di factory passare al service

        DateTimeSlotsComponents componentsV1 = DateTimeSlotsFactoryService.createDateTimeSlots(new DateTimeSlotsV1Factory());
        DateTimeSlotsComponents componentsV2 = DateTimeSlotsFactoryService.createDateTimeSlots(new DateTimeSlotsV2Factory());

        componentsV1.getTimeSlotsMinute().render(choice);// genera slots di minuti
        componentsV1.getSlotsHour().render(choice);// genera slot in ore
        componentsV1.getTimeSlotsDay().render(choice);// genera slots in giorni
        componentsV2.getTimeSlotsMinute().render(choice);// genera slots di minuti
        componentsV2.getSlotsHour().render(choice);// genera slot in ore
        componentsV2.getTimeSlotsDay().render(choice);// genera slots in giorni
    }
}
