package com.example.duanli.student_portal;

import android.content.Context;
import android.test.ServiceTestCase;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;

import java.lang.reflect.Method;
import java.util.regex.Pattern;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;


public class RegisterActivityTest {

    private Context getTestContext()
    {
        try
        {
            Method getTestContext = ServiceTestCase.class.getMethod("getTestContext");
            return (Context) getTestContext.invoke(this);
        }
        catch (final Exception exception)
        {
            exception.printStackTrace();
            return null;
        }
    }

    @Test
    public void ValidRegistrationTest() {
        RegisterActivity ra = new RegisterActivity();

        //Test false

        //test password does not match confirmation password
        ra.saveUser("xx@case.edu", "bb", "cc", true);
        assertThat(ra.success, is(false));
        //test invalid password
        ra.saveUser("fff@case.edu", "bb", "bb", true);
        assertThat(ra.success, is(false));
        //test invalid username
        ra.saveUser("fff", "bb", "bb", true);
        assertThat(ra.success, is(false));

        //Test empty

        //test empty username
        ra.saveUser("", "bb", "bb", true);
        assertThat(ra.success, is(false));
        //test empty password
        ra.saveUser("fff@case.edu", "","", true);
        assertThat(ra.success, is(false));

        //Test true

        //test valid password and username
        //length 8-20, at least 1 uppercase, 1 lowercase, 1 number
        ra.saveUser("fff@case.edu", "Abc123456", "Abc123456", true);
        assertThat(ra.success, is(true));
    }
}