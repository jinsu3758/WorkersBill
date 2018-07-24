package com.example.jinsu.work2.network;


import com.example.jinsu.work2.network.model.Login;
import com.example.jinsu.work2.network.model.LoginResponse;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

public interface ApiService {

    @POST("/v1/auth/token/")
    void login(@Body Login req, Callback<LoginResponse> cb);

}
