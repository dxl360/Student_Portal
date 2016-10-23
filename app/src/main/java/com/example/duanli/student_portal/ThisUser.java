package com.example.duanli.student_portal;

/**
 * Created by duanli on 10/23/16.
 */

public class ThisUser {
    static public String name, username, reservedItem,joinedEvent;

    public ThisUser (String name, String username, String reservedItem,String joinedEvent){
        this.name = name;
        this.username = username;
        this.reservedItem = reservedItem;
        this.joinedEvent = joinedEvent;
    }
}