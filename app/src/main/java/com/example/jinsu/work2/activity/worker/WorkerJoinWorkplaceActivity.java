package com.example.jinsu.work2.activity.worker;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.jinsu.work2.R;
import com.example.jinsu.work2.adapter.EmployerPlaceAdapter;
import com.example.jinsu.work2.databinding.ActivityWorkerJoinWorkplaceBinding;
import com.example.jinsu.work2.model.EmployerPlace;
import com.example.jinsu.work2.util.CallonClick;
import com.example.jinsu.work2.viewmodel.MainViewModel;
import com.example.jinsu.work2.viewmodel.VIewModelFactory;

import java.util.ArrayList;

public class WorkerJoinWorkplaceActivity extends AppCompatActivity implements CallonClick {

    private ActivityWorkerJoinWorkplaceBinding binding;
    private MainViewModel mainViewModel;
    private VIewModelFactory vIewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActivity();
    }

    private void initActivity()
    {
        binding = DataBindingUtil.setContentView(this,R.layout.activity_worker_join_workplace);
        vIewModelFactory = new VIewModelFactory(this);
        mainViewModel = ViewModelProviders.of(this,vIewModelFactory).get(MainViewModel.class);
        binding.setWorkerJoinWorkplace(mainViewModel);
    }

    @Override
    public void onBtnClick(View view) {
        switch (view.getId()) {
            case R.id.worker_join_workplace_btn_back: {
                super.onBackPressed();
                finish();
                break;
            }
            case R.id.worker_join_workplace_btn_create:{
                startActivity(new Intent(this,WorkerSelectWorkplaceActivity.class));
                break;
            }
        }

    }

    @Override
    public void textChanged(int index) {

    }
}
