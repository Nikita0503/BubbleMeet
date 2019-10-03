package com.rockstar.bubblemeetapplication.main;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;

import androidx.core.app.ActivityCompat;

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import com.rockstar.bubblemeetapplication.BaseContract;
import com.rockstar.bubblemeetapplication.model.Utils.APIUtils;
import com.rockstar.bubblemeetapplication.model.data.Coordinates;
import com.rockstar.bubblemeetapplication.model.data.Filter;
import com.rockstar.bubblemeetapplication.model.data.SignUpUserData;
import com.rockstar.bubblemeetapplication.model.data.UserDataFull;
import com.rockstar.bubblemeetapplication.singup.SignUpActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

import static android.content.Context.MODE_PRIVATE;


public class MainPresenter implements BaseContract.BasePresenter {

    private ArrayList<UserDataFull> mUsers;
    private CompositeDisposable mDisposable;
    private APIUtils mAPIUtils;
    private MainActivity mActivity;

    public MainPresenter(MainActivity activity){
        mActivity = activity;
        mAPIUtils = new APIUtils();
    }

    @Override
    public void onStart() {
        mDisposable = new CompositeDisposable();
        if(mUsers == null) {
            fetchData();
            fetchAllUsers();
        }
    }

    public ArrayList<UserDataFull> getUsers(){
        return mUsers;
    }

    public void fetchData(){
        Disposable disposableMyProfile = mAPIUtils.getAllUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<ArrayList<UserDataFull>>() {
                    @Override
                    public void onSuccess(ArrayList<UserDataFull> data) {
                        SharedPreferences pref = mActivity.getSharedPreferences("BubbleMeet", MODE_PRIVATE);
                        String email = pref.getString("email", "");
                        for(int i = 0; i < data.size(); i++){
                            if(data.get(i).email.equals(email)){
                                mActivity.setData(data.get(i));
                                break;
                            }
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                });
        mDisposable.add(disposableMyProfile);
    }

    public void fetchAllUsers(){
        mAPIUtils.setContext(mActivity.getApplicationContext());
        Disposable usersDisposable = mAPIUtils.getAllUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<ArrayList<UserDataFull>>() {
                    @Override
                    public void onSuccess(ArrayList<UserDataFull> userData) {
                        //Collections.shuffle(userData);
                        for(int i = 0; i < userData.size(); i++) {
                            SharedPreferences pref = mActivity.getSharedPreferences("BubbleMeet", MODE_PRIVATE);
                            String email = pref.getString("email", "");
                            if(userData.get(i).email.equals(email)){
                                userData.remove(i);
                                break;
                            }
                        }

                        //ArrayList<UserDataFull> users = new ArrayList<UserDataFull>();
                        //for(int i = 0; i < 49; i++){
                        //    users.add(userData.get(i));
                        //}


                        downloadPhotos(userData);
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
        mDisposable.add(usersDisposable);
    }

    public void downloadPhotos(final ArrayList<UserDataFull> users){
        Disposable data = mAPIUtils.fetchPhotos(users)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<ArrayList<UserDataFull>>() {
                    @Override
                    public void onSuccess(ArrayList<UserDataFull> value) {
                        Collections.shuffle(value);
                        mUsers = value;
                        mActivity.setUsers(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                });
        mDisposable.add(data);
    }

    @Override
    public void onStop() {
        mDisposable.clear();
    }
}
