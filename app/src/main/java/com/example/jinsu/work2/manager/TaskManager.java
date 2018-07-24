package com.example.jinsu.work2.manager;

import android.support.annotation.NonNull;

import com.example.jinsu.work2.common.BaseApplication;
import com.example.jinsu.work2.network.model.EmailVerify;
import com.example.jinsu.work2.network.model.Join;

import retrofit.Callback;

public class TaskManager {

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
    public static void api_create_user(Join join, @NonNull Callback callback) {
        BaseApplication.mApiService.create_user(join, callback);
    }

    /**
     * 우편번호 검사
     */
    public static void api_find_address(String query) {
        BaseApplication.mApiService.j
    }
}
