package com.example.jinsu.work2.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.example.jinsu.work2.R;
import com.example.jinsu.work2.common.BaseApplication;
import com.example.jinsu.work2.databinding.ActivityCertBinding;
import com.example.jinsu.work2.util.CallonClick;
import com.example.jinsu.work2.viewmodel.MainViewModel;
import com.example.jinsu.work2.viewmodel.VIewModelFactory;

public class CertActivity extends ParentActivity implements CallonClick{

    private ActivityCertBinding binding;
    private MainViewModel mainViewModel;
    private VIewModelFactory vIewModelFactory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActivity();
        //인증번호 요청

    }


    private void initActivity()
    {
        binding = DataBindingUtil.setContentView(this,R.layout.activity_cert);
        vIewModelFactory = new VIewModelFactory(this);
        mainViewModel = ViewModelProviders.of(this,vIewModelFactory).get(MainViewModel.class);
        binding.setCert(mainViewModel);
        mainViewModel.requestCert(BaseApplication.join.email);
        //비밀번호 EditText의 Focus설정
        /*binding.editOne.setNextFocusDownId(R.id.edit_two);
        binding.editTwo.setNextFocusDownId(R.id.edit_three);
        binding.editThree.setNextFocusDownId(R.id.edit_four);*/

    }

    //다음 단계 버튼
    @Override
    public void onBtnClick(View view) {
    }

    @Override
    public void textChanged(int flag) {
            switch (flag)
            {
                case 1:
                {
                    binding.editTwo.requestFocus();
                    break;
                }
                case 2:
                {
                    binding.editThree.requestFocus();
                    break;
                }
                case 3:
                {
                    binding.editFour.requestFocus();
                    break;

                }
                case 4:
                {
                    /*binding.editOne.setFocusable(false);
                    binding.editTwo.setFocusable(false);
                    binding.editThree.setFocusable(false);
                    binding.editFour.setFocusable(false);*/
                    break;
                }
                default:
                {
                    startActivity(new Intent(this,SelectActivity.class));
                    finish();
                }
            }
    }


}
