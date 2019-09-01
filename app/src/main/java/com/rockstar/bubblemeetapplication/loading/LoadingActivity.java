package com.rockstar.bubblemeetapplication.loading;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.rockstar.bubblemeetapplication.BaseContract;
import com.rockstar.bubblemeetapplication.auth.AuthActivity;
import com.rockstar.bubblemeetapplication.R;
import com.rockstar.bubblemeetapplication.filters.FiltersActivity;
import com.rockstar.bubblemeetapplication.main.MainActivity;

import java.util.concurrent.TimeUnit;

public class LoadingActivity extends AppCompatActivity implements BaseContract.BaseView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        initViews();
    }

    @Override
    public void initViews() {
        Intent intent = new Intent(this, AuthActivity.class);
        startActivity(intent);
        finish();
    }
}
