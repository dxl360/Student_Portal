package com.example.duanli.student_portal;

public class ThisUser{

    private ThisUser() {}

    private static int userid;

    public static int getUserID() {
        return userid;
    }

    public static void setUserID (int id){
        userid = id;
    }

}