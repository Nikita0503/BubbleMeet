package com.rockstar.bubblemeetapplication.watchers;

import com.rockstar.bubblemeetapplication.BaseContract;
import com.rockstar.bubblemeetapplication.R;
import com.rockstar.bubblemeetapplication.model.data.UserData;

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
        ArrayList<UserData> users = new ArrayList<UserData>();
        users.add(new UserData(1, "Gaben", "The International", "https://static.life.ru/posts/2016/11/925870/gr/north/174daf5b6fae9241709ac0bd3d8d7ada__980x.jpg"));
        mFragment.addUsers(users);
    }

    @Override
    public void onStop() {
        mDisposable.clear();
    }
}
