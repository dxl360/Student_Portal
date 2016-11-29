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

public class EventTab extends Fragment implements View.OnClickListener{

    TextView joined;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.events_tab,container,false);
        joined = (TextView) v.findViewById(R.id.eventJoined);
        joined.setOnClickListener(this);
        return v;
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.image1 || v.getId() == R.id.eventJoined) {
            Intent newActivity1 = new Intent(v.getContext(), EventListViewActivity.class);
            newActivity1.putExtra("case", 1);
            startActivity(newActivity1);
        }
    }
}