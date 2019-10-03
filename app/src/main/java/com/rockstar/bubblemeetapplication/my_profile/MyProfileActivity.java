package com.rockstar.bubblemeetapplication.my_profile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.abdularis.civ.CircleImageView;
import com.nex3z.flowlayout.FlowLayout;
import com.rockstar.bubblemeetapplication.BaseContract;
import com.rockstar.bubblemeetapplication.R;
import com.rockstar.bubblemeetapplication.auth.AuthActivity;
import com.rockstar.bubblemeetapplication.model.data.UserDataFull;
import com.squareup.picasso.Picasso;

public class MyProfileActivity extends AppCompatActivity implements BaseContract.BaseView {

    private MyProfilePresenter mPresenter;
    private TextView mTextViewName;
    private TextView mTextViewYearsOldAndCity;
    private TextView mTextViewHobbies;
    private ImageView mImageViewBack;
    private ImageView mImageViewExit;
    private CircleImageView mImageViewAvatar;
    private CircleImageView mImageViewAdditionPhoto1;
    private CircleImageView mImageViewAdditionPhoto2;
    private CircleImageView mImageViewAdditionPhoto3;
    private FlowLayout mFlowLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.rockstar.bubblemeetapplication.R.layout.activity_my_profile);
        initViews();
        mPresenter = new MyProfilePresenter(this);
    }

    @Override
    public void onStart(){
        super.onStart();
        mPresenter.onStart();
        //mPresenter.fetchData();
    }

    @Override
    public void initViews() {
        mTextViewName = (TextView) findViewById(com.rockstar.bubblemeetapplication.R.id.textViewName);
        mTextViewYearsOldAndCity = (TextView) findViewById(com.rockstar.bubblemeetapplication.R.id.textViewYearsOldAndCity3);
        mTextViewHobbies = (TextView) findViewById(com.rockstar.bubblemeetapplication.R.id.textViewHobbies);
        mImageViewAvatar = (CircleImageView) findViewById(com.rockstar.bubblemeetapplication.R.id.imageViewAvatar);
        mImageViewAdditionPhoto1 = (CircleImageView) findViewById(com.rockstar.bubblemeetapplication.R.id.imageViewPhoto1);
        mImageViewAdditionPhoto2 = (CircleImageView) findViewById(com.rockstar.bubblemeetapplication.R.id.imageViewPhoto2);
        mImageViewAdditionPhoto3 = (CircleImageView) findViewById(com.rockstar.bubblemeetapplication.R.id.imageViewPhoto3);
        mImageViewBack = (ImageView) findViewById(com.rockstar.bubblemeetapplication.R.id.imageViewBack);
        mImageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mImageViewExit = (ImageView) findViewById(com.rockstar.bubblemeetapplication.R.id.imageViewExit);
        mImageViewExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref = getSharedPreferences("BubbleMeet", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("email", "");
                editor.putString("password", "");
                editor.putString("session", "");
                editor.commit();
                Intent intent = new Intent(MyProfileActivity.this, AuthActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
        mFlowLayout = (FlowLayout) findViewById(R.id.flowLayoutGender);
    }

    public void setData(UserDataFull userData){
        Picasso.with(getApplicationContext())
                .load("http://185.25.116.211:11000/image/" + userData.avatarFull)
                .into(mImageViewAvatar);
        if(userData.photo.size() != 0) {
            if(userData.photo.get(0) != null){
                mImageViewAdditionPhoto1.setVisibility(View.VISIBLE);
                Picasso.with(getApplicationContext())
                        .load("http://185.25.116.211:11000/image/" + userData.photo.get(0))
                        .into(mImageViewAdditionPhoto1);
            }else {
                mImageViewAdditionPhoto1.setVisibility(View.INVISIBLE);
            }
            if(userData.photo.size() >= 2) {
                if (userData.photo.get(1) != null) {
                    mImageViewAdditionPhoto2.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext())
                            .load("http://185.25.116.211:11000/image/" + userData.photo.get(1))
                            .into(mImageViewAdditionPhoto2);
                } else {
                    mImageViewAdditionPhoto2.setVisibility(View.INVISIBLE);
                }
            }
            if(userData.photo.size() >= 3) {
                if (userData.photo.get(2) != null) {
                    mImageViewAdditionPhoto3.setVisibility(View.VISIBLE);
                    Picasso.with(getApplicationContext())
                            .load("http://185.25.116.211:11000/image/" + userData.photo.get(2))
                            .into(mImageViewAdditionPhoto3);
                } else {
                    mImageViewAdditionPhoto3.setVisibility(View.INVISIBLE);
                }
            }
        }else{
            mImageViewAdditionPhoto1.setVisibility(View.GONE);
            mImageViewAdditionPhoto2.setVisibility(View.GONE);
            mImageViewAdditionPhoto3.setVisibility(View.GONE);
        }
        mTextViewName.setText(userData.name);
        String yearsOldAndCity = "";
        yearsOldAndCity += userData.age + "";
        yearsOldAndCity += ", " + userData.city;
        mTextViewYearsOldAndCity.setText(yearsOldAndCity);
        mTextViewHobbies.setText(userData.hobbes);
        if(userData.looking != null && !userData.looking.equals("")){
            addInfo(userData.looking);
        }
        if(userData.gender != null && !userData.gender.equals("")){
            addInfo(userData.gender);
        }
        if(userData.height != null && !userData.height.equals("")){
            addInfo(userData.height);
        }
        if(userData.eyeColor != null && !userData.eyeColor.equals("")){
            addInfo(userData.eyeColor);
        }
        if(userData.loveCook == 1){
            addInfo(getResources().getString(R.string.cookingInfo));
        }else{
            addInfo(getResources().getString(R.string.not)
                    + " " + getResources().getString(R.string.cookingInfo));
        }
        if(userData.smoking == 1){
            addInfo(getResources().getString(R.string.smokingInfo));
        }else{
            addInfo(getResources().getString(R.string.not)
                    + " " + getResources().getString(R.string.smokingInfo));
        }
        if(userData.marred == 1){
            addInfo(getResources().getString(R.string.marriedInfo));
        }else{
            addInfo(getResources().getString(R.string.not)
                    + " " + getResources().getString(R.string.marriedInfo));
        }
        if(userData.children == 1){
            addInfo(getResources().getString(R.string.childrenInfo));
        }else{
            addInfo(getResources().getString(R.string.no)
                    + " " + getResources().getString(R.string.childrenInfo));
        }
    }

    private void addInfo(String info){
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30,  getResources().getDisplayMetrics());
        Button infoButton = new Button(getApplicationContext());
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
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        mPresenter.onStop();
    }
}