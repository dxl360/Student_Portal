//package com.example.duanli.student_portal;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.design.widget.FloatingActionButton;
//import android.support.v4.app.Fragment;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
///**
// *  @author Chris Tsuei
// * Event Fragment which will hold a preview of event items
// * called by the main activity fragment for display on the homepage
// */
//public class eventsFragment extends Fragment {
//
//    FloatingActionButton addEvent = null;
//
//    // empty constructor
//    public eventsFragment() {}
//
//    /**
//     * Method that processes the xml file and pulls/pushes the changes to the screen
//     * @param inflater LayoutInflater that will inflate fragment view
//     * @param container view the fragment's UI atached to
//     * @param savedInstanceState fragment being reconstructed from a previous state
//     * @return what the screen should look  like
//     */
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View rootView = inflater.inflate(R.layout.fragment_events, container, false);
//
//        // button interactions (click the plus button)
//        addEvent = (FloatingActionButton) rootView.findViewById(R.id.fabAddEvent);
//        addEvent.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent addEvent = new Intent(getActivity(), NewEventActivity.class);
//                addEvent.putExtra("edit", 0);
//                getActivity().startActivity(addEvent);
//            }
//        });
//        // Inflate the layout for this fragment
//        return rootView;
//    }
//
//
//}
package com.example.duanli.student_portal;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.view.MotionEvent;

import java.util.ArrayList;

/**
 *  @author Chris Tsuei
 * Event Fragment which will hold a preview of event items
 * called by the main activity fragment for display on the homepage
 */
public class EventFragment extends Fragment implements View.OnTouchListener{

    FloatingActionButton addEvent = null;
    SPDatabaseHelper spdh;
    ArrayList<Event> cells;
    int size;
    ImageButton left = null;
    ImageButton right = null;
    int items = 0;
    int current = 0;
    String nameScroll [];
    String descriptionScroll[];
    int id[] = null;
    TextView Title = null;

    // empty constructor
    public EventFragment() {}

    /**
     * Method that processes the xml file and pulls/pushes the changes to the screen
     * @param inflater LayoutInflater that will inflate fragment view
     * @param container view the fragment's UI atached to
     * @param savedInstanceState fragment being reconstructed from a previous state
     * @return what the screen should look  like
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_events, container, false);

        // button interactions (click the plus button)
        addEvent = (FloatingActionButton) rootView.findViewById(R.id.fabAddEvent);
        addEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addEvent = new Intent(getActivity(), NewEventActivity.class);
                addEvent.putExtra("edit", 0);
                getActivity().startActivity(addEvent);
            }
        });


        Title = (TextView) rootView.findViewById(R.id.topEvent);

        // Fetch data from DB to display
        spdh = new SPDatabaseHelper(getContext());
        cells = spdh.retrieveEvents(0);
        size = cells.size();
        nameScroll = new String[size];
        descriptionScroll = new String[size];
        id = new int[size];
        items = 0;

        topEvents();
        left = (ImageButton) rootView.findViewById(R.id.left_nav);
        right = (ImageButton) rootView.findViewById(R.id.right_nav);

        left.setOnTouchListener(this);
        right.setOnTouchListener(this);

        Title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewEvent = new Intent(getActivity(), EventDetailActivity.class);
                viewEvent.putExtra("eventId", id[current]);
                startActivity(viewEvent);

            }
        });
        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event)
    {
        if(v == left)
        {
            if(event.getAction() == MotionEvent.ACTION_DOWN)
            {
                left();
                v.setAlpha(1f);
            }
            else
            { v.setAlpha(.5f);}
            return false;
        }
        if(v == right)
        {
            if(event.getAction() == MotionEvent.ACTION_DOWN)
            {
                right();
                v.setAlpha(1f);
            }
            else
            { v.setAlpha(.5f);}
            return false;
        }
        return true;
    }


    public void topEvents() {

        for(int i = 0; i < size; i++) {
            this.nameScroll[i] = cells.get(i).getEventName();
            this.descriptionScroll[i] = cells.get(i).getDate()+" "+cells.get(i).getTime()+"-"+cells.get(i).getEndTime();
            this.id[i] = cells.get(i).getEventID();
        }
        items = size;
        current = 0;

        Title.setText(nameScroll[current] + '\n' + descriptionScroll[current]);

    }

    public void right() {

        if(current < items - 1) {
            current++;
        }
        else { current = 0;}

        Title.setText(nameScroll[current] + '\n' + descriptionScroll[current]);
    }

    public void left() {

        if(current == 0) {
            current = items - 1;
        }
        else {current --;}

        Title.setText(nameScroll[current] + '\n' +descriptionScroll[current]);
    }


}
