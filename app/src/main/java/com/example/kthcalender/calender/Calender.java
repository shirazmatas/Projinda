package com.example.kthcalender.calender;

import java.io.*;
import java.net.*;
import java.time.LocalDate;
import java.util.*;

public class Calender {
    public String iCalenderlink;
    public HashMap<LocalDate,List<Event>> events;

    public Calender(String iCalenderlink) throws IOException {
        this.iCalenderlink = iCalenderlink;
        this.events = new HashMap<LocalDate,List<Event>>();
        this.load(this.iCalenderlink);
    }
    public void load(String link) throws IOException {
        URL url = new URL(link);
        this.events = ICalParser.parse(url);
    }

    //returns list of the days events
    public List<Event> getDayEvents(LocalDate day){
        return events.get(day);
    }

    // Returns a list of lists of events for each day in a selected week
    public List<List<Event>> getWeekDays(LocalDate startOfWeek) {
        List<List<Event>> weekEvents = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            LocalDate day = startOfWeek.plusDays(i);
            List<Event> dayEvents = getDayEvents(day);
            weekEvents.add(dayEvents);
        }

        return weekEvents;
    }


}
