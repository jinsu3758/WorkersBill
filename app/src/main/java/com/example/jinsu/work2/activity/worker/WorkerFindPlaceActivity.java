package com.example.jinsu.work2.activity.worker;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.example.jinsu.work2.R;
import com.example.jinsu.work2.adapter.EmployerPlaceAdapter;
import com.example.jinsu.work2.databinding.ActivityWorkerFindPlaceBinding;
import com.example.jinsu.work2.model.Place;
import com.example.jinsu.work2.util.CallonClick;
import com.example.jinsu.work2.viewmodel.MainViewModel;
import com.example.jinsu.work2.viewmodel.VIewModelFactory;

import java.util.ArrayList;

public class WorkerFindPlaceActivity extends AppCompatActivity implements CallonClick {

    private ActivityWorkerFindPlaceBinding binding;
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
        binding = DataBindingUtil.setContentView(this, R.layout.activity_worker_find_place);
        vIewModelFactory = new VIewModelFactory(this);
        mainViewModel = ViewModelProviders.of(this,vIewModelFactory).get(MainViewModel.class);
        binding.setWorkerFindPlace(mainViewModel);

        //enter을 쳤을 때 반응하는 리스너
        binding.workerFindPlaceEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                switch (actionId)
                {
                    case EditorInfo.IME_ACTION_DONE:
                    {
                        Log.d("worker_find_place","enter 동작");
                        break;
                    }
                    default:
                    {
                        Log.d("worker_find_place",actionId + "");
                        return false;
                    }
                }
                return true;
            }
        });

    }

    private void initRecyclerView()
    {
        list = new ArrayList<>();
        adapter = new EmployerPlaceAdapter(this, list, new EmployerPlaceAdapter.onClickCallback() {
            @Override
            public void onClick(int position) {

            }
        });
        binding.workerFindPlaceRecycler.setHasFixedSize(true);
        binding.workerFindPlaceRecycler.setLayoutManager(new LinearLayoutManager(this));
        binding.workerFindPlaceRecycler.setAdapter(adapter);
    }

    private void setList()
    {
        list.clear();
//        list.add(new Place("e","e","e","e"));
        adapter.notifyDataSetChanged();

    }



    @Override
    public void onBtnClick(View view) {
        switch (view.getId()){
            case R.id.worker_find_place_btn_back:
            {
                super.onBackPressed();
                finish();
                break;
            }
            case R.id.worker_find_place_btn_find:
            {
                break;
            }
        }

    }

    //edittext의 텍스트가 바뀔 때마다
    @Override
    public void textChanged(String text) {
        Log.d("worker_find_place","edit : " + text);
    }
}
