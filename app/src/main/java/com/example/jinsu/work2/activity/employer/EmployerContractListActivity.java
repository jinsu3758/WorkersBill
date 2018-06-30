package com.example.jinsu.work2.activity.employer;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.example.jinsu.work2.R;
import com.example.jinsu.work2.databinding.ActivityEmployerContractListBinding;
import com.example.jinsu.work2.util.CallonClick;
import com.example.jinsu.work2.viewmodel.MainViewModel;
import com.example.jinsu.work2.viewmodel.VIewModelFactory;

public class EmployerContractListActivity extends AppCompatActivity implements CallonClick {

    ActivityEmployerContractListBinding binding;
    private MainViewModel mainViewModel;
    private VIewModelFactory vIewModelFactory;
    private RequestManager glide;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActivity();
    }

    private void initActivity()
    {
        glide = Glide.with(this);
        transaction = getSupportFragmentManager().beginTransaction();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_employer_contract_list);
        vIewModelFactory = new VIewModelFactory(this);
        mainViewModel = ViewModelProviders.of(this,vIewModelFactory).get(MainViewModel.class);
        binding.setEmployerContractList(mainViewModel);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,binding.employerContractListLayout,
                binding.toolbar , 0, 0);
        toggle.syncState();
    }

    @Override
    public void onBtnClick(View view) {
        switch (view.getId())
        {
            case R.id.employer_contract_list_btn_drawer:
            {
                super.onBackPressed();
                finish();
                break;
            }
            case R.id.employer_contract_list_btn_home:
            {
                startActivity(new Intent(this,EmployerHomeActivity.class));
                finish();
                break;
            }
            case R.id.employer_contract_list_tab1:
            {
                binding.employerContractListTabTxt1.setBackgroundColor(ContextCompat.getColor(this,R.color.colorPrimary));
                binding.employerContractListTabTxt2.setBackgroundColor(ContextCompat.getColor(this,R.color.gray));
                glide.load(R.drawable.round_imgeview_cabinet1).into(binding.employerContractListTabCircle1);
                glide.load(R.drawable.round_imgeview_cabinet2).into(binding.employerContractListTabCircle2);
                break;
            }
            case R.id.employer_contract_list_tab2:
            {
                binding.employerContractListTabTxt2.setBackgroundColor(ContextCompat.getColor(this,R.color.colorPrimary));
                binding.employerContractListTabTxt1.setBackgroundColor(ContextCompat.getColor(this,R.color.gray));
                glide.load(R.drawable.round_imgeview_cabinet2).into(binding.employerContractListTabCircle1);
                glide.load(R.drawable.round_imgeview_cabinet1).into(binding.employerContractListTabCircle2);
                break;
            }
        }
    }

    @Override
    public void textChanged(int index) {

    }

    @Override
    public void onBackPressed() {
        if (binding.employerContractListLayout.isDrawerOpen(GravityCompat.START)) {
            binding.employerContractListLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }
}
