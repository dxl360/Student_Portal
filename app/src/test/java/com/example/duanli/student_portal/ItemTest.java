//package com.example.duanli.student_portal;
//
//import org.junit.Test;
//import java.util.regex.Pattern;
//import static org.junit.Assert.*;
//
//public class ItemTest {
//
//    Item test = new Item();
//    Item test1 = new Item(null, null, null, null, null);
//    @Test
//    public void getSellerNameTest(){
//        //test true
//        String case1 = "Mark James";
//        test.setSellerName(case1);
//        test1.setSellerName(case1);
//        assertEquals(test.getSellerName(), case1);
//        assertEquals(test1.getSellerName(), case1);
//        //test empty
//        String case2 = "";
//        test.setSellerName(case2);
//        test1.setSellerName(case2);
//        assertEquals(test.getSellerName(), case2);
//        assertEquals(test1.getSellerName(), case2);
//    }
//    @Test
//    public void getItemNameTest(){
//        //test true
//        String case1 = "Algorithm Textbook";
//        test.setItemName(case1);
//        assertEquals(test.getItemName(), case1);
//        //test empty
//        String case2 = "";
//        test.setItemName(case2);
//        assertEquals(test.getItemName(), case2);
//    }
//    @Test
//    public void getPriceTest(){
//        //test true
//        String case1 = "20";
//        test.setPrice(case1);
//        assertEquals(test.getPrice(), case1);
//        //test empty
//        String case2 = "";
//        test.setPrice(case2);
//        assertEquals(test.getPrice(), case2);
//    }
//    @Test
//    public void getContactTest(){
//        //test true
//        String case1 = "2165563111";
//        test.setContact(case1);
//        assertEquals(test.getContact(), case1);
//        //test empty
//        String case2 = "";
//        test.setContact(case2);
//        assertEquals(test.getContact(), case2);
//    }
//    @Test
//    public void getDescriptionTest(){
//        //test true
//        String case1 = "This is a description.";
//        test.setDescription(case1);
//        assertEquals(test.getDescription(), case1);
//        //test empty
//        String case2 = "";
//        test.setDescription(case2);
//        assertEquals(test.getDescription(), case2);
//    }
//
//}