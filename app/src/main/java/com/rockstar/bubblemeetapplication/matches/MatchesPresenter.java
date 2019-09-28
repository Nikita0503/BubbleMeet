package com.rockstar.bubblemeetapplication.matches;

import com.rockstar.bubblemeetapplication.BaseContract;
import com.rockstar.bubblemeetapplication.R;
import com.rockstar.bubblemeetapplication.model.data.UserData;
import com.rockstar.bubblemeetapplication.model.data.UserDataFull;

import java.util.ArrayList;

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
        fetchMatches();
    }

    public void fetchMatches(){
        ArrayList<UserDataFull> users = new ArrayList<UserDataFull>();
        mFragment.addUsers(users);
    }

    @Override
    public void onStop() {
        mDisposable.clear();
    }
}
