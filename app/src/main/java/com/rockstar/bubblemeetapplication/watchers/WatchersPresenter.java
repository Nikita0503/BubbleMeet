package com.rockstar.bubblemeetapplication.watchers;

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

public class WatchersPresenter implements BaseContract.BasePresenter {

    private CompositeDisposable mDisposable;
    private APIUtils mAPIUtils;
    private WatchersFragment mFragment;

    public WatchersPresenter(WatchersFragment fragment) {
        mFragment = fragment;
        mAPIUtils = new APIUtils();
    }

    @Override
    public void onStart() {
        mDisposable = new CompositeDisposable();
        fetchWatchers();
    }

    public void fetchWatchers(){
        mAPIUtils.setContext(mFragment.getContext());
        Log.d("userWatchers", "user watchers...");
        Disposable disposableWatchers = mAPIUtils.getWatchers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<ArrayList<UserDataFull>>() {
                    @Override
                    public void onSuccess(ArrayList<UserDataFull> users) {
                        if(users.size() != 0){
                            users = removeDuplicates(users);
                        }
                        mFragment.setUsers(users);
                        Log.d("userWatchers", users.size()+"");
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException) {
                            HttpException exception = (HttpException) e;
                            ResponseBody responseBody = exception.response().errorBody();
                            try {
                                JSONObject responseError = new JSONObject(responseBody.string());
                                Log.d("userWatchers", responseError.toString());
                                mFragment.showMessage(responseError.getString("message"));
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                });
        mDisposable.add(disposableWatchers);
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
