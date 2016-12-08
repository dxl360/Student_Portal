/**
 * Created by XiaoyingJi on 2016/10/26.
 */
package com.example.duanli.student_portal;
import android.widget.TextView;

import com.example.duanli.student_portal.BuildConfig;
import com.example.duanli.student_portal.EventDetailActivity;
import com.example.duanli.student_portal.ItemDetailActivity;
import com.example.duanli.student_portal.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
//import org.robolectric.Robolectric;
//import org.robolectric.RobolectricTestRunner;
//import org.robolectric.annotation.Config;

import java.util.regex.Pattern;


import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


//@RunWith(RobolectricTestRunner.class)
//@Config(constants = BuildConfig.class)

public class DetailTest {

    private  ItemDetailActivity itemActivity;
    private  EventDetailActivity eventActivity;

    @Before
    public void setup() {
//        itemActivity=Robolectric.buildActivity(ItemDetailActivity.class).create().get();
//        eventActivity=Robolectric.buildActivity(EventDetailActivity.class).create().get();
    }

    @Test
    public void initialization_test() {

        TextView itemName = (TextView) itemActivity.findViewById(R.id.tvItemName);
        String value= itemName.getText().toString();
        assertEquals(value,"Mask");

//        TextView itemType = (TextView) itemActivity.findViewById(R.id.tvItemType);
//        value= itemType.getText().toString();
//        assertEquals(value,"Fun");

        TextView itemPrice = (TextView) itemActivity.findViewById(R.id.tvItemPrice);
        value= itemPrice.getText().toString();
        assertEquals(value,"$10");

        TextView itemSeller = (TextView) itemActivity.findViewById(R.id.tvItemSeller);
        value= itemSeller.getText().toString();
        assertEquals(value,"Student Portal Demo Group");

        TextView itemContact = (TextView) itemActivity.findViewById(R.id.tvSellerContact);
        value= itemContact.getText().toString();
        assertEquals(value,"abc@case.edu");

        TextView itemDescription = (TextView) itemActivity.findViewById(R.id.tvItemDescription);
        value= itemDescription.getText().toString();
        assertEquals(value,"@string/demoItemDescription");

        TextView eventName = (TextView) itemActivity.findViewById(R.id.tvEventName);
        value= eventName.getText().toString();
        assertEquals(value,"Halloween Maze");

        TextView eventLocation = (TextView) itemActivity.findViewById(R.id.tvEventLocation);
        value= eventLocation.getText().toString();
        assertEquals(value,"Nord Hull");

        TextView eventOrganizer = (TextView) itemActivity.findViewById(R.id.tvEventOrganizer);
        value= eventOrganizer.getText().toString();
        assertEquals(value,"Student Portal Demo Group");

        TextView eventTimeDate = (TextView) itemActivity.findViewById(R.id.tvEventTimeDate);
        value= eventTimeDate.getText().toString();
        assertEquals(value,"Monday Oct.31st 10:00pm");

        TextView eventCapacity = (TextView) itemActivity.findViewById(R.id.tvEventCapacity);
        value= eventCapacity.getText().toString();
        assertEquals(value,"20");

        TextView eventDescription = (TextView) itemActivity.findViewById(R.id.tvEventDescription);
        value= eventDescription.getText().toString();
        assertEquals(value,"@string/demoEventDescription");

        TextView eventHashTag = (TextView) itemActivity.findViewById(R.id.tvEventHashTag);
        value= eventHashTag.getText().toString();
        assertEquals(value,"#Halloween #Maze #Horror");
    }

}
