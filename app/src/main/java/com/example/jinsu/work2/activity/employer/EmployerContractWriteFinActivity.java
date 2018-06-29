package com.example.jinsu.work2.activity.employer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.example.jinsu.work2.R;
import com.example.jinsu.work2.databinding.ActivityEmployerContractWriteFinBinding;
import com.example.jinsu.work2.util.CallonClick;

public class EmployerContractWriteFinActivity extends AppCompatActivity implements CallonClick {

    private ActivityEmployerContractWriteFinBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employer_contract_write_fin);
    }

    @Override
    public void onBtnClick(View view) {
        switch (view.getId())
        {
            case R.id.employer_contract_write_fin_btn_back:
            {
                super.onBackPressed();
                finish();
                break;
            }
            case R.id.employer_contract_write_fin_btn_home:
            {
                startActivity(new Intent(this,EmployerHomeActivity.class));
                finish();
                break;
            }
            case R.id.employer_contract_write_fin_btn_send:
            {//전송완료 화면 떠야한다
                break;
            }
        }
    }

    @Override
    public void textChanged(int index) {

    }
}

