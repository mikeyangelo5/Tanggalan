package com.example.calendar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HalamanCalendar extends AppCompatActivity {
    private static final String TAG = "CalendarActivity";
    private CalendarView kalender;
    private Button btnEvent;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendarview);
        btnEvent = (Button) findViewById(R.id.btnAddEvent);
        kalender = (CalendarView) findViewById(R.id.calendarView);
        kalender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date = year + "/" + month + "/" + dayOfMonth;
                Log.d(TAG, "onSelectedDayChange: date: " + date);
            }
        });

        btnEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HalamanCalendar.this, AddEvent.class);
                startActivity(intent);
            }
        });
    }
}