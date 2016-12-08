package com.example.duanli.student_portal;

import org.junit.Test;

import android.app.Fragment;
import android.test.ActivityUnitTestCase;
import android.content.Intent;
import android.widget.Button;
import android.app.Instrumentation.ActivityMonitor;

public class ManageEventsTest {
    //extends ActivityUnitTestCase<ManageActivity>
//    private Intent mMainIntent;
//
//    public ManageEventsTest() {
//        super(ManageActivity.class);
//    }
//
//    @Override
//    protected void setUp() throws Exception {
//        super.setUp();
//        mMainIntent = new Intent(Intent.ACTION_MAIN);
//    }
//
//    @Test
//    public void testManageEventJoined () {
//        // register next activity, which should be the event tab
//        ActivityMonitor activityMonitor = getInstrumentation().addMonitor(EventTab.class.getName(), null, false);
//
//        // open current activity
//        ManageActivity myActivity = getActivity();
//        final Button button = (Button) myActivity.findViewById(R.id.button);
//        myActivity.runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                // click button and open next activity.
//                button.performClick();
//            }
//        });
//
//        android.app.Activity nextActivity = getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
//        // next activity is opened and captured.
//        assertNotNull(nextActivity);
//        nextActivity.finish();
//    }
}