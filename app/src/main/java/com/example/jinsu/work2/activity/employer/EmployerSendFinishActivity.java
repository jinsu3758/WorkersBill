package com.example.jinsu.work2.activity.employer;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.jinsu.work2.R;
import com.example.jinsu.work2.databinding.ActivityEmployerSendFinishBinding;
import com.example.jinsu.work2.util.CallonClick;
import com.example.jinsu.work2.viewmodel.MainViewModel;
import com.example.jinsu.work2.viewmodel.VIewModelFactory;

public class EmployerSendFinishActivity extends AppCompatActivity implements CallonClick {


    ActivityEmployerSendFinishBinding binding;
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

        binding = DataBindingUtil.setContentView(this, R.layout.activity_employer_send_finish);
        vIewModelFactory = new VIewModelFactory(this);
        mainViewModel = ViewModelProviders.of(this,vIewModelFactory).get(MainViewModel.class);
        binding.setEmployerSendFinish(mainViewModel);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,binding.employerHomeLayout ,binding.toolbar , 0, 0);
        toggle.syncState();
        //binding.employerSetupTxtName.setText(name);

    }

    @Override
    public void onBtnClick(View view) {
        if(view.getId() == R.id.employer_send_finish_btn_drawer)
        {
            binding.employerHomeLayout.openDrawer(GravityCompat.START);
        }
        else if(view.getId() == R.id.employer_send_finish_btn_home)
        {
            startActivity(new Intent(this,EmployerHomeActivity.class));
        }
        else if(view.getId() == R.id.employer_send_finish_btn_home2)
        {
            startActivity(new Intent(this,EmployerHomeActivity.class));
        }

    }

    @Override
    public void textChanged(int flag) {

    }

    @Override
    public void onBackPressed() {
        if (binding.employerHomeLayout.isDrawerOpen(GravityCompat.START)) {
            binding.employerHomeLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }
}
