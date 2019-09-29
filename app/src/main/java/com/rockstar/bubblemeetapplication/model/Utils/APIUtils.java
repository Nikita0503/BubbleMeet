package com.rockstar.bubblemeetapplication.model.Utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.rockstar.bubblemeetapplication.R;
import com.rockstar.bubblemeetapplication.model.data.Coordinates;
import com.rockstar.bubblemeetapplication.model.data.SignUpUserData;
import com.rockstar.bubblemeetapplication.model.data.UserDataFull;
import com.rockstar.bubblemeetapplication.singup.SignUpActivity;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;
import io.reactivex.Single;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIUtils {
    public static final String BASE_URL = "http://185.25.116.211:11000/";

    public Single<ResponseBody> authorization(String email, String password){
        Retrofit retrofit = getClient(BASE_URL);
        APIService apiService = retrofit.create(APIService.class);
        RequestBody requestBodyEmail = RequestBody.create(MediaType.parse("text/plain"), email);
        RequestBody requestBodyPassword = RequestBody.create(MediaType.parse("text/plain"), password);
        return apiService.authorization(requestBodyEmail, requestBodyPassword);
    }

    public Single<ResponseBody> singUp(SignUpUserData userData){
        Retrofit retrofit = getClient(BASE_URL);
        APIService apiService = retrofit.create(APIService.class);
        RequestBody requestBodyName = RequestBody.create(MediaType.parse("text/plain"), userData.getName());
        RequestBody requestBodyGender = RequestBody.create(MediaType.parse("text/plain"), userData.getGender());
        RequestBody requestBodyYearsOld = RequestBody.create(MediaType.parse("text/plain"), userData.getYearsOld());
        RequestBody requestBodyEmail = RequestBody.create(MediaType.parse("text/plain"), userData.getEmail());
        RequestBody requestBodyPassword = RequestBody.create(MediaType.parse("text/plain"), userData.getPassword());
        RequestBody requestBodyLogin = RequestBody.create(MediaType.parse("text/plain"), userData.getLogin());
        RequestBody requestBodyCity = RequestBody.create(MediaType.parse("text/plain"), userData.getCity());
        RequestBody requestBodyLocation = RequestBody.create(MediaType.parse("text/plain"), userData.getLocation());
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), userData.getMainPhoto());
        //ArrayList<RequestBody> requestPhotos = new ArrayList<RequestBody>();
        //if(userData.getMainPhoto() != null) {
        //
        //    requestPhotos.add(requestFile);
        //}
        //if(userData.getAdditionalPhoto() != null) {
        //    RequestBody requestFile1 = RequestBody.create(MediaType.parse("multipart/form-data"), userData.getAdditionalPhoto());
        //    requestPhotos.add(requestFile1);
        //}
        //if(userData.getAdditionalPhoto2() != null) {
        //    RequestBody requestFile2 = RequestBody.create(MediaType.parse("multipart/form-data"), userData.getAdditionalPhoto2());
        //    requestPhotos.add(requestFile2);
        //}
        //if(userData.getAdditionalPhoto3() != null) {
        //    RequestBody requestFile3 = RequestBody.create(MediaType.parse("multipart/form-data"), userData.getAdditionalPhoto3());
        //    requestPhotos.add(requestFile3);
        //}
        return apiService.signUp(requestBodyName,
                requestBodyGender,
                requestBodyYearsOld,
                requestBodyEmail,
                requestBodyPassword,
                requestFile,
                requestBodyLogin,
                requestBodyCity,
                requestBodyLocation);
    }

    public Single<ResponseBody> singUpFull(SignUpUserData userData){
        Retrofit retrofit = getClient(BASE_URL);
        APIService apiService = retrofit.create(APIService.class);
        RequestBody requestBodyName = RequestBody.create(MediaType.parse("text/plain"), userData.getName());
        RequestBody requestBodyGender = RequestBody.create(MediaType.parse("text/plain"), userData.getGender());
        RequestBody requestBodyYearsOld = RequestBody.create(MediaType.parse("text/plain"), userData.getYearsOld());
        RequestBody requestBodyEmail = RequestBody.create(MediaType.parse("text/plain"), userData.getEmail());
        RequestBody requestBodyPassword = RequestBody.create(MediaType.parse("text/plain"), userData.getPassword());
        RequestBody requestBodyHeight = RequestBody.create(MediaType.parse("text/plain"), userData.getHeight());
        RequestBody requestBodySmoking = RequestBody.create(MediaType.parse("text/plain"), userData.getSmoking());
        RequestBody requestBodyMarried = RequestBody.create(MediaType.parse("text/plain"), userData.getMarried());
        RequestBody requestBodyChildren = RequestBody.create(MediaType.parse("text/plain"), userData.getChildren());
        RequestBody requestBodyCooking = RequestBody.create(MediaType.parse("text/plain"), userData.getCooking());
        RequestBody requestBodyCity = RequestBody.create(MediaType.parse("text/plain"), userData.getCity());
        RequestBody requestBodyLookingFor = RequestBody.create(MediaType.parse("text/plain"), userData.getLookingFor());
        RequestBody requestBodyHobbies = RequestBody.create(MediaType.parse("text/plain"), userData.getHobbies());
        RequestBody requestBodyLogin = RequestBody.create(MediaType.parse("text/plain"), userData.getLogin());
        RequestBody requestBodyLocation = RequestBody.create(MediaType.parse("text/plain"), userData.getLocation());
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), userData.getMainPhoto());
        //ArrayList<RequestBody> requestPhotos = new ArrayList<RequestBody>();
        //if(userData.getMainPhoto() != null) {
        //    RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), userData.getMainPhoto());
        //    requestPhotos.add(requestFile);
        //}
        //if(userData.getAdditionalPhoto() != null) {
        //    RequestBody requestFile1 = RequestBody.create(MediaType.parse("multipart/form-data"), userData.getAdditionalPhoto());
        //    requestPhotos.add(requestFile1);
        //}
        //if(userData.getAdditionalPhoto2() != null) {
        //    RequestBody requestFile2 = RequestBody.create(MediaType.parse("multipart/form-data"), userData.getAdditionalPhoto2());
        //    requestPhotos.add(requestFile2);
        //}
        //if(userData.getAdditionalPhoto3() != null) {
        //    RequestBody requestFile3 = RequestBody.create(MediaType.parse("multipart/form-data"), userData.getAdditionalPhoto3());
        //    requestPhotos.add(requestFile3);
        //}
        return apiService.signUpFull(requestBodyName,
                requestBodyGender,
                requestBodyYearsOld,
                requestBodyEmail,
                requestBodyPassword,
                requestFile,
                requestBodyHeight,
                requestBodySmoking,
                requestBodyMarried,
                requestBodyChildren,
                requestBodyCooking,
                requestBodyCity,
                requestBodyLookingFor,
                requestBodyHobbies,
                requestBodyLogin,
                requestBodyLocation);
    }

    public Single<ArrayList<UserDataFull>> getAllUsers(){
        Retrofit retrofit = getClient(BASE_URL);
        APIService apiService = retrofit.create(APIService.class);
        return apiService.getAllUsers();
    }

    public Single<ArrayList<UserDataFull>> getFavourite(){
        Retrofit retrofit = getClient(BASE_URL);
        APIService apiService = retrofit.create(APIService.class);
        return apiService.getFavourite();
    }

    public Single<ArrayList<UserDataFull>> getFavouriteByMe(){
        Retrofit retrofit = getClient(BASE_URL);
        APIService apiService = retrofit.create(APIService.class);
        return apiService.getFavouriteByMe();
    }

    public Single<ArrayList<UserDataFull>> getWatchers(){
        Retrofit retrofit = getClient(BASE_URL);
        APIService apiService = retrofit.create(APIService.class);
        return apiService.getWatchers();
    }

    public Single<UserDataFull> getMyProfile(){
        Retrofit retrofit = getClient(BASE_URL);
        APIService apiService = retrofit.create(APIService.class);
        return apiService.getMyProfile();
    }

    public Single<ResponseBody> addFavourite(String id){
        Retrofit retrofit = getClient(BASE_URL);
        APIService apiService = retrofit.create(APIService.class);
        RequestBody requestBodyId = RequestBody.create(MediaType.parse("text/plain"), id);
        return apiService.addFavourite(requestBodyId);
    }


    public static Retrofit getClient(String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit;
    }
}
