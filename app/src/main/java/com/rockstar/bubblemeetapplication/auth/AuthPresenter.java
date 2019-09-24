package com.rockstar.bubblemeetapplication.auth;

import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import com.rockstar.bubblemeetapplication.BaseContract;
import com.rockstar.bubblemeetapplication.model.Utils.APIUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class AuthPresenter implements BaseContract.BasePresenter {

    private CompositeDisposable mDisposable;
    private APIUtils mAPIUtils;
    private AuthActivity mActivity;

    public AuthPresenter(AuthActivity activity) {
        mActivity = activity;
        mAPIUtils = new APIUtils();
    }

    @Override
    public void onStart() {
        mDisposable = new CompositeDisposable();
    }

    public void authorization(String email, String password){
        Log.d("authorization", email);
        Log.d("authorization", password);
        Disposable authDisposable = mAPIUtils.authorization(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<ResponseBody>() {
                    @Override
                    public void onSuccess(ResponseBody value) {
                        Log.d("Response", value.toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        if (e instanceof HttpException) {
                            HttpException exception = (HttpException) e;
                            ResponseBody responseBody = exception.response().errorBody();
                            try {
                                Log.d("Response", responseBody.string());
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
