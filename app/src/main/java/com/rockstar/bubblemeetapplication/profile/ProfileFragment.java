package com.rockstar.bubblemeetapplication.profile;

import android.content.Intent;
import android.graphics.Color;
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

    private UserDataFull mUser;
    private ProfilePresenter mPresenter;
    private TextView mTextViewName;
    private TextView mTextViewYearsOldAndCity;
    private TextView mTextViewHobbies;
    private ViewPager mViewPagerProfile;
    private TabLayout mTabLayoutProfile;
    private FlowLayout mFlowLayout;

    public ProfileFragment(){
        mPresenter = new ProfilePresenter(this);
    }

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
    public void onStart(){
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    public void initViews() {
        mTextViewName.setText(mUser.name);
        String yearsOldAndCity = "";
        yearsOldAndCity += mUser.age + "";
        yearsOldAndCity += ", " + mUser.city;
        mTextViewYearsOldAndCity.setText(yearsOldAndCity);
        mTextViewHobbies.setText(mUser.hobbes);
        mViewPagerProfile.setAdapter(new ProfilePagerAdapter(getContext(), mUser));
        mTabLayoutProfile.setupWithViewPager(mViewPagerProfile);

        if(mUser.looking != null && !mUser.looking.equals("")){
            addInfo(mUser.looking);
        }
        if(mUser.gender != null && !mUser.gender.equals("")){
            addInfo(mUser.gender);
        }
        if(mUser.height != null && !mUser.height.equals("")){
            addInfo(mUser.height);
        }
        if(mUser.eyeColor != null && !mUser.eyeColor.equals("")){
            addInfo(mUser.eyeColor);
        }
        if(mUser.loveCook == 1){
            addInfo(getResources().getString(R.string.cookingInfo));
        }else{
            addInfo(getResources().getString(R.string.not)
                    + " " + getResources().getString(R.string.cookingInfo));
        }
        if(mUser.smoking == 1){
            addInfo(getResources().getString(R.string.smokingInfo));
        }else{
            addInfo(getResources().getString(R.string.not)
                    + " " + getResources().getString(R.string.smokingInfo));
        }
        if(mUser.marred == 1){
            addInfo(getResources().getString(R.string.marriedInfo));
        }else{
            addInfo(getResources().getString(R.string.not)
                    + " " + getResources().getString(R.string.marriedInfo));
        }
        if(mUser.children == 1){
            addInfo(getResources().getString(R.string.childrenInfo));
        }else{
            addInfo(getResources().getString(R.string.no)
                    + " " + getResources().getString(R.string.childrenInfo));
        }
    }

    private void addInfo(String info){
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30,  getResources().getDisplayMetrics());
        Button infoButton = new Button(getContext());
        infoButton.setHeight(px);
        infoButton.setTextSize(11);
        infoButton.setWidth((int)(px*2.5));
        infoButton.setBackgroundResource(R.drawable.background_button_login);
        infoButton.setText(info);
        infoButton.setTextColor(Color.WHITE);
        mFlowLayout.addView(infoButton);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        mPresenter.onStop();
    }
}