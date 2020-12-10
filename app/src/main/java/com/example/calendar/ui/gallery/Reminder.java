package com.example.calendar.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calendar.Event;
import com.example.calendar.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class Reminder extends Fragment {

    //    private ReminderViewModel reminderViewModel;
    private RecyclerView reminder;
    private FirebaseFirestore firebaseFirestore;
    private FirestoreRecyclerAdapter reminderAdapter;
    private TextView text_gallery;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        reminderViewModel =
//                ViewModelProviders.of(this).get(ReminderViewModel.class);
        View view = inflater.inflate(R.layout.fragment_reminder, container, false);
        reminder = view.findViewById(R.id.dataReminder);
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
            protected void onBindViewHolder(@NonNull EventViewHolder holder, int position, @NonNull Event model) {
                holder.event.setText(model.getEvent());
                holder.tanggalMulai.setText(model.getTanggalMulai());
                holder.tanggalSelesai.setText(model.getTanggalSelesai());
                holder.address.setText(model.getAddress());
            }
        };

//        final TextView textView = view.findViewById(R.id.text_gallery);
//        reminderViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        reminder.setLayoutManager(new LinearLayoutManager(getContext()));
        reminder.setAdapter(reminderAdapter);
        return view;
    }

    private class EventViewHolder extends RecyclerView.ViewHolder{
        private TextView event;
        private TextView address;
        private TextView tanggalMulai;
        private TextView tanggalSelesai;
        private TextView note;

        public EventViewHolder(@NonNull View itemView){
            super(itemView);

            event = itemView.findViewById(R.id.isiEvent);
            address = itemView.findViewById(R.id.isiAddress);
            tanggalMulai = itemView.findViewById(R.id.isiTanggalMulai);
            tanggalSelesai = itemView.findViewById(R.id.isiTanggalSelesai);
            note = itemView.findViewById(R.id.isiNote);
        }
    }

    @Override
    public void onStop(){
        super.onStop();
        reminderAdapter.stopListening();
    }
    @Override
    public void onStart(){
        super.onStart();
        reminderAdapter.startListening();
    }
}