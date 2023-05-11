package com.example.kthcalender;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.kthcalender.calender.Calender;
import com.example.kthcalender.calender.CalenderHolder;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.kthcalender.databinding.ActivityMainBinding;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Calender cal;
        //String iCalLink = "https://www.kth.se/social/user/274804/icalendar/0acf359d0c48cb356538879820a7982f0310034d";
        /*
        try {
            cal = new Calender(iCalLink);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        CalenderHolder.setCalendar(cal); // This makes it accesible in other places
         */

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        /*
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String iCalLink = editText.getText().toString();
                textView.setText(iCalLink);
                editText.setVisibility(View.GONE);
                button.setVisibility(View.GONE);
                textView.setVisibility(View.GONE);
            }
        });
        */
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

}