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
import com.example.jinsu.work2.common.Constants;
import com.example.jinsu.work2.databinding.ActivityEmployerCalcListBinding;
import com.example.jinsu.work2.network.model.PersonnalCost;
import com.example.jinsu.work2.util.CallonClick;
import com.example.jinsu.work2.viewmodel.MainViewModel;
import com.example.jinsu.work2.viewmodel.VIewModelFactory;

import java.util.ArrayList;
import java.util.List;


public class EmployerCalcListActivity extends AppCompatActivity implements CallonClick {


    private ActivityEmployerCalcListBinding binding;
    private MainViewModel mainViewModel;
    private VIewModelFactory vIewModelFactory;
    private EmployerCalcListAdapter adapter;
    private List<PersonnalCost> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("calc_list",getCallingActivity().getShortClassName());
        initActivity();
        initRecyclerview();
    }


    private void initActivity()
    {
        list = new ArrayList<>();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_employer_calc_list);
        vIewModelFactory = new VIewModelFactory(this);
        mainViewModel = ViewModelProviders.of(this,vIewModelFactory).get(MainViewModel.class);
        binding.setEmployerCalcList(mainViewModel);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, binding.employerCalcListLayout ,binding.employerCalcListToolbar , 0, 0);
        toggle.syncState();

    }

    private void initRecyclerview()
    {
        adapter = new EmployerCalcListAdapter(this, list, new EmployerCalcListAdapter.onClickCallback() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent();
                intent.putExtra(Constants.CALC_KEY,list.get(position));
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        binding.employerCalcRecycle.setHasFixedSize(true);
        binding.employerCalcRecycle.setLayoutManager(new LinearLayoutManager(this));
        binding.employerCalcRecycle.setAdapter(adapter);
        setList();

    }

    @Override
    public void onBtnClick(View view) {
        if(view.getId() == R.id.employer_calc_list_btn_drawer)
        {
            binding.employerCalcListLayout.openDrawer(GravityCompat.START);
        }
        else if(view.getId() == R.id.employer_calc_list_btn_home)
        {
            startActivity(new Intent(this,EmployerHomeActivity.class));
            finish();
        }
    }

    public void setList()
    {
        list.clear();
        PersonnalCost p = new PersonnalCost();
        p.title = "rr";
        p.reg_date= "re";
        list.add(p);
        mainViewModel.getCalcList(new MainViewModel.ListCallback() {
            @Override
            public void getList(ArrayList<?> mlist) {
                list.addAll((ArrayList<PersonnalCost>)mlist);
                mainViewModel.setNum(list.size());
            }
        });
        adapter.notifyDataSetChanged();
    }

    @Override
    public void textChanged(int flag) {

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
