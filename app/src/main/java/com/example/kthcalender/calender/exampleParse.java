package com.example.kthcalender.calender;

import java.io.IOException;
import java.net.URL;

public class exampleParse {
    public static void main(String[] args) throws IOException {
        URL url = new URL("https://www.kth.se/social/user/274804/icalendar/0acf359d0c48cb356538879820a7982f0310034d");
        ICalParser.parse(url);
        //Calender calendertest = new Calender("https://www.kth.se/social/user/274804/icalendar/0acf359d0c48cb356538879820a7982f0310034d"); // min kth kalender
    }
}
