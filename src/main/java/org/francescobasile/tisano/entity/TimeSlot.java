package org.francescobasile.tisano.entity;

import jakarta.persistence.Entity;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Entity
public class TimeSlot extends AbstractEntity implements Comparable<TimeSlot> {

    private LocalDateTime start;
    private LocalDateTime end;

    public TimeSlot() {
        this.start = null;
        this.end = null;
    }

    public TimeSlot(LocalDateTime t1, LocalDateTime t2) {
        t1 = this.roundToHour(t1);
        t2 = this.roundToHour(t2);
        if (t1.equals(t2)) {
            this.start = null;
            this.end = null;
            return;
        }
        if (t1.isBefore(t2)) {
            this.start = t1;
            this.end = t2;
        } else {
            this.start = t2;
            this.end = t1;
        }
    }

    public TimeSlot(LocalDateTime start, LocalDateTime end, Long id) {
        this(start, end);
        this.setId(id);
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime tStart) {
        this.start = tStart;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime tEnd) {
        this.end = tEnd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TimeSlot timeSlot)) return false;
        return Objects.equals(start, timeSlot.start) && Objects.equals(end, timeSlot.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }

    @Override
    public int compareTo(TimeSlot ts2) {
        return 0;
    }

    public TimeSlot intersection(TimeSlot ts2) {
        LocalDateTime t1;
        LocalDateTime t2;

        if (this instanceof EmptySet) return new EmptySet();
        if (Objects.isNull(this.start) || Objects.isNull(this.end)) return new EmptySet();
        if (ts2 instanceof EmptySet) return new EmptySet();
        if (Objects.isNull(ts2.getStart()) || Objects.isNull(ts2.getEnd())) return new EmptySet();
        if (this.start.isAfter(ts2.getEnd()) || ts2.getStart().isAfter(this.end)) return new EmptySet();
        if (this.equals(ts2)) return this;

        t1 = this.start.isAfter(ts2.start) ? this.start : ts2.start;
        t2 = this.end.isAfter(ts2.end) ? ts2.start : this.end;
        return new TimeSlot(t1, t2);
    }

    public LocalDateTime roundToHour(LocalDateTime dt) {
        return dt.truncatedTo(ChronoUnit.HOURS);
    }


    public final class EmptySet extends TimeSlot {
        public EmptySet() {
            super();
        }
    }

    public static class TimeSlotPeriod extends TimeSlot {
        private final List<TimeSlot> periods;
        private final Map<Integer, TimeSlot> timeslotPartition;

        public TimeSlotPeriod() {
            super();
            this.periods = new ArrayList<>();
            timeslotPartition = new HashMap<>();
        }

        public void addTimeSlot(TimeSlot timeSlot) {
            periods.add(timeSlot);
        }

        public void removeTimeslot(TimeSlot timeSlot) {
            periods.remove(timeSlot);
        }

        public List<TimeSlot> getPeriods() {
            return periods;
        }

        public Map<Integer, TimeSlot> generatePartition() {
            if (periods.isEmpty()) return new HashMap<>();
            Set<LocalDateTime> spaceSlots = new HashSet<>();
            periods.forEach(ts -> {
                spaceSlots.add(ts.getStart());
                spaceSlots.add(ts.getEnd());
            });
            LocalDateTime[] spaceSlots2 = spaceSlots.stream().sorted().collect(Collectors.toCollection(LinkedHashSet::new)).toArray(new LocalDateTime[]{});
//        Map<Integer, TimeSlot> timeslotPartition = new HashMap<>();
            int limit = spaceSlots2.length - 1;
            for (int i = 0; i < limit; i++) {
                this.timeslotPartition.put(i, new TimeSlot(spaceSlots2[i], spaceSlots2[i + 1]));
            }
            return timeslotPartition;
        }

    }

    public class TimeSlotPeriodStats extends TimeSlotPeriod {
        private final Map<TimeSlot, Integer> moda;

        public TimeSlotPeriodStats(List<TimeSlot> timeSlots) {
            this.moda = new HashMap<>();
            timeSlots.forEach(ts -> {
                if (this.moda.containsKey(ts)) {
                    this.moda.put(ts, this.moda.get(ts) + 1);
                } else {
                    this.moda.putIfAbsent(ts, 1);
                }
            });
        }

        public Map.Entry<TimeSlot, Integer> getModa() {
            return Collections.max(moda.entrySet(), new Comparator<Map.Entry<TimeSlot, Integer>>() {
                @Override
                public int compare(Map.Entry<TimeSlot, Integer> o1, Map.Entry<TimeSlot, Integer> o2) {
                    return o1.getValue().compareTo(o2.getValue());
                }
            });
        }
    }
}
