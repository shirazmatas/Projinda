package com.example.kthcalender.calender;

// CalenderHolder.java
public class CalenderHolder {
    private static Calender calendarInstance;

    public static void setCalendar(Calender cal) {
        calendarInstance = cal;
    }

    public static Calender getCalendar() {
        return calendarInstance;
    }
}

