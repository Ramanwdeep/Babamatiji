package com.manishpreet.babamatiji.events;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.manishpreet.babamatiji.Event;
import com.manishpreet.babamatiji.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventsFragment extends Fragment {


    public static EventsFragment instance;
    FirebaseFirestore firebaseFirestore;
    EventRecyclerAdapter adapter;
    private RecyclerView listView;
    private Button button;

    public EventsFragment() {
        // Required empty public constructor
    }

    public static EventsFragment getInstance() {
        if (instance == null) {
            instance = new EventsFragment();
        }
        return instance;
    }


    FirebaseAuth auth;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_events, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        firebaseFirestore = FirebaseFirestore.getInstance();
        button = view.findViewById(R.id.add_option);

        auth = FirebaseAuth.getInstance();

        String currentUserMail = auth.getCurrentUser().getEmail();

        if (currentUserMail.equals("ramandeep@gmail.com")||currentUserMail.equals("davinder@gmail.com"))
        {button.setVisibility(View.VISIBLE);}
        else {button.setVisibility(View.INVISIBLE);}

        try {
            LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            listView = view.findViewById(R.id.eventsRecyclerView);
            getEvents();
            listView.setLayoutManager(manager);
            listView.setAdapter(adapter);
        }
        catch (Exception e)
        {
            Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddEvents.class);
                getActivity().startActivity(intent);
            }
        });

    }

    private void getEvents() {
        firebaseFirestore.collection("events").get()
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    try {

                        ArrayList<Event> eventList = new ArrayList<>();

                        for (int i = 0; i < task.getResult().size(); i++) {
                            Event event = task.getResult().getDocuments().get(i).toObject(Event.class);
                            eventList.add(event);
                        }

                        adapter = new EventRecyclerAdapter(getActivity(), eventList);
                        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

                        listView.setLayoutManager(manager);
                        listView.setAdapter(adapter);
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}

