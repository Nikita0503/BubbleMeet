package com.rockstar.bubblemeetapplication.singup;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import com.rockstar.bubblemeetapplication.BaseContract;
import com.rockstar.bubblemeetapplication.main.MainActivity;
import com.rockstar.bubblemeetapplication.model.Utils.APIUtils;
import com.rockstar.bubblemeetapplication.model.data.SignUpUserData;

import java.io.File;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

import com.rockstar.bubblemeetapplication.singup.Fragment1;

import org.json.JSONObject;

public class SignUpPresenter implements BaseContract.BasePresenter {

    public static final int GALLERY_MAIN_PHOTO = 0;
    public static final int GALLERY_ADDITIONAL_PHOTO_1 = 1;
    public static final int GALLERY_ADDITIONAL_PHOTO_2 = 2;
    public static final int GALLERY_ADDITIONAL_PHOTO_3 = 3;

    private SignUpUserData mUserData;
    private CompositeDisposable mDisposable;
    private APIUtils mAPIUtils;
    private SignUpActivity mActivity;

    public SignUpPresenter(SignUpActivity activity){
        mUserData = new SignUpUserData();
        mActivity = activity;
        mAPIUtils = new APIUtils();
    }

    @Override
    public void onStart() {
        mDisposable = new CompositeDisposable();
    }

    public void setEmail(String email) {
        mUserData.setEmail(email);
        setLogin(email);
    }

    public void setPassword(String password) {
        mUserData.setPassword(password);
    }

    public void setConfirmPassword(String confirmPassword) {
        mUserData.setConfirmPassword(confirmPassword);
    }

    public void setGender(String gender) {
        mUserData.setGender(gender);
    }

    public void setName(String name) {
        mUserData.setName(name);
    }

    public void setYearsOld(String yearsOld) {
        mUserData.setYearsOld(yearsOld);
    }

    public void setPhoto(int number, File photo){
        switch (number){
            case GALLERY_MAIN_PHOTO:
                setMainPhoto(photo);
                break;
            case GALLERY_ADDITIONAL_PHOTO_1:
                setAdditionalPhoto1(photo);
                break;
            case GALLERY_ADDITIONAL_PHOTO_2:
                setAdditionalPhoto2(photo);
                break;
            case GALLERY_ADDITIONAL_PHOTO_3:
                setAdditionalPhoto3(photo);
                break;
        }
    }

    public void setMainPhoto(File mainPhoto) {
        mUserData.setMainPhoto(mainPhoto);
    }

    public void setAdditionalPhoto1(File additionalPhoto1) {
        mUserData.setAdditionalPhoto1(additionalPhoto1);
    }

    public void setAdditionalPhoto2(File additionalPhoto2) {
        mUserData.setAdditionalPhoto2(additionalPhoto2);
    }

    public void setAdditionalPhoto3(File additionalPhoto3) {
        mUserData.setAdditionalPhoto3(additionalPhoto3);
    }

    public void setHeight(String height) {
        mUserData.setHeight(height);
    }

    public void setSmoking(String smoking) {
        mUserData.setSmoking(smoking);
    }

    public void setMarried(String married) {
        mUserData.setMarried(married);
    }

    public void setChildren(String children) {
        mUserData.setChildren(children);
    }

    public void setCooking(String cooking) {
        mUserData.setCooking(cooking);
    }

    public void setCity(String city) {
        mUserData.setCity(city);
    }

    public void setLookingFor(String lookingFor) {
        mUserData.setLookingFor(lookingFor);
    }

    public void setHobbies(String hobbies) {
        mUserData.setHobbies(hobbies);
    }

    private void setLogin(String email){
        String login = email.split("@")[0];
        mUserData.setLogin(login);
    }

    public void setLocation(String location){
        mUserData.setLocation(location);
    }

    public void sendNewUser(){
        mUserData.setCity("");
        Log.d("signUpData", mUserData.getName());
        Log.d("signUpData", mUserData.getGender());
        Log.d("signUpData", mUserData.getYearsOld());
        Log.d("signUpData", mUserData.getEmail());
        Log.d("signUpData", mUserData.getPassword());
        Log.d("signUpData", mUserData.getConfirmPassword());
        Log.d("signUpData", mUserData.getLogin());
        Log.d("signUpData", mUserData.getCity());
        Log.d("signUpData", mUserData.getLocation());
        Disposable authDisposable = mAPIUtils.singUp(mUserData)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<ResponseBody>() {
                    @Override
                    public void onSuccess(ResponseBody value) {
                        Log.d("Response", value.toString());
                        Intent intent = new Intent(mActivity, MainActivity.class);
                        mActivity.startActivity(intent);
                        mActivity.finish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        if (e instanceof HttpException) {
                            HttpException exception = (HttpException) e;
                            ResponseBody responseBody = exception.response().errorBody();
                            try {
                                JSONObject responseError = new JSONObject(responseBody.string());
                                Log.d("TAG", responseError.toString());
                                mActivity.showMessage(responseError.getString("message"));
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                });
        mDisposable.add(authDisposable);
    }

    public void sendNewUserFull(){
        Log.d("signUpData", mUserData.getName());
        Log.d("signUpData", mUserData.getGender());
        Log.d("signUpData", mUserData.getYearsOld());
        Log.d("signUpData", mUserData.getEmail());
        Log.d("signUpData", mUserData.getPassword());
        Log.d("signUpData", mUserData.getConfirmPassword());
        Log.d("signUpData", mUserData.getHeight());
        Log.d("signUpData", mUserData.getSmoking());
        Log.d("signUpData", mUserData.getMarried());
        Log.d("signUpData", mUserData.getChildren());
        Log.d("signUpData", mUserData.getCooking());
        Log.d("signUpData", mUserData.getLookingFor());
        Log.d("signUpData", mUserData.getHobbies());
        Log.d("signUpData", mUserData.getLogin());
        Log.d("signUpData", mUserData.getCity());
        Log.d("signUpData", mUserData.getLocation());

        Disposable authDisposable = mAPIUtils.singUpFull(mUserData)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<ResponseBody>() {
                    @Override
                    public void onSuccess(ResponseBody value) {
                        Log.d("Response", value.toString());
                        Intent intent = new Intent(mActivity, MainActivity.class);
                        mActivity.startActivity(intent);
                        mActivity.finish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        if (e instanceof HttpException) {
                            HttpException exception = (HttpException) e;
                            ResponseBody responseBody = exception.response().errorBody();
                            try {
                                JSONObject responseError = new JSONObject(responseBody.string());
                                Log.d("TAG", responseError.toString());
                                mActivity.showMessage(responseError.getString("message"));
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                });
        mDisposable.add(authDisposable);
    }

    @Override
    public void onStop() {
        mDisposable.clear();
    }
}
