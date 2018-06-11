package com.example.jinsu.work2.network;

import com.example.jinsu.work2.model.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetroService {
    @GET("/users/{KEY}")
    Call<User> getRespos(@Path("KEY") String id);

    @GET("/postCardKey")
    Call<ArrayList<User>> getMyGift(@Query("card_key") String card_key);

    @POST("/users/{KEY}")
    Call<User> postRespos(@Path("KEY") String id, @Body User user);

    @POST("/users/{EMAIL}")
    Call<String> postUser(@Path("EMAIL") String id);


}
