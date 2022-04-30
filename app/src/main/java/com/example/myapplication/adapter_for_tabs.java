package com.example.myapplication;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class adapter_for_tabs extends FragmentStatePagerAdapter {
    int counttabs;

    public adapter_for_tabs(FragmentManager fm) {
        super(fm);
    }

    public adapter_for_tabs(FragmentManager fm, int counttabs) {
        super(fm);
        this.counttabs = counttabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {

            case 0:
                return new sign_in_fragment();
            case 1:
                return new Sign_up_fragment();


            default:
                return null;

        }

    }

    @Override
    public int getCount() {
        return counttabs;
    }
}
