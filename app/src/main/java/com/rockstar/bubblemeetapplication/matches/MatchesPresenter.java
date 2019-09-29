package com.rockstar.bubblemeetapplication.matches;

import com.rockstar.bubblemeetapplication.BaseContract;
import com.rockstar.bubblemeetapplication.R;
import com.rockstar.bubblemeetapplication.model.Utils.APIUtils;
import com.rockstar.bubblemeetapplication.model.data.UserData;
import com.rockstar.bubblemeetapplication.model.data.UserDataFull;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

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
        Disposable disposableMatches = mAPIUtils.getFavourite()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<ArrayList<UserDataFull>>() {
                    @Override
                    public void onSuccess(ArrayList<UserDataFull> users) {
                        //mFragment.setUsers(users);
                        mUsersWhoLikeMe = users;
                        fetchFavoriteByMe();
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
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
                        mUsersWhoILike = users;
                        findCoincidences();
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
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

    @Override
    public void onStop() {
        mDisposable.clear();
    }
}
