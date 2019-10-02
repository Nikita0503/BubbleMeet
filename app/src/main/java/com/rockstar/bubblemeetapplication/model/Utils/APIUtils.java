package com.rockstar.bubblemeetapplication.model.Utils;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.rockstar.bubblemeetapplication.R;
import com.rockstar.bubblemeetapplication.model.data.Coordinates;
import com.rockstar.bubblemeetapplication.model.data.SignUpUserData;
import com.rockstar.bubblemeetapplication.model.data.UserDataFull;
import com.rockstar.bubblemeetapplication.singup.SignUpActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;
import io.reactivex.Single;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;

public class APIUtils {
    public static final String BASE_URL = "http://185.25.116.211:11000/";
    private Retrofit mRetrofit;
    private Context mContext;

    public void setContext(Context context){
        mContext = context;
    }

    public Single<Response<ResponseBody>> authorization(String email, String password){
        if(mRetrofit == null) {
            mRetrofit = getClient(BASE_URL);
        }
        APIService apiService = mRetrofit.create(APIService.class);

        RequestBody requestBodyEmail = RequestBody.create(MediaType.parse("text/plain"), email);
        RequestBody requestBodyPassword = RequestBody.create(MediaType.parse("text/plain"), password);
        return apiService.authorization(requestBodyEmail, requestBodyPassword);
    }

