package com.example.jinsu.work2.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.jinsu.work2.R;
import com.example.jinsu.work2.activity.employer.EmployerPlaceActivity;
import com.example.jinsu.work2.activity.worker.WorkerJoinPlaceActivity;
import com.example.jinsu.work2.databinding.ActivitySelectBinding;
import com.example.jinsu.work2.util.CallonClick;
import com.example.jinsu.work2.viewmodel.MainViewModel;
import com.example.jinsu.work2.viewmodel.VIewModelFactory;

public class SelectActivity extends AppCompatActivity implements CallonClick {

    private ActivitySelectBinding binding;
    private MainViewModel mainViewModel;
    private VIewModelFactory vIewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActivity();
    }

    private void initActivity()
    {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_select);
        vIewModelFactory = new VIewModelFactory(this);
        mainViewModel = ViewModelProviders.of(this,vIewModelFactory).get(MainViewModel.class);
        binding.setSelect(mainViewModel);
    }

    @Override
    public void onBtnClick(View view) {
        switch (view.getId())
        {
            case R.id.select_btn_employer:
            {
                startActivity(new Intent(this, EmployerPlaceActivity.class));
                break;
            }
            case R.id.select_btn_worker:
            {
                startActivity(new Intent(this, WorkerJoinPlaceActivity.class));
                break;
            }
        }

    }

    @Override
    public void textChanged(String text) {

    }
}
