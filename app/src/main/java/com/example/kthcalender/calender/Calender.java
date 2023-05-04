package com.example.kthcalender.calender;

import java.io.*;
import java.net.*;
import java.util.*;

public class Calender {
    public String iCalenderlink;
    public HashMap<Date,List<Event>> events;

    public Calender(String iCalenderlink) throws IOException {
        this.iCalenderlink = iCalenderlink;
        this.events = new HashMap<Date,List<Event>>();
        this.load(this.iCalenderlink);
    }
    public void load(String link) throws IOException {
        URL url = new URL(link);
        this.events = ICalParser.parse(url);
    }

    //returns list of the days events
    public List<Event> getDayEvents(Date day){
        return events.get(day);
    }

    //returns the list of list of days events
    public List<List<Event>> getWeekDays(int weekNumber){
        List<List<Event>> weekEvents = new ArrayList<>();
        // Proof read code
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.WEEK_OF_YEAR, weekNumber);
        cal.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
        for (int i = 0; i < 7; i++) {
            weekEvents.add(this.getDayEvents(cal.getTime()));
            cal.add(Calendar.DAY_OF_WEEK, 1);
        }
        // End of proofread
        return weekEvents;
    }
}
