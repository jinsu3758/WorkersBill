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
import com.example.jinsu.work2.network.model.CompanyDashBoard;
import com.example.jinsu.work2.network.model.DoWork;
import com.example.jinsu.work2.network.model.NotificationModel;
import com.example.jinsu.work2.network.model.PushToken;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ApiTestWorker extends ParentActivity implements View.OnClickListener {

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
        lblTitle.setText("회사(근로자)");
        btn1.setText("근로자용 종합정보");
        btn2.setText("출근하기");
        btn3.setText("퇴근하기");
    }

    @OnClick({R.id.btn1,R.id.btn2,R.id.btn3,R.id.btn4})
    @Override
    public void onClick(View view) {
        if(view == btn1) {
            TaskManager.api_get_worker_dashboard(3, new Callback<CompanyDashBoard>() {
                @Override
                public void success(CompanyDashBoard companies, Response response) {
                    showLog(LOG_LOGCAT_TOAST, CommonClass.toJson(companies));
                    edt1.setText(CommonClass.toJson(companies));
                }

                @Override
                public void failure(RetrofitError error) {
                    showLog(LOG_LOGCAT_TOAST, CommonClass.showError(error));
                    edt1.setText(CommonClass.showError(error));
                }
            });
        } else if(view == btn2) {
            TaskManager.api_do_work_on(3, new Callback<DoWork>() {
                @Override
                public void success(DoWork doWork, Response response) {
                    showLog(LOG_LOGCAT_TOAST, CommonClass.toJson(doWork));
                    edt1.setText(CommonClass.toJson(doWork));
                }

                @Override
                public void failure(RetrofitError error) {
                    showLog(LOG_LOGCAT_TOAST, CommonClass.showError(error));
                    edt1.setText(CommonClass.showError(error));
                }
            });

        } else if(view == btn3) { //푸시도큰변경
            TaskManager.api_do_work_off(3, new Callback<DoWork>() {
                @Override
                public void success(DoWork doWork, Response response) {
                    showLog(LOG_LOGCAT_TOAST, CommonClass.toJson(doWork));
                    edt1.setText(CommonClass.toJson(doWork));
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
