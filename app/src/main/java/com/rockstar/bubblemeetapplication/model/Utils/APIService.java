package com.rockstar.bubblemeetapplication.model.Utils;

import com.rockstar.bubblemeetapplication.model.data.SignUpUserData;

import java.util.ArrayList;

import io.reactivex.Single;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
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
    @Multipart
    @POST("user")
    Single<ResponseBody> signUp(@Part("name") RequestBody name,
                                @Part("gender") RequestBody gender,
                                @Part("age") RequestBody age,
                                @Part("email") RequestBody email,
                                @Part("password") RequestBody password,
                                @Part("file") ArrayList<RequestBody> file);

    @Multipart
    @POST("user")
    Single<ResponseBody> signUpFull(@Part("name") RequestBody name,
                                @Part("gender") RequestBody gender,
                                @Part("age") RequestBody age,
                                @Part("email") RequestBody email,
                                @Part("password") RequestBody password,
                                @Part("file") ArrayList<RequestBody> file,
                                @Part("height") RequestBody height,
                                @Part("smoking") RequestBody smoking,
                                @Part("marred") RequestBody married,
                                @Part("children") RequestBody children,
                                @Part("cook") RequestBody cook,
                                @Part("last") RequestBody city,
                                @Part("looking") RequestBody looking,
                                @Part("hobbes") RequestBody hobbes);

    @GET("allUser")
    Single<ArrayList<SignUpUserData>> getAllUsers();

}
