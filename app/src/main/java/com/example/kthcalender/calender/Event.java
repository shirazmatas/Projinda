package com.example.kthcalender.calender;

import java.text.SimpleDateFormat;
import java.util.*;

public class Event {
    //TODO
    // Complete elements of a event (Föreläsning, Övning, etc..)
    public String event_name;
    public String event_type;
    public String location;
    public String description;
    public Date date; //YYYYMMDD.
    public int start; //Time of day
    public int end;
    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        String str = "Event Name: " + event_name + "\n"
                + "Event Type: " + event_type + "\n"
                + "Location: " + location + "\n"
                + "Description: " + description + "\n"
                + "Date: " + dateFormat.format(date) + "\n"
                + "Start Time: " + timeFormat.format(start) + "\n"
                + "End Time: " + timeFormat.format(end) + "\n";
        return str;
    }


}
