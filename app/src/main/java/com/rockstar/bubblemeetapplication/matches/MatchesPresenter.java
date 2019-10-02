package com.rockstar.bubblemeetapplication.matches;

import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import com.rockstar.bubblemeetapplication.BaseContract;
import com.rockstar.bubblemeetapplication.R;
import com.rockstar.bubblemeetapplication.model.Utils.APIUtils;
import com.rockstar.bubblemeetapplication.model.data.UserData;
import com.rockstar.bubblemeetapplication.model.data.UserDataFull;

import org.json.JSONObject;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class MatchesPresenter implements BaseContract.BasePresenter {

    private ArrayList<UserDataFull> mUsersWhoLikeMe;
    private ArrayList<UserDataFull> mUsersWhoILike;
    private CompositeDisposable mDisposable;
    private APIUtils mAPIUtils;
    private MatchesFragment mFragment;

    public MatchesPresenter(MatchesFragment fragment) {
        mFragment = fragment;
        mAPIUtils = new APIUtils();
    }

    @Override
    public void onStart() {
        mDisposable = new CompositeDisposable();
        fetchMatches();
    }

    public void fetchMatches(){
        mAPIUtils.setContext(mFragment.getContext());
        Disposable disposableMatches = mAPIUtils.getFavourite()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<ArrayList<UserDataFull>>() {
                    @Override
                    public void onSuccess(ArrayList<UserDataFull> users) {
                        //mFragment.setUsers(users);
                        if(users.size() != 0){
                            users = removeDuplicates(users);
                        }
                        mUsersWhoLikeMe = users;
                        fetchFavoriteByMe();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException) {
                            HttpException exception = (HttpException) e;
                            ResponseBody responseBody = exception.response().errorBody();
                            try {
                                JSONObject responseError = new JSONObject(responseBody.string());
                                Log.d("userMatches", responseError.toString());
                                mFragment.showMessage(responseError.getString("message"));
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                });
        mDisposable.add(disposableMatches);
    }

    private void fetchFavoriteByMe(){
        Disposable disposableByMe = mAPIUtils.getFavouriteByMe()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<ArrayList<UserDataFull>>() {
                    @Override
                    public void onSuccess(ArrayList<UserDataFull> users) {
                        if(users.size() != 0){
                            users = removeDuplicates(users);
                        }
                        mUsersWhoILike = users;
                        findCoincidences();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException) {
                            HttpException exception = (HttpException) e;
                            ResponseBody responseBody = exception.response().errorBody();
                            try {
                                JSONObject responseError = new JSONObject(responseBody.string());
                                Log.d("FavoritByMe", responseError.toString());
                                mFragment.showMessage(responseError.getString("message"));
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                });
        mDisposable.add(disposableByMe);
    }

    private void findCoincidences(){
        ArrayList<UserDataFull> users = new ArrayList<UserDataFull>();
        for(int i = 0; i < mUsersWhoILike.size(); i++){
            for(int j = 0; j < mUsersWhoLikeMe.size(); j++){
                if(mUsersWhoILike.get(i).id == mUsersWhoLikeMe.get(j).id){
                    users.add(mUsersWhoILike.get(i));
                    break;
                }
            }
        }
        mFragment.setUsers(users);
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
