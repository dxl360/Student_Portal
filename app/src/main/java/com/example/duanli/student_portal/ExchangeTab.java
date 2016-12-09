package com.example.duanli.student_portal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ExchangeTab extends Fragment implements View.OnClickListener {

    TextView bought, watchlisted, sold;
    Bundle bundle;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.exchange_tab,container,false);
        bought = (TextView) v.findViewById(R.id.itemBought);
        bought.setOnClickListener(this);
        watchlisted = (TextView) v.findViewById(R.id.itemWatchlisted);
        watchlisted.setOnClickListener(this);
        sold = (TextView) v.findViewById(R.id.itemSold);
        sold.setOnClickListener(this);
        return v;
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.boughtImage || v.getId() == R.id.itemBought) {
            ItemListViewActivity fragment = new ItemListViewActivity();
            bundle = new Bundle();
            bundle.putInt("case", 1);
            fragment.setArguments(bundle);
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frag_container, fragment);
            fragmentTransaction.commit();
        }
        else if (v.getId() == R.id.watchlistImage || v.getId() == R.id.itemWatchlisted) {
            ItemListViewActivity fragment = new ItemListViewActivity();
            bundle = new Bundle();
            bundle.putInt("case", 2);
            fragment.setArguments(bundle);
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frag_container, fragment);
            fragmentTransaction.commit();
        }
        else if (v.getId() == R.id.soldImage || v.getId() == R.id.itemSold) {
            ItemListViewActivity fragment = new ItemListViewActivity();
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