package com.rockstar.bubblemeetapplication.model.Utils;

import com.rockstar.bubblemeetapplication.model.data.SignUpUserData;
import com.rockstar.bubblemeetapplication.model.data.UserDataFull;

import java.util.ArrayList;
import java.util.Map;

import io.reactivex.Completable;
import io.reactivex.Single;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface APIService {

    @Multipart
    @POST("login")
    Single<Response<ResponseBody>> authorization(@Part("email") RequestBody email, @Part("password") RequestBody password);

    @Multipart
    @POST("forgot")
    Single<ResponseBody> forgotPassword(@Part("email") RequestBody forgot);

    //@POST("login")
    //Single<ResponseBody> authorization(@Query("email") String email, @Query("password") String password);
    @Multipart
    @POST("user")
    Single<Response<ResponseBody>> signUp(@Part("name") RequestBody name,
                                @Part("gender") RequestBody gender,
                                @Part("age") RequestBody age,
                                @Part("email") RequestBody email,
                                @Part("password") RequestBody password,
                                @Part MultipartBody.Part avatarSmall,
                                @Part MultipartBody.Part avatarFull,
                                @Part("login") RequestBody login,
                                @Part("city") RequestBody city,
                                @Part("location") RequestBody location);

    @Multipart
    @POST("user")
    Single<Response<ResponseBody>> signUpFull(@Part("name") RequestBody name,
                                @Part("gender") RequestBody gender,
                                @Part("age") RequestBody age,
                                @Part("email") RequestBody email,
                                @Part("password") RequestBody password,
                                @Part MultipartBody.Part avatarSmall,
                                @Part MultipartBody.Part avatarFull,
                                @Part("height") RequestBody height,
                                @Part("smoking") RequestBody smoking,
                                @Part("marred") RequestBody married,
                                @Part("children") RequestBody children,
                                @Part("cook") RequestBody cook,
                                @Part("city") RequestBody city,
                                @Part("looking") RequestBody looking,
                                @Part("hobbes") RequestBody hobbes,
                                @Part("login") RequestBody login,
                                @Part("location") RequestBody location);

    @GET("allUser")
    Single<ArrayList<UserDataFull>> getAllUsers();

    @GET("temporary")
    Single<ArrayList<UserDataFull>> getTemporaryFavourite(@Header("Cookie") String sessionIdAndToken);

    @GET("userFavorite")
    Single<ArrayList<UserDataFull>> getFavourite(@Header("Cookie") String sessionIdAndToken);

    @GET("favorite")
    Single<ArrayList<UserDataFull>> getFavouriteByMe(@Header("Cookie") String sessionIdAndToken);

    @GET("history")
    Single<ArrayList<UserDataFull>> getWatchers(@Header("Cookie") String sessionIdAndToken);

    @Multipart
    @POST("temporary")
    Single<ResponseBody> addTemporaryFavourite(@Header("Cookie") String sessionIdAndToken, @Part("favorite") RequestBody favorite);

    @Multipart
    @POST("favorite")
    Single<ResponseBody> addFavourite(@Header("Cookie") String sessionIdAndToken, @Part("favorite") RequestBody favorite);

    @Multipart
    @POST("history")
    Single<ResponseBody> addWatcher(@Header("Cookie") String sessionIdAndToken, @Part("id") RequestBody favorite);

    @Multipart
    @POST("addImage")
    Single<ResponseBody> addPhoto(@Header("Cookie") String sessionIdAndToken, @Part MultipartBody.Part file);
}
