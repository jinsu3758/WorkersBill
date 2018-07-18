package com.example.jinsu.work2.network;

import com.example.jinsu.work2.model.Test;
import com.example.jinsu.work2.model.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetroService {
    @FormUrlEncoded
    @POST("/v1/auth/sign-in")
    Call<String> onLogin(@Field("email") String email, @Field("authCode") String authCode);

    @GET("/users/{KEY}")
    Call<User> getRespos(@Path("KEY") String id);

    @GET("/postCardKey")
    Call<ArrayList<User>> getMyGift(@Query("card_key") String card_key);

    @POST("/users/{KEY}")
    Call<User> postRespos(@Path("KEY") String id, @Body User user);

    @POST("/users/{EMAIL}")
    Call<String> postUser(@Path("EMAIL") String id);

    @GET("controller/findId/")
    Call<Test> onLogin(@Query("id") String id);

    @POST("/v1/users")
    Call<User> setUser(@Body User user);

}
