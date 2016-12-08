package com.example.duanli.student_portal;

import org.junit.Test;
import java.util.regex.Pattern;
import static org.junit.Assert.*;

public class UserTest {

    User test = new User();
    User test1 = new User(0, null, null);
    User test2 = new User(0, null, null, 0, null, null, 0, 0, null);
    @Test
    public void getUserIDTest(){
        //test true
        int case1 = 101;
        test.setUserID(case1);
        test1.setUserID(case1);
        test2.setUserID(case1);
        assertEquals(test.getUserID(), case1);
        assertEquals(test1.getUserID(), case1);
        assertEquals(test2.getUserID(), case1);
        //test empty
        int case2 = 0;
        test.setUserID(case2);
        test1.setUserID(case2);
        test2.setUserID(case2);
        assertEquals(test.getUserID(), case2);
        assertEquals(test1.getUserID(), case2);
        assertEquals(test2.getUserID(), case2);
    }
    @Test
    public void getUserNameTest(){
        //test true
        String case1 = "Mark James";
        test.setUserName(case1);
        assertEquals(test.getUserName(), case1);
        //test empty
        String case2 = "";
        test.setUserName(case2);
        assertEquals(test.getUserName(), case2);
    }
    @Test
    public void getPasswordTest(){
        //test true
        String case1 = "123456";
        test.setPassword(case1);
        assertEquals(test.getPassword(), case1);
        //test empty
        String case2 = "";
        test.setPassword(case2);
        assertEquals(test.getPassword(), case2);
    }
    @Test
    public void getGenderTest(){
        //test true
        int case1 = 1;
        test.setGender(case1);
        assertEquals(test.getGender(), case1);
        //test empty
        int case2 = 2;
        test.setGender(case2);
        assertEquals(test.getGender(), case2);
    }
    @Test
    public void getContactTest(){
        //test true
        String case1 = "2165563111";
        test.setContact(case1);
        assertEquals(test.getContact(), case1);
        //test empty
        String case2 = "";
        test.setContact(case2);
        assertEquals(test.getContact(), case2);
    }
    @Test
    public void getMajorTest(){
        //test true
        String case1 = "Computer Science";
        test.setMajor(case1);
        assertEquals(test.getMajor(), case1);
        //test empty
        String case2 = "";
        test.setMajor(case2);
        assertEquals(test.getMajor(), case2);
    }@Test
    public void getEmailTest(){
        //test true
        String case1 = "abc123@case.edu";
        test.setEmail(case1);
        assertEquals(test.getEmail(), case1);
        //test empty
        String case2 = "@";
        test.setEmail(case2);
        assertEquals(test.getEmail(), case2);
    }
    @Test
    public void getRateTest(){
        //test true
        double case1 = 4.5;
        test.setRate(case1);
        assertEquals((int)test.getRate(), (int)case1);
        //test empty
        int case2 = -1;
        test.setRate(case2);
        assertEquals((int)test.getRate(), (int)case2);
    }@Test
    public void getNumberRateTest(){
        //test true
        int case1 = 100;
        test.setNumberRate(case1);
        assertEquals(test.getNumberRate(), case1);
        //test empty
        int case2 = 0;
        test.setNumberRate(case2);
        assertEquals(test.getNumberRate(), case2);
    }
}