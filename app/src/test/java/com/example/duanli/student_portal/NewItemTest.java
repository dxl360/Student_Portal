package com.example.duanli.student_portal;

import org.junit.Test;
import java.util.regex.Pattern;
import static org.junit.Assert.*;

public class NewItemTest {

    NewItemActivity test = new NewItemActivity();

    @Test
    public void isValidSellerNameTest() {
        //test empty
        String case1 = "";
        assertTrue(!test.isValidSellerName(case1));
        //test boundary
        String case2 = "asdfasldfakjsdlfajdslkafjlsdjfadslkfjalsdkfjlasdjfalsdjfasldjfalsdjfasdlkjfajlsdkflajsdfkaldjfa";
        assertTrue(!test.isValidSellerName(case2));
        //test wrong character
        String case3 = ";;d.;as;d,f";
        assertTrue(!test.isValidSellerName(case3));
        //test true
        String case4 = "Michael";
        assertTrue(test.isValidSellerName(case4));
    }
    @Test
    public void isValidItemNameTest() {
        //test empty
        String case1 = "";
        assertTrue(!test.isValidItemName(case1));
        //test boundary
        String case2 = "asdfasldfakjsdlfajdslkafjlsdjfadslkfjalsdkfjlasdjfalsdjfasldjfalsdjfasdlkjfajlsdkflajsdfkaldjfa";
        assertTrue(!test.isValidItemName(case2));
        //test wrong character
        String case3 = ";;d.;as;d,f";
        assertTrue(!test.isValidItemName(case3));
        //test true
        String case4 = "Algorithm";
        assertTrue(test.isValidItemName(case4));
    }
    @Test
    public void isValidContactTest() {
        //test empty
        String case1 = "";
        assertTrue(!test.isValidContact(case1));
        //test letters
        String case2 = "a234567890";
        assertTrue(!test.isValidContact(case2));
        //test format1
        String case3 = "1234567890";
        assertTrue(test.isValidContact(case3));
        //test format2
        String case4 = "123-456-7890";
        assertTrue(test.isValidContact(case4));
        //test format3
        String case6 = "123-456-7890 ext0123";
        assertTrue(test.isValidContact(case6));
        //test format4
        String case5 = "(123)-234-8900";
        assertTrue(test.isValidContact(case5));
        //test boundary
        String case7 = "123456789";
        assertTrue(!test.isValidContact(case7));
        String case8 = "12345689121";
        assertTrue(!test.isValidContact(case8));
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