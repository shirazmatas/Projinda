package com.example.kthcalender.calender;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event {
    //TODO
    // Complete elements of a event (Föreläsning, Övning, etc..)
    public String event_name;
    public String event_type;
    public String location;
    public String description;
    public LocalDate date; //YYYYMMDD.
    public int start; //Time of day
    public int end;
    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formattedDate = date.format(dateFormatter);
        String str = "Event Name: " + event_name + "\n"
                + "Event Type: " + event_type + "\n"
                + "Location: " + location + "\n"
                + "Description: " + description + "\n"
                + "Date: " + formattedDate + "\n"
                + "Start Time: " + start + "\n"
                + "End Time: " + end + "\n";
        return str;
    }


}
