//import android.widget.TextView;
//
//import com.example.duanli.student_portal.BuildConfig;
//import com.example.duanli.student_portal.EventDetailActivity;
//import com.example.duanli.student_portal.ItemDetailActivity;
//import com.example.duanli.student_portal.R;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.robolectric.Robolectric;
//import org.robolectric.RobolectricTestRunner;
//import org.robolectric.annotation.Config;
//
//import java.util.regex.Pattern;
//
//
//import static junit.framework.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertThat;
//import static org.junit.Assert.assertTrue;
//import static org.junit.Assert.*;
//
//
//
//public void initialization_test() {
//
//        TextView itemName = (TextView) itemActivity.findViewById(R.id.tvItemName);
//        String value= itemName.getText().toString();
//        assertEquals(value,"Mask");
//
//        TextView itemType = (TextView) itemActivity.findViewById(R.id.tvItemType);
//        value= itemType.getText().toString();
//        assertEquals(value,"Fun");
//
//        TextView itemPrice = (TextView) itemActivity.findViewById(R.id.tvItemPrice);
//        value= itemPrice.getText().toString();
//        assertEquals(value,"$10");
//
//        TextView itemSeller = (TextView) itemActivity.findViewById(R.id.tvItemSeller);
//        value= itemSeller.getText().toString();
//        assertEquals(value,"Student Portal Demo Group");
//
//        TextView itemContact = (TextView) itemActivity.findViewById(R.id.tvSellerContact);
//        value= itemContact.getText().toString();
//        assertEquals(value,"abc@case.edu");
//
//        TextView itemDescription = (TextView) itemActivity.findViewById(R.id.tvItemDescription);
//        value= itemDescription.getText().toString();
//        assertEquals(value,"@string/demoItemDescription");
//
//        TextView eventName = (TextView) itemActivity.findViewById(R.id.tvEventName);
//        value= eventName.getText().toString();
//        assertEquals(value,"Halloween Maze");
//
//        TextView eventLocation = (TextView) itemActivity.findViewById(R.id.tvEventLocation);
//        value= eventLocation.getText().toString();
//        assertEquals(value,"Nord Hull");
//
//        TextView eventOrganizer = (TextView) itemActivity.findViewById(R.id.tvEventOrganizer);
//        value= eventOrganizer.getText().toString();
//        assertEquals(value,"Student Portal Demo Group");
//
//        TextView eventTimeDate = (TextView) itemActivity.findViewById(R.id.tvEventTimeDate);
//        value= eventTimeDate.getText().toString();
//        assertEquals(value,"Monday Oct.31st 10:00pm");
//
//        TextView eventCapacity = (TextView) itemActivity.findViewById(R.id.tvEventCapacity);
//        value= eventCapacity.getText().toString();
//        assertEquals(value,"20");
//
//        TextView eventDescription = (TextView) itemActivity.findViewById(R.id.tvEventDescription);
//        value= eventDescription.getText().toString();
//        assertEquals(value,"@string/demoEventDescription");
//
//        TextView eventHashTag = (TextView) itemActivity.findViewById(R.id.tvEventHashTag);
//        value= eventHashTag.getText().toString();
//        assertEquals(value,"#Halloween #Maze #Horror");
//        }
//
//
//}

package com.example.duanli.student_portal;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void loginActivityTest() {

        ViewInteraction appCompatAutoCompleteTextView = onView(
                withId(R.id.email));
        appCompatAutoCompleteTextView.perform(scrollTo(), replaceText("dxl360@case.edu"), closeSoftKeyboard());

        ViewInteraction appCompatEditText = onView(
                withId(R.id.password));
        appCompatEditText.perform(scrollTo(), replaceText("123456"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.email_sign_in_button), withText("Sign in register"),
                        withParent(allOf(withId(R.id.email_login_form),
                                withParent(withId(R.id.login_form))))));
        appCompatButton.perform(scrollTo(), click());

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
