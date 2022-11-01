package com.example.smartportal;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MyAdapter extends FragmentPagerAdapter {

    private Context myContext;
    int totalTabs;
    String username;

    public MyAdapter(Context context, FragmentManager fm, int totalTabs, String uName) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
        this.username = uName;
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new MidtermFragment(username);
            case 1:
                return new RevisionFragment(username);
            case 2:
                return new TerminalFragment(username);
            default:
                return null;
        }
    }

    // this counts total number of tabs
    @Override
    public int getCount() {
        return totalTabs;
    }
}