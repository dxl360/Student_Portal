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
    public void emailValidator_CorrectEmailSimple_ReturnsTrue() {
        RegisterActivity ra = new RegisterActivity();
        ra.saveUser("xx@case.edu", "bb", "cc", true);
        assertThat(ra.success, is(false));
        ra.saveUser("fff@case.edu", "ff", "ff", true);
        assertThat(ra.success, is(true));
        ra.saveUser("", "vv", "vv", true);
        assertThat(ra.success, is(false));
    }
}