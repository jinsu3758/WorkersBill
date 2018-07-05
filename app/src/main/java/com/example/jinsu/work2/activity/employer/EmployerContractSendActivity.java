package com.example.jinsu.work2.activity.employer;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.jinsu.work2.R;
import com.example.jinsu.work2.activity.worker.WorkerHomeActivity;
import com.example.jinsu.work2.databinding.ActivityEmployerContractSendBinding;
import com.example.jinsu.work2.util.CallonClick;
import com.example.jinsu.work2.viewmodel.MainViewModel;
import com.example.jinsu.work2.viewmodel.VIewModelFactory;

public class EmployerContractSendActivity extends AppCompatActivity implements CallonClick {

    private ActivityEmployerContractSendBinding binding;
    private MainViewModel mainViewModel;
    private VIewModelFactory vIewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActivity();
    }

    private void initActivity()
    {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_employer_contract_send);
        vIewModelFactory = new VIewModelFactory(this);
        mainViewModel = ViewModelProviders.of(this,vIewModelFactory).get(MainViewModel.class);
        binding.setEmployerContractSend(mainViewModel);
    }
    @Override
    public void onBtnClick(View view) {
        switch (view.getId()) {
            case R.id.employer_contract_send_btn_back: {
                super.onBackPressed();
                finish();
                break;
            }
            case R.id.employer_contract_send_btn_home: {
                startActivity(new Intent(this, EmployerHomeActivity.class));
                break;
            }
            case R.id.employer_contract_send_btn_send: {
                startActivity(new Intent(this, EmployerPredictPaymentActivity.class));
                break;
            }
        }

    }

    @Override
    public void textChanged(int index) {

    }
}
