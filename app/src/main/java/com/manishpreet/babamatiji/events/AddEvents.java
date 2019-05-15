package com.manishpreet.babamatiji.events;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.manishpreet.babamatiji.Event;
import com.manishpreet.babamatiji.R;

import java.util.Calendar;
import java.util.Date;

public class AddEvents extends AppCompatActivity {
    Button button;
    EditText editText;
    TextView selectDate;
    FirebaseFirestore firebaseFirestore;
    Calendar calendar;
    int day,month,year;
    Event event;
DatePickerDialog datePickerDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_events);
        firebaseFirestore = FirebaseFirestore.getInstance();
        calendar=Calendar.getInstance();
        day=calendar.get(Calendar.DAY_OF_MONTH);
        month=calendar.get(Calendar.MONTH);
        year=calendar.get(Calendar.YEAR);
        button = findViewById(R.id.add_events);
        editText = findViewById(R.id.edit_event);
        event= new Event();
        selectDate = findViewById(R.id.selectDate);
        selectDate.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                datePickerDialog= new DatePickerDialog(AddEvents.this,
                         myDateListener, year, month, day);
                datePickerDialog.show();
             }
         });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String event = editText.getText().toString();
                if (selectDate.getText().toString().equals("Select Date"))
                    Toast.makeText(AddEvents.this, "Select Date First", Toast.LENGTH_SHORT).show();
                else
                addEventToDatabase(event);
            }
        });


    }
    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int year, int month, int day) {

                    showDate(year, month+1, day);
                }
            };

    private void showDate(int year, int month, int day) {
        String date=day+"/"+month+"/"+year;
        selectDate.setText(date);
        event.setDate(date);
    }

    private void addEventToDatabase(String string) {

event.setEvent(string);
        firebaseFirestore.collection("events").document().set(event)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            //dialog.hide();
                            Toast.makeText(AddEvents.this, "event added", Toast.LENGTH_SHORT).show();
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //dialog.hide();
                Toast.makeText(AddEvents.this, e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}

