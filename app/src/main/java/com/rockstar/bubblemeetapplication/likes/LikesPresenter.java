package com.rockstar.bubblemeetapplication.likes;

import com.rockstar.bubblemeetapplication.BaseContract;
import com.rockstar.bubblemeetapplication.R;
import com.rockstar.bubblemeetapplication.model.data.UserData;
import com.rockstar.bubblemeetapplication.model.data.UserDataFull;

import java.util.ArrayList;

import io.reactivex.disposables.CompositeDisposable;

public class LikesPresenter implements BaseContract.BasePresenter {

    private CompositeDisposable mDisposable;
    private LikesFragment mFragment;

    public LikesPresenter(LikesFragment fragment) {
        mFragment = fragment;
    }

    @Override
    public void onStart() {
        mDisposable = new CompositeDisposable();
        fetchLikes();
    }

    public void fetchLikes(){
        ArrayList<UserDataFull> users = new ArrayList<UserDataFull>();
        mFragment.addUsers(users);
    }

    @Override
    public void onStop() {
        mDisposable.clear();
    }
}
