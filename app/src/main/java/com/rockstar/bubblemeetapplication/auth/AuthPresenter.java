package com.rockstar.bubblemeetapplication.auth;

import com.rockstar.bubblemeetapplication.BaseContract;

import io.reactivex.disposables.CompositeDisposable;

public class AuthPresenter implements BaseContract.BasePresenter {

    private CompositeDisposable mDisposable;
    private AuthActivity mActivity;

    public AuthPresenter(AuthActivity activity) {
        mActivity = activity;
    }

    @Override
    public void onStart() {
        mDisposable = new CompositeDisposable();
    }

    public void fetchToken(String login, String password){

    }

    @Override
    public void onStop() {
        mDisposable.clear();
    }
}
