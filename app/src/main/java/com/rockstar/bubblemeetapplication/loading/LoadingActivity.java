package com.rockstar.bubblemeetapplication.loading;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.rockstar.bubblemeetapplication.BaseContract;
import com.rockstar.bubblemeetapplication.auth.AuthActivity;
import com.rockstar.bubblemeetapplication.R;
import com.rockstar.bubblemeetapplication.filters.FiltersActivity;
import com.rockstar.bubblemeetapplication.main.MainActivity;
import com.rockstar.bubblemeetapplication.my_profile.MyProfileActivity;

import java.util.concurrent.TimeUnit;

public class LoadingActivity extends AppCompatActivity implements BaseContract.BaseView {

    private LoadingPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        initViews();
        mPresenter = new LoadingPresenter(this);
    }

    @Override
    public void onStart(){
        super.onStart();
        mPresenter.onStart();
        SharedPreferences pref = getSharedPreferences("BubbleMeet", MODE_PRIVATE);
        String email = pref.getString("email", "");
        String password = pref.getString("password", "");
        if(email.equals("") || password.equals("")){
            Intent intent = new Intent(this, AuthActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }else{
            mPresenter.authorization(email, password);
        }
    }

    @Override
    public void initViews() {

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
