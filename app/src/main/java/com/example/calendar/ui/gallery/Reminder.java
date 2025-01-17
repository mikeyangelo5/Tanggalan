package com.example.calendar.ui.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calendar.Event;
import com.example.calendar.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class Reminder extends Fragment {

    private RecyclerView dataReminder;
    private FirebaseFirestore firebaseFirestore;
    private FirestoreRecyclerAdapter reminderAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final String TAG = "Reminder";
        View view = inflater.inflate(R.layout.fragment_reminder, container, false);
        dataReminder = view.findViewById(R.id.dataReminder);
        firebaseFirestore = firebaseFirestore.getInstance();

        Query query = firebaseFirestore.collection("Event");
        FirestoreRecyclerOptions<Event> options = new FirestoreRecyclerOptions.Builder<Event>()
                .setQuery(query, Event.class)
                .build();

        reminderAdapter = new FirestoreRecyclerAdapter<Event, EventViewHolder>(options) {
            @NonNull
            @Override
            public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.designevent, parent,false);
                return new EventViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull EventViewHolder holder, int position, @NonNull final Event model) {
                final String tglMulai = model.getTanggalMulai();
                holder.event.setText(model.getEvent());
                holder.tanggalMulai.setText(model.getTanggalMulai());
                holder.tanggalSelesai.setText(model.getTanggalSelesai());
                holder.address.setText(model.getAddress());
                holder.note.setText(model.getNote());

                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CollectionReference cr = firebaseFirestore.collection("Event");
                        DocumentReference dr = cr.document(tglMulai);
                        dr.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()) {
                                    Toast.makeText(getActivity(), "Delete Success", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getActivity(), "Delete Failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
            }
        };

        dataReminder.setLayoutManager(new LinearLayoutManager(getActivity()));
        dataReminder.setAdapter(reminderAdapter);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        reminderAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        reminderAdapter.stopListening();
    }

    private class EventViewHolder extends RecyclerView.ViewHolder{
        private TextView event;
        private TextView address;
        private TextView tanggalMulai;
        private TextView tanggalSelesai;
        private TextView note;
        private Button btnDelete;

        public EventViewHolder(@NonNull View itemView){
            super(itemView);

            event = itemView.findViewById(R.id.isiEvent);
            address = itemView.findViewById(R.id.isiAddress);
            tanggalMulai = itemView.findViewById(R.id.isiTanggalMulai);
            tanggalSelesai = itemView.findViewById(R.id.isiTanggalSelesai);
            note = itemView.findViewById(R.id.isiNote);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}