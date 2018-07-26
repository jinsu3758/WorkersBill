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
import com.example.jinsu.work2.databinding.ActivityEmployerSetupBinding;
import com.example.jinsu.work2.util.CallonClick;
import com.example.jinsu.work2.viewmodel.MainViewModel;
import com.example.jinsu.work2.viewmodel.VIewModelFactory;

public class EmployerSetupActivity extends AppCompatActivity implements CallonClick {


    private ActivityEmployerSetupBinding binding;
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

        binding = DataBindingUtil.setContentView(this, R.layout.activity_employer_setup);
        vIewModelFactory = new VIewModelFactory(this);
        mainViewModel = ViewModelProviders.of(this,vIewModelFactory).get(MainViewModel.class);
        binding.setEmployerSetup(mainViewModel);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,binding.employerSetupLayout ,binding.toolbar , 0, 0);
        toggle.syncState();
        //binding.employerSetupTxtName.setText(name);

    }

    @Override
    public void onBtnClick(View view) {
        if(view.getId() == R.id.employer_setup_btn_drawer)
        {
            binding.employerSetupLayout.openDrawer(GravityCompat.START);
        }
        else if(view.getId() == R.id.employer_setup_btn_home)
        {
            startActivity(new Intent(this,EmployerHomeActivity.class));
        }
        else if(view.getId() == R.id.employer_setup_btn_workplace_info)
        {

        }
        else if(view.getId() == R.id.employer_setup_btn_cscenter)
        {

        }
        else if(view.getId() == R.id.employer_setup_btn_policy)
        {

        }
        else if(view.getId() == R.id.employer_setup_btn_notice)
        {

        }
        else if(view.getId() == R.id.employer_setup_btn_version_info)
        {

        }
    }

    @Override
    public void textChanged(int flag) {

    }

    @Override
    public void onBackPressed() {
        if (binding.employerSetupLayout.isDrawerOpen(GravityCompat.START)) {
            binding.employerSetupLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }
}
