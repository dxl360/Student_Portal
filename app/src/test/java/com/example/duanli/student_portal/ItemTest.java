package com.example.duanli.student_portal;

import org.junit.Test;
import java.util.regex.Pattern;
import static org.junit.Assert.*;

public class ItemTest {

    Item test = new Item();
    Item test1 = new Item(0, null, 0, 0, null, null, 0);
    Item test2 = new Item(0, null, 0);
    @Test
    public void getSellerIDTest(){
        //test true
        int case1 = 1;
        test.setSellerID(case1);
        test1.setSellerID(case1);
        test2.setSellerID(case1);
        assertEquals(test.getSellerID(), case1);
        assertEquals(test1.getSellerID(), case1);
        assertEquals(test2.getSellerID(), case1);
        //test false
        int case2 = 0;
        test.setSellerID(case2);
        test1.setSellerID(case2);
        test2.setSellerID(case2);
        assertEquals(test.getSellerID(), case2);
        assertEquals(test1.getSellerID(), case2);
        assertEquals(test2.getSellerID(), case2);
    }
    @Test
    public void getItemIDTest(){
        //test true
        int case1 = 1;
        test.setItemID(case1);
        test1.setItemID(case1);
        assertEquals(test.getItemID(), case1);
        assertEquals(test1.getItemID(), case1);
        //test false
        int case2 = 0;
        test.setItemID(case2);
        test1.setItemID(case2);
        assertEquals(test.getItemID(), case2);
        assertEquals(test1.getItemID(), case2);
    }
    @Test
    public void getItemNameTest(){
        //test true
        String case1 = "Algorithm Textbook";
        test.setItemName(case1);
        assertEquals(test.getItemName(), case1);
        //test empty
        String case2 = "";
        test.setItemName(case2);
        assertEquals(test.getItemName(), case2);
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
    @Test
    public void getStatusTest(){
        //test true
        int case1 = 2;
        test2.setStatus(case1);
        assertEquals(test2.getStatus(), case1);
        //test false
        int case2 = 0;
        test2.setStatus(case2);
        assertEquals(test2.getStatus(), case2);
    }

}