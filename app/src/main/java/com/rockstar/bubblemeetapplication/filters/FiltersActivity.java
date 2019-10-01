package com.rockstar.bubblemeetapplication.filters;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.rockstar.bubblemeetapplication.BaseContract;
import com.rockstar.bubblemeetapplication.R;
import com.rockstar.bubblemeetapplication.auth.AuthActivity;

public class FiltersActivity extends AppCompatActivity implements BaseContract.BaseView {

    private String mGender;
    private String mAge;
    private String mDistance;
    private String mEyeColor;
    private String mHeight;
    private String mSmoking;
    private String mMarried;
    private String mChildren;
    private String mLookingFor;
    private String mLoveToCook;

    private ImageView mImageViewBack;
    private ImageView mImageViewFilter;

    private Button mButtonMan;
    private Button mButtonWoman;
    private Button mButtonLGBT;

    private Button mButton17_25;
    private Button mButton26_30;
    private Button mButton31_35;
    private Button mButton36_40;
    private Button mButton41_45;
    private Button mButton46_50;
    private Button mButton51_more;

    private Button mButton1km;
    private Button mButton5km;
    private Button mButton10km;
    private Button mButton50km;
    private Button mButtonAllArea;

    private Button mButtonEyeAmber;
    private Button mButtonEyeBlue;
    private Button mButtonEyeBrown;
    private Button mButtonEyeGray;
    private Button mButtonEyeGreen;
    private Button mButtonEyeHazel;
    private Button mButtonEyeRedAndViolet;
    private Button mButtonEyeSpectrumOfEyeColor;

    private Button mButtonHeight120;
    private Button mButtonHeight150;
    private Button mButtonHeight180;
    private Button mButtonHeight210;
    private Button mButtonHeightNoMatter;

    private Button mButtonSmokingYes;
    private Button mButtonSmokingNo;
    private Button mButtonSmokingNoMatter;

    private Button mButtonMarriedYes;
    private Button mButtonMarriedNo;
    private Button mButtonMarriedNoMatter;

    private Button mButtonChildrenYes;
    private Button mButtonChildrenNo;
    private Button mButtonChildrenNoMatter;

    private Button mButtonLookingForLongTerm;
    private Button mButtonLookingForDating;
    private Button mButtonLookingForPlay;
    private Button mButtonLookingForJustFriendship;

