package com.example.kthcalender.calender;

import java.io.*;
import java.net.*;
import java.util.*;

public class Calender {
    public String iCalenderlink;
    public ArrayList<ArrayList<Event>> events;

    public Calender(String iCalenderlink) throws IOException {
        this.iCalenderlink = iCalenderlink;
        this.events = new ArrayList<ArrayList<Event>>();
        this.load(this.iCalenderlink);
    }
    public void load(String link) throws IOException {
        URL url = new URL(link);

    }
}
