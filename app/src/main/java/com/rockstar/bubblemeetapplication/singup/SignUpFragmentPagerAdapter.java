package com.rockstar.bubblemeetapplication.singup;


import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.io.File;
import java.util.ArrayList;


public class SignUpFragmentPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<SignUpView> mFragments;
    //private Fragment1 mFragment1;
    //private Fragment2 mFragment2;
    //private Fragment3 mFragment3;
    //private Fragment4 mFragment4;
    //private Fragment5 mFragment5;
    //private Fragment6 mFragment6;
    //private Fragment7 mFragment7;
    //private Fragment8 mFragment8;
    //private Fragment9 mFragment9;
    //private Fragment10 mFragment10;

    public SignUpFragmentPagerAdapter(FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
        mFragments = new ArrayList<SignUpView>();
        //mFragment1 = new Fragment1();
        //mFragment2 = new Fragment2();
        //mFragment3 = new Fragment3();
        //mFragment4 = new Fragment4();
        //mFragment5 = new Fragment5();
        //mFragment6 = new Fragment6();
        //mFragment7 = new Fragment7();
        //mFragment8 = new Fragment8();
        //mFragment9 = new Fragment9();
        //mFragment10 = new Fragment10();
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
        //mFragment1.setPhoto(number, photo);
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
