package com.rockstar.bubblemeetapplication.model.data;

import android.graphics.drawable.Drawable;

public class UserData {
    private int mId;
    private String mName;
    private String mCity;
    private String mPhoto;

    public UserData(int id, String name, String city, String photo) {
        mId = id;
        mName = name;
        mCity = city;
        mPhoto = photo;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getCity() {
        return mCity;
    }

    public void setCity(String city) {
        mCity = city;
    }

    public String getPhoto() {
        return mPhoto;
    }

    public void setPhoto(String photo) {
        mPhoto = photo;
    }
}
