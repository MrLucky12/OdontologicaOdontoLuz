package com.zumaran.odontooga.app.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DayPlan {
    
    private TimePeriod wh;
    private List<TimePeriod> breaks;

    public DayPlan() { breaks = new ArrayList(); }

    public DayPlan(TimePeriod wh) {
        this.wh = wh;
        this.breaks = new ArrayList();
    }

    public List<TimePeriod> getTimePeriodsWithBreaksExcluded() {
        ArrayList<TimePeriod> TimePeriodsWithBreaksExcluded = new ArrayList<>();
        TimePeriodsWithBreaksExcluded.add(getWh());
        List<TimePeriod> breaks = getBreaks();

        if (!breaks.isEmpty()) 
        {
            ArrayList<TimePeriod> toAdd = new ArrayList();

            for (TimePeriod break1 : breaks) 
            {
                if (break1.getStart().isBefore(wh.getStart())) { break1.setStart(wh.getStart()); }

                if (break1.getEnd().isAfter(wh.getEnd())) { break1.setEnd(wh.getEnd()); }

                for (TimePeriod peroid : TimePeriodsWithBreaksExcluded) 
                {
                    if (break1.getStart().equals(peroid.getStart()) && break1.getEnd().isAfter(peroid.getStart()) && break1.getEnd().isBefore(peroid.getEnd())) 
                        { peroid.setStart(break1.getEnd()); }

                    if (break1.getStart().isAfter(peroid.getStart()) && break1.getStart().isBefore(peroid.getEnd()) && break1.getEnd().equals(peroid.getEnd())) 
                        { peroid.setEnd(break1.getStart()); }

                    if (break1.getStart().isAfter(peroid.getStart()) && break1.getEnd().isBefore(peroid.getEnd())) 
                        { 
                            toAdd.add(new TimePeriod(peroid.getStart(), break1.getStart()));
                            peroid.setStart(break1.getEnd());
                        } 
                }
            }

            TimePeriodsWithBreaksExcluded.addAll(toAdd);
            Collections.sort(TimePeriodsWithBreaksExcluded);
        }

        return TimePeriodsWithBreaksExcluded;
    }

}
