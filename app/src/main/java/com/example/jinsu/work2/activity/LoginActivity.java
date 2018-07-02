package com.example.jinsu.work2.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.jinsu.work2.activity.employer.EmployerPlaceActivity;
import com.example.jinsu.work2.util.CallonClick;
import com.example.jinsu.work2.R;
import com.example.jinsu.work2.databinding.ActivityLoginBinding;
import com.example.jinsu.work2.viewmodel.EmployerViewModel;
import com.example.jinsu.work2.viewmodel.VIewModelFactory;

public class LoginActivity extends AppCompatActivity implements CallonClick {
    private ActivityLoginBinding binding;
    private EmployerViewModel employerViewModel;
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
        employerViewModel = ViewModelProviders.of(this,vIewModelFactory).get(EmployerViewModel.class);
        binding.setLogin(employerViewModel);

    }

    @Override
    public void onBtnClick(View view) {
        startActivity(new Intent(this, EmployerPlaceActivity.class));
        finish();
//        employerViewModel.onLogin();
    }

    @Override
    public void textChanged(int index) {

    }
}
