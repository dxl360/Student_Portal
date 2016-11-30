package com.example.duanli.student_portal;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author Chris Tsuei
 * Exchange Fragment which will hold a preview of exchange items
 * called by the main activity fragment for display on the homepage
 */
public class ExchangeFragment extends Fragment {

    FloatingActionButton addItem = null;

    // empty constructor
    public ExchangeFragment() {}

    /**
     * Method that processes the xml file and pulls/pushes the changes to the screen
     * @param inflater LayoutInflater that will inflate fragment view
     * @param container view the fragment's UI atached to
     * @param savedInstanceState fragment being reconstructed from a previous state
     * @return what the screen should look  like
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_exchange, container, false);

        // button interactions (click the plus button)
        addItem = (FloatingActionButton) rootView.findViewById(R.id.fabAddItem);
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addItem = new Intent(getActivity(), NewItemActivity.class);
                getActivity().startActivity(addItem);
            }
        });

        // Inflate the layout for this fragment
        return rootView;
    }
}
