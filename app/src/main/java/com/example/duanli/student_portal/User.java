package com.example.duanli.student_portal;

/**
 * Created by XiaoyingJi on 2016/11/17.
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
    private int contact;

    public User() {super();}

    public User(int userID, String username, String password)
    {
        super();
        this.userID=userID;
        this.userName=username;
        this.password=password;
    }

    public User(int ID, String username, String password, int gender, String major, String email, double rate, int numberRate, int contact)
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

    public String getUserName(){return this.userName;}

    public String getPassword(){return this.password;}

    public int getUserID() {return this.userID;}

    public int getGender() {return gender;}

    public String getMajor() {return major;}

    public String getEmail() {return email;}

    public double getRate(){return rate;}

    public int getNumberRate(){return numberRate;}

    public int getContact(){return contact;}

    public boolean verifyPassword(String input)
    {
        if (input.equals(password))
            return true;
        else
            return false;
    }

    public boolean setPassword(String input)
    {
        if (this.password.equals(input))
        {
            //indicating the orignal password is same as new one
            return false;
        }
        else
        {
            this.password=input;
            return true;
        }
    }

    public boolean setGender(int input)
    {
        InputValidator va=new InputValidator();
        if (va.isValidGender(input))
        {
            this.gender=input;
            return true;
        }
        return false;
    }

    public void setMajor(String input) {this.major=input;}

    public void setEmail(String input) {this.email=input;}

    public double computeRate(double x)
    {
        double totalRate=this.numberRate*this.rate+x;
        double newRate=totalRate/(this.numberRate++);
        return newRate;
    }

    public void updateNOR() {this.numberRate++;}





}