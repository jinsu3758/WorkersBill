package com.example.jinsu.work2.network;


import com.example.jinsu.work2.apitest.FindAddress;
import com.example.jinsu.work2.network.model.Company;
import com.example.jinsu.work2.network.model.CompanyContract;
import com.example.jinsu.work2.network.model.CompanyDashBoard;
import com.example.jinsu.work2.network.model.DoWork;
import com.example.jinsu.work2.network.model.NotificationModel;
import com.example.jinsu.work2.network.model.PushToken;
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
import retrofit.http.PUT;
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
    void company_me_create(@Body Company company, Callback<Company> cb);

    @GET("/v1/me/companies")
    void company_me_list(Callback<ArrayList<Company>> cb);

    @GET("/v1/me/companies/{companyId}")
    void company_me_read(@Path("companyId") int companyid, Callback<Company> cb);

    ///------------------------------
    /// 사업장 검색
    ///------------------------------
    @GET("/v1/companies")
    void company_list(Callback<ArrayList<Company>> cb);

    //추가 합류 요청
    @POST("/v1/companies/{companyId}/employees")
    void company_add_request(@Path("companyId") int companyid, Callback<Company> cb);

    //푸시정보 변경
    @PUT("/v1/me/notification/token")
    void change_push_token(@Body PushToken token, Callback<HashMap<String,Object>> cb);

    //내 일람 정보
    @GET("/v1/me/companies/{companyId}/notifications")
    void get_notification_list(@Path("companyId") int companyId, Callback<ArrayList<NotificationModel>> cb);

    //근로자용 종합정보
    @GET("/v1/me/companies/{companyId}/dashboard")
    void get_worker_dashboard(@Path("companyId") int companyId, Callback<CompanyDashBoard> cb);

    //출근하기
    @PUT("/v1/me/companies/{companyId}/do-work")
    void do_work(@Path("companyId") int companyId, Callback<DoWork> cb);

    //퇴근하기
    @PUT("/v1/me/companies/{companyId}/do-work-off")
    void do_work_off(@Path("companyId") int companyId, Callback<DoWork> cb);


    @GET("/v1/me/companies/{companyId}/contracts")
    void contract_me_company_list(@Path("companyId") int companyId, Callback<ArrayList<CompanyContract>> cb);

    //조회 내정보 - 사업장 - 근로계약서
    @GET("/v2/me/companies/{companyId}/contracts/{contractId}")
    void contract_me_company_read(@Path("companyId") int companyId, @Path("contractId") int contractId, Callback<CompanyContract> cb);

    //조회 내정보 - 사업장 - (승인요청계약서)
    @GET("/v2/me/companies/{companyId}/contracts/request-approval")
    void contract_me_company_request_approval_read(@Path("companyId") int companyId, Callback<CompanyContract> cb);

    //승인 요청 계약서 승인처리
    @POST("/v2/me/companies/{companyId}/contracts/do-approval")
    void contract_me_company_approval(@Path("companyId") int companyId, Callback<HashMap<String,String>> cb);

    //도구 - 클라이언트 IP 확인
    @GET("/v1/tools/client-ip-address")
    void get_client_ip_address(Callback<HashMap<String,String>> cb);

    //주소찾기
    @GET("/v2/tools/address")
    void api_find_brief_address(@Query("query") String query, Callback<ArrayList<FindAddress>> cb);

    //기존 계산 불러오기
    @GET("/v1/me/companies/{CompanyId}/schedules")
    void api_get_schedules(@Query("query") String query, Callback<FindAddress> cb);

}
