package com.example.jinsu.work2.manager;

import android.support.annotation.NonNull;

import com.example.jinsu.work2.network.model.FindAddress;
import com.example.jinsu.work2.common.BaseApplication;
import com.example.jinsu.work2.network.model.Company;
import com.example.jinsu.work2.network.model.CompanyContract;
import com.example.jinsu.work2.network.model.CompanyDashBoard;
import com.example.jinsu.work2.network.model.DoWork;
import com.example.jinsu.work2.network.model.EmailVerify;
import com.example.jinsu.work2.network.model.Join;
import com.example.jinsu.work2.network.model.Login;
import com.example.jinsu.work2.network.model.NotificationModel;
import com.example.jinsu.work2.network.model.PersonnalCost;
import com.example.jinsu.work2.network.model.PersonnalCostRequest;
import com.example.jinsu.work2.network.model.PushToken;
import com.example.jinsu.work2.network.model.User;
import com.example.jinsu.work2.network.model.WorkSchedule;
import com.example.jinsu.work2.network.model.WorkScheduleItem;

import java.util.ArrayList;
import java.util.HashMap;

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
     * 내 사업장 생성
     */
    public static void api_company_me_create(@NonNull Company company,
                                            @NonNull Callback<Company> callback) {
        BaseApplication.mApiService.company_me_create(company, callback);
    }

    /**
     * 내 사업장 상세보기
     */
    public static void api_company_me_list(Callback<ArrayList<Company>> callback) {
        BaseApplication.mApiService.company_me_list(callback);
    }

    /**
     * 내 사업장 리스트
     */
    public static void api_company_me_read(int companyId, @NonNull Callback<Company> callback) {
        BaseApplication.mApiService.company_me_read(companyId, callback);
    }

    /**
     * 전체 사업장 상세보기
     */
    public static void api_company_list(Callback<ArrayList<Company>> callback) {
        BaseApplication.mApiService.company_list(callback);
    }

    /**
     * 추가 근로자 합류요청
     */
    public static void api_company_add_request(int companyId, Callback<Company> callback) {
        BaseApplication.mApiService.company_add_request(companyId, callback);
    }


    /**
     * 알림 목록
     */
    public static void api_get_notification_list(int companyId, Callback<ArrayList<NotificationModel>> callback) {
        BaseApplication.mApiService.get_notification_list(companyId, callback);
    }

    /**
     * 푸시토큰 갱신
     */
    public static void api_change_push_token(PushToken token, Callback<HashMap<String,Object>> callback) {
        BaseApplication.mApiService.change_push_token(token, callback);
    }

    /**
     * 출근하기
     */
    public static void api_do_work_on(int companyId, Callback<DoWork> callback) {
        BaseApplication.mApiService.do_work(companyId, callback);
    }

    /**
     * 퇴근하기
     */
    public static void api_do_work_off(int companyId, Callback<DoWork> callback) {
        BaseApplication.mApiService.do_work_off(companyId, callback);
    }

    /**
     * 근로자용 종합정보
     */
    public static void api_get_worker_dashboard(int companyId, Callback<CompanyDashBoard> callback) {
        BaseApplication.mApiService.get_worker_dashboard(companyId, callback);
    }

    /**
     * 나의 계약서 목록
     */
    public static void api_get_contract_me_company_list(int companyId, Callback<ArrayList<CompanyContract>> callback) {
        BaseApplication.mApiService.contract_me_company_list(companyId, callback);
    }

    /**
     * 나의 계약서 조회
     */
    public static void api_get_contract_me_company_read(int companyId, int contractId, Callback<CompanyContract> callback) {
        BaseApplication.mApiService.contract_me_company_read(companyId, contractId, callback);
    }

    /**
     * 승인요청계약서
     */
    public static void api_get_contract_me_company_request(int companyId, int contractId, Callback<CompanyContract> callback) {
        BaseApplication.mApiService.contract_me_company_request_approval_read(companyId, callback);
    }

    /**
     * 승인하기
     */
    public static void api_contract_me_company_approval(int companyId, Callback<HashMap<String,String>> callback) {
        BaseApplication.mApiService.contract_me_company_approval(companyId, callback);
    }

    /**
     * 클라이언트 ip 확인
     */
    public static void api_get_client_ip(Callback<HashMap<String,String>> callback) {
        BaseApplication.mApiService.get_client_ip_address(callback);
    }

    /**
     * 주소찾기
     */
    public static void find_brief_address(String query, Callback<ArrayList<FindAddress>> callback) {
        BaseApplication.mApiService.find_brief_address(query, callback);
    }

    /**
     * 인건비계산
     */
    public static void get_personnal_cost(int CompanyId, Callback<PersonnalCost> cb) {
        BaseApplication.mApiService.get_personnal_cost(CompanyId, cb);
    }

    /**
     * 인건비 이전 목록
     */
    public static void get_personnal_cost_list(int CompanyId, Callback<ArrayList<PersonnalCost>> cb) {
        BaseApplication.mApiService.get_personnal_cost_list(CompanyId, cb);
    }

    /**
     * 인건비계산
     */
    public static void personnal_cost_save(int companyId,
                                          int scheduleId,
                                          PersonnalCostRequest request,
                                          Callback<PersonnalCost> cb) {
        BaseApplication.mApiService.personnal_cost_save(companyId, scheduleId, request, cb);
    }

    /**
     * 인건비계산
     */
    public static void personnal_work_schedule(int companyId,
                                          int scheduleId,
                                          ArrayList<WorkSchedule> list,
                                          Callback<WorkScheduleItem> cb) {
        BaseApplication.mApiService.personnal_work_schedule(companyId, scheduleId, list, cb);
    }

}
