package com.rockstar.bubblemeetapplication.bubble;

import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import com.rockstar.bubblemeetapplication.BaseContract;
import com.rockstar.bubblemeetapplication.model.Utils.APIUtils;
import com.rockstar.bubblemeetapplication.model.data.UserDataFull;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class BubblePresenter implements BaseContract.BasePresenter {

    private BubbleFragment2 mFragment;
    private CompositeDisposable mDisposable;
    private APIUtils mAPIUtils;

    public BubblePresenter(BubbleFragment2 fragment2){
        mFragment = fragment2;
        mAPIUtils = new APIUtils();
    }

    @Override
    public void onStart() {
        mDisposable = new CompositeDisposable();
    }

    public void fetchAllUsers(){
        Disposable usersDisposable = mAPIUtils.getAllUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<ArrayList<UserDataFull>>() {
                    @Override
                    public void onSuccess(ArrayList<UserDataFull> userData) {
                        Collections.shuffle(userData);
                        for(int i = 0; i < userData.size(); i++) {
                            Log.d("Response", userData.get(i).name);
                            Log.d("Response", userData.get(i).avatarSmall);
                        }
                        mFragment.setUsers(userData);
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
                                mFragment.showMessage(responseError.getString("message"));
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                });
        mDisposable.add(usersDisposable);
    }

    @Override
    public void onStop() {
        mDisposable.clear();
    }
}
