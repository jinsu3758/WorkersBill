package com.example.jinsu.work2.manager;

import android.support.annotation.NonNull;

import com.example.jinsu.work2.common.BaseApplication;
import com.example.jinsu.work2.network.model.Company;
import com.example.jinsu.work2.network.model.EmailVerify;
import com.example.jinsu.work2.network.model.Join;
import com.example.jinsu.work2.network.model.Login;
import com.example.jinsu.work2.network.model.User;

import java.util.ArrayList;

import retrofit.Callback;

public class TaskManager {

    /**
     * 내정보
     */
    public static void api_get_info(@NonNull Callback<User> callback) {
        BaseApplication.mApiService.get_me_info(callback);
    }

    /**
     * 로그인
     */
    public static void login(Login login, @NonNull Callback callback) {
        BaseApplication.mApiService.login(login, callback);
    }

    /**
     * 이메일 중복검사
     */
    public static void api_check_exist_email(String email, @NonNull Callback callback) {
        BaseApplication.mApiService.find_by_email(email, callback);
    }

    /**
     * 인증번호 요청
     */
    public static void api_request_verify_email(String email, @NonNull Callback callback) {
        BaseApplication.mApiService.request_verify_email(email, callback);
    }

    /**
     * 인증번호 검증
     */
    public static void api_verify_email(String email, int code, @NonNull Callback callback) {
        BaseApplication.mApiService.do_verify_email(new EmailVerify(email, code), callback);
    }

    /**
     * 이메일 중복검사
     */
    public static void api_create_user(Join join, @NonNull Callback<Join> callback) {
        BaseApplication.mApiService.create_user(join, callback);
    }

    /**
     * 우편번호 검사
     */
    public static void api_find_address(String query, @NonNull Callback callback) {
        BaseApplication.mApiService.api_find_address(query, callback);
    }

    /**
     * 사업장 생성
     */
    public static void api_company_create(@NonNull Company company,
                                          @NonNull Callback<Company> callback) {
        BaseApplication.mApiService.company_create(company, callback);
    }

    /**
     * 사업장 상세보기
     */
    public static void api_company_list(Callback<ArrayList<Company>> callback) {
        BaseApplication.mApiService.company_list(callback);
    }

    /**
     * 사업장 리스트
     */
    public static void api_company_read(int companyId, @NonNull Callback<Company> callback) {
        BaseApplication.mApiService.company_read(companyId, callback);
    }

}
