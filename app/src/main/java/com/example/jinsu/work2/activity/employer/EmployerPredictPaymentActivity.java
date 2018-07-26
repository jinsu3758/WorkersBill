package com.example.jinsu.work2.activity.employer;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.jinsu.work2.R;
import com.example.jinsu.work2.databinding.ActivityEmployerPredictPaymentBinding;
import com.example.jinsu.work2.util.CallonClick;
import com.example.jinsu.work2.viewmodel.MainViewModel;
import com.example.jinsu.work2.viewmodel.VIewModelFactory;

public class EmployerPredictPaymentActivity extends AppCompatActivity implements CallonClick {

    private ActivityEmployerPredictPaymentBinding binding;
    private MainViewModel mainViewModel;
    private VIewModelFactory vIewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActivity();
    }

    private void initActivity()
    {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_employer_predict_payment);
        vIewModelFactory = new VIewModelFactory(this);
        mainViewModel = ViewModelProviders.of(this,vIewModelFactory).get(MainViewModel.class);
        binding.setEmployerPredictPayment(mainViewModel);
    }
    @Override
    public void onBtnClick(View view) {
        switch (view.getId()) {
            case R.id.employer_predict_payment_btn_back: {
                super.onBackPressed();
                finish();
                break;
            }
            case R.id.employer_predict_payment_btn_home: {
                startActivity(new Intent(this, EmployerHomeActivity.class));
                break;
            }
            case R.id.employer_predict_payment_pay_send: {
                startActivity(new Intent(this, EmployerSendFinishActivity.class));
                break;
            }
        }

    }

    @Override
    public void textChanged(int flag) {

    }
}
