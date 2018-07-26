package com.example.jinsu.work2.activity.employer;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.jinsu.work2.R;
import com.example.jinsu.work2.databinding.ActivityEmployerContractSelectBinding;
import com.example.jinsu.work2.dialog.SelectDataDialog;
import com.example.jinsu.work2.util.CallonClick;
import com.example.jinsu.work2.viewmodel.MainViewModel;
import com.example.jinsu.work2.viewmodel.VIewModelFactory;

public class EmployerContractSelectActivity extends AppCompatActivity implements CallonClick {

    private ActivityEmployerContractSelectBinding binding;
    private MainViewModel mainViewModel;
    private VIewModelFactory vIewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActivity();
    }

    private void initActivity()
    {
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        Log.d("emloyer_home", "name : "+name);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_employer_contract_select);
        vIewModelFactory = new VIewModelFactory(this);
        mainViewModel = ViewModelProviders.of(this,vIewModelFactory).get(MainViewModel.class);
        binding.setEmployerContractSelect(mainViewModel);


    }


    @Override
    public void onBtnClick(View view) {

        switch (view.getId())
        {
            case R.id.employer_contract_select_btn_back:
            {
                super.onBackPressed();
                finish();
                break;
            }
            case R.id.employer_contract_select_btn_home:
            {
                super.onBackPressed();
                finish();
                break;
            }
            case R.id.employer_contract_select_btn_load:
            {
                SelectDataDialog dialog = new SelectDataDialog(this, new SelectDataDialog.onBtnCallback() {
                    @Override
                    public void onCalc() {
                        startActivity(new Intent(getApplicationContext(),EmployerCalcListActivity.class));
                    }

                    @Override
                    public void onContract() {
                        startActivity(new Intent(getApplicationContext(),EmployerContractListActivity.class));
                    }
                });
                dialog.show();
                break;
            }
            case R.id.employer_contract_select_btn_calc:
            {
                startActivity(new Intent(this,EmployerCalcActivity.class));
                break;
            }
            case R.id.employer_contract_select_btn_now:
            {
                startActivity(new Intent(this,EmployerContractWriteActivity.class));
                break;
            }
        }
    }

    @Override
    public void textChanged(int flag) {

    }
}
