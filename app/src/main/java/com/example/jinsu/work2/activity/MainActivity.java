package com.example.jinsu.work2.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.jinsu.work2.R;
import com.example.jinsu.work2.databinding.ActivityMainBinding;
import com.example.jinsu.work2.util.CallonClick;
import com.example.jinsu.work2.viewmodel.MainViewModel;
import com.example.jinsu.work2.viewmodel.VIewModelFactory;

public class MainActivity extends AppCompatActivity implements CallonClick{

    private ActivityMainBinding binding;
    private MainViewModel mainViewModel;
    private VIewModelFactory vIewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActivity();
    }

    private void initActivity()
    {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        vIewModelFactory = new VIewModelFactory(this);
        mainViewModel = ViewModelProviders.of(this,vIewModelFactory).get(MainViewModel.class);
        binding.setMain(mainViewModel);

    }

    @Override
    public void onBtnClick(View view) {
        Log.d("main_activity",view.getResources().getResourceEntryName(view.getId()));
        if(view.getResources().getResourceEntryName(view.getId()).toString().equals("main_btn_login"))
        {
            Log.d("main_activity",view.getResources().getResourceEntryName(view.getId()) + "111");
            startActivity(new Intent(this,LoginActivity.class));
        }
        else
        {
            startActivity(new Intent(this,JoinActivity.class));
        }
    }

    @Override
    public void textChanged(int flag) {

    }
}
