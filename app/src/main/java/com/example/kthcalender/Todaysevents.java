package com.example.kthcalender;

import android.os.Bundle;

import com.example.kthcalender.calender.Calender;
import com.example.kthcalender.calender.CalenderHolder;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kthcalender.databinding.ActivityTodayseventsBinding;

import java.io.IOException;
import java.time.LocalDate;

public class Todaysevents extends AppCompatActivity {

    private ActivityTodayseventsBinding binding;
    public String icallink;
    public Calender cal;
    TextView eventtext;
    TextView dateinfo;
    LocalDate date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        date =LocalDate.now();
        //icallink = bundle.getString("icallink");
        icallink = "https://www.kth.se/social/user/274804/icalendar/0acf359d0c48cb356538879820a7982f0310034d";
        try {
            cal = new Calender(icallink);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        binding = ActivityTodayseventsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dateinfo = (TextView) findViewById(R.id.dateview);
        Calender sharedCal = CalenderHolder.getCalendar(); // gets cal
        eventtext = (TextView) findViewById(R.id.eventsview);
        eventtext.setText(cal.getDayEvents(date).toString());
        dateinfo.setText(date.toString());
        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;
        toolBarLayout.setTitle(getTitle());
        Button fab = binding.yesterday;
        Button forward = binding.tomorrow;
        fab.setOnClickListener(new View.OnClickListener() { // yesterday click
            @Override
            public void onClick(View view) {
                date = date.minusDays(1);
                dateinfo.setText(date.toString());
                eventtext = (TextView) findViewById(R.id.eventsview);
                eventtext.setText(cal.getDayEvents(date).toString());
            }
        });
        forward.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                date = date.plusDays(1);
                dateinfo.setText(date.toString());
                eventtext = (TextView) findViewById(R.id.eventsview);
                eventtext.setText(cal.getDayEvents(date).toString());
            }
        });
    }
}