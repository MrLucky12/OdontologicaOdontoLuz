package com.zumaran.odontooga.app.models;

import java.time.LocalTime;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TimePeriod implements Comparable<TimePeriod>{
    
    private LocalTime start;
    private LocalTime end;

    @Override
    public int compareTo(TimePeriod o) { return this.getStart().compareTo(o.getStart()); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimePeriod peroid = (TimePeriod) o;
        return this.start.equals(peroid.getStart()) && this.end.equals(peroid.getEnd());
    }

    @Override
    public int hashCode() { return Objects.hash(start, end); }

    @Override
    public String toString() { return "TimePeroid{"+"start=" + start +", end=" + end +'}'; }


}
