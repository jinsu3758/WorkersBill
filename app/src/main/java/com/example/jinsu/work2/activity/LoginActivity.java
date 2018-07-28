package com.example.jinsu.work2.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.example.jinsu.work2.R;
import com.example.jinsu.work2.databinding.ActivityLoginBinding;
import com.example.jinsu.work2.util.CallonClick;
import com.example.jinsu.work2.viewmodel.MainViewModel;
import com.example.jinsu.work2.viewmodel.VIewModelFactory;

public class LoginActivity extends ParentActivity implements CallonClick {
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
        startActivity(new Intent(this, SelectActivity.class));
//        mainViewModel.onLogin();
    }

    @Override
    public void textChanged(int flag) {
        startActivity(new Intent(this, SelectActivity.class));
        finish();
    }




}
