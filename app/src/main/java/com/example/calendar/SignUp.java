package com.example.calendar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignUp extends AppCompatActivity {
    private Button daftar;
    private EditText newUsername;
    private EditText newPassword;
    private FirebaseFirestore firebaseFirestoreDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        firebaseFirestoreDb = FirebaseFirestore.getInstance();
        daftar = (Button) findViewById(R.id.btnRegis);
        newUsername= (EditText) findViewById(R.id.newUsername);
        newPassword= (EditText) findViewById(R.id.newPassword);

        daftar.setOnClickListener(new View.OnClickListener() {
            Intent intent = new Intent(SignUp.this, NavigationDrawer.class);
                @Override
                public void onClick(View v) {
                    //sanity check
                    if (!newUsername.getText().toString().isEmpty() && !newPassword.getText().toString().isEmpty()) {
                        tambahUser();
                        Toast.makeText(SignUp.this, "Daftar Berhasil", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(),"No dan Nama Mhs tidak boleh kosong", Toast.LENGTH_SHORT).show();
                    }
                }
            });
    }


    private void tambahUser() {

        Users user = new Users(newUsername.getText().toString(), newPassword.getText().toString());

        firebaseFirestoreDb.collection("DaftarUser").document(user.getUsername()).set(user)
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

