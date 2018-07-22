package com.example.jinsu.work2.activity.worker;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.jinsu.work2.R;
import com.example.jinsu.work2.databinding.ActivityWorkerContractRequestBinding;
import com.example.jinsu.work2.util.CallonClick;
import com.example.jinsu.work2.viewmodel.MainViewModel;
import com.example.jinsu.work2.viewmodel.VIewModelFactory;

public class WorkerContractRequestActivity extends AppCompatActivity implements CallonClick {

    private ActivityWorkerContractRequestBinding binding;
    private MainViewModel mainViewModel;
    private VIewModelFactory vIewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActivity();
    }

    private void initActivity()
    {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_worker_contract_request);
        vIewModelFactory = new VIewModelFactory(this);
        mainViewModel = ViewModelProviders.of(this,vIewModelFactory).get(MainViewModel.class);
        binding.setWorkerContractRequest(mainViewModel);
    }
    @Override
    public void onBtnClick(View view) {

        switch (view.getId()) {
            case R.id.worker_contract_request_btn_home: {
                startActivity(new Intent(this,WorkerHomeActivity.class));
                break;
            }
            case R.id.worker_contract_request_btn_reject:{
                startActivity(new Intent(this,WorkerHomeActivity.class));
                break;
            }
            case R.id.worker_contract_request_btn_approve:{
                startActivity(new Intent(this,WorkerContractSendFinActivity.class));
                break;
            }
        }

    }

    @Override
    public void textChanged(String text) {

    }
}
