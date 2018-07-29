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
import com.example.jinsu.work2.network.model.FindAddress;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ApiTestEtc extends ParentActivity implements View.OnClickListener {

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
        lblTitle.setText("기타");
        btn1.setText("Client IP");
        btn2.setText("주소검색");
    }

    @OnClick({R.id.btn1,R.id.btn2,R.id.btn3,R.id.btn4})
    @Override
    public void onClick(View view) {
        if(view == btn1) {
            TaskManager.api_get_client_ip(new Callback<HashMap<String, String>>() {
                @Override
                public void success(HashMap<String, String> ips, Response response) {
                    showLog(LOG_LOGCAT_TOAST, CommonClass.toJson(ips));
                    edt1.setText(CommonClass.toJson(ips));
                }

                @Override
                public void failure(RetrofitError error) {
                    showLog(LOG_LOGCAT_TOAST, CommonClass.showError(error));
                    edt1.setText(CommonClass.showError(error));
                }
            });
        } else if(view == btn2) { //주소검색
            TaskManager.find_brief_address("개봉동 493", new Callback<ArrayList<FindAddress>>() {
                @Override
                public void success(ArrayList<FindAddress> findAddresses, Response response) {
                    showLog(LOG_LOGCAT_TOAST, CommonClass.toJson(findAddresses));
                    edt1.setText(CommonClass.toJson(findAddresses));
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
