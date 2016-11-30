package com.example.duanli.student_portal;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author Chris Tsuei
 * Main page Fragment activity calling its associated xml file
 */
public class MainActivity extends Fragment {

    // empty constructor
    public MainActivity() {}

    /**
     * Method that processes the xml file and pulls/pushes the changes to the screen
     * @param inflater LayoutInflater that will inflate fragment view
     * @param container view the fragment's UI atached to
     * @param savedInstanceState fragment being reconstructed from a previous state
     * @return what the screen should look  like
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_main, container, false);

        // inserting the events fragment into the events frame
        eventsFragment event_frag = new eventsFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction =
                getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.event_frag, event_frag);
        fragmentTransaction.commit();

        // inserting the exchange fragment into the exchange frame
        ExchangeFragment exchange_frag = new ExchangeFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction2 =
                getFragmentManager().beginTransaction();
        fragmentTransaction2.replace(R.id.exchange_frag, exchange_frag);
        fragmentTransaction2.commit();

        return rootView;
    }

}
