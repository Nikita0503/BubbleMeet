package com.rockstar.bubblemeetapplication.singup;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


public class SignUpFragmentPagerAdapter extends FragmentPagerAdapter {
    public SignUpFragmentPagerAdapter(FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                return new Fragment1();
            case 1:
                return new Fragment1();
            case 2:
                return new Fragment1();
            case 3:
                return new Fragment1();
            case 4:
                return new Fragment1();
            case 5:
                return new Fragment1();
            case 6:
                return new Fragment1();
            case 7:
                return new Fragment1();
            case 8:
                return new Fragment1();
            case 9:
                return new Fragment1();
            default:
                return null;
        }
    }

    // Returns total number of pages
    @Override
    public int getCount() {
        return 10;
    }
}
