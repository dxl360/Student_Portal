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

/**
 * @author Chris Tsuei
 * Exchange Fragment which will hold a preview of exchange items
 * called by the main activity fragment for display on the homepage
 */
public class ExchangeFragment extends Fragment {

    FloatingActionButton addItem = null;

    ImageButton left = null;
    ImageButton right = null;
    int items = 0;
    int current = 0;

    String nameScroll [];
    String descriptionScroll[];

    TextView Title = null;

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
                addItem.putExtra("edit", 0);
                getActivity().startActivity(addItem);
            }
        });

        Title = (TextView) rootView.findViewById(R.id.topItem);

        nameScroll = new String[4];
        descriptionScroll = new String[4];
        items = 0;

        topEvents();
        left = (ImageButton) rootView.findViewById(R.id.left_nav);
        right = (ImageButton) rootView.findViewById(R.id.right_nav);

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                left();
            }
        });

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                right();
            }
        });
        // Inflate the layout for this fragment
        return rootView;
    }

    public void topEvents() {

        for(int i = 0; i < 4; i++) {

            this.nameScroll[i] = Integer.toString(i);
            this.descriptionScroll[i] = new String("desc" + i);
        }
        items = 4;
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
