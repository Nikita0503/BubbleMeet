package com.rockstar.bubblemeetapplication.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.rockstar.bubblemeetapplication.BaseContract;
import com.rockstar.bubblemeetapplication.R;
import com.rockstar.bubblemeetapplication.auth.AuthActivity;

public class ProfileFragment extends Fragment implements BaseContract.BaseView  {

    String mName;
    TextView mTextViewName;
    ViewPager mViewPagerProfile;
    TabLayout mTabLayoutProfile;

    public void setName(String name){
        mName = name;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(com.rockstar.bubblemeetapplication.R.layout.fragment_profile, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mTextViewName = (TextView) view.findViewById(R.id.textViewName2);
        mViewPagerProfile = (ViewPager) view.findViewById(R.id.profile_viewpager);
        mTabLayoutProfile = (TabLayout) view.findViewById(R.id.tab_layout);
        initViews();
    }

    @Override
    public void initViews() {
        mTextViewName.setText(mName);
        mViewPagerProfile.setAdapter(new ProfilePagerAdapter(getContext()));
        mTabLayoutProfile.setupWithViewPager(mViewPagerProfile);
    }
}