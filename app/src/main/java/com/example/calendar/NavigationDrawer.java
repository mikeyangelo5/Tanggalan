package com.example.calendar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NavigationDrawer extends AppCompatActivity {
    private static final String TAG = "CalendarActivity";
    private CalendarView kalender;
    private AppBarConfiguration mAppBarConfiguration;
    private TextView email;
    private Button btnEvent;
    private TextView tvDate;
    private FirebaseAuth mAuth;
    RecyclerView recyclerView;
    private FirebaseFirestore firebaseFirestoreDb;
    private FirestoreRecyclerAdapter adapter;
    String uid;
    List<String> itemlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        uid = mAuth.getUid();
        itemlist = new ArrayList<>();
        firebaseFirestoreDb = FirebaseFirestore.getInstance();
        email = (TextView) findViewById(R.id.username);
        setContentView(R.layout.activity_navigation_drawer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        tvDate = findViewById(R.id.tv_date);
        setSupportActionBar(toolbar);
        FloatingActionButton btnEvent = findViewById(R.id.btnAddEvent);
        btnEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NavigationDrawer.this, AddEvent.class);
                startActivity(intent);
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_reminder, R.id.nav_week)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        kalender = (CalendarView) findViewById(R.id.calendarView);
//        kalender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
//            @Override
//            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
//                String date = year + "/" + month + "/" + dayOfMonth;
//                Log.d(TAG, "onSelectedDayChange: yyyy/mm/dd: " + date);
//
//                Intent intent = new Intent(NavigationDrawer.this, AddEvent.class);
//                intent.putExtra("date", date);
//                startActivity(intent);
//
//            }
//
//        });

//        loadUserInformation();
//
    }
//    private void loadUserInformation(){
//        Log.i(TAG, "getAllDocument: masuk logi");
//        String string= mAuth.getCurrentUser().getEmail().toString();
//        firebaseFirestoreDb.collection("DaftarUser")
//                .document(string)
//                .get()
//                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//                    @Override
//                    public void onSuccess(DocumentSnapshot documentSnapshot) {
//                        email.setText(documentSnapshot.get("username").toString());
//
//                    }
//                });
//    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),HalamanLogin.class));
                finish();

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}