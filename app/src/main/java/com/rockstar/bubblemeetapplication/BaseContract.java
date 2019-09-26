package com.rockstar.bubblemeetapplication;

public interface BaseContract {
    interface BaseView{
        void initViews();
        void showMessage(String message);
    }
    interface BasePresenter{
        void onStart();
        void onStop();
    }
}
