package com.example.duanli.student_portal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewEventActivity extends AppCompatActivity implements View.OnClickListener{

    Button Create;
    EditText etEventName,etDate,etTime,etLocation,etPrice,etCapacity,etDescription;
    String organizerName,eventName,date,time,location,price,capacity,description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);

        etEventName = (EditText) findViewById(R.id.etEventName);
        etDate = (EditText) findViewById(R.id.etDate);
        etTime = (EditText) findViewById(R.id.etTime);
        etLocation = (EditText) findViewById(R.id.etLocation);
        etCapacity = (EditText) findViewById(R.id.etCapacity);
        etPrice = (EditText) findViewById(R.id.etPrice);
        etDescription = (EditText) findViewById(R.id.etDescription);
        Create = (Button) findViewById(R.id.button);
        Create.setOnClickListener(this);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.button) {
            Toast.makeText(NewEventActivity.this, "Submitting", Toast.LENGTH_SHORT).show();
            //Intent intent = new Intent(this, Db_connection.class);
            //startActivity(intent);
            organizerName = ThisUser.username;
            eventName = etEventName.getText().toString();
            date = etDate.getText().toString();
            time = etTime.getText().toString();
            location = etLocation.getText().toString();
            price = etPrice.getText().toString();
            capacity = etCapacity.getText().toString();
            description = etDescription.getText().toString();

            Event event = new Event(organizerName,eventName,date,time,location,price,capacity,description);
            event.addEvent();
            finish();
        }

    }
}
