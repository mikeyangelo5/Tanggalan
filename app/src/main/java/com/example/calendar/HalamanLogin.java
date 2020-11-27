package com.example.calendar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class HalamanLogin extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Button gotoCalendar;
    private TextView gotoDaftar;
    private EditText input_email;
    private EditText input_password;
    private FirebaseAuth mAuth;
    private FirebaseFirestore firebaseFirestoreDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        firebaseFirestoreDb = FirebaseFirestore.getInstance();
        input_email = (EditText) findViewById(R.id.input_email);
        input_password = (EditText) findViewById(R.id.input_password);
        gotoCalendar = (Button) findViewById(R.id.btnLogin);
        gotoDaftar = (TextView) findViewById(R.id.btnDaftar);
        final Intent intent = new Intent(HalamanLogin.this, NavigationDrawer.class);

        if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), NavigationDrawer.class));
            finish();
        }

        gotoCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = input_email.getText().toString().trim();
                String password = input_password.getText().toString().trim();

                if(TextUtils.isEmpty(email) && password.length() < 8){
                    input_email.setError("Email harus di Isi!!!");
                    input_password.setError("Password minimal harus 8 karakter");
                    return;
                }

                if(TextUtils.isEmpty(email) && TextUtils.isEmpty(password)){
                    input_email.setError("Email harus di Isi!!!");
                    input_password.setError("Password Harus di Isi!!!");
                    return;
                }
                if (TextUtils.isEmpty(email)) {
                    input_email.setError("Email harus di Isi!!!");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    input_password.setError("Password Harus di Isi!!!");
                    return;
                }

                if (password.length() < 8) {
                    input_password.setError("Password minimal harus 8 karakter");
                    return;
                }
                //Login ke firebase
                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(HalamanLogin.this,"Berhasil Login",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),NavigationDrawer.class));
                        } else{
                            Toast.makeText(HalamanLogin.this, "Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
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