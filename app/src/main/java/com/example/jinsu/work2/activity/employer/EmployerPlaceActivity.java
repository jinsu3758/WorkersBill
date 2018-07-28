package com.example.jinsu.work2.activity.employer;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.example.jinsu.work2.R;
import com.example.jinsu.work2.adapter.EmployerPlaceAdapter;
import com.example.jinsu.work2.databinding.ActivityEmployerPlaceBinding;
import com.example.jinsu.work2.network.model.Company;
import com.example.jinsu.work2.util.CallonClick;
import com.example.jinsu.work2.viewmodel.MainViewModel;
import com.example.jinsu.work2.viewmodel.VIewModelFactory;

import java.util.ArrayList;

public class EmployerPlaceActivity extends AppCompatActivity implements CallonClick {

    private ActivityEmployerPlaceBinding binding;
    private MainViewModel mainViewModel;
    private VIewModelFactory vIewModelFactory;
    private EmployerPlaceAdapter adapter;
    private ArrayList<Company> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActivity();
        initRecyclerView();

    }

    @Override
    protected void onResume()
    {
        setList();
        super.onResume();
    }

    private void initActivity()
    {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_employer_place);
        vIewModelFactory = new VIewModelFactory(this);
        mainViewModel = ViewModelProviders.of(this,vIewModelFactory).get(MainViewModel.class);
        binding.setEmployerplace(mainViewModel);
    }

    private void initRecyclerView()
    {
       list = new ArrayList<>();
       adapter = new EmployerPlaceAdapter(this, list, new EmployerPlaceAdapter.onClickCallback() {
           @Override
           public void onClick(int position) {
                Intent intent = new Intent(getBaseContext(),EmployerHomeActivity.class);
                intent.putExtra("name",list.get(position).name);
                startActivity(intent);
           }
       });
       binding.employerPlaceRecycler.setHasFixedSize(true);
       binding.employerPlaceRecycler.setLayoutManager(new LinearLayoutManager(this));
       binding.employerPlaceRecycler.setAdapter(adapter);
//       setList();
    }

    private void setList()
    {
        list.clear();
        mainViewModel.getCompanyList(new MainViewModel.ListCallback() {
            @Override
            public void getList(ArrayList<?> mlist) {
                list.addAll((ArrayList<Company>)mlist);
            }
        });
        adapter.notifyDataSetChanged();

    }



    @Override
    public void onBtnClick(View view) {
        if(view.getId() == R.id.employer_place_btn_back)
        {
            super.onBackPressed();
            finish();
        }
        else
        {
            startActivity(new Intent(this,EmployerCreatePlaceActivity.class));
        }
    }

    @Override
    public void textChanged(int flag) {

    }
}
