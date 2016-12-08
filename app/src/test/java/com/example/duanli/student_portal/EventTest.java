package com.example.duanli.student_portal;

import org.junit.Test;
import java.util.regex.Pattern;
import static org.junit.Assert.*;

public class EventTest {

    Event test = new Event();
    Event test1 = new Event(0, 0, null, null, null, null, 0, 0, null);
    @Test
    public void getOrganizerIDTest(){
        //test true
        int case1 = 1;
        test.setOrganizerID(case1);
        test1.setOrganizerID(case1);
        assertEquals(test.getOrganizerID(), case1);
        assertEquals(test1.getOrganizerID(), case1);
        //test false
        int case2 = 2;
        test.setOrganizerID(case2);
        test1.setOrganizerID(case2);
        assertEquals(test.getOrganizerID(), case2);
        assertEquals(test1.getOrganizerID(), case2);
    }
    @Test
    public void getEventIDTest(){
        //test true
        int case1 = 1;
        test.setEventID(case1);
        test1.setEventID(case1);
        assertEquals(test.getEventID(), case1);
        assertEquals(test1.getEventID(), case1);
        //test false
        int case2 = -1;
        test.setEventID(case2);
        test1.setEventID(case2);
        assertEquals(test.getEventID(), case2);
        assertEquals(test1.getEventID(), case2);
    }
    @Test
    public void getEventNameTest(){
        //test true
        String case1 = "Thanksgiving Party";
        test.setEventName(case1);
        assertEquals(test.getEventName(), case1);
        //test empty
        String case2 = "";
        test.setEventName(case2);
        assertEquals(test.getEventName(), case2);
    }
    @Test
    public void getDateTest(){
        //test true
        String case1 = "2016-10-10";
        test.setDate(case1);
        assertEquals(test.getDate(), case1);
        //test empty
        String case2 = "";
        test.setDate(case2);
        assertEquals(test.getDate(), case2);
    }
    @Test
    public void getTimeTest(){
        //test true
        String case1 = "10:00";
        test.setTime(case1);
        assertEquals(test.getTime(), case1);
        //test empty
        String case2 = "";
        test.setTime(case2);
        assertEquals(test.getTime(), case2);
    }
    @Test
    public void getLocationTest(){
        //test true
        String case1 = "Nord 400";
        test.setLocation(case1);
        assertEquals(test.getLocation(), case1);
        //test empty
        String case2 = "";
        test.setLocation(case2);
        assertEquals(test.getLocation(), case2);
    }
    @Test
    public void getPriceTest(){
        //test true
        int case1 = 20;
        test.setPrice(case1);
        assertEquals(test.getPrice(), case1);
        //test false
        int case2 = 0;
        test.setPrice(case2);
        assertEquals(test.getPrice(), case2);
    }
    @Test
    public void getCapacityTest(){
        //test true
        int case1 = 100;
        test.setCapacity(case1);
        assertEquals(test.getCapacity(), case1);
        //test false
        int case2 = 0;
        test.setCapacity(case2);
        assertEquals(test.getCapacity(), case2);
    }
    @Test
    public void getDescriptionTest(){
        //test true
        String case1 = "This is a description.";
        test.setDescription(case1);
        assertEquals(test.getDescription(), case1);
        //test empty
        String case2 = "";
        test.setDescription(case2);
        assertEquals(test.getDescription(), case2);
    }

}