package com.example.calendar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class AddEvent extends AppCompatActivity {
    private static final String TAG = AddEvent.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
    }
}