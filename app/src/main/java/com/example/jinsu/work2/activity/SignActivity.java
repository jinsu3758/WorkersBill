package com.example.jinsu.work2.activity;

import android.app.AlertDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.jinsu.work2.R;
import com.example.jinsu.work2.common.BaseApplication;
import com.example.jinsu.work2.databinding.ActivitySignBinding;
import com.example.jinsu.work2.manager.TaskManager;
import com.example.jinsu.work2.network.CommonClass;
import com.example.jinsu.work2.network.model.Join;
import com.example.jinsu.work2.util.CallonClick;
import com.example.jinsu.work2.util.PreferenceUtil;
import com.example.jinsu.work2.viewmodel.MainViewModel;
import com.example.jinsu.work2.viewmodel.VIewModelFactory;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SignActivity extends AppCompatActivity implements CallonClick {

    private ActivitySignBinding binding;
    private MainViewModel mainViewModel;
    private VIewModelFactory factory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActivity();
    }

    private void initActivity()
    {
        binding = DataBindingUtil.setContentView(this,R.layout.activity_sign);
        factory = new VIewModelFactory(this);
        mainViewModel = ViewModelProviders.of(this,factory).get(MainViewModel.class);
        binding.setSign(mainViewModel);
    }

    @Override
    public void onBtnClick(View view)
    {
        //서명 저장
        if(view.getId() == R.id.sign_next_btn)
        {
            binding.layoutDrawView.buildDrawingCache();
            Bitmap bitmap = binding.layoutDrawView.getDrawingCache();
            mainViewModel.savdSign(this,bitmap);
        }
        //서명 지우기
        else
        {
            binding.drawView.clear();
        }
    }
    @Override
    public void textChanged(int flag) {
        TaskManager.api_create_user(BaseApplication.join, new Callback<Join>() {
            @Override
            public void success(Join join, Response response) {
                Log.d("sign_at","성공");
                if(join != null) {
                    PreferenceUtil.saveUser(join);
                    AlertDialog.Builder alert = new AlertDialog.Builder(getApplicationContext());
                    alert.setTitle("인증코드 발송").setMessage("'" + BaseApplication.join.email + "'로 인증코드가 전송되었습니다.\n이메일 확인 후에 작성해주세요")
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    startActivity(new Intent(getApplicationContext(), CertActivity.class) );
                                }
                            }).setNegativeButton("취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            return;
                        }
                    });
                    alert.create();
                    alert.show();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("sign_at", CommonClass.showError(error));
            }
        });

    }
}
