package com.rockstar.bubblemeetapplication.bubble;

import android.content.SharedPreferences;
import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import com.rockstar.bubblemeetapplication.BaseContract;
import com.rockstar.bubblemeetapplication.model.Utils.APIUtils;
import com.rockstar.bubblemeetapplication.model.data.Filter;
import com.rockstar.bubblemeetapplication.model.data.UserData;
import com.rockstar.bubblemeetapplication.model.data.UserDataFull;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

import static android.content.Context.MODE_PRIVATE;

public class BubblePresenter implements BaseContract.BasePresenter {

    private Filter mFilter;
    private BubbleFragment2 mFragment;
    private CompositeDisposable mDisposable;
    private APIUtils mAPIUtils;

    public BubblePresenter(BubbleFragment2 fragment2){
        mFragment = fragment2;
        mAPIUtils = new APIUtils();
    }

    @Override
    public void onStart() {
        mDisposable = new CompositeDisposable();
    }

    public void setFilter(Filter filter){
        mFilter = filter;
    }

    public void fetchAllUsers(){
        Disposable usersDisposable = mAPIUtils.getAllUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<ArrayList<UserDataFull>>() {
                    @Override
                    public void onSuccess(ArrayList<UserDataFull> userData) {
                        //Collections.shuffle(userData);
                        for(int i = 0; i < userData.size(); i++) {
                            SharedPreferences pref = mFragment.getContext().getSharedPreferences("BubbleMeet", MODE_PRIVATE);
                            String email = pref.getString("email", "");
                            if(userData.get(i).email.equals(email)){
                                userData.remove(i);
                                break;
                            }
                        }
                        ArrayList<UserDataFull> users = new ArrayList<UserDataFull>();
                        for(int i = 0; i < 49; i++) {
                            Log.d("Response", userData.get(i).name);
                            Log.d("Response", userData.get(i).avatarSmall);
                            Log.d("Response", userData.get(i).loveCook+"");
                            users.add(userData.get(i));
                        }

                        if(mFilter != null){
                            Log.d("Filter", mFilter.gender);
                            Log.d("Filter", mFilter.age);
                            Log.d("Filter", mFilter.distance);
                            Log.d("Filter", mFilter.eyeColor);
                            Log.d("Filter", mFilter.height);
                            Log.d("Filter", mFilter.smoking);
                            Log.d("Filter", mFilter.married);
                            Log.d("Filter", mFilter.children);
                            Log.d("Filter", mFilter.lookingFor);
                            Log.d("Filter", mFilter.loveToCook);
                            users = filter(users);
                        }else{
                            Log.d("Filter", "null");
                        }
                        mFragment.setUsers(users);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        if (e instanceof HttpException) {
                            HttpException exception = (HttpException) e;
                            ResponseBody responseBody = exception.response().errorBody();
                            try {
                                JSONObject responseError = new JSONObject(responseBody.string());
                                Log.d("TAG", responseError.toString());
                                mFragment.showMessage(responseError.getString("message"));
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                });
        mDisposable.add(usersDisposable);
    }

    private ArrayList<UserDataFull> filter(ArrayList<UserDataFull> users){
        if(!mFilter.gender.equals("")) {
            users = filterGender(users);
        }
        if(!mFilter.age.equals("")){
            users = filterAge(users);
        }
        if(!mFilter.eyeColor.equals("")){
            users = filterEyeColor(users);
        }
        if(!mFilter.height.equals("")){
            users = filterHeight(users);
        }
        return users;
    }

    private ArrayList<UserDataFull> filterGender(ArrayList<UserDataFull> users){
        ArrayList<UserDataFull> userList = new ArrayList<UserDataFull>();
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).gender.equals(mFilter.gender)){
                userList.add(users.get(i));
            }
        }
        return userList;
    }

    private ArrayList<UserDataFull> filterAge(ArrayList<UserDataFull> users){
        ArrayList<UserDataFull> userList = new ArrayList<UserDataFull>();
        for(int i = 0; i < users.size(); i++){
            int age = Integer.parseInt(users.get(i).age);
            int from = Integer.parseInt(mFilter.age.split("-")[0]);
            int to = Integer.parseInt(mFilter.age.split("-")[1]);
            if(age > from && age < to){
                userList.add(users.get(i));
            }
        }
        return userList;
    }

    private ArrayList<UserDataFull> filterDistance(ArrayList<UserDataFull> users){
        ArrayList<UserDataFull> userList = new ArrayList<UserDataFull>();
        //TODO: filter
        return userList;
    }

    private ArrayList<UserDataFull> filterEyeColor(ArrayList<UserDataFull> users){
        ArrayList<UserDataFull> userList = new ArrayList<UserDataFull>();
        for(int i = 0; i < users.size(); i++){
            if(mFilter.eyeColor.equals(users.get(i).eyeColor)){
                userList.add(users.get(i));
            }
        }
        return userList;
    }

    private ArrayList<UserDataFull> filterHeight(ArrayList<UserDataFull> users){
        ArrayList<UserDataFull> userList = new ArrayList<UserDataFull>();
        for(int i = 0; i < users.size(); i++){
            try {
                if(Integer.parseInt(mFilter.height) < Integer.parseInt(users.get(i).height)){
                    userList.add(users.get(i));
                }
            }catch (Exception c){
                c.printStackTrace();
            }

        }
        return userList;
    }

    @Override
    public void onStop() {
        mDisposable.clear();
    }
}
