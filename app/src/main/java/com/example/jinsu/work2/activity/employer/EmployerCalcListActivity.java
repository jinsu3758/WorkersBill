package com.example.jinsu.work2.activity.employer;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.example.jinsu.work2.R;
import com.example.jinsu.work2.adapter.EmployerCalcListAdapter;
import com.example.jinsu.work2.databinding.ActivityEmployerCalcListBinding;
import com.example.jinsu.work2.model.CalcContent;
import com.example.jinsu.work2.util.CallonClick;
import com.example.jinsu.work2.viewmodel.MainViewModel;
import com.example.jinsu.work2.viewmodel.VIewModelFactory;

import java.util.List;

import io.realm.Realm;


public class EmployerCalcListActivity extends AppCompatActivity implements CallonClick {


    private ActivityEmployerCalcListBinding binding;
    private MainViewModel mainViewModel;
    private VIewModelFactory vIewModelFactory;
    EmployerCalcListAdapter adapter;
    private List<CalcContent> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActivity();
        initRecyclerview();
    }


    private void initActivity()
    {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_employer_calc_list);
        vIewModelFactory = new VIewModelFactory(this);
        mainViewModel = ViewModelProviders.of(this,vIewModelFactory).get(MainViewModel.class);
        binding.setEmployerCalcList(mainViewModel);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, binding.employerCalcListLayout ,binding.employerCalcListToolbar , 0, 0);
        toggle.syncState();

    }

    private void initRecyclerview()
    {
        list = Realm.getDefaultInstance().where(CalcContent.class).findAll();
        mainViewModel.setNum(list.size());
        adapter = new EmployerCalcListAdapter(this, list, new EmployerCalcListAdapter.onClickCallback() {
            @Override
            public void onClick(int position) {

            }
        });
        binding.employerCalcRecycle.setHasFixedSize(true);
        binding.employerCalcRecycle.setLayoutManager(new LinearLayoutManager(this));
        binding.employerCalcRecycle.setAdapter(adapter);
    }

    @Override
    public void onBtnClick(View view) {
        if(view.getId() == R.id.employer_calc_list_btn_drawer)
        {
            binding.employerCalcListLayout.openDrawer(GravityCompat.START);
        }
        else if(view.getId() == R.id.employer_calc_list_btn_home)
        {
            Log.v("태그9","999999999");
            startActivity(new Intent(this,EmployerHomeActivity.class));
            finish();
        }
    }

    @Override
    public void textChanged(int index) {

    }

    @Override
    public void onBackPressed() {
        if (binding.employerCalcListLayout.isDrawerOpen(GravityCompat.START)) {
            binding.employerCalcListLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }
}
