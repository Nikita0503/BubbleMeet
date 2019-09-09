package com.rockstar.bubblemeetapplication.likes;

import com.rockstar.bubblemeetapplication.BaseContract;

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
    }

    public void fetchLikes(){

    }

    @Override
    public void onStop() {
        mDisposable.clear();
    }
}
