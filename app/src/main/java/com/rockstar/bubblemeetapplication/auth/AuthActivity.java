package com.rockstar.bubblemeetapplication.auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.rockstar.bubblemeetapplication.BaseContract;
import com.rockstar.bubblemeetapplication.R;
import com.rockstar.bubblemeetapplication.singup.SignUpActivity;
import com.rockstar.bubblemeetapplication.main.MainActivity;


public class AuthActivity extends AppCompatActivity implements BaseContract.BaseView {

    private AuthPresenter mPresenter;
    private TextView mTextViewDontHaveAccount;
    private TextView mTextViewForgotPassword;
    private EditText mEditTextEmail;
    private EditText mEditTextPassword;
    private Button mButtonLogin;
    private ImageView mImageViewLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        initViews();
        mPresenter = new AuthPresenter(this);
    }

    @Override
    public void onStart(){
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    public void initViews() {
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
        mTextViewDontHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AuthActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(AuthActivity.this, MainActivity.class);
                //startActivity(intent);
                //finish();
                String email = mEditTextEmail.getText().toString();
                String password = mEditTextPassword.getText().toString();
                mPresenter.authorization(email, password);
            }
        });
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        mPresenter.onStop();
    }
}
