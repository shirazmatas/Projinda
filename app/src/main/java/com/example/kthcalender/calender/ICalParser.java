package com.example.kthcalender.calender;

import java.io.*;
import java.util.*;
import java.net.*;
// The parser takes in a URL ical link and spits out a listoflist of events divided into weeks. It only reads the current year.
// week 1 becomes slot 1. week 0 is therefore empty.
public class ICalParser {
    // Below is chatgpt suggestion, check and make it accurate
    public static List<List<Event>> parse(URL url) throws IOException {
        List<List<Event>> events = new ArrayList<>();
        URLConnection connection = url.openConnection();
        InputStream inputStream = connection.getInputStream();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            FileWriter writer = new FileWriter("Calender2.ics");
            String line;
            // We can use regex, first we want to find each instance of BEGIN:VEVENT. Then take time and extract from regex [A-Z][A-Z][0-9][0-9] etc
            //
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.write('\n');
            }
            writer.close();
        }
        return events;
    }
}
