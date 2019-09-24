package com.rockstar.bubblemeetapplication.model.Utils;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.Single;
import okhttp3.MediaType;
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

    public static Retrofit getClient(String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit;
    }
}
