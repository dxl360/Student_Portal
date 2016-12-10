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
    EditText etEventName,etDate,etEndTime,etTime,etLocation,etPrice,etCapacity,etURL,etDescription;
    String eventName,date,time,endTime,location,URL,description;
    int organizerID, price,capacity, eventId;
    SPDatabaseHelper spdh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);
        spdh = SPDatabaseHelper.getInstance(this);
        etEventName = (EditText) findViewById(R.id.etEventName);
        etDate = (EditText) findViewById(R.id.etDate);
        etTime = (EditText) findViewById(R.id.etTime);
        etEndTime = (EditText) findViewById(R.id.etEndTime);
        etLocation = (EditText) findViewById(R.id.etLocation);
        etCapacity = (EditText) findViewById(R.id.etCapacity);
        etPrice = (EditText) findViewById(R.id.etPrice);
        etURL = (EditText) findViewById(R.id.etURL);
        etDescription = (EditText) findViewById(R.id.etDescription);
        Create = (Button) findViewById(R.id.button);
        Create.setOnClickListener(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        System.out.println(getIntent().getExtras().getInt("edit"));
        if (getIntent().getExtras().getInt("edit")==1){
            eventId = getIntent().getExtras().getInt("eventId");
            Event current = spdh.queryEvent(eventId);
            etEventName.setText(current.getEventName());
            etDate.setText(current.getDate());
            etTime.setText(current.getTime());
            etEndTime.setText(current.getEndTime());
            etLocation.setText(current.getLocation());
            etCapacity.setText(String.valueOf(current.getCapacity()));
            etPrice.setText(String.valueOf(current.getPrice()));
            etURL.setText(current.getDescription());
            etDescription.setText(current.getDescription());
        }
    }

    @Override
    public void onClick(View v) {



        if(v.getId() == R.id.button) {
            Toast.makeText(NewEventActivity.this, "Submitting", Toast.LENGTH_SHORT).show();
            //Intent intent = new Intent(this, Db_connection.class);
            //startActivity(intent);
            organizerID = ThisUser.getUserID();
            System.out.println(organizerID);
            eventName = etEventName.getText().toString();
            date = etDate.getText().toString();
            time = etTime.getText().toString();
            endTime = etEndTime.getText().toString();
            location = etLocation.getText().toString();
            price = Integer.parseInt(etPrice.getText().toString());
            capacity = Integer.parseInt(etCapacity.getText().toString());
            URL = etURL.getText().toString();
            description = etDescription.getText().toString();
//            if (isValidEventName(eventName) && isValidDate(date) && isValidTime(time) && isValidDescription(description));
            if (getIntent().getExtras().getInt("edit")==1){
                Event event = new Event(eventId,organizerID,URL,eventName,date,time,endTime,location,price,capacity,description);
                spdh.updateEvent(event);
            }
            else {
                Event event = new Event(-1,organizerID,URL,eventName,date,time,endTime,location,price,capacity,description);
                spdh.insertEvent(event);
                spdh.insertOrganize(spdh.queryEventName(eventName).getEventID(), ThisUser.getUserID());
            }
//            System.out.println(spdh.queryEvent(1).getDescription());
            finish();
            startActivity(new Intent(this, SlidingMenu.class));
        }

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
