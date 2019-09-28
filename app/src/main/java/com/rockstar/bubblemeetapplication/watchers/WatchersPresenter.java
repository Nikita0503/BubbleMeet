package com.rockstar.bubblemeetapplication.watchers;

import com.rockstar.bubblemeetapplication.BaseContract;
import com.rockstar.bubblemeetapplication.R;
import com.rockstar.bubblemeetapplication.model.data.UserData;
import com.rockstar.bubblemeetapplication.model.data.UserDataFull;

import java.util.ArrayList;

import io.reactivex.disposables.CompositeDisposable;

public class WatchersPresenter implements BaseContract.BasePresenter {

    private CompositeDisposable mDisposable;
    private WatchersFragment mFragment;

    public WatchersPresenter(WatchersFragment fragment) {
        mFragment = fragment;
    }

    @Override
    public void onStart() {
        mDisposable = new CompositeDisposable();
        fetchWatchers();
    }

    public void fetchWatchers(){
        ArrayList<UserDataFull> users = new ArrayList<UserDataFull>();
        mFragment.addUsers(users);
    }

    @Override
    public void onStop() {
        mDisposable.clear();
    }
}
