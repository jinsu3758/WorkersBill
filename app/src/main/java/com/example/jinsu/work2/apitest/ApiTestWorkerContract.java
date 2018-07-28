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
import com.example.jinsu.work2.network.model.CompanyContract;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ApiTestWorkerContract extends ParentActivity implements View.OnClickListener {

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
        lblTitle.setText("회사-근로계약서(근로자)");
        btn1.setText("계약서 리스트");
        btn2.setText("계약서 조회");
        btn3.setText("승인요청계약서");
        btn4.setText("승인하기");
        btn4.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.btn1,R.id.btn2,R.id.btn3,R.id.btn4})
    @Override
    public void onClick(View view) {
        if(view == btn1) {
            TaskManager.api_get_contract_me_company_list(3, new Callback<ArrayList<CompanyContract>>() {
                @Override
                public void success(ArrayList<CompanyContract> companyContracts, Response response) {
                    showLog(LOG_LOGCAT_TOAST, CommonClass.toJson(companyContracts));
                    edt1.setText(CommonClass.toJson(companyContracts));
                }

                @Override
                public void failure(RetrofitError error) {
                    showLog(LOG_LOGCAT_TOAST, CommonClass.showError(error));
                    edt1.setText(CommonClass.showError(error));
                }
            });
        } else if(view == btn2) {
            TaskManager.api_get_contract_me_company_read(3, 8, new Callback<CompanyContract>() {
                @Override
                public void success(CompanyContract contract, Response response) {
                    showLog(LOG_LOGCAT_TOAST, CommonClass.toJson(contract));
                    edt1.setText(CommonClass.toJson(contract));
                }

                @Override
                public void failure(RetrofitError error) {
                    showLog(LOG_LOGCAT_TOAST, CommonClass.showError(error));
                    edt1.setText(CommonClass.showError(error));
                }
            });

        } else if(view == btn3) { //사업자 조회
            TaskManager.api_get_contract_me_company_request(3, 8, new Callback<CompanyContract>() {
                @Override
                public void success(CompanyContract company, Response response) {
                    showLog(LOG_LOGCAT_TOAST, CommonClass.toJson(company));
                    edt1.setText(CommonClass.toJson(company));
                }

                @Override
                public void failure(RetrofitError error) {
                    showLog(LOG_LOGCAT_TOAST, CommonClass.showError(error));
                    edt1.setText(CommonClass.showError(error));
                }
            });
        } else if(view == btn4) {
            TaskManager.api_contract_me_company_approval(3, new Callback<HashMap<String, String>>() {
                @Override
                public void success(HashMap<String, String> stringStringHashMap, Response response) {
                    showLog(LOG_LOGCAT_TOAST, CommonClass.toJson(stringStringHashMap));
                    edt1.setText(CommonClass.toJson(stringStringHashMap));
                }

                @Override
                public void failure(RetrofitError error) {
                    showLog(LOG_LOGCAT_TOAST, CommonClass.showError(error));
                    edt1.setText(CommonClass.showError(error));
                }
            });
        }
    }


}
