package com.example.jinsu.work2.activity.worker;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.jinsu.work2.R;
import com.example.jinsu.work2.activity.employer.EmployerCalcListActivity;
import com.example.jinsu.work2.databinding.ActivityWorkerHomeBinding;
import com.example.jinsu.work2.dialog.BeforeJoinDialog;
import com.example.jinsu.work2.util.CallonClick;
import com.example.jinsu.work2.viewmodel.MainViewModel;
import com.example.jinsu.work2.viewmodel.VIewModelFactory;

public class WorkerHomeActivity extends AppCompatActivity implements CallonClick {

    private ActivityWorkerHomeBinding binding;
    private MainViewModel mainViewModel;
    private VIewModelFactory vIewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActivity();
    }

    private void initActivity()
    {
        binding = DataBindingUtil.setContentView(this,R.layout.activity_worker_home);
        vIewModelFactory = new VIewModelFactory(this);
        mainViewModel = ViewModelProviders.of(this,vIewModelFactory).get(MainViewModel.class);
        binding.setWorkerHome(mainViewModel);
    }

    @Override
    public void onBtnClick(View view) {
        switch (view.getId()){
            case R.id.worker_home_btn_change:{
                startActivity(new Intent(this,WorkerSelectWorkplaceActivity.class));
                break;
            }
            case R.id.worker_home_btn_goto_office:{
                BeforeJoinDialog dialog = new BeforeJoinDialog(this, new BeforeJoinDialog.onBtnCallback() {
                    @Override
                    public void onSave(String name) {
                    }

                    @Override
                    public void onWrite() {

                    }
                });
                dialog.show();
                break;
            }
            case R.id.worker_home_btn_leave_office:{
                BeforeJoinDialog dialog = new BeforeJoinDialog(this, new BeforeJoinDialog.onBtnCallback() {
                    @Override
                    public void onSave(String name) {
                    }

                    @Override
                    public void onWrite() {

                    }
                });
                dialog.show();
                break;
            }
            case R.id.worker_home_btn_cont_request_alarm:{
                startActivity(new Intent(this,WorkerContractRequestActivity.class));
                break;
            }
            case R.id.worker_home_btn_cont_container:{
                startActivity(new Intent(this,WorkerContractContainerActivity.class));

                break;
            }

        }

    }

    @Override
    public void textChanged(int index) {

    }
}
