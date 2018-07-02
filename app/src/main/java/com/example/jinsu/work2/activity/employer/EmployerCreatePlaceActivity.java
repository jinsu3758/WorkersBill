package com.example.jinsu.work2.activity.employer;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.jinsu.work2.R;
import com.example.jinsu.work2.databinding.ActivityEmployerCreatePlaceBinding;
import com.example.jinsu.work2.util.CallonClick;
import com.example.jinsu.work2.viewmodel.EmployerViewModel;
import com.example.jinsu.work2.viewmodel.VIewModelFactory;

public class EmployerCreatePlaceActivity extends AppCompatActivity implements CallonClick {
    private ActivityEmployerCreatePlaceBinding binding;
    private EmployerViewModel employerViewModel;
    private VIewModelFactory vIewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActivity();
    }

    private void initActivity()
    {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_employer_create_place);
        vIewModelFactory = new VIewModelFactory(this);
        employerViewModel = ViewModelProviders.of(this,vIewModelFactory).get(EmployerViewModel.class);
        binding.setEmployercreateplace(employerViewModel);
    }



    @Override
    public void onBtnClick(View view) {
        switch (view.getId())
        {
            case R.id.employer_create_place_btn_back:
            {
                super.onBackPressed();
                finish();
                break;
            }
            case R.id.employer_create_place_btn_addr:
            {
                break;
            }
            case R.id.employer_create_place_btn_addr2:
            {
                break;
            }
            case R.id.employer_create_place_btn_wifi:
            {
                employerViewModel.getWifi();
                break;
            }
            case R.id.employer_create_place_btn_create:
            {
                super.onBackPressed();
                finish();
                break;
            }
        }

    }

    @Override
    public void textChanged(int index) {

    }
}
