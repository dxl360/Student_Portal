package com.example.duanli.student_portal;

public class ThisUser{

    private ThisUser() {}

    private static int userid;
    private static String username;
    private static String email;

    public static int getUserID() {
        return userid;
    }

    public static void setUserID (int id){
        userid = id;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername (String name){
        username = name;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail (String myemail){
        email = myemail;
    }

}