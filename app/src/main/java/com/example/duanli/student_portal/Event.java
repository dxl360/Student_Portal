package com.example.duanli.student_portal;

import java.io.Serializable;

/**
 * Created by duanli on 10/23/16.
 */

public class Event implements Serializable {

    private int eventID= -1;
    private int organizerID=0;
    private String eventName="";
    private String eventPictureUrl="";
    private String date="";
    private String time="";
    private String endTime="";
    private String location="";
    private int price=0;
    private int capacity=0;
    private String description="";


    public int getOrganizerID() {
        return organizerID;
    }
    public void setOrganizerID(int organizerID) {
        this.organizerID = organizerID;
    }

    public int getEventID() {return eventID;}
    public void setEventID(int eventID) {
        this.eventID = eventID;
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

    public String getEndTime() {
        return endTime;
    }
    public void setEndTime(String endTime) {
        this.endTime = endTime;
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

    public String getEventPictureUrl(){ return eventPictureUrl;}
    public void setEventPictureUrl(String posterURL) {this.eventPictureUrl=eventPictureUrl;}

    public Event(){}

    public Event(int EventID, int OrganizerID, String EventPictureUrl, String EventName,String Date,String Time, String EndTime, String Location,int Price,int Capacity,String Description) {
        eventID=EventID;
        organizerID = OrganizerID;
        eventPictureUrl = EventPictureUrl;
        eventName = EventName;
        date = Date;
        time = Time;
        endTime = EndTime;
        location = Location;
        price = Price;
        capacity = Capacity;
        description = Description;
    }

}