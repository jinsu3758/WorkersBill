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

public class ApiTestCoastCompany extends ParentActivity implements View.OnClickListener {

    @BindView(R.id.lblTitle) public TextView lblTitle;
    @BindView(R.id.toolbar) public Toolbar toolbar;
    @BindView(R.id.btn1) public Button btn1;
    @BindView(R.id.btn2) public Button btn2;
    @BindView(R.id.btn3) public Button btn3;
    @BindView(R.id.btn4) public Button btn4;
    @BindView(R.id.btn5) public Button btn5;
    @BindView(R.id.edt1) public EditText edt1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_test_main);
        ButterKnife.bind(this);
        lblTitle.setText("회사-근로계약서(사업자)");
        btn1.setText("생성");
        btn2.setText("리스트");
        btn3.setText("조회");
        btn4.setText("조회(계약서)");
        btn5.setText("계약 승인처리");
        btn4.setVisibility(View.VISIBLE);
        btn5.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.btn1,R.id.btn2,R.id.btn3,R.id.btn4,R.id.btn5})
    @Override
    public void onClick(View view) {
        if(view == btn1) {

        } else if(view == btn2) { //사업자 목록
            TaskManager.api_company_list(new Callback<ArrayList<Company>>() {
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
            TaskManager.api_company_add_request(companyId, new Callback<Company>() {
                @Override
                public void success(Company company, Response response) {
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

        }
    }


}
