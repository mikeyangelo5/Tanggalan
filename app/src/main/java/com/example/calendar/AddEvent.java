package com.example.calendar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddEvent extends AppCompatActivity {
    private static final String TAG = AddEvent.class.getSimpleName();
    private Button createEvent;
    private Button cancelCreateEvent;
    private EditText titleEvent;
    private EditText addressEvent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        createEvent = (Button) findViewById(R.id.btnSaveEvent);
        cancelCreateEvent = (Button) findViewById(R.id.btnCancel);
        titleEvent = (EditText) findViewById(R.id.input_titleEvent);
        addressEvent = (EditText) findViewById(R.id.input_Address);

        createEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddEvent.this, NavigationDrawer.class);
                startActivity(i);
            }
        });

        cancelCreateEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddEvent.this, NavigationDrawer.class);
                startActivity(i);
            }
        });
    }
}