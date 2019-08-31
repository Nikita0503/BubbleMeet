package com.rockstar.bubblemeetapplication.loading;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.rockstar.bubblemeetapplication.auth.AuthActivity;
import com.rockstar.bubblemeetapplication.R;
import com.rockstar.bubblemeetapplication.filters.FiltersActivity;
import com.rockstar.bubblemeetapplication.main.MainActivity;

public class LoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
