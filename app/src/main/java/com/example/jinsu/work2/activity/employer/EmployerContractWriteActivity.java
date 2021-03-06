package com.example.jinsu.work2.activity.employer;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.jinsu.work2.R;
import com.example.jinsu.work2.databinding.ActivityEmployerContractWriteBinding;
import com.example.jinsu.work2.util.CallonClick;
import com.example.jinsu.work2.viewmodel.MainViewModel;
import com.example.jinsu.work2.viewmodel.VIewModelFactory;

public class EmployerContractWriteActivity extends AppCompatActivity implements CallonClick {

    private ActivityEmployerContractWriteBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employer_contract_write);
    }


    @Override
    public void onBtnClick(View view) {
        switch (view.getId())
        {
            case R.id.employer_contract_write_btn_back:
            {
                super.onBackPressed();
                finish();
                break;
            }
            case R.id.employer_contract_write_btn_home:
            {
                break;
            }
            case R.id.employer_contract_write_btn_save:
            {
                break;
            }
            case R.id.employer_contract_write_btn_write:
            {
                Log.v("태그4", "4444444");
                startActivity(new Intent(this,EmployerHomeActivity.class));
                finish();
                //break;
            }
        }
    }

    @Override
    public void textChanged(int index) {

    }
}
