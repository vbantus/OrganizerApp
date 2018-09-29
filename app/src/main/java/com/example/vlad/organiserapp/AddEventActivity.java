package com.example.vlad.organiserapp;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Date;


public class AddEventActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    private String dateOfEvent;
    private Intent fromActivity;
    private TextView selectedDate;
    private TextView titleOfEvent;
    private TextView timeTextView;


    private TextView descriptionOfEvent;

    private CustomEvent customEvent;

    private Date dateTimeOfEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        fromActivity = getIntent();
        dateOfEvent = fromActivity.getExtras().getString("dateOfEvent");
        selectedDate = findViewById(R.id.selectedDate);
        titleOfEvent = findViewById(R.id.titleOfEvent);
        descriptionOfEvent = findViewById(R.id.descriptionOfEvent);
        timeTextView = findViewById(R.id.timeTextView);

        selectedDate.setText(selectedDate.getText() + dateOfEvent.toString());
        dateTimeOfEvent = new Date();
    }

    public void onClick_saveButton(View v){
        String [] date = dateOfEvent.toString().split("/");

        dateTimeOfEvent.setDate(Integer.parseInt(date[0]));
        dateTimeOfEvent.setMonth(Integer.parseInt(date[1]));
        dateTimeOfEvent.setYear(Integer.parseInt(date[2]));

        customEvent = new CustomEvent(titleOfEvent.getText().toString(),
                descriptionOfEvent.getText().toString(), dateTimeOfEvent);
        Log.d("AddEventActivity","Was added an event : "+customEvent.toString());
        finish();
    }


    public void onClick_chooseTimeButton(View v){
        TimePickerDialog timePickerDialog = new TimePickerDialog(AddEventActivity.this,AddEventActivity.this,
                dateTimeOfEvent.getHours(),dateTimeOfEvent.getMinutes(),true);
        timePickerDialog.show();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        dateTimeOfEvent.setHours(hourOfDay);
        dateTimeOfEvent.setMinutes(minute);
        dateTimeOfEvent.setSeconds(0);

        timeTextView.setText(hourOfDay+":"+minute);

    }


}
