package com.rockstar.bubblemeetapplication.my_profile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import com.rockstar.bubblemeetapplication.BaseContract;
import com.rockstar.bubblemeetapplication.auth.AuthActivity;
import com.rockstar.bubblemeetapplication.main.MainActivity;
import com.rockstar.bubblemeetapplication.model.Utils.APIUtils;
import com.rockstar.bubblemeetapplication.model.data.UserDataFull;

import org.json.JSONObject;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

import static android.content.Context.MODE_PRIVATE;

public class MyProfilePresenter implements BaseContract.BasePresenter {

    private CompositeDisposable mDisposable;
    private APIUtils mAPIUtils;
    private MyProfileActivity mActivity;

    public MyProfilePresenter(MyProfileActivity activity) {
        mActivity = activity;
        mAPIUtils = new APIUtils();
    }

    @Override
    public void onStart() {
        mDisposable = new CompositeDisposable();
        fetchData();
    }

    public void fetchData(){
        mAPIUtils.setContext(mActivity.getApplicationContext());
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

    @Override
    public void onStop() {
        mDisposable.clear();
    }
}
