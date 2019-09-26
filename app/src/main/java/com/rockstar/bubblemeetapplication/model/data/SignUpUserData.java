package com.rockstar.bubblemeetapplication.model.data;

import java.io.File;

public class SignUpUserData {
    private String mEmail;
    private String mPassword;
    private String mConfirmPassword;
    private String mGender;
    private String mName;
    private String mYearsOld;
    private File mMainPhoto;
    private File mAdditionalPhoto1;
    private File mAdditionalPhoto2;
    private File mAdditionalPhoto3;
    //Additional information
    private String mHeight;
    private String mSmoking;
    private String mMarried;
    private String mChildren;
    private String mCooking;
    private String mCity;
    private String mLookingFor;
    private String mHobbies;

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public String getConfirmPassword() {
        return mConfirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        mConfirmPassword = confirmPassword;
    }

    public String getGender() {
        return mGender;
    }

    public void setGender(String gender) {
        mGender = gender;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getYearsOld() {
        return mYearsOld;
    }

    public void setYearsOld(String yearsOld) {
        mYearsOld = yearsOld;
    }

    public File getMainPhoto() {
        return mMainPhoto;
    }

    public void setMainPhoto(File mainPhoto) {
        mMainPhoto = mainPhoto;
    }

    public File getAdditionalPhoto() {
        return mAdditionalPhoto1;
    }

    public void setAdditionalPhoto1(File additionalPhoto1) {
        mAdditionalPhoto1 = additionalPhoto1;
    }

    public File getAdditionalPhoto2() {
        return mAdditionalPhoto2;
    }

    public void setAdditionalPhoto2(File additionalPhoto2) {
        mAdditionalPhoto2 = additionalPhoto2;
    }

    public File getAdditionalPhoto3() {
        return mAdditionalPhoto3;
    }

    public void setAdditionalPhoto3(File additionalPhoto3) {
        mAdditionalPhoto3 = additionalPhoto3;
    }

    public String getHeight() {
        return mHeight;
    }

    public void setHeight(String height) {
        mHeight = height;
    }

    public String getSmoking() {
        return mSmoking;
    }

    public void setSmoking(String smoking) {
        mSmoking = smoking;
    }

    public String getMarried() {
        return mMarried;
    }

    public void setMarried(String married) {
        mMarried = married;
    }

    public String getChildren() {
        return mChildren;
    }

    public void setChildren(String children) {
        mChildren = children;
    }

    public String getCooking() {
        return mCooking;
    }

    public void setCooking(String cooking) {
        mCooking = cooking;
    }

    public String getCity() {
        return mCity;
    }

    public void setCity(String city) {
        mCity = city;
    }

    public String getLookingFor() {
        return mLookingFor;
    }

    public void setLookingFor(String lookingFor) {
        mLookingFor = lookingFor;
    }

    public String getHobbies() {
        return mHobbies;
    }

    public void setHobbies(String hobbies) {
        mHobbies = hobbies;
    }
}
