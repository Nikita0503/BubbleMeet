package com.rockstar.bubblemeetapplication.singup;


import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.io.File;


public class SignUpFragmentPagerAdapter extends FragmentPagerAdapter {

    private Fragment1 mFragment1;

    public SignUpFragmentPagerAdapter(FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
        mFragment1 = new Fragment1();
    }

    @Override
    public Fragment getItem(int position) {
                switch(position) {
            case 0:
                return mFragment1;
            case 1:
                return new Fragment2();
            case 2:
                return new Fragment3();
            case 3:
                return new Fragment4();
            case 4:
                return new Fragment5();
            case 5:
                return new Fragment6();
            case 6:
                return new Fragment7();
            case 7:
                return new Fragment8();
            case 8:
                return new Fragment9();
            case 9:
                return new Fragment10();
            default:
                return null;
        }
    }

    public void addPhoto(int number, File photo){
        mFragment1.setPhoto(number, photo);
    }

    @Override
    public int getCount() {
        return 10;
    }


}
