import org.francescobasile.tisano.entity.TimeSlot;
import org.francescobasile.tisano.entity.TimeSlot.TimeSlotPeriod;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

//TODO: associare i timeslots alle risorse e calcolare i periodi di indisponibilità
@DisplayName("Test TimeSlots ed intersezione intervalli")
class TestMapEntry {

    int y = 2023, M = 1, d = 15, h = 8, m = 30;
    LocalDateTime ltSeed = LocalDateTime.of(y, M, d, h, m);
    DateTimeFormatter patternFormatter = DateTimeFormatter.ofPattern("dd/MM/yy hh:mm");

    TimeSlot ts1 = new TimeSlot(ltSeed.minusDays(10), ltSeed.plusDays(10));
    TimeSlot ts2 = new TimeSlot(ltSeed.minusDays(15), ltSeed.plusDays(1));
    TimeSlot ts3 = new TimeSlot(ltSeed.plusDays(3), ltSeed.plusDays(5));

    TimeSlotPeriod slotPeriod = new TimeSlotPeriod();
    LocalDateTime[] tsTest = {ts1.getStart(), ts1.getEnd(), ts2.getStart(), ts2.getEnd(), ts3.getStart(), ts3.getEnd()};

    String[] timeSlotsAttesi = {
            "05/01/23 08:00", "25/01/23 08:00",
            "31/12/22 08:00", "16/01/23 08:00",
            "18/01/23 08:00", "20/01/23 08:00"
    };
    String[] partitionsAttese = {
            "31/12/22 08:00", "05/01/23 08:00",
            "05/01/23 08:00", "16/01/23 08:00",
            "16/01/23 08:00", "18/01/23 08:00",
            "18/01/23 08:00", "20/01/23 08:00",
            "20/01/23 08:00", "25/01/23 08:00",
    };
    String[][] partitionIntersezioneAttesa = new String[][]{
            {null, "31/12/22 08:00&05/01/23 08:00", null},
            {"05/01/23 08:00&16/01/23 08:00", "05/01/23 08:00&16/01/23 08:00", null},
            {"16/01/23 08:00&18/01/23 08:00", null, null},
            {"18/01/23 08:00&20/01/23 08:00", null, "18/01/23 08:00&20/01/23 08:00"},
            {"20/01/23 08:00&25/01/23 08:00", null, null},
    };

    @Test
    @DisplayName("Test Inizializzazione Timeslots")
    void testInizializzaTimeSlots() {
        for (int i = 0; i < tsTest.length; i++) {
            assertEquals(timeSlotsAttesi[i], tsTest[i].format(patternFormatter));
        }
    }

    @Test
    @DisplayName("Test delle Partizioni")
    void partizioneTimeSlot() {
        slotPeriod.addTimeSlot(ts1);
        slotPeriod.addTimeSlot(ts2);
        slotPeriod.addTimeSlot(ts3);

        Map<Integer, TimeSlot> partition = slotPeriod.generatePartition();

        for (Map.Entry<Integer, TimeSlot> entry : partition.entrySet()) {
            Integer k = entry.getKey();
            TimeSlot v = entry.getValue();
            assertEquals(partitionsAttese[2 * k], v.getStart().format(patternFormatter));
            assertEquals(partitionsAttese[2 * k + 1], v.getEnd().format(patternFormatter));
        }

    }

    @Test
    @DisplayName("Test Intersezioni delle Partizioni con TimeSlots")
    void intersezioniPartizioniTimeSlots() {
        final Map<Integer, TimeSlot> partition = slotPeriod.generatePartition();
        final List<TimeSlot> periods = slotPeriod.getPeriods();

        for (int i = 0; i < partition.size(); i++) {
            for (int j = 0; j < periods.size(); j++) {
                TimeSlot ts = partition.get(i).intersection(periods.get(j));
                assertEquals(partitionIntersezioneAttesa[i][j],
                        ts.getStart().format(patternFormatter) + "&" + ts.getEnd().format(patternFormatter));
            }
        }
    }
}

class MatriceIntersezioniTimeSlots {
}