package com.rockstar.bubblemeetapplication.matches;

import com.rockstar.bubblemeetapplication.BaseContract;

import io.reactivex.disposables.CompositeDisposable;

public class MatchesPresenter implements BaseContract.BasePresenter {

    private CompositeDisposable mDisposable;
    private MatchesFragment mFragment;

    public MatchesPresenter(MatchesFragment fragment) {
        mFragment = fragment;
    }

    @Override
    public void onStart() {
        mDisposable = new CompositeDisposable();
    }

    public void fetchMatches(){

    }

    @Override
    public void onStop() {
        mDisposable.clear();
    }
}