    private Button mButtonLoveToCookYes;
    private Button mButtonLoveToCookNo;
    private Button mButtonLoveToCookNoMatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_filters);
        mGender = "";
        mAge = "";
        mDistance = "";
        mEyeColor = "";
        mHeight = "";
        mSmoking = "";
        mMarried = "";
        mChildren = "";
        mLookingFor = "";
        mLoveToCook = "";
        initViews();
    }

    @Override
    public void initViews() {
        mImageViewBack = (ImageView) findViewById(R.id.imageViewBack);
        mImageViewFilter = (ImageView) findViewById(R.id.imageViewSearch);
        mImageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mImageViewFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("gender", mGender);
                intent.putExtra("age", mAge);
                intent.putExtra("distance", mDistance);
                intent.putExtra("eyeColor", mEyeColor);
                intent.putExtra("height", mHeight);
                intent.putExtra("smoking", mSmoking);
                intent.putExtra("married", mMarried);
                intent.putExtra("children", mChildren);
                intent.putExtra("lookingFor", mLookingFor);
                intent.putExtra("loveToCook", mLoveToCook);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        mButtonMan = (Button) findViewById(R.id.buttonMan);
        mButtonWoman = (Button) findViewById(R.id.buttonWoman);
        mButtonLGBT = (Button) findViewById(R.id.buttonLGBT);

        mButton17_25 = (Button) findViewById(R.id.buttonAge17_25);
        mButton26_30 = (Button) findViewById(R.id.buttonAge26_30);
        mButton31_35 = (Button) findViewById(R.id.buttonAge31_35);
        mButton36_40 = (Button) findViewById(R.id.buttonAge36_40);
        mButton41_45 = (Button) findViewById(R.id.buttonAge41_45);
        mButton46_50 = (Button) findViewById(R.id.buttonAge46_50);
        mButton51_more = (Button) findViewById(R.id.buttonAge51_more);

        mButton1km = (Button) findViewById(R.id.button1km);
        mButton5km = (Button) findViewById(R.id.button5km);
        mButton10km = (Button) findViewById(R.id.button10km);
        mButton50km = (Button) findViewById(R.id.button50km);
        mButtonAllArea = (Button) findViewById(R.id.buttonAllArea);

        mButtonEyeAmber = (Button) findViewById(R.id.buttonAmber);
        mButtonEyeBlue = (Button) findViewById(R.id.buttonBlue);
        mButtonEyeBrown = (Button) findViewById(R.id.buttonBrown);
        mButtonEyeGray = (Button) findViewById(R.id.buttonGray);
        mButtonEyeGreen = (Button) findViewById(R.id.buttonGreen);
        mButtonEyeHazel = (Button) findViewById(R.id.buttonHazel);
        mButtonEyeRedAndViolet = (Button) findViewById(R.id.buttonRedAndViolet);
        mButtonEyeSpectrumOfEyeColor = (Button) findViewById(R.id.buttonSpectrumOfEyeColor);

        mButtonHeight120 = (Button) findViewById(R.id.button120m);
        mButtonHeight150 = (Button) findViewById(R.id.button150m);
        mButtonHeight180 = (Button) findViewById(R.id.button180m);
        mButtonHeight210 = (Button) findViewById(R.id.button210m);
        mButtonHeightNoMatter = (Button) findViewById(R.id.buttonNoMatterHeight);

        mButtonSmokingYes = (Button) findViewById(R.id.buttonSmoking);
        mButtonSmokingNo = (Button) findViewById(R.id.buttonNoSmoking);
        mButtonSmokingNoMatter = (Button) findViewById(R.id.buttonNoMatterSmoking);

        mButtonMarriedYes = (Button) findViewById(R.id.buttonYesMarried);
        mButtonMarriedNo = (Button) findViewById(R.id.buttonNoMarried);
        mButtonMarriedNoMatter = (Button) findViewById(R.id.buttonNoMatterMarried);

        mButtonChildrenYes = (Button) findViewById(R.id.buttonYesChildren);
        mButtonChildrenNo = (Button) findViewById(R.id.buttonNoChildren);
        mButtonChildrenNoMatter = (Button) findViewById(R.id.buttonNoMatterChildren);

        mButtonLookingForLongTerm = (Button) findViewById(R.id.buttonLongTerm);
        mButtonLookingForDating = (Button) findViewById(R.id.buttonDating);
        mButtonLookingForPlay = (Button) findViewById(R.id.buttonForPlay);
        mButtonLookingForJustFriendship = (Button) findViewById(R.id.buttonJustFriendship);

        mButtonLoveToCookYes = (Button) findViewById(R.id.buttonYesLoveToCook);
        mButtonLoveToCookNo = (Button) findViewById(R.id.buttonNoLoveToCook);
        mButtonLoveToCookNoMatter = (Button) findViewById(R.id.buttonNoMatterLoveToCook);

        initGenderTags();
        initAgeTags();
        initLocationTags();
        initEyeColorTags();
        initHeightTags();
        initSmokingTags();
        initMarried();
        initChildrenTags();
        initLookingForTags();
        initLoveToCookTags();
    }

    private void initLoveToCookTags(){
        mButtonLoveToCookYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mLoveToCook.equals("") || !mLoveToCook.equals("1")){
                    mLoveToCook = "1";
                    mButtonLoveToCookYes.setBackground(getResources().getDrawable(R.drawable.background_button_login_selected));
                    mButtonLoveToCookNo.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButtonLoveToCookNoMatter.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                }else {
                    if (mLoveToCook.equals("1")) {
                        mLoveToCook = "";
                        mButtonLoveToCookYes.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonLoveToCookNo.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonLoveToCookNoMatter.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    }
                }
            }
        });
        mButtonLoveToCookNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mLoveToCook.equals("") || !mLoveToCook.equals("0")){
                    mLoveToCook = "0";
                    mButtonLoveToCookYes.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButtonLoveToCookNo.setBackground(getResources().getDrawable(R.drawable.background_button_login_selected));
                    mButtonLoveToCookNoMatter.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                }else {
                    if (mLoveToCook.equals("0")) {
                        mLoveToCook = "";
                        mButtonLoveToCookYes.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonLoveToCookNo.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonLoveToCookNoMatter.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    }
                }
            }
        });
        mButtonLoveToCookNoMatter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mLoveToCook.equals("") || !mLoveToCook.equals("No matter")){
                    mLoveToCook = "No matter";
                    mButtonLoveToCookYes.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButtonLoveToCookNo.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButtonLoveToCookNoMatter.setBackground(getResources().getDrawable(R.drawable.background_button_login_selected));
                }else {
                    if (mLoveToCook.equals("No matter")) {
                        mLoveToCook = "";
                        mButtonLoveToCookYes.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonLoveToCookNo.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonLoveToCookNoMatter.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    }
                }
            }
        });
    }

    private void initLookingForTags(){
        mButtonLookingForDating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mLookingFor.equals("") || !mLookingFor.equals("Dating")){
                    mLookingFor = "Dating";
                    mButtonLookingForDating.setBackground(getResources().getDrawable(R.drawable.background_button_login_selected));
                    mButtonLookingForJustFriendship.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButtonLookingForLongTerm.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButtonLookingForPlay.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                }else{
                    if(mLookingFor.equals("Dating")){
                        mLookingFor = "";
                        mButtonLookingForDating.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonLookingForJustFriendship.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonLookingForLongTerm.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonLookingForPlay.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    }
                }
            }
        });
        mButtonLookingForJustFriendship.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mLookingFor.equals("") || !mLookingFor.equals("Just friendship")){
                    mLookingFor = "Just friendship";
                    mButtonLookingForDating.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButtonLookingForJustFriendship.setBackground(getResources().getDrawable(R.drawable.background_button_login_selected));
                    mButtonLookingForLongTerm.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButtonLookingForPlay.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                }else{
                    if(mLookingFor.equals("Just friendship")){
                        mLookingFor = "";
                        mButtonLookingForDating.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonLookingForJustFriendship.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonLookingForLongTerm.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonLookingForPlay.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    }
                }
            }
        });
        mButtonLookingForLongTerm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mLookingFor.equals("") || !mLookingFor.equals("Long term")){
                    mLookingFor = "Long term";
                    mButtonLookingForDating.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButtonLookingForJustFriendship.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButtonLookingForLongTerm.setBackground(getResources().getDrawable(R.drawable.background_button_login_selected));
                    mButtonLookingForPlay.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                }else{
                    if(mLookingFor.equals("Long term")){
                        mLookingFor = "";
                        mButtonLookingForDating.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonLookingForJustFriendship.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonLookingForLongTerm.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonLookingForPlay.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    }
                }
            }
        });
        mButtonLookingForPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mLookingFor.equals("") || !mLookingFor.equals("For play")){
                    mLookingFor = "For play";
                    mButtonLookingForDating.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButtonLookingForJustFriendship.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButtonLookingForLongTerm.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButtonLookingForPlay.setBackground(getResources().getDrawable(R.drawable.background_button_login_selected));
                }else{
                    if(mLookingFor.equals("For play")){
                        mLookingFor = "";
                        mButtonLookingForDating.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonLookingForJustFriendship.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonLookingForLongTerm.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonLookingForPlay.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    }
                }
            }
        });
    }

    private void initChildrenTags(){
        mButtonChildrenYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mChildren.equals("") || !mChildren.equals("1")){
                    mChildren = "1";
                    mButtonChildrenYes.setBackground(getResources().getDrawable(R.drawable.background_button_login_selected));
                    mButtonChildrenNo.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButtonChildrenNoMatter.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                }else{
                    if(mChildren.equals("1")){
                        mChildren = "";
                        mButtonChildrenYes.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonChildrenNo.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonChildrenNoMatter.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    }
                }
            }
        });
        mButtonChildrenNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mChildren.equals("") || !mChildren.equals("0")){
                    mChildren = "0";
                    mButtonChildrenYes.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButtonChildrenNo.setBackground(getResources().getDrawable(R.drawable.background_button_login_selected));
                    mButtonChildrenNoMatter.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                }else{
                    if(mChildren.equals("0")){
                        mChildren = "";
                        mButtonChildrenYes.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonChildrenNo.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonChildrenNoMatter.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    }
                }
            }
        });
        mButtonChildrenNoMatter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mChildren.equals("") || !mChildren.equals("No matter")){
                    mChildren = "No matter";
                    mButtonChildrenYes.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButtonChildrenNo.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButtonChildrenNoMatter.setBackground(getResources().getDrawable(R.drawable.background_button_login_selected));
                }else{
                    if(mChildren.equals("No matter")){
                        mChildren = "";
                        mButtonChildrenYes.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonChildrenNo.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonChildrenNoMatter.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    }
                }
            }
        });
    }

    private void initMarried(){
        mButtonMarriedYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mMarried.equals("") || !mMarried.equals("1")){
                    mMarried = "1";
                    mButtonMarriedYes.setBackground(getResources().getDrawable(R.drawable.background_button_login_selected));
                    mButtonMarriedNo.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButtonMarriedNoMatter.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                }else{
                    if(mMarried.equals("1")){
                        mMarried = "";
                        mButtonMarriedYes.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonMarriedNo.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonMarriedNoMatter.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    }
                }
            }
        });
        mButtonMarriedNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mMarried.equals("") || !mMarried.equals("0")){
                    mMarried = "0";
                    mButtonMarriedYes.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButtonMarriedNo.setBackground(getResources().getDrawable(R.drawable.background_button_login_selected));
                    mButtonMarriedNoMatter.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                }else{
                    if(mMarried.equals("0")){
                        mMarried = "";
                        mButtonMarriedYes.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonMarriedNo.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonMarriedNoMatter.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    }
                }
            }
        });
        mButtonMarriedNoMatter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mMarried.equals("") || !mMarried.equals("No matter")){
                    mMarried = "No matter";
                    mButtonMarriedYes.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButtonMarriedNo.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButtonMarriedNoMatter.setBackground(getResources().getDrawable(R.drawable.background_button_login_selected));
                }else{
                    if(mMarried.equals("No matter")){
                        mMarried = "";
                        mButtonMarriedYes.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonMarriedNo.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonMarriedNoMatter.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    }
                }
            }
        });
    }

    private void initSmokingTags(){
        mButtonSmokingYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mSmoking.equals("") || !mSmoking.equals("1")){
                    mSmoking = "1";
                    mButtonSmokingYes.setBackground(getResources().getDrawable(R.drawable.background_button_login_selected));
                    mButtonSmokingNo.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButtonSmokingNoMatter.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                }else{
                    if(mSmoking.equals("1")){
                        mSmoking = "";
                        mButtonSmokingYes.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonSmokingNo.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonSmokingNoMatter.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    }
                }
            }
        });
        mButtonSmokingNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mSmoking.equals("") || !mSmoking.equals("0")){
                    mSmoking = "0";
                    mButtonSmokingYes.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButtonSmokingNo.setBackground(getResources().getDrawable(R.drawable.background_button_login_selected));
                    mButtonSmokingNoMatter.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                }else{
                    if(mSmoking.equals("0")){
                        mSmoking = "";
                        mButtonSmokingYes.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonSmokingNo.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonSmokingNoMatter.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    }
                }
            }
        });
        mButtonSmokingNoMatter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mSmoking.equals("") || !mSmoking.equals("No matter")){
                    mSmoking = "No matter";
                    mButtonSmokingYes.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButtonSmokingNo.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButtonSmokingNoMatter.setBackground(getResources().getDrawable(R.drawable.background_button_login_selected));
                }else{
                    if(mSmoking.equals("No matter")){
                        mSmoking = "";
                        mButtonSmokingYes.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonSmokingNo.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonSmokingNoMatter.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    }
                }
            }
        });
    }

    private void initHeightTags(){
        mButtonHeight120.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mHeight.equals("") || !mHeight.equals("120")){
                    mHeight = "120";
                    mButtonHeight120.setBackground(getResources().getDrawable(R.drawable.background_button_login_selected));
                    mButtonHeight150.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButtonHeight180.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButtonHeight210.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButtonHeightNoMatter.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                }else{
                    if(mHeight.equals("120")){
                        mHeight = "";
                        mButtonHeight120.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonHeight150.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonHeight180.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonHeight210.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonHeightNoMatter.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    }
                }
            }
        });
        mButtonHeight150.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mHeight.equals("") || !mHeight.equals("150")){
                    mHeight = "150";
                    mButtonHeight120.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButtonHeight150.setBackground(getResources().getDrawable(R.drawable.background_button_login_selected));
                    mButtonHeight180.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButtonHeight210.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButtonHeightNoMatter.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                }else{
                    if(mHeight.equals("150")){
                        mHeight = "";
                        mButtonHeight120.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonHeight150.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonHeight180.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonHeight210.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonHeightNoMatter.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    }
                }
            }
        });
        mButtonHeight180.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mHeight.equals("") || !mHeight.equals("180")){
                    mHeight = "180";
                    mButtonHeight120.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButtonHeight150.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButtonHeight180.setBackground(getResources().getDrawable(R.drawable.background_button_login_selected));
                    mButtonHeight210.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButtonHeightNoMatter.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                }else{
                    if(mHeight.equals("180")){
                        mHeight = "";
                        mButtonHeight120.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonHeight150.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonHeight180.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonHeight210.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonHeightNoMatter.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    }
                }
            }
        });
        mButtonHeight210.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mHeight.equals("") || !mHeight.equals("210")){
                    mHeight = "210";
                    mButtonHeight120.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButtonHeight150.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButtonHeight180.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButtonHeight210.setBackground(getResources().getDrawable(R.drawable.background_button_login_selected));
                    mButtonHeightNoMatter.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                }else{
                    if(mHeight.equals("210")){
                        mHeight = "";
                        mButtonHeight120.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonHeight150.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonHeight180.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonHeight210.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonHeightNoMatter.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    }
                }
            }
        });
        mButtonHeightNoMatter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mHeight.equals("") || !mHeight.equals("0")){
                    mHeight = "0";
                    mButtonHeight120.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButtonHeight150.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButtonHeight180.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButtonHeight210.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButtonHeightNoMatter.setBackground(getResources().getDrawable(R.drawable.background_button_login_selected));
                }else{
                    if(mHeight.equals("0")){
                        mHeight = "";
                        mButtonHeight120.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonHeight150.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonHeight180.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonHeight210.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonHeightNoMatter.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    }
                }
            }
        });
    }

    private void initEyeColorTags(){
        mButtonEyeAmber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mEyeColor.equals("") || !mEyeColor.equals("Amber")){
                        mEyeColor = "Amber";
                        mButtonEyeAmber.setBackground(getResources().getDrawable(R.drawable.background_button_login_selected));
                        mButtonEyeBlue.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonEyeBrown.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonEyeGray.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonEyeGreen.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonEyeHazel.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonEyeRedAndViolet.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonEyeSpectrumOfEyeColor.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    }else{
                        if(mEyeColor.equals("Amber")){
                            mEyeColor = "";
                            mButtonEyeAmber.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                            mButtonEyeBlue.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                            mButtonEyeBrown.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                            mButtonEyeGray.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                            mButtonEyeGreen.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                            mButtonEyeHazel.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                            mButtonEyeRedAndViolet.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                            mButtonEyeSpectrumOfEyeColor.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        }
                    }

            }
        });
        mButtonEyeBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mEyeColor.equals("") || !mEyeColor.equals("Blue")){

                        mEyeColor = "Blue";
                        mButtonEyeAmber.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonEyeBlue.setBackground(getResources().getDrawable(R.drawable.background_button_login_selected));
                        mButtonEyeBrown.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonEyeGray.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonEyeGreen.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonEyeHazel.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonEyeRedAndViolet.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonEyeSpectrumOfEyeColor.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    }else{
                        if(mEyeColor.equals("Blue")){
                            mEyeColor = "";
                            mButtonEyeAmber.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                            mButtonEyeBlue.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                            mButtonEyeBrown.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                            mButtonEyeGray.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                            mButtonEyeGreen.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                            mButtonEyeHazel.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                            mButtonEyeRedAndViolet.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                            mButtonEyeSpectrumOfEyeColor.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        }
                    }

            }
        });
        mButtonEyeBrown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mEyeColor.equals("") || !mEyeColor.equals("Brown")){

                        mEyeColor = "Brown";
                        mButtonEyeAmber.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonEyeBlue.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonEyeBrown.setBackground(getResources().getDrawable(R.drawable.background_button_login_selected));
                        mButtonEyeGray.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonEyeGreen.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonEyeHazel.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonEyeRedAndViolet.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonEyeSpectrumOfEyeColor.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    }else{
                        if(mEyeColor.equals("Brown")){
                            mEyeColor = "";
                            mButtonEyeAmber.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                            mButtonEyeBlue.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                            mButtonEyeBrown.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                            mButtonEyeGray.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                            mButtonEyeGreen.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                            mButtonEyeHazel.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                            mButtonEyeRedAndViolet.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                            mButtonEyeSpectrumOfEyeColor.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        }
                    }

            }
        });
        mButtonEyeGray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    if(mEyeColor.equals("") || !mEyeColor.equals("Gray")){
                        mEyeColor = "Gray";
                        mButtonEyeAmber.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonEyeBlue.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonEyeBrown.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonEyeGray.setBackground(getResources().getDrawable(R.drawable.background_button_login_selected));
                        mButtonEyeGreen.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonEyeHazel.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonEyeRedAndViolet.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonEyeSpectrumOfEyeColor.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    }else{
                        if(mEyeColor.equals("Gray")){
                            mEyeColor = "";
                            mButtonEyeAmber.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                            mButtonEyeBlue.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                            mButtonEyeBrown.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                            mButtonEyeGray.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                            mButtonEyeGreen.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                            mButtonEyeHazel.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                            mButtonEyeRedAndViolet.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                            mButtonEyeSpectrumOfEyeColor.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        }
                    }

            }
        });
        mButtonEyeGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mEyeColor.equals("") || !mEyeColor.equals("Green")){

                        mEyeColor = "Green";
                        mButtonEyeAmber.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonEyeBlue.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonEyeBrown.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonEyeGray.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonEyeGreen.setBackground(getResources().getDrawable(R.drawable.background_button_login_selected));
                        mButtonEyeHazel.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonEyeRedAndViolet.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonEyeSpectrumOfEyeColor.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    }else{
                        if(mEyeColor.equals("Green")){
                            mEyeColor = "";
                            mButtonEyeAmber.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                            mButtonEyeBlue.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                            mButtonEyeBrown.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                            mButtonEyeGray.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                            mButtonEyeGreen.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                            mButtonEyeHazel.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                            mButtonEyeRedAndViolet.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                            mButtonEyeSpectrumOfEyeColor.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        }
                    }

            }
        });
        mButtonEyeHazel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    if(mEyeColor.equals("") || !mEyeColor.equals("Hazel")){
                        mEyeColor = "Hazel";
                        mButtonEyeAmber.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonEyeBlue.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonEyeBrown.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonEyeGray.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonEyeGreen.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonEyeHazel.setBackground(getResources().getDrawable(R.drawable.background_button_login_selected));
                        mButtonEyeRedAndViolet.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonEyeSpectrumOfEyeColor.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    }else{
                        if(mEyeColor.equals("Hazel")){
                            mEyeColor = "";
                            mButtonEyeAmber.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                            mButtonEyeBlue.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                            mButtonEyeBrown.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                            mButtonEyeGray.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                            mButtonEyeGreen.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                            mButtonEyeHazel.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                            mButtonEyeRedAndViolet.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                            mButtonEyeSpectrumOfEyeColor.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        }
                    }

            }
        });
        mButtonEyeRedAndViolet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    if(mEyeColor.equals("") || !mEyeColor.equals("Red and violet")){
                        mEyeColor = "Red and violet";
                        mButtonEyeAmber.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonEyeBlue.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonEyeBrown.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonEyeGray.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonEyeGreen.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonEyeHazel.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonEyeRedAndViolet.setBackground(getResources().getDrawable(R.drawable.background_button_login_selected));
                        mButtonEyeSpectrumOfEyeColor.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    }else{
                        if(mEyeColor.equals("Red and violet")){
                            mEyeColor = "";
                            mButtonEyeAmber.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                            mButtonEyeBlue.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                            mButtonEyeBrown.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                            mButtonEyeGray.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                            mButtonEyeGreen.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                            mButtonEyeHazel.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                            mButtonEyeRedAndViolet.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                            mButtonEyeSpectrumOfEyeColor.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        }
                    }
            }
        });
        mButtonEyeSpectrumOfEyeColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mEyeColor.equals("") || !mEyeColor.equals("Spectrum of eye color")){
                    mEyeColor = "Spectrum of eye color";
                    mButtonEyeAmber.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButtonEyeBlue.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButtonEyeBrown.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButtonEyeGray.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButtonEyeGreen.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButtonEyeHazel.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButtonEyeRedAndViolet.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButtonEyeSpectrumOfEyeColor.setBackground(getResources().getDrawable(R.drawable.background_button_login_selected));
                }else{
                    if(mEyeColor.equals("Spectrum of eye color")){
                        mEyeColor = "";
                        mButtonEyeAmber.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonEyeBlue.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonEyeBrown.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonEyeGray.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonEyeGreen.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonEyeHazel.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonEyeRedAndViolet.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonEyeSpectrumOfEyeColor.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    }
                }
            }
        });
    }

    private void initLocationTags(){
        mButton1km.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mDistance.equals("") || !mDistance.equals("1")){
                    mDistance = "1";
                    mButton1km.setBackground(getResources().getDrawable(R.drawable.background_button_login_selected));
                    mButton5km.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButton10km.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButton50km.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButtonAllArea.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                }else{
                    if(mDistance.equals("1")){
                        mDistance= "";
                        mButton1km.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButton5km.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButton10km.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButton50km.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonAllArea.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    }
                }
            }
        });
        mButton5km.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mDistance.equals("") || !mDistance.equals("5")){
                    mDistance = "5";
                    mButton1km.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButton5km.setBackground(getResources().getDrawable(R.drawable.background_button_login_selected));
                    mButton10km.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButton50km.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButtonAllArea.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                }else{
                    if(mDistance.equals("5")){
                        mDistance= "";
                        mButton1km.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButton5km.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButton10km.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButton50km.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonAllArea.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    }
                }
            }
        });
        mButton10km.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mDistance.equals("") || !mDistance.equals("10")){
                    mDistance = "10";
                    mButton1km.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButton5km.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButton10km.setBackground(getResources().getDrawable(R.drawable.background_button_login_selected));
                    mButton50km.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButtonAllArea.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                }else{
                    if(mDistance.equals("10")){
                        mDistance= "";
                        mButton1km.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButton5km.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButton10km.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButton50km.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonAllArea.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    }
                }
            }
        });
        mButton50km.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mDistance.equals("") || !mDistance.equals("50")){
                    mDistance = "50";
                    mButton1km.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButton5km.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButton10km.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButton50km.setBackground(getResources().getDrawable(R.drawable.background_button_login_selected));
                    mButtonAllArea.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                }else{
                    if(mDistance.equals("50")){
                        mDistance= "";
                        mButton1km.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButton5km.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButton10km.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButton50km.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonAllArea.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    }
                }
            }
        });
        mButtonAllArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mDistance.equals("") || !mDistance.equals("1000000")){
                    mDistance = "1000000";
                    mButton1km.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButton5km.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButton10km.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButton50km.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButtonAllArea.setBackground(getResources().getDrawable(R.drawable.background_button_login_selected));
                }else{
                    if(mDistance.equals("1000000")){
                        mDistance= "";
                        mButton1km.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButton5km.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButton10km.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButton50km.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonAllArea.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    }
                }
            }
        });
    }

    private void initAgeTags(){
        mButton17_25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mAge.equals("") || !mAge.equals("17-25")) {
                    mAge = "17-25";
                    mButton17_25.setBackground(getResources().getDrawable(R.drawable.background_button_login_selected));
                    mButton26_30.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButton31_35.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButton36_40.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButton41_45.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButton46_50.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButton51_more.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                }else{
                    if(mAge.equals("17-25")){
                        mAge = "";
                        mButton17_25.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButton26_30.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButton31_35.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButton36_40.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButton41_45.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButton46_50.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButton51_more.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    }
                }
            }
        });
        mButton26_30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mAge.equals("") || !mAge.equals("26-30")) {
                    mAge = "26-30";
                    mButton17_25.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButton26_30.setBackground(getResources().getDrawable(R.drawable.background_button_login_selected));
                    mButton31_35.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButton36_40.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButton41_45.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButton46_50.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButton51_more.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                }else{
                    if(mAge.equals("26-30")){
                        mAge = "";
                        mButton17_25.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButton26_30.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButton31_35.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButton36_40.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButton41_45.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButton46_50.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButton51_more.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    }
                }
            }
        });
        mButton31_35.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mAge.equals("") || !mAge.equals("31-35")) {
                    mAge = "31-35";
                    mButton17_25.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButton26_30.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButton31_35.setBackground(getResources().getDrawable(R.drawable.background_button_login_selected));
                    mButton36_40.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButton41_45.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButton46_50.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButton51_more.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                }else{
                    if(mAge.equals("31-35")){
                        mAge = "";
                        mButton17_25.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButton26_30.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButton31_35.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButton36_40.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButton41_45.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButton46_50.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButton51_more.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    }
                }
            }
        });
        mButton36_40.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mAge.equals("") || !mAge.equals("36-40")) {
                    mAge = "36-40";
                    mButton17_25.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButton26_30.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButton31_35.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButton36_40.setBackground(getResources().getDrawable(R.drawable.background_button_login_selected));
                    mButton41_45.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButton46_50.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButton51_more.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                }else{
                    if(mAge.equals("36-40")){
                        mAge = "";
                        mButton17_25.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButton26_30.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButton31_35.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButton36_40.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButton41_45.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButton46_50.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButton51_more.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    }
                }
            }
        });
        mButton41_45.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mAge.equals("") || !mAge.equals("41-45")) {
                    mAge = "41-45";
                    mButton17_25.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButton26_30.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButton31_35.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButton36_40.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButton41_45.setBackground(getResources().getDrawable(R.drawable.background_button_login_selected));
                    mButton46_50.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButton51_more.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                }else{
                    if(mAge.equals("41-45")){
                        mAge = "";
                        mButton17_25.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButton26_30.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButton31_35.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButton36_40.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButton41_45.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButton46_50.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButton51_more.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    }
                }
            }
        });
        mButton46_50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mAge.equals("") || !mAge.equals("46-50")) {
                    mAge = "46-50";
                    mButton17_25.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButton26_30.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButton31_35.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButton36_40.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButton41_45.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButton46_50.setBackground(getResources().getDrawable(R.drawable.background_button_login_selected));
                    mButton51_more.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                }else{
                    if(mAge.equals("46-50")){
                        mAge = "";
                        mButton17_25.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButton26_30.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButton31_35.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButton36_40.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButton41_45.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButton46_50.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButton51_more.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    }
                }
            }
        });
        mButton51_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mAge.equals("") || !mAge.equals("51-1000")) {
                    mAge = "51-1000";
                    mButton17_25.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButton26_30.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButton31_35.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButton36_40.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButton41_45.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButton46_50.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButton51_more.setBackground(getResources().getDrawable(R.drawable.background_button_login_selected));
                }else{
                    if(mAge.equals("51-1000")){
                        mAge = "";
                        mButton17_25.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButton26_30.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButton31_35.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButton36_40.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButton41_45.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButton46_50.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButton51_more.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    }
                }
            }
        });
    }

    private void initGenderTags(){
        mButtonMan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mGender.equals("") || !mGender.equals("Man")) {
                    mGender = "Man";
                    mButtonMan.setBackground(getResources().getDrawable(R.drawable.background_button_login_selected));
                    mButtonWoman.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButtonLGBT.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                }else {
                    if (mGender.equals("Man")) {
                        mGender = "";
                        mButtonMan.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonWoman.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonLGBT.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    }
                }
            }
        });
        mButtonWoman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mGender.equals("") || !mGender.equals("Woman")) {
                    mGender = "Woman";
                    mButtonMan.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButtonWoman.setBackground(getResources().getDrawable(R.drawable.background_button_login_selected));
                    mButtonLGBT.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                }else{
                    if(mGender.equals("Woman")) {
                        mGender = "";
                        mButtonMan.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonWoman.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonLGBT.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    }
                }
            }
        });
        mButtonLGBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mGender.equals("") || !mGender.equals("LGBT")) {
                    mGender = "LGBT";
                    mButtonMan.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButtonWoman.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    mButtonLGBT.setBackground(getResources().getDrawable(R.drawable.background_button_login_selected));
                }else{
                    if(mGender.equals("LGBT")) {
                        mGender = "";
                        mButtonMan.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonWoman.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                        mButtonLGBT.setBackground(getResources().getDrawable(R.drawable.background_button_login));
                    }
                }
            }
        });
    }


    @Override
    public void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}