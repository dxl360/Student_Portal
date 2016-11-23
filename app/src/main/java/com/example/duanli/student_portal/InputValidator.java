package com.example.duanli.student_portal;

import android.content.Context;

/**
 * Created by XiaoyingJi on 2016/11/19.
 */
//The class validates whether the user input meets the requirment


public class InputValidator{

    //validates the username
    public boolean isValidUsername(String input)
    {
        //if the username exceeds maximum length
        //Only contains alphanumeric characters, underscore and dot.
        //Underscore and dot can't be at the end or start of a username (e.g _username / username_ / .username / username.).
        //Underscore and dot can't be next to each other (e.g user_.name).
        //Underscore or dot can't be used multiple times in a row (e.g user__name / user..name).name
        //Number of characters must be between 5 to 20.
        String USERNAME_REGEX="^(?=.{5,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$";
        if (!input.equals("")) {
            if (input.matches(USERNAME_REGEX)) {
                return true;}}
        return false;
    }

    //Gender must be an integer value between 0 to 4
    //0: not provided; 1:Male; 2:Female; 3:Other
    public boolean isValidGender(int input)
    {
        if ((input<0) || (input>=4)) {
            return false;}
        return true;
    }


    //email has to contain @
    //Underscore and dot can't be at the end or start of a email address
    //Underscore and dot can't be next to each other (e.g user_.name).
    //Underscore or dot can't be used multiple times in a row (e.g user__name / user..name).name
    public static boolean isValidEmail(String s) {
        String EMAIL_REGEX = "^(?![_.])[a-zA-Z0-9._](?!.*[_.]{2})(.*)([@]{1})(.{1,})(\\.)(.{1,})+(?<![_.])$";
        if (!s.equals("")) {
            if (s.matches(EMAIL_REGEX))
                return true;}
        return false;
    }

    //password length should between 8 and 20
    //at least 1 number, 1 lower case letter, 1 upper case letter
    public static boolean isValidPassword(String s){
        String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,20}$";
        if (!s.equals("")) {
            if (s.matches(PASSWORD_REGEX)) {
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

    public static boolean isValidContact(String s){
        if (s.equals("")){return false;}
        //validate phone numbers of format "1234567890"
        if (s.matches("\\d{10}")) return true;
            //validating phone number with -, . or spaces
        else if(s.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) return true;
            //validating phone number with extension length from 3 to 5
        else if(s.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}")) return true;
            //validating phone number where area code is in braces ()
        else if(s.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}")) return true;
            //return false if nothing matches the input
        else return false;
    }


    public static boolean isValidRate(double x)
    {
        if (x<0||x>5)
        {
            return false;
        }
        return true;
    }

    public static boolean isValidName(String s)
    {
        String ITEMNAME_REGEX="^(?=.{5,20}$)[a-zA-Z0-9]+$";
        if (!s.equals("")) {
            if (s.matches(ITEMNAME_REGEX)) {
                return true;}}
        return false;
    }

    public static boolean isValidURL(String s)
    {
        String URL_REGEX = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
        if (!s.equals("")) {
            if (s.matches(URL_REGEX)) {
                return true;}}
        return false;
    }








}
