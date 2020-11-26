package com.example.calendar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HalamanLogin extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Button gotoCalendar;
    private Button gotoDaftar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gotoCalendar = (Button) findViewById(R.id.btnLogin);
        gotoDaftar = (Button) findViewById(R.id.btnDaftar);

        gotoCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HalamanLogin.this, NavigationDrawer.class);
                startActivity(intent);
            }
        });

        gotoDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HalamanLogin.this, SignUp.class);
                startActivity(intent);
            }
        });
    }
}