package com.rockstar.bubblemeetapplication;

public interface BaseContract {
    interface BaseView{
        void initViews();
    }
    interface BasePresenter{
        void onStart();
        void onStop();
    }
}
