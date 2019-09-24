package com.rockstar.bubblemeetapplication.model.Utils;

import io.reactivex.Single;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface APIService {
    @Multipart
    @POST("login")
    Single<ResponseBody> authorization(@Part("email") RequestBody email, @Part("password") RequestBody password);
    //@POST("login")
    //Single<ResponseBody> authorization(@Query("email") String email, @Query("password") String password);
}
