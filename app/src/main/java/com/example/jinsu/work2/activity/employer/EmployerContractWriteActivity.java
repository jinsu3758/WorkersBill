package com.example.jinsu.work2.activity.employer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.jinsu.work2.R;
import com.example.jinsu.work2.databinding.ActivityEmployerContractWriteBinding;
import com.example.jinsu.work2.util.CallonClick;

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
                startActivity(new Intent(this,EmployerHomeActivity.class));
                finish();
                break;
            }
            case R.id.employer_contract_write_btn_save:
            {
                break;
            }
            case R.id.employer_contract_write_btn_write:
            {
                break;
            }
        }
    }

    @Override
    public void textChanged(int index) {

    }
}
