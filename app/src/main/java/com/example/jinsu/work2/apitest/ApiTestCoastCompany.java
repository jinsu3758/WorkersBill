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
import com.example.jinsu.work2.network.model.PersonnalCost;
import com.example.jinsu.work2.network.model.PersonnalCostRequest;
import com.example.jinsu.work2.network.model.WorkSchedule;
import com.example.jinsu.work2.network.model.WorkScheduleItem;

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
        lblTitle.setText("회사 - 인건비 계산 (사업주)");
        toolbar.setTitle("회사 - 인건비 계산 (사업주)");
        btn1.setText("인건비 계산");
        btn2.setText("기존계산 불러오기");
        btn3.setText("인건비 저장");
        btn4.setText("근무일정 저장");
        btn4.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.btn1,R.id.btn2,R.id.btn3,R.id.btn4,R.id.btn5})
    @Override
    public void onClick(View view) {
        if(view == btn1) {
            TaskManager.get_personnal_cost(3, new Callback<PersonnalCost>() {
                @Override
                public void success(PersonnalCost personnalCost, Response response) {
                    showLog(LOG_LOGCAT_TOAST, CommonClass.toJson(personnalCost));
                    edt1.setText(CommonClass.toJson(personnalCost));
                }

                @Override
                public void failure(RetrofitError error) {
                    showLog(LOG_LOGCAT_TOAST, CommonClass.showError(error));
                    edt1.setText(CommonClass.showError(error));
                }
            });
        } else if(view == btn2) {
            TaskManager.get_personnal_cost_list(3, new Callback<ArrayList<PersonnalCost>>() {
                @Override
                public void success(ArrayList<PersonnalCost> perlist, Response response) {
                    showLog(LOG_LOGCAT_TOAST, CommonClass.toJson(perlist));
                    edt1.setText(CommonClass.toJson(perlist));
                }

                @Override
                public void failure(RetrofitError error) {
                    showLog(LOG_LOGCAT_TOAST, CommonClass.showError(error));
                    edt1.setText(CommonClass.showError(error));
                }
            });

        } else if(view == btn3) {
            PersonnalCostRequest personnalCostRequest = new PersonnalCostRequest();
            personnalCostRequest.employee_id = 0;
            personnalCostRequest.salary_method = "TIME";
            personnalCostRequest.company_id = 3;
            personnalCostRequest.title = "";
            TaskManager.personnal_cost_save(
                    3,
                    51,
                    personnalCostRequest, new Callback<PersonnalCost>() {
                @Override
                public void success(PersonnalCost personnalCost, Response response) {
                    showLog(LOG_LOGCAT_TOAST, CommonClass.toJson(personnalCost));
                    edt1.setText(CommonClass.toJson(personnalCost));
                }

                @Override
                public void failure(RetrofitError error) {
                    showLog(LOG_LOGCAT_TOAST, CommonClass.showError(error));
                    edt1.setText(CommonClass.showError(error));
                }
            });
        } else if(view == btn4) {
            ArrayList<WorkSchedule> list = new ArrayList<>();
            list.add(new WorkSchedule(String.valueOf(1)));

            TaskManager.personnal_work_schedule(
                    3,
                    51,
                    list, new Callback<WorkScheduleItem>() {
                        @Override
                        public void success(WorkScheduleItem workScheduleItem, Response response) {
                            showLog(LOG_LOGCAT_TOAST, CommonClass.toJson(workScheduleItem));
                            edt1.setText(CommonClass.toJson(workScheduleItem));
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
