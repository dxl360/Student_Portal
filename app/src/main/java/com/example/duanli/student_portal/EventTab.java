package com.example.duanli.student_portal;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.os.Bundle;

public class EventTab extends Fragment implements View.OnClickListener{

    TextView joined, interested, started;
    Bundle bundle;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.events_tab,container,false);
        joined = (TextView) v.findViewById(R.id.eventJoined);
        joined.setOnClickListener(this);
        interested = (TextView) v.findViewById(R.id.eventInterested);
        interested.setOnClickListener(this);
        started = (TextView) v.findViewById(R.id.eventStarted);
        started.setOnClickListener(this);
        return v;
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.image1 || v.getId() == R.id.eventJoined) {
            EventListViewActivity fragment = new EventListViewActivity();
            bundle = new Bundle();
            bundle.putInt("case", 1);
            fragment.setArguments(bundle);
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frag_container, fragment);
            fragmentTransaction.commit();
        }
        else if (v.getId() == R.id.image2 || v.getId() == R.id.eventInterested) {
            EventListViewActivity fragment = new EventListViewActivity();
            bundle = new Bundle();
            bundle.putInt("case", 2);
            fragment.setArguments(bundle);
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frag_container, fragment);
            fragmentTransaction.commit();
        }
        else if (v.getId() == R.id.image3 || v.getId() == R.id.eventStarted) {
            EventListViewActivity fragment = new EventListViewActivity();
            bundle = new Bundle();
            bundle.putInt("case", 3);
            fragment.setArguments(bundle);
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frag_container, fragment);
            fragmentTransaction.commit();
        }
    }
}