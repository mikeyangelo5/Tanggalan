package com.example.calendar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignUp extends AppCompatActivity {
    private Button daftar;
    private EditText newEmail;
    private EditText newPassword;
    private FirebaseAuth mAuth;
    private FirebaseFirestore firebaseFirestoreDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();
        daftar = (Button) findViewById(R.id.btnRegis);
        newEmail = (EditText) findViewById(R.id.newEmail);
        newPassword = (EditText) findViewById(R.id.newPassword);
        firebaseFirestoreDb = FirebaseFirestore.getInstance();
        final Intent intent = new Intent(SignUp.this, NavigationDrawer.class);

        if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), NavigationDrawer.class));
            finish();
        }

        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = newEmail.getText().toString().trim();
                String password = newPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email) && TextUtils.isEmpty(password)){
                    newEmail.setError("Email harus di Isi!!!");
                    newPassword.setError("Password Harus di Isi!!!");
                    return;
                }
                if (TextUtils.isEmpty(email)) {
                    newEmail.setError("Email harus di Isi!!!");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    newPassword.setError("Password Harus di Isi!!!");
                    return;
                }

                if (password.length() < 8) {
                    newPassword.setError("Password minimal harus 8 karakter");
                    return;
                }
                //regis ke firebase
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            DocumentReference documentReference = firebaseFirestoreDb.collection("DaftarUser")
                                    .document();
                            Users users = new Users();
                            users.setEmail(newEmail.getText().toString());
                            users.setPassword(newPassword.getText().toString());
                            documentReference.set(users);
                            Toast.makeText(SignUp.this, "Data Tersimpan", Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                        } else {
                            Toast.makeText(SignUp.this, "Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT);
                        }

                    }
                });
            }
        });
    }
}


