package com.example.jinsu.work2.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.jinsu.work2.activity.employer.EmployerPlaceActivity;
import com.example.jinsu.work2.common.BaseApplication;
import com.example.jinsu.work2.network.model.Login;
import com.example.jinsu.work2.network.model.LoginResponse;
import com.example.jinsu.work2.util.CallonClick;
import com.example.jinsu.work2.R;
import com.example.jinsu.work2.databinding.ActivityLoginBinding;
import com.example.jinsu.work2.viewmodel.MainViewModel;
import com.example.jinsu.work2.viewmodel.VIewModelFactory;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class LoginActivity extends AppCompatActivity implements CallonClick {
    private ActivityLoginBinding binding;
    private MainViewModel mainViewModel;
    private VIewModelFactory vIewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActivity();
    }

    private void initActivity()
    {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        vIewModelFactory = new VIewModelFactory(this);
        mainViewModel = ViewModelProviders.of(this,vIewModelFactory).get(MainViewModel.class);
        binding.setLogin(mainViewModel);

    }

    @Override
    public void onBtnClick(View view) {
        //edit
        api_login("rinjae+1@lof.kr", "1");
//        mainViewModel.onLogin();
    }

    @Override
    public void textChanged(String text) {

    }

    public void api_login(String email, String passwd) {
        Login login = new Login(email,passwd);
        BaseApplication.mApiService.login(login, new Callback<LoginResponse>() {
            @Override
            public void success(LoginResponse loginResponse, Response response) {
                //성공
                startActivity(new Intent(LoginActivity.this, SelectActivity.class));
                finish();
            }

            @Override
            public void failure(RetrofitError error) {
                //실패
            }
        });
    }
}
