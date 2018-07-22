package com.example.jinsu.work2.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.jinsu.work2.util.CallonClick;
import com.example.jinsu.work2.R;
import com.example.jinsu.work2.databinding.ActivitySignBinding;
import com.example.jinsu.work2.viewmodel.MainViewModel;
import com.example.jinsu.work2.viewmodel.VIewModelFactory;

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
            DrawView drawView = (DrawView)findViewById(R.id.drawView);
            drawView.clear();
        }
    }
    @Override
    public void textChanged(String text) {

    }
}
