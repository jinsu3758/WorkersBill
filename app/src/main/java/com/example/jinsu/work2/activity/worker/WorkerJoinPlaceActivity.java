package com.example.jinsu.work2.activity.worker;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.example.jinsu.work2.R;
import com.example.jinsu.work2.adapter.EmployerPlaceAdapter;
import com.example.jinsu.work2.databinding.ActivityWorkerJoinPlaceBinding;
import com.example.jinsu.work2.model.Place;
import com.example.jinsu.work2.util.CallonClick;
import com.example.jinsu.work2.viewmodel.MainViewModel;
import com.example.jinsu.work2.viewmodel.VIewModelFactory;

import java.util.ArrayList;

public class WorkerJoinPlaceActivity extends AppCompatActivity implements CallonClick {

    private ActivityWorkerJoinPlaceBinding binding;
    private MainViewModel mainViewModel;
    private VIewModelFactory vIewModelFactory;
    private EmployerPlaceAdapter adapter;
    private ArrayList<Place> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActivity();
        initRecyclerView();
    }

    private void initActivity()
    {
        binding = DataBindingUtil.setContentView(this,R.layout.activity_worker_join_place);
        vIewModelFactory = new VIewModelFactory(this);
        mainViewModel = ViewModelProviders.of(this,vIewModelFactory).get(MainViewModel.class);
        binding.setWorkerJoinPlace(mainViewModel);
    }

    private void initRecyclerView()
    {
        list = new ArrayList<>();
        adapter = new EmployerPlaceAdapter(this, list, new EmployerPlaceAdapter.onClickCallback() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getBaseContext(), WorkerHomeActivity.class);
                intent.putExtra("name",list.get(position).getName());
                startActivity(intent);
            }
        });
        binding.workerJoinPlaceRecycler.setHasFixedSize(true);
        binding.workerJoinPlaceRecycler.setLayoutManager(new LinearLayoutManager(this));
        binding.workerJoinPlaceRecycler.setAdapter(adapter);
//        setList();
    }

    private void setList()
    {
        list.clear();
        list.add(new Place("e","e","e","e"));
        adapter.notifyDataSetChanged();

    }

    @Override
    protected void onResume()
    {
        setList();
        super.onResume();
    }

    @Override
    public void onBtnClick(View view) {
        switch (view.getId()) {
            case R.id.worker_join_place_btn_back: {
                super.onBackPressed();
                finish();
                break;
            }
            case R.id.worker_join_place_btn_create:{
                startActivity(new Intent(this,WorkerFindPlaceActivity.class));
                break;
            }
        }

    }

    @Override
    public void textChanged(String text) {

    }
}
