package com.example.duanli.student_portal;

import java.io.Serializable;

/**
 * Created by duanli on 10/23/16.
 */

public class Event implements Serializable {

    private int eventID= -1;
    private String organizerName="";
    private String eventName="";
    private String posterURL="";
    private String date="";
    private String time="";
    private String location="";
    private int price=0;
    private int capacity=0;
    private String description="";


    public String getOrganizerNameName() {
        return organizerName;
    }
    public void setOrganizerNameName(String organizerName) {
        this.organizerName = organizerName;
    }

    public int getEventID() {return eventID;}

    public String getItemName() {
        return eventName;
    }
    public void setItemName(String eventName) {
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

    public int getPrice() {
        return price;
    }
    public void setPrice(int price) { this.price = price;}

    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getPosterURL(){ return posterURL;}
    public void setPosterURL(String posterURL) {this.posterURL=posterURL;}

    public Event(){}

    public Event(String OrganizerName,String EventName,String Date,String Time,String Location,int Price,int Capacity,String Description) {
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
