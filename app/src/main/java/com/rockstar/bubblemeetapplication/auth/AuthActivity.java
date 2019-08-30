package com.rockstar.bubblemeetapplication.auth;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.rockstar.bubblemeetapplication.R;


public class AuthActivity extends AppCompatActivity {

    ImageView imageViewLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        imageViewLogo = (ImageView) findViewById(R.id.imageViewLogo);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.logo_trans);
        imageViewLogo.startAnimation(animation);


    }
}
