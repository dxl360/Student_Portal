package com.example.duanli.student_portal;

import android.content.Intent;
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
            organizerName = new User().getUserName();
            eventName = etEventName.getText().toString();
            date = etDate.getText().toString();
            time = etTime.getText().toString();
            location = etLocation.getText().toString();
            price = etPrice.getText().toString();
            capacity = etCapacity.getText().toString();
            description = etDescription.getText().toString();
            if (isValidOrganizerName(organizerName) && isValidEventName(eventName) && isValidDate(date) && isValidTime(time) && isValidDescription(description));
            Event event = new Event(organizerName,eventName,date,time,location,price,capacity,description);
            event.addEvent();
            finish();
            startActivity(new Intent(this, EventDetailActivity.class));
        }

    }

    public static boolean isValidOrganizerName(String s)
    {
        String ITEMNAME_REGEX="^(?=.{5,20}$)[a-zA-Z0-9]+$";
        if (!s.equals("")) {
            if (s.matches(ITEMNAME_REGEX)) {
                return true;}}
        return false;
    }

    public static boolean isValidEventName(String s)
    {
        String ITEMNAME_REGEX="^(?=.{5,20}$)[a-zA-Z0-9]+$";
        if (!s.equals("")) {
            if (s.matches(ITEMNAME_REGEX)) {
                return true;}}
        return false;
    }

    //date in formate yyyy-mm-dd from 1900-01-01 through 2099-12-31
    public static boolean isValidDate(String s){
        String DATE_REGEX = "^(19|20)\\d\\d[- /.](0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])$";
        if (!s.equals("")) {
            if (s.matches(DATE_REGEX)) {
                return true;}}
        return false;
    }

    //time in format xx:xx
    public static boolean isValidTime(String s){
        String TIME_REGEX = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
        if (!s.equals("")) {
            if (s.matches(TIME_REGEX)) {
                return true;}}
        return false;
    }

    public static boolean isValidDescription(String s)
    {
        String ITEMNAME_REGEX="^(?=.{5,20}$)[a-zA-Z0-9]+$";
        if (!s.equals("")) {
            if (s.matches(ITEMNAME_REGEX)) {
                return true;}}
        return false;
    }
}
