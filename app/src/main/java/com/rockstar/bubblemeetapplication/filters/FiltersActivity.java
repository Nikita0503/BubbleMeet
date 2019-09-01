package com.rockstar.bubblemeetapplication.filters;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.rockstar.bubblemeetapplication.BaseContract;
import com.rockstar.bubblemeetapplication.R;
import com.rockstar.bubblemeetapplication.auth.AuthActivity;

public class FiltersActivity extends AppCompatActivity implements BaseContract.BaseView {

    ImageView mImageViewBack;
    ImageView mImageViewFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_filters);
        initViews();
    }

    @Override
    public void initViews() {
        mImageViewBack = (ImageView) findViewById(R.id.imageViewBack);
        mImageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}