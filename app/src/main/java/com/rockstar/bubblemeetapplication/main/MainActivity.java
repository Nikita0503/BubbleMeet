package com.rockstar.bubblemeetapplication.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.rockstar.bubblemeetapplication.BaseContract;
import com.rockstar.bubblemeetapplication.R;
import com.rockstar.bubblemeetapplication.filters.FiltersActivity;
import com.rockstar.bubblemeetapplication.my_profile.MyProfileActivity;

public class MainActivity extends AppCompatActivity implements BaseContract.BaseView {

    ImageView mImageViewFilters;
    ImageView mImageViewProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    @Override
    public void initViews() {
        mImageViewFilters = (ImageView) findViewById(R.id.imageViewFilters);
        mImageViewFilters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FiltersActivity.class);
                startActivity(intent);
            }
        });
        mImageViewProfile = (ImageView) findViewById(R.id.imageViewProfile);
        mImageViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MyProfileActivity.class);
                startActivity(intent);
            }
        });
    }
}