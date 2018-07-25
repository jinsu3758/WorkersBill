package com.example.jinsu.work2.activity;

import android.app.AlertDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.example.jinsu.work2.R;
import com.example.jinsu.work2.databinding.ActivityJoinBinding;
import com.example.jinsu.work2.util.CallonClick;
import com.example.jinsu.work2.viewmodel.MainViewModel;
import com.example.jinsu.work2.viewmodel.VIewModelFactory;

//회원가입 액티비티
public class JoinActivity extends ParentActivity implements CallonClick{

    //    private ActivityJoinBinding binding;
    private ActivityJoinBinding binding;
    private MainViewModel mainViewModel;
    private VIewModelFactory vIewModelFactory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActivity();
    }

    private void initActivity()
    {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_join);
        vIewModelFactory = new VIewModelFactory(this);
        mainViewModel = ViewModelProviders.of(this,vIewModelFactory).get(MainViewModel.class);
        binding.setJoin(mainViewModel);

    }

    //이메일 인증하기 버튼 누를시
    @Override
    public void onBtnClick(View vIew) {
        mainViewModel.onJoinUser(JoinActivity.this);
//        startActivity(new Intent(this,CertActivity.class));
    }

    @Override
    public void textChanged(String text) {
        if(text == null)
        {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage("이미 가입된 이메일입니다.")
                    .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            return;
                        }
                    });
            alert.create();
            alert.show();
        }
        else
        {

            startActivity(new Intent(this, InputInfoActivity.class) );

        }
    }

}
