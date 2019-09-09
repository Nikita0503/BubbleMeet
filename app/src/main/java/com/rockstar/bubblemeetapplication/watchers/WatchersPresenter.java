package com.rockstar.bubblemeetapplication.watchers;

import com.rockstar.bubblemeetapplication.BaseContract;

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
    }

    public void fetchWatchers(){

    }

    @Override
    public void onStop() {
        mDisposable.clear();
    }
}
