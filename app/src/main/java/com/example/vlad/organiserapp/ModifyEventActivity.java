package com.example.vlad.organiserapp;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

public class ModifyEventActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    private TextView descriptionOfEvent;
    private TextView titleOfEvent;
    private TextView timeTextView;
    private CustomEvent customEvent;
    private Switch isAlarmSet;

    private Intent fromActivity;
    int eventId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_event);

        // find all TextInputs
        descriptionOfEvent = findViewById(R.id.descriptionOfEvent);
        titleOfEvent = findViewById(R.id.titleOfEvent);
        timeTextView = findViewById(R.id.timeTextView);

        isAlarmSet = findViewById(R.id.isAlarmSetTextView);

        // get eventById
        fromActivity = getIntent();
        eventId = fromActivity.getExtras().getInt("eventId");
        customEvent = CustomEventXmlParser.getEventById(eventId);
        Log.d("Modifyactivity","Passed customEventObject : " + customEvent.toString());

        // set all TextInputs
        titleOfEvent.setText(customEvent.getTitle());
        descriptionOfEvent.setText(customEvent.getDescription());
        timeTextView.setText(customEvent.getDate().getHours() + ":" + customEvent.getDate().getMinutes());
        isAlarmSet.setChecked(customEvent.getIsAlarmSet() == 1 ? true : false);

    }

    public void onClick_chooseTimeButton(View v){
        TimePickerDialog timePickerDialog = new TimePickerDialog(ModifyEventActivity.this,ModifyEventActivity.this,
                customEvent.getDate().getHours(),customEvent.getDate().getMinutes(),true);
        timePickerDialog.show();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        customEvent.getDate().setHours(hourOfDay);
        customEvent.getDate().setMinutes(minute);
        customEvent.getDate().setSeconds(0);

        timeTextView.setText(hourOfDay+":"+minute);

    }


    public void onClick_saveButton(View v){

        if (isAlarmSet.isChecked())
            customEvent.setIsAlarmSet(1);
        else
            customEvent.setIsAlarmSet(0);

        customEvent.setTitle(titleOfEvent.getText().toString());
        customEvent.setDescription(descriptionOfEvent.getText().toString());
        CustomEventXmlParser.modifyXml(customEvent);

        finish();
    }



    public void onClick_backButton(View v){
        finish();
    }
}
