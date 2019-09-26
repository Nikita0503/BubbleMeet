package com.rockstar.bubblemeetapplication.singup;


import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.io.File;
import java.util.ArrayList;


public class SignUpFragmentPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<SignUpView> mFragments;

    public SignUpFragmentPagerAdapter(FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
        mFragments = new ArrayList<SignUpView>();
        mFragments.add(new Fragment1());
        mFragments.add(new Fragment2());
        mFragments.add(new Fragment3());
        mFragments.add(new Fragment4());
        mFragments.add(new Fragment5());
        mFragments.add(new Fragment6());
        mFragments.add(new Fragment7());
        mFragments.add(new Fragment8());
        mFragments.add(new Fragment9());
        mFragments.add(new Fragment10());
    }

    @Override
    public Fragment getItem(int position) {
                switch(position) {
            case 0:
                return (Fragment1) mFragments.get(position);
            case 1:
                return (Fragment2) mFragments.get(position);
            case 2:
                return (Fragment3) mFragments.get(position);
            case 3:
                return (Fragment4) mFragments.get(position);
            case 4:
                return (Fragment5) mFragments.get(position);
            case 5:
                return (Fragment6) mFragments.get(position);
            case 6:
                return (Fragment7) mFragments.get(position);
            case 7:
                return (Fragment8) mFragments.get(position);
            case 8:
                return (Fragment9) mFragments.get(position);
            case 9:
                return (Fragment10) mFragments.get(position);
            default:
                return null;
        }
    }

    public void addPhoto(int number, File photo){
        ((Fragment1) mFragments.get(0)).setPhoto(number, photo);
    }

    public boolean isCorrect(int fragmentNumber){
        return mFragments.get(fragmentNumber).isCorrect();
    }

    @Override
    public int getCount() {
        return 10;
    }


}
