package com.rockstar.bubblemeetapplication.likes;

import com.rockstar.bubblemeetapplication.BaseContract;
import com.rockstar.bubblemeetapplication.R;
import com.rockstar.bubblemeetapplication.model.data.UserData;

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
        ArrayList<UserData> users = new ArrayList<UserData>();
        users.add(new UserData(1, "Louie", "Jetix", "https://cs8.pikabu.ru/post_img/2016/01/26/11/1453833149191625972.jpg"));
        users.add(new UserData(2, "Android", "USA", "https://res.cloudinary.com/lmru/image/upload/f_auto,q_auto,w_500,h_500,c_pad,b_white,d_photoiscoming.png/LMCode/11082822.jpg"));
        users.add(new UserData(3, "Sea", "Ocean", "https://st3.depositphotos.com/16208016/19034/i/1600/depositphotos_190343438-stock-photo-vertical-gardening-harmony-nature-park.jpg"));
        mFragment.addUsers(users);
    }

    @Override
    public void onStop() {
        mDisposable.clear();
    }
}
