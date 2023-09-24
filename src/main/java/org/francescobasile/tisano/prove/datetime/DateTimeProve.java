package org.francescobasile.tisano.prove.datetime;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

public class DateTimeProve {
    public static void main(String[] args) {
//
// https://docs.oracle.com/javase/tutorial/datetime/iso/overview.html
//
//        classi "temporal-based"
//        java.time.Instant         -- timestamp in secondi da "epoch" con risoluzione in ns (per confronto con vari istanti di tempo)
//        java.time.LocalDateTime   -- data e ora completa
        //        java.time.LocalDate   -- birthday, giorno di appuntamento (solo data), data del matrimonio, etc.
        //        java.time.LocalTime   -- orario di appuntamento (solo orario)
        //        java.time.YearMonth   -- il mese di uno specifico anno
        //        java.time.MonthDay    -- il giorno di uno specifico mese
//        java.time.ZonedDateTime   -- data e ora internazionale completa, incluso offset di fuso orario e Zona
//        java.time.Duration        -- contiene durata di tempo in secondi (con cast a minuti, ore e giorni)
//        java.time.Period          -- contiene durata di tempo in giorni/mesi/anni
//utile-->java.time.TemporalAdjusters -- modificatore (personalizzabile) di un valore temporale che ritorna un valore aggiustato (fine mese, primo giorno del mese, etc.)
//        java.util.Locale          -- classe di utilità per recuperare le impostazioni locali del server
//utile-->java.time.temporal.TemporalQueries  -- https://docs.oracle.com/javase/tutorial/datetime/iso/queries.html (custom queries)
        LocalDateTime data1 = LocalDateTime.now();

        System.out.println("DAY OF WEEK------------------------------------------------");
        DayOfWeek dow = DayOfWeek.MONDAY;
        Locale locale = Locale.getDefault();
        System.out.println(dow.getDisplayName(TextStyle.FULL, locale));
        System.out.println(dow.getDisplayName(TextStyle.NARROW, locale));
        System.out.println(dow.getDisplayName(TextStyle.SHORT, locale));

        Month mese = Month.SEPTEMBER;
        System.out.println(mese.getDisplayName(TextStyle.FULL, locale));
        System.out.println(mese.getDisplayName(TextStyle.NARROW, locale));
        System.out.println(mese.getDisplayName(TextStyle.SHORT, locale));

        DayOfWeek dow2 = data1.getDayOfWeek();
        System.out.println(dow2.getDisplayName(TextStyle.FULL, locale));

        System.out.println("TEMPORAL ADJUSTER------------------------------------------------");
//      maggior parte delle classi in java.time sono immutable
//      l'equivalente immutable del metodo 'set' è 'with'
//      metodi 'from' consentono conversione tra classi
        data1 = LocalDateTime.now();
        data1 = LocalDateTime.of(2023, Month.JUNE, 1, 20, 15);
        DateTimeFormatter timeFormatter;
        timeFormatter = DateTimeFormatter.ofPattern("dd/MM/yy hh:mm:ss");

        System.out.println(data1.with(TemporalAdjusters.lastDayOfMonth()).minusDays(2));
        System.out.println(data1.format(timeFormatter));

        System.out.println("DATETIME FORMATTER------------------------------------------------");
        timeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL).withZone(ZoneId.of("Europe/Paris"));
        System.out.println(data1.format(timeFormatter));
        timeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG).withZone(ZoneId.of("Europe/Paris"));
        System.out.println(data1.format(timeFormatter));
        timeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withZone(ZoneId.of("Europe/Paris"));
        System.out.println(data1.format(timeFormatter));
        timeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).withZone(ZoneId.of("Europe/Paris"));
        System.out.println(data1.format(timeFormatter));

        System.out.println("YEARMONTH------------------------------------------------");
        YearMonth date = YearMonth.now();
        System.out.printf("%s: %n", date);
        System.out.printf("%s: %d%n", date, date.lengthOfMonth());

        YearMonth date2 = YearMonth.of(2010, Month.FEBRUARY);
        System.out.printf("%s: %n", date2);
        System.out.printf("%s: %d%n", date2, date2.lengthOfMonth());

        YearMonth date3 = YearMonth.of(2012, Month.FEBRUARY);
        System.out.printf("%s: %n", date3);
        System.out.printf("%s: %d%n", date3, date3.lengthOfMonth());

        System.out.println("MONTHDAY------------------------------------------------");
        MonthDay date4 = MonthDay.of(Month.FEBRUARY, 29);
        boolean validLeapYear = date4.isValidYear(2010);
        System.out.printf("%s: %n", date4);

        System.out.println("YEAR------------------------------------------------");
        Year year1;
        year1 = Year.of(LocalDate.now().getYear());
        year1 = Year.from(LocalDate.now().with(TemporalAdjusters.firstDayOfYear()).minusMonths(6));
        validLeapYear = Year.of(2012).isLeap();
        System.out.println(year1);

        System.out.println("TIMEZONE------------------------------------------------");
        // time zone è una regione geografica in formato region/city (Asia/Tokio) e un offset rispetto a Greenwich/UTC (es: Tokio è +09:00)
        // Le API sono
        // java.time.ZoneId     -- specifica una identificatore di zona e fornisce regole di conversione tra Instant e java.time.LocalDate
        // java.time.ZoneOffset specifica l'offset di una Zone rispetto a Greenwich
        // Ottieni tutte le zone
        Set<String> allZones = ZoneId.getAvailableZoneIds();
        List<String> zoneList = new ArrayList<>(allZones);
        Collections.sort(zoneList);

        LocalDateTime dt = LocalDateTime.now();
        zoneList.forEach(z -> {
            ZoneId zone = ZoneId.of(z);
            ZonedDateTime zdt = dt.atZone(zone);
            ZoneOffset offset = zdt.getOffset();
            System.out.printf("%35s %10s%n", zone, offset);
        });

        System.out.println("INSTANT------------------------------------------------");
        // Instant definisce il timestamp in formato macchina in nanosecondi da "epoch"
        // Metodi manipolazione: plus, minus, isAfter, isBefore. Until ritorna il tempo tra due instants objects.
        // Il metodo ofInstant invocato su una istanza di classe LocalDateTime o ZoneDatetime converte un instant nel rispettivo oggetto.
        Instant adesso = Instant.now();
        Instant traUnOra = adesso.minus(3650L * 2, ChronoUnit.DAYS); // uso di plus
        System.out.println(adesso + " tra un'ra: " + traUnOra);

        LocalDateTime ldt2 = LocalDateTime.ofInstant(traUnOra, ZoneId.systemDefault());
        System.out.printf("%02d/%02d/%d, %02d:%02d%n", ldt2.getDayOfMonth(), ldt2.getMonth().getValue(), ldt2.getYear(), ldt2.getHour(), ldt2.getMinute());

        System.out.println("PARSING------------------------------------------------");
        // Classi Temporal-based forniscono metodi per il parsing di stringa che contiene info datetime e metodi per il formatting

    }
}
