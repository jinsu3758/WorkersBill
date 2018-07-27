package com.example.jinsu.work2.network;


import com.example.jinsu.work2.network.model.Company;
import com.example.jinsu.work2.network.model.DocumentList;
import com.example.jinsu.work2.network.model.EmailVerify;
import com.example.jinsu.work2.network.model.Join;
import com.example.jinsu.work2.network.model.Login;
import com.example.jinsu.work2.network.model.LoginResponse;
import com.example.jinsu.work2.network.model.User;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

public interface ApiService {

    ///------------------------------
    /// 내 정 보
    ///------------------------------
    @GET("/v1/me")
    void get_me_info(Callback<User> cb);

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

    ///------------------------------
    /// 내 정 보 - 사업장
    ///------------------------------
    @POST("/v1/me/companies")
    void company_create(@Body Company company, Callback<Company> cb);

    @GET("/v1/me/companies")
    void company_list(Callback<ArrayList<Company>> cb);

    @GET("/v1/me/companies/{companyId}")
    void company_read(@Path("companyId") int companyid, Callback<Company> cb);

}
