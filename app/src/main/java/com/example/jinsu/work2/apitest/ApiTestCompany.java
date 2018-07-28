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

public class ApiTestCompany extends ParentActivity implements View.OnClickListener {

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
        lblTitle.setText("회사(사업주)");
        btn1.setText("조회");
        btn2.setText("내정보-알림목록");
        btn3.setText("푸시토큰 변경");
    }

    @OnClick({R.id.btn1,R.id.btn2,R.id.btn3,R.id.btn4})
    @Override
    public void onClick(View view) {
        if(view == btn1) {
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
        } else if(view == btn2) { //알림목록
            TaskManager.api_get_notification_list(3, new Callback<ArrayList<NotificationModel>>() {
                @Override
                public void success(ArrayList<NotificationModel> notificationModels, Response response) {
                    showLog(LOG_LOGCAT_TOAST, CommonClass.toJson(notificationModels));
                    edt1.setText(CommonClass.toJson(notificationModels));
                }

                @Override
                public void failure(RetrofitError error) {
                    showLog(LOG_LOGCAT_TOAST, CommonClass.showError(error));
                    edt1.setText(CommonClass.showError(error));
                }
            });

        } else if(view == btn3) { //푸시도큰변경
            PushToken pushToken = new PushToken();
            pushToken.device = "android";
            pushToken.token = "asdfsadfsadfasdf";
            TaskManager.api_change_push_token(pushToken, new Callback<HashMap<String, Object>>() {
                @Override
                public void success(HashMap<String, Object> company, Response response) {
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
