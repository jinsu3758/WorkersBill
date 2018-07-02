package com.example.jinsu.work2.activity.employer;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.bumptech.glide.RequestManager;
import com.example.jinsu.work2.R;
import com.example.jinsu.work2.adapter.EmployerCurWorkerAdapter;
import com.example.jinsu.work2.adapter.EmployerReqWorkerAdapter;
import com.example.jinsu.work2.databinding.ActivityEmployerManageBinding;
import com.example.jinsu.work2.model.Worker;
import com.example.jinsu.work2.util.CallonClick;
import com.example.jinsu.work2.viewmodel.EmployerViewModel;
import com.example.jinsu.work2.viewmodel.VIewModelFactory;

import java.util.ArrayList;

public class EmployerManageActivity extends AppCompatActivity implements CallonClick{

    private ActivityEmployerManageBinding binding;
    private EmployerViewModel employerViewModel;
    private VIewModelFactory vIewModelFactory;
    private RequestManager requestManager;

    private EmployerReqWorkerAdapter req_adapter;
    private EmployerCurWorkerAdapter cur_adapter;
    private ArrayList<Worker> req_worker;
    private ArrayList<Worker> cur_worker;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActivity();
        initRecyclerview();

    }

    @Override
    protected void onResume()
    {
        setList();
        super.onResume();
    }

    private void initActivity()
    {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_employer_manage);
        vIewModelFactory = new VIewModelFactory(this);
        employerViewModel = ViewModelProviders.of(this,vIewModelFactory).get(EmployerViewModel.class);
        binding.setEmployerManage(employerViewModel);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, binding.employerManageLayout ,
                binding.toolbar , 0, 0);
        toggle.syncState();
    }

    private void initRecyclerview()
    {
        req_worker = new ArrayList<>();
        cur_worker = new ArrayList<>();

        req_adapter = new EmployerReqWorkerAdapter(req_worker, requestManager,
                new EmployerReqWorkerAdapter.onClickCallback() {
                    @Override
                    public void onClick(int position, Boolean code) {

                    }
                });
        cur_adapter = new EmployerCurWorkerAdapter(cur_worker, requestManager,
                new EmployerCurWorkerAdapter.onClickCallback() {
                    @Override
                    public void onClick(int position) {

                    }
                });
        binding.employerManageReqRecycler.setHasFixedSize(true);
        binding.employerManageReqRecycler.setLayoutManager(new LinearLayoutManager(this));
        binding.employerManageReqRecycler.setAdapter(req_adapter);
        binding.employerManageReqRecycler.setNestedScrollingEnabled(false);

        binding.employerManageCurRecycler.setHasFixedSize(true);
        binding.employerManageCurRecycler.setLayoutManager(new LinearLayoutManager(this));
        binding.employerManageCurRecycler.setAdapter(cur_adapter);
        binding.employerManageCurRecycler.setNestedScrollingEnabled(false);
    }

    private void setList()
    {
        employerViewModel.getReqWorker(new EmployerViewModel.WorkerCallback() {
            @Override
            public void get(ArrayList<Worker> workers) {
                req_worker.addAll(workers);
                req_adapter.notifyDataSetChanged();
            }
        });

        employerViewModel.getCurWorker(new EmployerViewModel.WorkerCallback() {
            @Override
            public void get(ArrayList<Worker> workers) {
                cur_worker.addAll(workers);
                cur_adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onBtnClick(View view) {
        switch (view.getId())
        {
            case R.id.employer_manage_btn_drawer:
            {
                binding.employerManageLayout.openDrawer(GravityCompat.START);
                break;
            }
            case R.id.employer_manage_btn_home:
            {
                startActivity(new Intent(this,EmployerHomeActivity.class));
                finish();
                break;
            }
        }
    }

    @Override
    public void textChanged(int index) {

    }

    @Override
    public void onBackPressed() {
        if (binding.employerManageLayout.isDrawerOpen(GravityCompat.START)) {
            binding.employerManageLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }
}
