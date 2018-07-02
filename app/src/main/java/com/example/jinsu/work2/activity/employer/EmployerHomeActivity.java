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
import com.example.jinsu.work2.databinding.ActivityEmployerHomeBinding;
import com.example.jinsu.work2.util.CallonClick;
import com.example.jinsu.work2.viewmodel.EmployerViewModel;
import com.example.jinsu.work2.viewmodel.VIewModelFactory;

public class EmployerHomeActivity extends AppCompatActivity implements CallonClick {


    ActivityEmployerHomeBinding binding;
    private EmployerViewModel employerViewModel;
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

        binding = DataBindingUtil.setContentView(this, R.layout.activity_employer_home);
        vIewModelFactory = new VIewModelFactory(this);
        employerViewModel = ViewModelProviders.of(this,vIewModelFactory).get(EmployerViewModel.class);
        binding.setEmployerHome(employerViewModel);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,binding.employerHomeLayout ,binding.toolbar , 0, 0);
        toggle.syncState();
        binding.employerHomeTxtName.setText(name);

    }

    @Override
    public void onBtnClick(View view) {
        if(view.getId() == R.id.employer_home_btn_admin)
        {
            startActivity(new Intent(this,EmployerManageActivity.class));
        }
        else if(view.getId() == R.id.employer_home_contract)
        {
            startActivity(new Intent(this,EmployerContractSelectActivity.class));
        }
        else if(view.getId() == R.id.employer_home_calc)
        {
            startActivity(new Intent(this,EmployerCalcActivity.class));
        }
        else if(view.getId() == R.id.employer_home_btn_drawer)
        {
            binding.employerHomeLayout.openDrawer(GravityCompat.START);
//            startActivity(new Intent(this,EmployerCreatePlaceActivity.class));
        }
    }

    @Override
    public void textChanged(int index) {

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
