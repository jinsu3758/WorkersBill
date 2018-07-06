package com.example.jinsu.work2.activity.worker;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.jinsu.work2.R;
import com.example.jinsu.work2.databinding.ActivityWorkerContractSendFinBinding;
import com.example.jinsu.work2.util.CallonClick;
import com.example.jinsu.work2.viewmodel.MainViewModel;
import com.example.jinsu.work2.viewmodel.VIewModelFactory;

import static com.example.jinsu.work2.R.id.worker_home_btn_cont_request_alarm;

public class WorkerContractSendFinActivity extends AppCompatActivity implements CallonClick {

    private ActivityWorkerContractSendFinBinding binding;
    private MainViewModel mainViewModel;
    private VIewModelFactory vIewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActivity();
    }

    private void initActivity()
    {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_worker_contract_send_fin);
        vIewModelFactory = new VIewModelFactory(this);
        mainViewModel = ViewModelProviders.of(this,vIewModelFactory).get(MainViewModel.class);
        binding.setWorkerContractSendFin(mainViewModel);
    }

    @Override
    public void onBtnClick(View view) {
        switch (view.getId()){
            case R.id.worker_contract_send_fin_btn_home:{
                startActivity(new Intent(this,WorkerHomeActivity.class));
                break;
            }
            case R.id.contract_send_fin_btn_home:{
                startActivity(new Intent(this,WorkerHomeActivity.class));
                break;
            }

        }

    }

    @Override
    public void textChanged(int index) {

    }
}
