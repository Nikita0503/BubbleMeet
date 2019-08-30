package com.rockstar.bubblemeetapplication.auth;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.rockstar.bubblemeetapplication.R;


public class AuthActivity extends AppCompatActivity {

    TextView mTextViewDontHaveAccount;
    TextView mTextViewForgotPassword;
    EditText mEditTextEmail;
    EditText mEditTextPassword;
    Button mButtonLogin;
    ImageView mImageViewLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        mTextViewDontHaveAccount = (TextView) findViewById(R.id.textViewDontHaveAccount);
        mTextViewForgotPassword = (TextView) findViewById(R.id.textViewForgotPassword);
        mEditTextEmail = (EditText) findViewById(R.id.editTextEmail);
        mEditTextPassword = (EditText) findViewById(R.id.editTextPassword);
        mButtonLogin = (Button) findViewById(R.id.buttonLogin);
        mImageViewLogo = (ImageView) findViewById(R.id.imageViewLogo);
        Animation animationLogo = AnimationUtils.loadAnimation(this, R.anim.logo_trans);
        mImageViewLogo.startAnimation(animationLogo);
        Animation animationOtherViews = AnimationUtils.loadAnimation(AuthActivity.this, R.anim.alpha_auth_views);
        mTextViewDontHaveAccount.startAnimation(animationOtherViews);
        mTextViewForgotPassword.startAnimation(animationOtherViews);
        mEditTextEmail.startAnimation(animationOtherViews);
        mEditTextPassword.startAnimation(animationOtherViews);
        mButtonLogin.startAnimation(animationOtherViews);

    }
}
