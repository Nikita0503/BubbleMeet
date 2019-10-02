package com.rockstar.bubblemeetapplication.profile_preview;

import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import com.rockstar.bubblemeetapplication.BaseContract;
import com.rockstar.bubblemeetapplication.model.Utils.APIUtils;
import com.rockstar.bubblemeetapplication.model.data.UserDataFull;
import com.rockstar.bubblemeetapplication.profile.ProfileFragment;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class ProfilePreviewPresenter implements BaseContract.BasePresenter {

    private CompositeDisposable mDisposable;
    private APIUtils mAPIUtils;
    private ProfilePreviewFragment mFragment;

    public ProfilePreviewPresenter(ProfilePreviewFragment fragment){
        mFragment = fragment;
        mAPIUtils = new APIUtils();
    }

    @Override
    public void onStart() {
        mDisposable = new CompositeDisposable();
    }

    public void addFavourite(int idInteger){
        mAPIUtils.setContext(mFragment.getContext());
        final String id = String.valueOf(idInteger);
        Log.d("addFavourite", id + " in process...");
        Disposable disposableAddFavourite = mAPIUtils.addFavourite(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<ResponseBody>() {

                    @Override
                    public void onSuccess(ResponseBody value) {
                        Log.d("addFavourite", value.toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException) {
                            HttpException exception = (HttpException) e;
                            ResponseBody responseBody = exception.response().errorBody();
                            try {
                                JSONObject responseError = new JSONObject(responseBody.string());
                                Log.d("addFavourite", responseError.toString());
                                mFragment.showMessage(responseError.getString("message"));
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                });
        mDisposable.add(disposableAddFavourite);
    }

    public void addTemporaryFavourite(int idInteger){
        mAPIUtils.setContext(mFragment.getContext());
        final String id = String.valueOf(idInteger);
        Log.d("addTemporaryFavourite", id + " in process...");
        Disposable disposableAddFavourite = mAPIUtils.addTemporaryFavourite(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<ResponseBody>() {

                    @Override
                    public void onSuccess(ResponseBody value) {
                        Log.d("addFavourite", value.toString());
                        fetchTemporaryFavourite();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException) {
                            HttpException exception = (HttpException) e;
                            ResponseBody responseBody = exception.response().errorBody();
                            try {
                                JSONObject responseError = new JSONObject(responseBody.string());
                                Log.d("addFavourite", responseError.toString());
                                mFragment.showMessage(responseError.getString("message"));
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                });
        mDisposable.add(disposableAddFavourite);
    }


    public void fetchTemporaryFavourite(){
        mAPIUtils.setContext(mFragment.getContext());
        Disposable disposableTemporaryFavourite = mAPIUtils.getTemporaryFavourite()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<ArrayList<UserDataFull>>() {

                    @Override
                    public void onSuccess(ArrayList<UserDataFull> users) {
                        if(users.size() != 0){
                            users = removeDuplicates(users);
                        }
                        Collections.reverse(users);
                        mFragment.setTemporaryFavourite(users);
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException) {
                            HttpException exception = (HttpException) e;
                            ResponseBody responseBody = exception.response().errorBody();
                            try {
                                JSONObject responseError = new JSONObject(responseBody.string());
                                Log.d("addFavourite", responseError.toString());
                                mFragment.showMessage(responseError.getString("message"));
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                });
        mDisposable.add(disposableTemporaryFavourite);
    }

    public ArrayList<UserDataFull> removeDuplicates(ArrayList<UserDataFull> users) {
        ArrayList<UserDataFull> unique = new ArrayList<UserDataFull>();
        unique.add(users.get(0));
        for(int i = 1; i < users.size(); i++){
            boolean isUnique = true;
            for(int j = 0; j < unique.size(); j++){
                if(users.get(i).id == unique.get(j).id){
                    isUnique = false;
                }
            }
            if(isUnique){
                unique.add(users.get(i));
            }
        }
        return unique;
    }

    @Override
    public void onStop() {
        mDisposable.clear();
    }
}
