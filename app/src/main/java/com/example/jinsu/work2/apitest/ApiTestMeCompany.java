package com.example.jinsu.work2.apitest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jinsu.work2.R;
import com.example.jinsu.work2.activity.ParentActivity;
import com.example.jinsu.work2.manager.TaskManager;
import com.example.jinsu.work2.network.CommonClass;
import com.example.jinsu.work2.network.model.Company;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ApiTestMeCompany extends ParentActivity implements View.OnClickListener {

    @BindView(R.id.lblTitle) public TextView lblTitle;
    @BindView(R.id.toolbar) public Toolbar toolbar;
    @BindView(R.id.btn1) public Button btn1;
    @BindView(R.id.btn2) public Button btn2;
    @BindView(R.id.btn3) public Button btn3;
    @BindView(R.id.btn4) public Button btn4;
    @BindView(R.id.edt1) public EditText edt1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_test_main);
        ButterKnife.bind(this);
        lblTitle.setText("사업장목록(사업주)");
        btn1.setText("생성");
        btn2.setText("리스트");
        btn3.setText("조회");
    }

    @OnClick({R.id.btn1,R.id.btn2,R.id.btn3,R.id.btn4})
    @Override
    public void onClick(View view) {
        if(view == btn1) { //사업자 생성
            Company company = new Company();
            company.name = "테스트 주식회사";
            company.registration_number = "1231231232"; //TODO 새로생성시 이부분을 변경해야합니다.
            company.head_postcode = 12345;
            company.head_address = "경북 경주시";
            company.postcode = 12345;
            company.address = "경기도 고양시";
            company.usage_wifi = true;
            company.phone = "123123123";
            company.wifi_ip_address = "127.0.0.1";
            company.is_less_then_5_employee = true;
            TaskManager.api_company_me_create(company, new Callback<Company>() {
                @Override
                public void success(Company company, Response response) {
                    showLog(LOG_LOGCAT_TOAST, CommonClass.toJson(company));
                    edt1.setText(CommonClass.toJson(company));
                }

                @Override
                public void failure(RetrofitError error) {
                    //실패
                    showLog(LOG_LOGCAT_TOAST, CommonClass.showError(error));
                    edt1.setText(CommonClass.showError(error));
                }
            });

        } else if(view == btn2) { //사업자 목록
            TaskManager.api_company_me_list(new Callback<ArrayList<Company>>() {
                @Override
                public void success(ArrayList<Company> companies, Response response) {
                    showLog(LOG_LOGCAT_TOAST, CommonClass.toJson(companies));
                    edt1.setText(CommonClass.toJson(companies));
                }

                @Override
                public void failure(RetrofitError error) {
                    showLog(LOG_LOGCAT_TOAST, CommonClass.showError(error));
                    edt1.setText(CommonClass.showError(error));
                }
            });

        } else if(view == btn3) { //사업자 조회
            int companyId = 3;
            TaskManager.api_company_me_read(companyId, new Callback<Company>() {
                @Override
                public void success(Company company, Response response) {
                    showLog(LOG_LOGCAT_TOAST, CommonClass.toJson(company));
                    edt1.setText(CommonClass.toJson(company));
                }

                @Override
                public void failure(RetrofitError error) {
                    //실패
                    showLog(LOG_LOGCAT_TOAST, CommonClass.showError(error));
                    edt1.setText(CommonClass.showError(error));
                }
            });

        } else if(view == btn4) {

        }
    }


}
