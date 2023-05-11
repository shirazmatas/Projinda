package com.example.kthcalender.calender;

import android.os.StrictMode;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.net.*;
import java.time.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// The parser takes in a URL ical link and spits out a listoflist of events divided into weeks. It only reads the current year.
// week 1 becomes slot 1. week 0 is therefore empty.
public class ICalParser {
    public static HashMap<LocalDate,List<Event>> parse(URL url) throws IOException {
        // Variables
        HashMap<LocalDate,List<Event>> events = new HashMap<>();
        //App will crash if we dont run async for internet stuff. Instead we just disable safety
        StrictMode.ThreadPolicy gfgPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(gfgPolicy);

        //Start network thing
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
        DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"))) {
            FileWriter writer = new FileWriter("Calender2.ics"); // writes in this file to save for later use
            String line;
            // We can use regex, first we want to find each instance of BEGIN:VEVENT. Then take time and extract from regex [A-Z][A-Z][0-9][0-9] etc
            //

            while ((line = reader.readLine()) != null) {
                if (eventBegin.matcher(line).find()){ // if eventbegin
                    Event newEvent = new Event();
                    writer.write(line+'\n'); // locally saved
                    while ((line= reader.readLine()) != null && !line.equals("END:VEVENT")){
                        writer.write(line+'\n'); // locally saved
                        Matcher sum = summary.matcher(line);
                        Matcher start = starttime.matcher(line);
                        Matcher end = endtime.matcher(line);
                        Matcher descript = description.matcher(line);
                        Matcher local = location.matcher(line);
                        if (sum.find()){
                            newEvent.event_name = line.substring(sum.end()); // Everything after match
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
                        else if (start.find()){
                            String timestring = line.substring(start.end()); // YYYYMMDDTHHMMSS(time zone)
                            newEvent.date = LocalDate.parse(timestring,dateformat); //chatgpt generated format somehow.
                            newEvent.start = Integer.parseInt(timestring.substring(9,13));
                            System.out.println(newEvent.date);
                            System.out.println(newEvent.start);
                        }
                        else if (end.find()){
                            String timestring = line.substring(end.end());
                            newEvent.end = Integer.parseInt(timestring.substring(9,13));
                        }
                        else if (descript.find()){
                            newEvent.description = line.substring(descript.end());
                        }
                        else if (local.find()){
                            newEvent.location = line.substring(local.end());
                        }
                    }
                    List<Event> list = events.computeIfAbsent(newEvent.date, k -> new ArrayList<>());
                    list.add(newEvent);
                }
                else{
                    writer.write(line+'\n'); // locally saved
                }
            }
            writer.close();
        }
        return events;
    }
}