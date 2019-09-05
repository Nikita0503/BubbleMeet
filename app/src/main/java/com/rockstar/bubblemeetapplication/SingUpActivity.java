package com.rockstar.bubblemeetapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.rockstar.bubblemeetapplication.singup.DialogSignUpFragment;

public class SingUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);
        DialogSignUpFragment dialogSignUpFragment = new DialogSignUpFragment();
        dialogSignUpFragment.show(getSupportFragmentManager(), "123");
    }
}
