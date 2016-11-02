package com.example.duanli.student_portal;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)

//Test the EventActivity Class. This class contains the following method, including method1, method2, method3...
//Test onCreate() method.
//Test0
//(insert your code here)
////Test1
//(insert your code here)
//Test many

public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.duanli.student_portal", appContext.getPackageName());


    }



}
