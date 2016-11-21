package com.example.duanli.student_portal;

import org.junit.Test;
import java.util.regex.Pattern;
import static org.junit.Assert.*;

public class NewEventTest{

    NewEventActivity test = new NewEventActivity();

    @Test
    public void isValidOrganizerNameTest() {
        //test empty
        String case1 = "";
        assertTrue(!test.isValidOrganizerName(case1));
        //test boundary
        String case2 = "asdfasldfakjsdlfajdslkafjlsdjfadslkfjalsdkfjlasdjfalsdjfasldjfalsdjfasdlkjfajlsdkflajsdfkaldjfa";
        assertTrue(!test.isValidOrganizerName(case2));
        //test wrong character
        String case3 = ";;d.;as;d,f";
        assertTrue(!test.isValidOrganizerName(case3));
        //test true
        String case4 = "StudentGovernment";
        assertTrue(test.isValidOrganizerName(case4));
    }
    @Test
    public void isValidEventNameTest() {
        //test empty
        String case1 = "";
        assertTrue(!test.isValidEventName(case1));
        //test boundary
        String case2 = "asdfasldfakjsdlfajdslkafjlsdjfadslkfjalsdkfjlasdjfalsdjfasldjfalsdjfasdlkjfajlsdkflajsdfkaldjfa";
        assertTrue(!test.isValidEventName(case2));
        //test wrong character
        String case3 = ";;d.;as;d,f";
        assertTrue(!test.isValidEventName(case3));
        //test true
        String case4 = "ThanksgivingParty";
        assertTrue(test.isValidEventName(case4));
    }
    @Test
    public void isValidDateTest() {
        //test empty
        String case1 = "";
        assertTrue(!test.isValidDate(case1));
        //test out of boundary
        String case2 = "1801-02-01";
        assertTrue(!test.isValidDate(case2));
        String case3 = "2100-09-07";
        assertTrue(!test.isValidDate(case3));
        //test boundary
        String case4 = "2012-13-07";
        assertTrue(!test.isValidDate(case4));
        String case5 = "2012-02-35";
        assertTrue(!test.isValidDate(case5));
        //test wrong format
        String case6 = "02121995";
        assertTrue(!test.isValidDate(case6));
        //test true
        String case7 = "2016-11-20";
        assertTrue(test.isValidDate(case7));
    }

    @Test
    public void isValidTimeTest() {
        //test empty
        String case1 = "";
        assertTrue(!test.isValidTime(case1));
        //test boundary
        String case2 = "27:42";
        assertTrue(!test.isValidTime(case2));
        String case3 = "13:87";
        assertTrue(!test.isValidTime(case3));
        //test wrong format
        String case4 = "10:00 a.m";
        assertTrue(!test.isValidTime(case4));
        //test true
        String case5 = "10:00";
        assertTrue(test.isValidTime(case5));
        String case6 = "17:23";
        assertTrue(test.isValidTime(case6));
    }
    @Test
    public void isValidDescriptionTest() {
        //test empty
        String case1 = "";
        assertTrue(!test.isValidDescription(case1));
        //test boundary
        String case2 = "asdfasldfakjsdlfajdslkafjlsdjfadslkfjalsdkfjlasdjfalsdjfasldjfalsdjfasdlkjfajlsdkflajsdfkaldjfa";
        assertTrue(!test.isValidDescription(case2));
        //test wrong character
        String case3 = ";;d.;as;d,f";
        assertTrue(!test.isValidDescription(case3));
        //test true
        String case4 = "Description";
        assertTrue(test.isValidDescription(case4));
    }
}