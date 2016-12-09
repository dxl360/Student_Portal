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

import java.util.ArrayList;

/**
 * @author Chris Tsuei
 * Exchange Fragment which will hold a preview of exchange items
 * called by the main activity fragment for display on the homepage
 */
public class ExchangeFragment extends Fragment {

    FloatingActionButton addItem = null;
    SPDatabaseHelper spdh;
    ArrayList<Item> cells;
    int size;
    ImageButton left = null;
    ImageButton right = null;
    int items = 0;
    int current = 0;
    String nameScroll [] = null;
    String descriptionScroll[] = null;
    int id[] = null;
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
        spdh = SPDatabaseHelper.getInstance(this.getContext());
        // button interactions (click the plus button)
        addItem = (FloatingActionButton) rootView.findViewById(R.id.fabAddItem);
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addItem = new Intent(getActivity(), NewItemActivity.class);
                addItem.putExtra("edit",0);
                getActivity().startActivity(addItem);
            }
        });

        Title = (TextView) rootView.findViewById(R.id.topItem);

        // Fetch data from DB to display
        cells = spdh.retrieveItems(0);
        size = cells.size();
        nameScroll = new String[size];
        descriptionScroll = new String[size];
        id = new int[size];
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

        Title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewEvent = new Intent(getActivity(), ItemDetailActivity.class);
                viewEvent.putExtra("itemId", id[current]);
                startActivity(viewEvent);

            }
        });

        // Inflate the layout for this fragment
        return rootView;
    }

    public void topEvents() {

        for(int i = 0; i < size; i++) {
            this.nameScroll[i] = cells.get(i).getItemName();
            this.descriptionScroll[i] = "Price is $"+String.valueOf(cells.get(i).getPrice());
            this.id[i] = cells.get(i).getItemID();
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
