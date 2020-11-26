package com.example.calendar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;

public class AddEvent extends AppCompatActivity {
    private static final String TAG = AddEvent.class.getSimpleName();
    private Button createEvent;
    private Button cancelCreateEvent;
    private EditText titleEvent;
    private EditText addressEvent;
    private EditText inputEvent;
    private EditText inputAddress;
    private TextView tvDate;
    private FirebaseFirestore firebaseFirestoreDb;
    DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        createEvent = (Button) findViewById(R.id.btnSaveEvent);
        cancelCreateEvent = (Button) findViewById(R.id.btnCancel);
        titleEvent = (EditText) findViewById(R.id.input_titleEvent);
        addressEvent = (EditText) findViewById(R.id.input_Address);
        tvDate = findViewById(R.id.tv_date);
        firebaseFirestoreDb = FirebaseFirestore.getInstance();

        createEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!titleEvent.getText().toString().isEmpty() && !addressEvent.getText().toString().isEmpty() && !tvDate.getText().toString().isEmpty()) {
                    tambahEvent();
                    Toast.makeText(AddEvent.this, "Tambah Event Berhasil", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(AddEvent.this, NavigationDrawer.class);
                    startActivity(i);
                } else {
                    Toast.makeText(getApplicationContext(),"Tanggal, nama event dan address harus terisi!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cancelCreateEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddEvent.this, NavigationDrawer.class);
                startActivity(i);
            }
        });

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(calendar.YEAR);
        final int month = calendar.get(calendar.MONTH);
        final int day = calendar.get(calendar.DAY_OF_MONTH);

        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        AddEvent.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth
                        , setListener, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));
                datePickerDialog.show();
            }
        });
        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                String newMonth = "";

                if(month==1){
                    newMonth = "January";
                }
                else if(month==2){
                    newMonth = "February";
                }
                else if(month==3){
                    newMonth = "March";
                }
                else if(month==4){
                    newMonth = "April";
                }
                else if(month==5){
                    newMonth = "May";
                }
                else if(month==6){
                    newMonth = "June";
                }
                else if(month==7){
                    newMonth = "July";
                }
                else if(month==8){
                    newMonth = "August";
                }
                else if(month==9){
                    newMonth = "September";
                }
                else if(month==10){
                    newMonth = "October";
                }
                else if(month==11){
                    newMonth = "November";
                }
                else if(month==12){
                    newMonth = "December";
                }
                String date = dayOfMonth + " " + newMonth + " " + year;
                tvDate.setText(date);
            }
        };
    }

    public void tambahEvent(){
        Event event = new Event(tvDate.getText().toString(), titleEvent.getText().toString(), addressEvent.getText().toString());
        firebaseFirestoreDb.collection("Event").document(event.getEvent()).set(event)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(), "User berhasil didaftarkan", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "ERROR" + e.toString(),
                                Toast.LENGTH_SHORT).show();
                        Log.d("TAG", e.toString());
                    }
                });

    }
}