package com.example.jinsu.work2.activity.employer;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.jinsu.work2.R;
import com.example.jinsu.work2.databinding.ActivityEmployerCalcBinding;
import com.example.jinsu.work2.dialog.CalcSaveDialog;
import com.example.jinsu.work2.util.CallonClick;
import com.example.jinsu.work2.viewmodel.MainViewModel;
import com.example.jinsu.work2.viewmodel.VIewModelFactory;

public class EmployerCalcActivity extends AppCompatActivity implements CallonClick{

    ActivityEmployerCalcBinding binding;
    private MainViewModel mainViewModel;
    private VIewModelFactory vIewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActivity();
    }

    private void initActivity()
    {
        binding = DataBindingUtil.setContentView(this,R.layout.activity_employer_calc);
        vIewModelFactory = new VIewModelFactory(this);
        mainViewModel = ViewModelProviders.of(this,vIewModelFactory).get(MainViewModel.class);
        binding.setEmployerCalc(mainViewModel);
    }

    @Override
    public void onBtnClick(View view) {
        switch (view.getId())
        {
            case R.id.employer_calc_btn_back:
            {
                super.onBackPressed();
                finish();
                break;
            }
            case R.id.employer_calc_btn_load:
            {
                startActivity(new Intent(getBaseContext(),EmployerCalcListActivity.class));
                break;
            }
            case R.id.employer_calc_btn_calc:
            {
                break;
            }
            case R.id.employer_calc_btn_save:
            {
                CalcSaveDialog dialog = new CalcSaveDialog(this, new CalcSaveDialog.onBtnCallback() {
                @Override
                public void onSave(String name) {
                    mainViewModel.onSaveCalc(name);
                    startActivity(new Intent(getBaseContext(),EmployerCalcListActivity.class));
                }

                @Override
                public void onWrite() {

                }
                 });
                dialog.show();
                break;
            }
            case R.id.employer_calc_btn_write:
            {
                break;
            }
        }
    }

    @Override
    public void textChanged(int index) {

    }
}
