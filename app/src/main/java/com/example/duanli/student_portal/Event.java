package com.example.duanli.student_portal;

import java.io.Serializable;

/**
 * Created by duanli on 10/23/16.
 */

public class Event implements Serializable {

//    private int eventID;
    private String organizerName;
    private String eventName;
    private String date;
    private String time;
    private String location;
    private String price;
    private String capacity;
    private String description;

//    public int getEventId() { return eventID; }

    public String getOrganizerName() {
        return organizerName;
    }
    public void setOrganizerName(String organizerName) {
        this.organizerName = organizerName;
    }

    public String getEventName() {
        return eventName;
    }
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public String getPrice() {
        return price;
    }
    public void setPrice(String price) { this.price = price;
    }

    public String getCapacity() {
        return capacity;
    }
    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Event(){}

    public Event(String OrganizerName,String EventName,String Date,String Time,String Location,String Price,String Capacity,String Description) {
        organizerName = OrganizerName;
        eventName = EventName;
        date = Date;
        time = Time;
        location = Location;
        price = Price;
        capacity = Capacity;
        description = Description;
    }

    public boolean addEvent(){
//        DatabaseManager DbMan = DatabaseManager.getInstance();
//        DbMan.addEvent(organizerName,eventName,date,time,capacity,location,description);
        return true;
    }

}
