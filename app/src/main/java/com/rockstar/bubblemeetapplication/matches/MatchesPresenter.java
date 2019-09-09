package com.rockstar.bubblemeetapplication.matches;

import com.rockstar.bubblemeetapplication.BaseContract;
import com.rockstar.bubblemeetapplication.R;
import com.rockstar.bubblemeetapplication.model.data.UserData;

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
        ArrayList<UserData> users = new ArrayList<UserData>();
        users.add(new UserData(1, "Pudge", "Dota 2", "https://steamuserimages-a.akamaihd.net/ugc/959713744229690446/17C3F0A2F46FCA6B9C39CFE6FBAC22202BEA3897/"));
        users.add(new UserData(2, "BMW", "Germany", "https://cdn.motor1.com/images/mgl/N0b9G/s1/new-bmw-x6-m-renderings.jpg"));
        mFragment.addUsers(users);
    }

    @Override
    public void onStop() {
        mDisposable.clear();
    }
}
