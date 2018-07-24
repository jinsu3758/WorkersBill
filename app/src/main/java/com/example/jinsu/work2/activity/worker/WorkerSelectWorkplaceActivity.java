package com.example.jinsu.work2.activity.worker;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.jinsu.work2.R;
import com.example.jinsu.work2.databinding.ActivityWorkerSelectWorkplaceBinding;
import com.example.jinsu.work2.util.CallonClick;
import com.example.jinsu.work2.viewmodel.MainViewModel;
import com.example.jinsu.work2.viewmodel.VIewModelFactory;

public class WorkerSelectWorkplaceActivity extends AppCompatActivity implements CallonClick {

    private ActivityWorkerSelectWorkplaceBinding binding;
    private MainViewModel mainViewModel;
    private VIewModelFactory vIewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActivity();
    }

    private void initActivity()
    {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_worker_select_workplace);
        vIewModelFactory = new VIewModelFactory(this);
        mainViewModel = ViewModelProviders.of(this,vIewModelFactory).get(MainViewModel.class);
        binding.setWorkerSelectWorkplace(mainViewModel);
    }

    @Override
    public void onBtnClick(View view) {
        switch (view.getId()){
            case R.id.worker_select_workplace_btn_back:{
                super.onBackPressed();
                finish();
                break;
            }
            case R.id.worker_select_workplace_btn_find:{
                break;
            }
        }

    }

    @Override
    public void textChanged(String text) {

    }

//    @Override
//    public void textChanged(int index) {
//
//    }
}
