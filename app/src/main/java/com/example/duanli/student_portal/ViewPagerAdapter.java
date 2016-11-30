package com.example.duanli.student_portal;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.content.Context;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    final int PAGE_COUNT = 2;
    private String tabTitles[] = new String[] { "Events", "Exchange"};
    private Context context;

    public ViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0) {
            EventTab tab1 = new EventTab();
            return tab1;
        }
        else {
            ExchangeTab tab2 = new ExchangeTab();
            return tab2;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}