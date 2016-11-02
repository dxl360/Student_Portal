package com.example.duanli.student_portal;

import org.junit.Test;
import android.test.ActivityUnitTestCase;
import android.content.Intent;
import android.widget.Button;
public class CreateEventTest extends ActivityUnitTestCase<NewEventActivity> {

    private Intent mMainIntent;


    public CreateEventTest() {
        super(NewEventActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mMainIntent = new Intent(Intent.ACTION_MAIN);
    }

    @Test
    public void testButton () {
        NewEventActivity activity = startActivity(mMainIntent, null, null);
        Button buttonCreate = (Button)activity.findViewById(com.example.duanli.student_portal.R.id.button);
        buttonCreate.performClick();
        Intent i = getStartedActivityIntent();
        assertNotNull(i);
        //assertTrue(isFinishCalled());
    }
}