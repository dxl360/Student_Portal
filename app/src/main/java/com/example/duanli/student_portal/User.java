package com.example.duanli.student_portal;

/**
 * Created by duanli on 10/23/16.
 */

public class User {
    private int userID = -1;
    private String userName="";
    private String password="";
    private int gender=0;
    private String major="";
    private String email;
    private double rate=-1;
    private int numberRate=0;
    private String contact;

    public User() {super();}

    public User(int ID, String username, String password)
    {
        super();
        this.userID=ID;
        this.userName=username;
        this.password=password;
    }

    public User(int ID, String username, String password, int gender, String major, String email, double rate, int numberRate, String contact)
    {
        this.userID=ID;
        this.userName=username;
        this.password=password;
        this.gender=gender;
        this.major=major;
        this.email=email;
        this.rate=rate;
        this.numberRate=numberRate;
        this.contact=contact;
    }

    public void setUserName(String userName) {this.userName=userName;}
    public String getUserName(){return this.userName;}

    public void setPassword(String password) {this.password=password;}
    public String getPassword(){return this.password;}

    public void setUserID(int userID) {this.userID=userID;}
    public int getUserID() {return this.userID;}

    public void setGender(int gender) {this.gender=gender;}
    public int getGender() {return gender;}

    public void setMajor(String major) {this.major=major;}
    public String getMajor() {return major;}

    public void setEmail(String email) {this.email=email;}
    public String getEmail() {return email;}

    public void setRate(double rate) {this.rate=rate;}
    public double getRate(){return rate;}

    public void setNumberRate(int numberRate) {this.numberRate=numberRate;}
    public int getNumberRate(){return numberRate;}

    public void setContact(String contact) {this.contact=contact;}
    public String getContact(){return contact;}

//    public boolean verifyPassword(String input)
//    {
//        if (input.equals(password))
//            return true;
//        else
//            return false;
//    }
//
//    public boolean setPassword(String input)
//    {
//        if (this.password.equals(input))
//        {
//            //indicating the orignal password is same as new one
//            return false;
//        }
//        else
//        {
//            this.password=input;
//            return true;
//        }
//    }
//
//    public boolean setGender(int input)
//    {
//        Validator va=new Validator();
//        if (va.validateGender(input))
//        {
//            this.gender=input;
//            return true;
//        }
//        return false;
//    }
//
//    public double computeRate(double x)
//    {
//        double totalRate=this.numberRate*this.rate+x;
//        double newRate=totalRate/(this.numberRate++);
//        return newRate;
//    }
//
//    public void updateNOR() {this.numberRate++;}
}