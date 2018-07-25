package com.example.jinsu.work2.network;


import com.example.jinsu.work2.network.model.DocumentList;
import com.example.jinsu.work2.network.model.EmailVerify;
import com.example.jinsu.work2.network.model.Join;
import com.example.jinsu.work2.network.model.Login;
import com.example.jinsu.work2.network.model.LoginResponse;

import java.util.HashMap;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

public interface ApiService {

    @POST("/v1/auth/token/")
    void login(@Body Login req, Callback<LoginResponse> cb);

    @GET("/v1/users/find-by-email")
    void find_by_email(@Query("email") String email, Callback<HashMap<String,Object>> cb);

    @GET("/v1/users/{email}/do-verify-email")
    void request_verify_email(@Path("email") String email, Callback<HashMap<String,Object>> cb);

    @POST("/v1/users/do-verify-email")
    void do_verify_email(@Body EmailVerify emailVerify, Callback<HashMap<String,Object>> cb);

    @POST("/v1/users")
    void create_user(@Body Join join, Callback<Join> cb);

    @GET("/v1/tools/address")
    void api_find_address(@Query("query") String query, Callback<DocumentList> cb);

}
