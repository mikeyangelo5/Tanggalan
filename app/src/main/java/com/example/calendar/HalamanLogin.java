package com.example.calendar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HalamanLogin extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Button btncalendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btncalendar = (Button) findViewById(R.id.btncalendar);

        btncalendar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HalamanLogin.this, HalamanCalendar.class);
                startActivity(intent);
            }
        });
    }
}