    public Single<Response<ResponseBody>> singUp(SignUpUserData userData){
        if(mRetrofit == null) {
            mRetrofit = getClient(BASE_URL);
        }
        APIService apiService = mRetrofit.create(APIService.class);
        RequestBody requestBodyName = RequestBody.create(MediaType.parse("text/plain"), userData.getName());
        RequestBody requestBodyGender = RequestBody.create(MediaType.parse("text/plain"), userData.getGender());
        RequestBody requestBodyYearsOld = RequestBody.create(MediaType.parse("text/plain"), userData.getYearsOld());
        RequestBody requestBodyEmail = RequestBody.create(MediaType.parse("text/plain"), userData.getEmail());
        RequestBody requestBodyPassword = RequestBody.create(MediaType.parse("text/plain"), userData.getPassword());
        RequestBody requestBodyLogin = RequestBody.create(MediaType.parse("text/plain"), userData.getLogin());
        RequestBody requestBodyCity = RequestBody.create(MediaType.parse("text/plain"), userData.getCity());
        RequestBody requestBodyLocation = RequestBody.create(MediaType.parse("text/plain"), userData.getLocation());
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), userData.getMainPhoto());
        MultipartBody.Part avatarSmall = MultipartBody.Part.createFormData("avatarSmall", userData.getLogin(), requestFile);
        MultipartBody.Part avatarFull = MultipartBody.Part.createFormData("avatarFull", userData.getLogin(), requestFile);
        return apiService.signUp(requestBodyName,
                    requestBodyGender,
                    requestBodyYearsOld,
                    requestBodyEmail,
                    requestBodyPassword,
                    avatarSmall,
                    avatarFull,
                    requestBodyLogin,
                    requestBodyCity,
                    requestBodyLocation);

    }

    public Single<Response<ResponseBody>> singUpFull(SignUpUserData userData){
        if(mRetrofit == null) {
            mRetrofit = getClient(BASE_URL);
        }
        APIService apiService = mRetrofit.create(APIService.class);
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
        MultipartBody.Part avatarSmall = MultipartBody.Part.createFormData("avatarSmall", userData.getLogin(), requestFile);
        MultipartBody.Part avatarFull = MultipartBody.Part.createFormData("avatarFull", userData.getLogin(), requestFile);
        return apiService.signUpFull(requestBodyName,
                requestBodyGender,
                requestBodyYearsOld,
                requestBodyEmail,
                requestBodyPassword,
                avatarSmall,
                avatarFull,
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
        if(mRetrofit == null) {
            mRetrofit = getClient(BASE_URL);
        }
        APIService apiService = mRetrofit.create(APIService.class);
        return apiService.getAllUsers();
    }

    public Single<ArrayList<UserDataFull>> getTemporaryFavourite(){
        SharedPreferences pref = mContext.getSharedPreferences("BubbleMeet", MODE_PRIVATE);
        String session = pref.getString("session", "");
        if(mRetrofit == null) {
            mRetrofit = getClient(BASE_URL);
        }
        APIService apiService = mRetrofit.create(APIService.class);
        return apiService.getTemporaryFavourite(session);
    }

    public Single<ArrayList<UserDataFull>> getFavourite(){
        SharedPreferences pref = mContext.getSharedPreferences("BubbleMeet", MODE_PRIVATE);
        String session = pref.getString("session", "");
        if(mRetrofit == null) {
            mRetrofit = getClient(BASE_URL);
        }
        APIService apiService = mRetrofit.create(APIService.class);
        return apiService.getFavourite(session);
    }

    public Single<ArrayList<UserDataFull>> getFavouriteByMe(){
        SharedPreferences pref = mContext.getSharedPreferences("BubbleMeet", MODE_PRIVATE);
        String session = pref.getString("session", "");
        if(mRetrofit == null) {
            mRetrofit = getClient(BASE_URL);
        }
        APIService apiService = mRetrofit.create(APIService.class);
        return apiService.getFavouriteByMe(session);
    }

    public Single<ArrayList<UserDataFull>> getWatchers(){
        SharedPreferences pref = mContext.getSharedPreferences("BubbleMeet", MODE_PRIVATE);
        String session = pref.getString("session", "");
        if(mRetrofit == null) {
            mRetrofit = getClient(BASE_URL);
        }
        APIService apiService = mRetrofit.create(APIService.class);
        return apiService.getWatchers(session);
    }

    public Single<ResponseBody> addFavourite(String id){
        SharedPreferences pref = mContext.getSharedPreferences("BubbleMeet", MODE_PRIVATE);
        String session = pref.getString("session", "");
        if(mRetrofit == null) {
            mRetrofit = getClient(BASE_URL);
        }
        APIService apiService = mRetrofit.create(APIService.class);
        //Map<String,String> params = new HashMap<String, String>();
        //params.put("favorite", id);
        RequestBody requestBodyId = RequestBody.create(MediaType.parse("text/plain"), id);
        return apiService.addFavourite(session, requestBodyId);
    }

    public Single<ResponseBody> addTemporaryFavourite(String id){
        SharedPreferences pref = mContext.getSharedPreferences("BubbleMeet", MODE_PRIVATE);
        String session = pref.getString("session", "");
        if(mRetrofit == null) {
            mRetrofit = getClient(BASE_URL);
        }
        APIService apiService = mRetrofit.create(APIService.class);
        RequestBody requestBodyId = RequestBody.create(MediaType.parse("text/plain"), id);
        return apiService.addTemporaryFavourite(session, requestBodyId);
    }

    public Single<ResponseBody> addWatcher(String id){
        SharedPreferences pref = mContext.getSharedPreferences("BubbleMeet", MODE_PRIVATE);
        String session = pref.getString("session", "");
        if(mRetrofit == null) {
            mRetrofit = getClient(BASE_URL);
        }
        APIService apiService = mRetrofit.create(APIService.class);
        RequestBody requestBodyId = RequestBody.create(MediaType.parse("text/plain"), id);
        return apiService.addWatcher(session, requestBodyId);
    }

    public Single<ResponseBody> addPhoto(String login, File photo){
        Log.d("additionPhotos", photo.getAbsolutePath());
        SharedPreferences pref = mContext.getSharedPreferences("BubbleMeet", MODE_PRIVATE);
        String session = pref.getString("session", "");
        if(mRetrofit == null) {
            mRetrofit = getClient(BASE_URL);
        }
        APIService apiService = mRetrofit.create(APIService.class);
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), photo);
        MultipartBody.Part photoFile = MultipartBody.Part.createFormData("file", login, requestFile);
        return apiService.addPhoto(session, photoFile);
    }

    public Single<ResponseBody> forgotPassword(String email){
        if(mRetrofit == null) {
            mRetrofit = getClient(BASE_URL);
        }
        APIService apiService = mRetrofit.create(APIService.class);
        Map<String,String> params = new HashMap<String, String>();
        params.put("email", email);
        RequestBody requestBodyEmail = RequestBody.create(MediaType.parse("text/plain"), email);
        //RequestBody requestBodyPassword = RequestBody.create(MediaType.parse("text/plain"), password);
        return apiService.forgotPassword(requestBodyEmail);
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
