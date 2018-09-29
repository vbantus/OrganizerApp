package com.example.vlad.organiserapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.Toast;

import java.util.Date;

public class CalendarActivity extends AppCompatActivity {

    private String dateOfEvent;
    private CalendarView calendarView;
    Date date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        calendarView = findViewById(R.id.calendarView);

        date = new Date();
        dateOfEvent = date.getDate() + "/" + date.getMonth() + "/" + date.getYear();

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Toast.makeText(getBaseContext(),"You have selected : " + dayOfMonth + "day ",Toast.LENGTH_SHORT).show();
                dateOfEvent = dayOfMonth + "/" + month + "/" + year;
            }
        });
    }


    public void onClick_addEventButton(View v){

        Intent addEventIntent = new Intent(CalendarActivity.this,AddEventActivity.class);
        addEventIntent.putExtra("dateOfEvent",dateOfEvent);
        startActivity(addEventIntent);
    }


}
