package com.example.duanli.student_portal;

import android.content.Context;
//import android.support.test.InstrumentationRegistry;
import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;
import android.test.ServiceTestCase;
import android.content.Context;
import android.test.InstrumentationTestCase;
import android.test.mock.MockContext;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import com.example.duanli.student_portal.SPDatabaseHelper;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by duanli on 12/8/16.
 */

public class DatabaseTest extends AndroidTestCase   {
//    private Context getTestContext()
//    {
//        try
//        {
//            Method getTestContext = ServiceTestCase.class.getMethod("getTestContext");
//            return (Context) getTestContext.invoke(this);
//        }
//        catch (final Exception exception)
//        {
//            exception.printStackTrace();
//            return null;
//        }
//    }


    Context context;
    SPDatabaseHelper db;


    @Before
    public void setUp() throws Exception {
        super.setUp();
        //Context context = InstrumentationRegistry.getTargetContext();
        context = new RenamingDelegatingContext(getContext(), "test_");
        //db = new SPDatabaseHelper(context);
        db = SPDatabaseHelper.getInstance(context);
        User user1 = new User(-1, "abc123@case.edu", "123456");
        User user2 = new User(-1, "abc456@case.edu", "123456");
//        boolean result = db.insertUser(user1);
//        assertThat(result, is(false));
    }
//
//    @Override
//    public void tearDown() throws Exception {
//        db.close();
//        super.tearDown();
//    }
//    private SPDatabaseHelper db = SPDatabaseHelper.getInstance(context);
    @Test
    public void insertUserTest(){
        User user1 = new User(-1, "abc123@case.edu", "123456");
        User user2 = new User(-1, "abc456@case.edu", "123456");
        boolean result = db.insertUser(user1);
        assertThat(result, is(false));
    }
}
