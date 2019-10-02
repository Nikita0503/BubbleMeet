package com.rockstar.bubblemeetapplication.bubble;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;

import androidx.core.app.ActivityCompat;

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import com.rockstar.bubblemeetapplication.BaseContract;
import com.rockstar.bubblemeetapplication.model.Utils.APIUtils;
import com.rockstar.bubblemeetapplication.model.data.Coordinates;
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

    public BubblePresenter(BubbleFragment2 fragment){
        mFragment = fragment;
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
        mAPIUtils.setContext(mFragment.getContext());
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
                        mFragment.setUsers(users); //TODO:
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
        if(!mFilter.distance.equals("")){
            users = filterDistance(users);
        }
        if(!mFilter.eyeColor.equals("")){
            users = filterEyeColor(users);
        }
        if(!mFilter.height.equals("")){
            users = filterHeight(users);
        }
        if(!mFilter.smoking.equals("")){
            users = filterSmoking(users);
        }
        if(!mFilter.married.equals("")){
            users = filterMarried(users);
        }
        if(!mFilter.children.equals("")){
            users = filterChildren(users);
        }
        if(!mFilter.lookingFor.equals("")){
            users = filterLookingFor(users);
        }
        if(!mFilter.loveToCook.equals("")){
            users = filterLoveToCook(users);
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
        Coordinates currentCoordinates = getCoordinates();
        ArrayList<UserDataFull> userList = new ArrayList<UserDataFull>();
        for(int i = 0; i < users.size(); i++){
            try {
                String coordinates = users.get(i).location;
                double lat = Double.parseDouble(coordinates.split(",")[0]);
                double lon = Double.parseDouble(coordinates.split(",")[1]);
                Coordinates userCoordinates = new Coordinates(lat, lon);
                double difference = differenceBetween(currentCoordinates.latitude, currentCoordinates.longitude,
                        userCoordinates.latitude, userCoordinates.longitude, 'K');
                Log.d("distance", users.get(i).name + " " + difference + "");
                Log.d("distance", currentCoordinates.latitude + " " + currentCoordinates.longitude);
                Log.d("distance", userCoordinates.latitude + " " + userCoordinates.longitude);
                if(difference < Double.parseDouble(mFilter.distance)){
                    userList.add(users.get(i));
                }
            }catch (Exception c){
                c.printStackTrace();
            }
        }
        return userList;
    }

    public Coordinates getCoordinates() {
        Coordinates coord;
        LocationManager lm = (LocationManager) mFragment.getActivity().getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(mFragment.getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(mFragment.getContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return null;
        }
        Location location = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        if(location!=null) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            coord = new Coordinates(latitude, longitude);
            return coord;
        }else{
            return null;
        }
    }

    private double differenceBetween(double lat1, double lon1, double lat2, double lon2, char unit) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        if (unit == 'K') {
            dist = dist * 1.609344;
        } else if (unit == 'N') {
            dist = dist * 0.8684;
        }
        return (dist);
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::  This function converts decimal degrees to radians             :*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::  This function converts radians to decimal degrees             :*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
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

    private ArrayList<UserDataFull> filterSmoking(ArrayList<UserDataFull> users){
        ArrayList<UserDataFull> userList = new ArrayList<UserDataFull>();
        for(int i = 0; i < users.size(); i++){
            if(mFilter.smoking.equals("No matter")){
                userList.add(users.get(i));
            }else {
                if (mFilter.smoking.equals(String.valueOf(users.get(i).smoking))) {
                    userList.add(users.get(i));
                }
            }
        }
        return userList;
    }

    private ArrayList<UserDataFull> filterMarried(ArrayList<UserDataFull> users){
        ArrayList<UserDataFull> userList = new ArrayList<UserDataFull>();
        for(int i = 0; i < users.size(); i++){
            if(mFilter.married.equals("No matter")){
                userList.add(users.get(i));
            }else {
                if (mFilter.married.equals(String.valueOf(users.get(i).marred))) {
                    userList.add(users.get(i));
                }
            }
        }
        return userList;
    }

    private ArrayList<UserDataFull> filterChildren(ArrayList<UserDataFull> users){
        ArrayList<UserDataFull> userList = new ArrayList<UserDataFull>();
        for(int i = 0; i < users.size(); i++){
            if(mFilter.children.equals("No matter")){
                userList.add(users.get(i));
            }else {
                if (mFilter.children.equals(String.valueOf(users.get(i).children))) {
                    userList.add(users.get(i));
                }
            }
        }
        return userList;
    }

    private ArrayList<UserDataFull> filterLookingFor(ArrayList<UserDataFull> users){
        ArrayList<UserDataFull> userList = new ArrayList<UserDataFull>();
        for(int i = 0; i < users.size(); i++){
            if (mFilter.lookingFor.equals(String.valueOf(users.get(i).looking))) {
                userList.add(users.get(i));
            }
        }
        return userList;
    }

    private ArrayList<UserDataFull> filterLoveToCook(ArrayList<UserDataFull> users){
        ArrayList<UserDataFull> userList = new ArrayList<UserDataFull>();
        for(int i = 0; i < users.size(); i++){
            if(mFilter.loveToCook.equals("No matter")){
                userList.add(users.get(i));
            }else {
                if (mFilter.loveToCook.equals(String.valueOf(users.get(i).loveCook))) {
                    userList.add(users.get(i));
                }
            }
        }
        return userList;
    }


    @Override
    public void onStop() {
        mDisposable.clear();
    }
}
