package com.rockstar.bubblemeetapplication.main;

import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import com.rockstar.bubblemeetapplication.BaseContract;
import com.rockstar.bubblemeetapplication.model.Utils.APIUtils;
import com.rockstar.bubblemeetapplication.model.data.SignUpUserData;
import com.rockstar.bubblemeetapplication.model.data.UserDataFull;
import com.rockstar.bubblemeetapplication.singup.SignUpActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;


public class MainPresenter implements BaseContract.BasePresenter {

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
    }

    @Override
    public void onStop() {
        mDisposable.clear();
    }
}
