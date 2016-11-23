package com.example.duanli.student_portal;

import android.test.suitebuilder.annotation.MediumTest;

import com.example.duanli.student_portal.InputValidator;
import com.example.duanli.student_portal.NewEventActivity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertTrue;

/**
 * Created by XiaoyingJi on 2016/11/19.
 */
@RunWith(JUnit4.class)
@MediumTest
public class InputValidatorTest {

    InputValidator test = new InputValidator();

    /**
     * Created by XiaoyingJi on 2016/11/20.
     */
    @Test
    public void isValidURLTest()
    {
        String case1=""; //test empty
        assertTrue(!test.isValidURL(case1));
        String case2="http://google.com";
        assertTrue(test.isValidURL(case2));
        String case3="ht./google.com";
        assertTrue(!test.isValidURL(case3));
    }
    @Test
    public void isValidGenderTest() {
        int case1 = -1;//test out range
        int case5 = 4;
        assertTrue(!test.isValidGender(case1));
        assertTrue(!test.isValidGender(case5));
        int case2 = 0; //test boundary
        int case3 = 3; //test boundary
        int case4 = 1; //test middle
        assertTrue(test.isValidGender(case2));
        assertTrue(test.isValidGender(case3));
        assertTrue(test.isValidGender(case4));
    }
    @Test
    public void isValidEmailTest() {
        String case1 = ""; //test empty
        assertTrue(!test.isValidEmail(case1));
        String case2 = "test"; //test false
        assertTrue(!test.isValidEmail(case2));
        String case3 = "test@case.edu"; //test true
        assertTrue(test.isValidEmail(case3));
        String case4 = "test..@case.edu";//test..
        assertTrue(!test.isValidEmail(case4));
        String case5 = ".test@case.edu"; //test . start
        assertTrue(!test.isValidEmail(case5));
        String case6 = "test@case.edu."; //test. end
        assertTrue(!test.isValidEmail(case6));
        String case7 = "test._@case.edu";//test ._
        assertTrue(!test.isValidEmail(case7));
    }
    @Test
    public void isValidRateTest() {
        double case1 = -1;//test boundary
        double case2 = 6; //test boundary
        double case3 = 2.4; //test true
        assertTrue(!test.isValidRate(case1));
        assertTrue(!test.isValidRate(case2));
        assertTrue(test.isValidRate(case3));
    }
    @Test
    public void isValidUsername() {
        String case1 = ""; //test empty
        assertTrue(!test.isValidUsername(case1));
        String case2 = "abcd"; //test less than 5 characters
        assertTrue(!test.isValidUsername(case2));
        String case3 = "abcdefghiabcdefghijklmnopqr"; //test larger than 20 characters
        assertTrue(!test.isValidUsername(case3));
        String case4 = "abcdefgh."; //test . or _ at end
        String case5 = "abcdefgh_";
        assertTrue(!test.isValidUsername(case4));
        assertTrue(!test.isValidUsername(case5));
        String case6 = ".abcdefgh"; //test . or _ at start
        String case7 = "_abcdefgh";
        assertTrue(!test.isValidUsername(case6));
        assertTrue(!test.isValidUsername(case7));
        String case8 = "ab..cdefgh";//test . twice
        assertTrue(!test.isValidUsername(case8));
        String case9 = "ab_.cdefgh";//test _. together
        assertTrue(!test.isValidUsername(case9));
        String case10 = "ab.cdefgh";//test . once
        assertTrue(test.isValidUsername(case10));
        String case11 = "abcde_fgh";//test _ once
        assertTrue(test.isValidUsername(case11));
        String case12 = "ab.c.cdefgh";//test . twice in different positions
        assertTrue(test.isValidUsername(case12));
    }

    @Test
    public void isValidPasswordTest() {
        String case1 = "";//test empty
        assertTrue(!test.isValidPassword(case1));
        String case2 = "asdfbgh";//test less than 8 characters
        assertTrue(!test.isValidPassword(case2));
        String case3 = "asdfbasdjfasdflkjadfiahsoilejfgh";//test larger than 20 characters
        assertTrue(!test.isValidPassword(case3));
        String case4 = "ask345.134";//test no upper case
        String case5 = "ASK345.112";//test no lower case
        String case6 = "ASKabcdfds";//test no number
        assertTrue(!test.isValidPassword(case4));
        assertTrue(!test.isValidPassword(case5));
        assertTrue(!test.isValidPassword(case6));
        String case7 = "akH1erts";//test boundary
        String case8 = "akH1ertsHK./F1234ab";//test boundary
        assertTrue(test.isValidPassword(case7));
        assertTrue(test.isValidPassword(case8));
    }

    @Test
    public void isValidDateTest() {
        String case1 = "";//test empty
        assertTrue(!test.isValidDate(case1));
        String case2 = "1801-02-01"; //test out of boundary
        String case3 = "2100-09-07"; //test out of boundary
        String case4 = "2012-13-07"; //test boundary
        String case5 = "2012-02-35"; //test boundary
        assertTrue(!test.isValidDate(case2));
        assertTrue(!test.isValidDate(case3));
        assertTrue(!test.isValidDate(case4));
        assertTrue(!test.isValidDate(case5));
        String case6 = "02121995"; //test wrong format
        assertTrue(!test.isValidDate(case6));
        String case7 = "2016-11-20"; //test true
        assertTrue(test.isValidDate(case7));
    }

    @Test
    public void isValidTimeTest() {
        String case1 = "";//test empty
        assertTrue(!test.isValidTime(case1));
        String case2 = "27:42";//test boundary
        String case3 = "13:87";//test boundary
        assertTrue(!test.isValidTime(case2));
        assertTrue(!test.isValidTime(case3));
        String case4 = "10:00 a.m"; //test wrong format
        assertTrue(!test.isValidTime(case4));
        String case5 = "10:00"; //test true
        assertTrue(test.isValidTime(case5));
        String case6 = "17:23"; //test true
        assertTrue(test.isValidTime(case6));
    }

    @Test
    public void isValidContactTest() {
        String case1 = ""; //test empty
        String case2 = "a234567890"; //test letters
        String case3 = "1234567890"; //test format1
        String case4 = "123-456-7890"; //test format2
        String case5 = "(123)-234-8900"; //test format4
        String case6 = "123-456-7890 ext0123"; //test format3
        String case7 = "123456789"; //test boundary
        String case8 = "12345689121"; //test boundary
        assertTrue(!test.isValidContact(case1));
        assertTrue(!test.isValidContact(case2));
        assertTrue(test.isValidContact(case3));
        assertTrue(test.isValidContact(case4));
        assertTrue(test.isValidContact(case5));
        assertTrue(test.isValidContact(case6));
        assertTrue(!test.isValidContact(case7));
        assertTrue(!test.isValidContact(case8));
    }

    @Test
    public void isValidNameTest() {
        String case1 = "";//test empty
        assertTrue(!test.isValidName(case1));
        //test boundary
        String case2 = "asdfasldfakjsdlfajdslkafjlsdjfadslkfjalsdkfjlasdjfalsdjfasldjfalsdjfasdlkjfajlsdkflajsdfkaldjfa";//test boundary
        assertTrue(!test.isValidName(case2));
        String case3 = ";;d.;as;d,f";//test wrong character
        assertTrue(!test.isValidName(case3));
        String case4 = "Validname";//test true
        assertTrue(test.isValidName(case4));
    }


}
