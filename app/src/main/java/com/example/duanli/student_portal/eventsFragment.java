package com.example.duanli.student_portal;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 *  @author Chris Tsuei
 * Event Fragment which will hold a preview of event items
 * called by the main activity fragment for display on the homepage
 */
public class eventsFragment extends Fragment {

    FloatingActionButton addEvent = null;

    // empty constructor
    public eventsFragment() {}

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
                getActivity().startActivity(addEvent);
            }
        });
        // Inflate the layout for this fragment
        return rootView;
    }


}
