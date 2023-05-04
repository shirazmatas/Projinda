package com.example.kthcalender.calender;

import java.io.*;
import java.util.*;
import java.net.*;
import java.util.regex.Pattern;

// The parser takes in a URL ical link and spits out a listoflist of events divided into weeks. It only reads the current year.
// week 1 becomes slot 1. week 0 is therefore empty.
public class ICalParser {
    public static HashMap<Date,List<Event>> parse(URL url) throws IOException {
        // Variables
        HashMap<Date,List<Event>> events = new HashMap<>();
        URLConnection connection = url.openConnection();
        InputStream inputStream = connection.getInputStream();
        int numberEvents=0;

        //Regex patterns for the different keywords
        Pattern eventBegin = Pattern.compile("BEGIN:VEVENT");
        Pattern summary = Pattern.compile("SUMMARY"); // when we find summary we name the event
        //event types
        Pattern tenta = Pattern.compile("Tenta",Pattern.CASE_INSENSITIVE); // find any tenta
        Pattern lecture = Pattern.compile("Föreläsning -",Pattern.CASE_INSENSITIVE);
        Pattern ovning = Pattern.compile("Övning -",Pattern.CASE_INSENSITIVE);
        Pattern seminarium = Pattern.compile("Seminarium -", Pattern.CASE_INSENSITIVE);
        Pattern starttime = Pattern.compile("DTSTART;VALUE=DATE-TIME:",Pattern.CASE_INSENSITIVE);
        Pattern endtime = Pattern.compile("DTEND;VALUE=DATE-TIME:",Pattern.CASE_INSENSITIVE);
        Pattern description = Pattern.compile("DESCRIPTION:", Pattern.CASE_INSENSITIVE);
        Pattern location = Pattern.compile("LOCATION:", Pattern.CASE_INSENSITIVE);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"))) {
            FileWriter writer = new FileWriter("Calender2.ics"); // writes in this file to save for later use
            String line;
            // We can use regex, first we want to find each instance of BEGIN:VEVENT. Then take time and extract from regex [A-Z][A-Z][0-9][0-9] etc
            //

            while ((line = reader.readLine()) != null) {
                writer.write(line+'\n'); // locally saved
                if (eventBegin.matcher(line).find()){ // if eventbegin
                    Event newEvent = new Event();
                    while ((line= reader.readLine()) != null && !line.equals("END:VEVENT")){
                        if (summary.matcher(line).find()){
                            newEvent.event_name = line.substring(summary.matcher(line).end()); // Everything after match
                            if (tenta.matcher(line).find()){
                                newEvent.event_type = "Tenta";
                            }
                            else if(lecture.matcher(line).find()){
                                newEvent.event_type = "Föreläsning";
                            }
                            else if (ovning.matcher(line).find()){
                                newEvent.event_type = "Övning";
                            }
                            else if (seminarium.matcher(line).find()){
                                newEvent.event_type = "Seminarium";
                            }
                            else{
                                newEvent.event_type = "Other";
                            }
                        }
                        else if (starttime.matcher(line).find()){
                            String timestring = line.substring(starttime.matcher(line).end()); // YYYYMMDDTHHMMSS(time zone)
                            newEvent.date = new Date(Integer.parseInt(timestring.substring(0,8))); // YYYYMMDD
                            newEvent.start = Integer.parseInt(timestring.substring(9,13));
                        }
                        else if (endtime.matcher(line).find()){
                            String timestring = line.substring(starttime.matcher(line).end());
                            newEvent.end = Integer.parseInt(timestring.substring(9,13));
                        }
                        else if (description.matcher(line).find()){
                            newEvent.description = line.substring(description.matcher(line).end());
                        }
                        else if (location.matcher(line).find()){
                            newEvent.location = line.substring(location.matcher(line).end());
                        }
                    }
                    List<Event> list = events.computeIfAbsent(newEvent.date, k -> new ArrayList<>());
                    list.add(newEvent);
                }
            }
            writer.close();
        }
        return events;
    }
}
