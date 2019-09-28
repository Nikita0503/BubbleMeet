package com.rockstar.bubblemeetapplication.profile;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.nex3z.flowlayout.FlowLayout;
import com.rockstar.bubblemeetapplication.BaseContract;
import com.rockstar.bubblemeetapplication.R;
import com.rockstar.bubblemeetapplication.auth.AuthActivity;
import com.rockstar.bubblemeetapplication.model.data.UserData;
import com.rockstar.bubblemeetapplication.model.data.UserDataFull;

public class ProfileFragment extends Fragment implements BaseContract.BaseView  {

    UserDataFull mUser;
    TextView mTextViewName;
    TextView mTextViewYearsOldAndCity;
    TextView mTextViewHobbies;
    ViewPager mViewPagerProfile;
    TabLayout mTabLayoutProfile;
    FlowLayout mFlowLayout;

    public void setUser(UserDataFull user){
        mUser = user;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(com.rockstar.bubblemeetapplication.R.layout.fragment_profile, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mTextViewName = (TextView) view.findViewById(R.id.textViewName2);
        mTextViewYearsOldAndCity = (TextView) view.findViewById(R.id.textViewYearsOldAndCity2);
        mTextViewHobbies = (TextView) view.findViewById(R.id.textViewHobbies);
        mViewPagerProfile = (ViewPager) view.findViewById(R.id.profile_viewpager);
        mTabLayoutProfile = (TabLayout) view.findViewById(R.id.tab_layout);
        mFlowLayout = (FlowLayout) view.findViewById(R.id.flowLayoutGender);
        initViews();
    }

    @Override
    public void initViews() {
        mTextViewName.setText(mUser.name);
        mTextViewYearsOldAndCity.setText(mUser.age + ", " + mUser.city);
        mTextViewHobbies.setText(mUser.hobbes);
        mViewPagerProfile.setAdapter(new ProfilePagerAdapter(getContext(), mUser));
        mTabLayoutProfile.setupWithViewPager(mViewPagerProfile);
        int px = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40,  getResources().getDisplayMetrics());
        if(mUser.looking != null){
            Button buttonLooking = new Button(getContext());
            buttonLooking.setHeight(px);
            buttonLooking.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
            buttonLooking.setBackgroundResource(R.drawable.background_button_login);
            buttonLooking.setText(mUser.looking);
            mFlowLayout.addView(buttonLooking);
        }
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}