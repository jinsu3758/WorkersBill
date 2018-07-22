package com.example.jinsu.work2.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.jinsu.work2.util.CallonClick;
import com.example.jinsu.work2.R;
import com.example.jinsu.work2.databinding.ActivityCertBinding;
import com.example.jinsu.work2.viewmodel.MainViewModel;
import com.example.jinsu.work2.viewmodel.VIewModelFactory;

public class CertActivity extends AppCompatActivity implements CallonClick{

    private ActivityCertBinding binding;
    private MainViewModel mainViewModel;
    private VIewModelFactory vIewModelFactory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActivity();

    }

    private void initActivity()
    {
        binding = DataBindingUtil.setContentView(this,R.layout.activity_cert);
        vIewModelFactory = new VIewModelFactory(this);
        mainViewModel = ViewModelProviders.of(this,vIewModelFactory).get(MainViewModel.class);
        binding.setCert(mainViewModel);
        //비밀번호 EditText의 Focus설정
        /*binding.editOne.setNextFocusDownId(R.id.edit_two);
        binding.editTwo.setNextFocusDownId(R.id.edit_three);
        binding.editThree.setNextFocusDownId(R.id.edit_four);*/

    }

    //다음 단계 버튼
    @Override
    public void onBtnClick(View view) {
        switch (view.getId())
        {
            case R.id.cert_btn_again:
            {
                break;
            }
        }
        //startActivity(new Intent(this,SignActivity.class));
        startActivity(new Intent(this,InputInfoActivity.class));
    }

    @Override
    public void textChanged(String text) {
            switch (text)
            {
                case "1":
                {
                    binding.editTwo.requestFocus();
                    break;
                }
                case "2":
                {
                    binding.editThree.requestFocus();
                    break;
                }
                case "3":
                {
                    binding.editFour.requestFocus();
                    break;

                }
                case "4":
                {
                    /*binding.editOne.setFocusable(false);
                    binding.editTwo.setFocusable(false);
                    binding.editThree.setFocusable(false);
                    binding.editFour.setFocusable(false);*/
                    break;
                }
            }
    }


}
