package com.example.duanli.student_portal;

/**
 * Created by tianlinlu on 16/10/27.
 */

import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.widget.Button;
import org.junit.Test;

public class ItemListViewTest extends ActivityUnitTestCase<ItemListViewActivity> {

    private Intent mMainIntent;


    public ItemListViewTest() {
        super(ItemListViewActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mMainIntent = new Intent(Intent.ACTION_MAIN);
    }

    @Test
    public void testButton () {
        ItemListViewActivity activity = startActivity(mMainIntent, null, null);
        Button buttonCreate = (Button)activity.findViewById(com.example.duanli.student_portal.R.id.button);
        buttonCreate.performClick();
        Intent i = getStartedActivityIntent();
        assertNotNull(i);
        //assertTrue(isFinishCalled());
    }