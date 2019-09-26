package com.rockstar.bubblemeetapplication.singup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import com.rockstar.bubblemeetapplication.BaseContract;
import com.rockstar.bubblemeetapplication.R;
import com.rockstar.bubblemeetapplication.singup.DialogSignUpFragment;
import com.squareup.picasso.Picasso;

import java.io.File;

public class SignUpActivity extends AppCompatActivity implements BaseContract.BaseView {

    private DialogSignUpFragment mDialogFragment;
    private SignUpPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);
        initViews();
        mPresenter = new SignUpPresenter(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.onStart();
        Log.d("123", "MainActivity: onStart()");
    }

    @Override
    public void initViews() {
        mDialogFragment = new DialogSignUpFragment();
        mDialogFragment.show(getSupportFragmentManager(), "123");
    }

    public void setName(String name){
        mPresenter.setName(name);
    }

    public void setGender(String gender){
        mPresenter.setGender(gender);
    }

    public void setYearsOld(String age){
        mPresenter.setYearsOld(age);
    }

    public void setEmail(String email){
        mPresenter.setEmail(email);
    }

    public void setPassword(String password){
        mPresenter.setPassword(password);
    }

    public void setHeight(String height){
        mPresenter.setHeight(height);
    }

    public void setSmoking(String smoking){
        mPresenter.setSmoking(smoking);
    }

    public void setMarried(String married){
        mPresenter.setMarried(married);
    }

    public void setChildren(String children){
        mPresenter.setChildren(children);
    }

    public void setCooking(String cooking){
        mPresenter.setCooking(cooking);
    }

    public void setCity(String city){
        mPresenter.setCity(city);
    }

    public void setLookingFor(String lookingFor){
        mPresenter.setLookingFor(lookingFor);
    }

    public void setHobbies(String hobbies){
        mPresenter.setHobbies(hobbies);
    }

    public void setConfirmPassword(String confirmPassword){
        mPresenter.setConfirmPassword(confirmPassword);
    }

    public void signUpNewUser(){
        mPresenter.sendNewUser();
    }

    public void signUpNewUserFull(){
        mPresenter.sendNewUserFull();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        if (resultCode == RESULT_OK) {
            Uri selectedImage = imageReturnedIntent.getData();
            File file = new File(getRealPathFromUri(getApplicationContext(), selectedImage));
            mDialogFragment.addPhoto(requestCode, file);
            mPresenter.setPhoto(requestCode, file);
        }
    }

    private String getRealPathFromUri(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onStop();
        Log.d("123", "MainActivity: onDestroy()");
    }
}
