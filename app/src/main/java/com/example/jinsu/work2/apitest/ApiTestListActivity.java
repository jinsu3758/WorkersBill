package com.example.jinsu.work2.apitest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jinsu.work2.R;
import com.example.jinsu.work2.activity.LoginActivity;
import com.example.jinsu.work2.activity.ParentActivity;
import com.example.jinsu.work2.activity.SelectActivity;
import com.example.jinsu.work2.manager.InfoManager;
import com.example.jinsu.work2.manager.TaskManager;
import com.example.jinsu.work2.network.CommonClass;
import com.example.jinsu.work2.network.model.Login;
import com.example.jinsu.work2.network.model.LoginResponse;
import com.example.jinsu.work2.network.model.User;

import org.jsoup.helper.StringUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ApiTestListActivity extends ParentActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    @BindView(R.id.toolbar)         public Toolbar toolbar;
    @BindView(R.id.listview)        public ListView listView;
    @BindView(R.id.btnCompany)      public Button btnCompany;
    @BindView(R.id.btnWorker)       public Button btnWorker;

    private ListAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_api_test);
        ButterKnife.bind(this);

        toolbar.setTitle("API TEST");
        mAdapter = new ListAdapter();
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if(i == 0) {
            TaskManager.api_get_info(new Callback<User>() {
                @Override
                public void success(User user, Response response) {
                    showLog(LOG_LOGCAT_TOAST, CommonClass.toJson(user));
                }

                @Override
                public void failure(RetrofitError error) {
                    CommonClass.showError(error);
                }
            });
        }
        else if(i == 1) return;
        else if(i == 2) return;
        else if(i == 3) startActivity(new Intent(this, ApiTestMeWorker.class));
        else if(i == 4) startActivity(new Intent(this, ApiTestMeCompany.class));
        else if(i == 5) startActivity(new Intent(this, ApiTestWorker.class));
        else if(i == 6) startActivity(new Intent(this, ApiTestCompany.class));
        else if(i == 7) startActivity(new Intent(this, ApiTestWorkerContract.class));
        else if(i == 8) startActivity(new Intent(this, ApiTestCompanyContract.class));
        else if(i == 9) startActivity(new Intent(this, ApiTestCompany.class));


    }

    @OnClick({R.id.btnCompany, R.id.btnWorker})
    @Override
    public void onClick(View view) {
        if(view == btnCompany) {
            InfoManager.setOAuthToken("65Sk67qJ66qN5ryZ8KmCvOWHheWhivCipZfrqqHwqL2X5pyJ45qC5oW/65ON4Yqr4qCj8KuXi+W3juyenvCqpLzOpfCThrXphZbwpJ635amR8KKJhuCmr+SviuiZiPCTgJ7wp6qC8KO+p/CpoLzsjKDwlqOJ8JKFmvCgubvwpISb6ZKkzIjo");
            showLog(LOG_LOGCAT_TOAST,"success Login Employer");
        } else if(view == btnWorker) {
            //m3xIg084fUST6HYFNiXLnUn7Mpoy8JujNwatojBkfSJGkXCkFzeEu4Q0vRTpvMfLNTMJ0uF7XLYlYJgkrx0kBqcJ8VEmA4NjkRLeGCWSrWzdeuv1Bj46HyGgQVvAhZlcjmO2vdPjmhApbtLVsorpPeSsqC46b71UWQXND4oXmQJKHCqJLTNz
            Login login = new Login("rinjae+1@lof.kr","1");
            TaskManager.login(login, new Callback<LoginResponse>() {
                @Override
                public void success(LoginResponse loginResponse, Response response) {
                    InfoManager.setOAuthToken(loginResponse.access_token);
                    //성공
                    showLog(LOG_LOGCAT_TOAST, CommonClass.toJson(loginResponse));
                }

                @Override
                public void failure(RetrofitError error) {
                    //실패
                    showLog(LOG_LOGCAT_TOAST, CommonClass.showError(error));
                }
            });
        }
    }

    private class ListAdapter extends BaseAdapter {

        private ArrayList<String> apiItems;

        public ListAdapter() {
            apiItems = new ArrayList<>();
            apiItems.add("내정보");
            apiItems.add("로그인");
            apiItems.add("회원가입");
            apiItems.add("사업장 목록 (근로자)");
            apiItems.add("사업장 목록 (사업주)");
            apiItems.add("회사 (근로자)");
            apiItems.add("회사 (사업주)");
            apiItems.add("회사 - 근로계약서 (근로자)");
            apiItems.add("회사 - 근로계약서 (사업주)");
            apiItems.add("회사 - 인건비 계산 (사업주)");
        }

        @Override
        public int getCount() {
            return apiItems.size();
        }

        @Override
        public String getItem(int i) {
            return apiItems.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if(view == null) {
                view = getLayoutInflater().inflate(R.layout.item_api_test, viewGroup, false);
                view.setTag(R.id.lblName, view.findViewById(R.id.lblName));
            }

            String apiType = getItem(i);
            if(!StringUtil.isBlank(apiType)) {
                TextView lblName = (TextView) view.getTag(R.id.lblName);
                lblName.setText(apiType);
            }
            return view;
        }
    }


}
