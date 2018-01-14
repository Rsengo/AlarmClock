package com.example.ytgv8b.firsttry;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TimePicker;

public class AddNotification extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notification);
        TimePicker tp = (TimePicker)findViewById(R.id.timePicker);
        tp.setIs24HourView(true);
    }
}
