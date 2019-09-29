package com.rockstar.bubblemeetapplication.my_profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.rockstar.bubblemeetapplication.BaseContract;
import com.rockstar.bubblemeetapplication.auth.AuthActivity;
import com.rockstar.bubblemeetapplication.model.data.UserDataFull;

public class MyProfileActivity extends AppCompatActivity implements BaseContract.BaseView {

    ImageView mImageViewBack;
    ImageView mImageViewExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.rockstar.bubblemeetapplication.R.layout.activity_my_profile);
        initViews();
    }

    @Override
    public void initViews() {
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
                Intent intent = new Intent(MyProfileActivity.this, AuthActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
    }

    public void setData(UserDataFull userData){

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}