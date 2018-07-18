package com.example.jinsu.work2.fragment.employer;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.example.jinsu.work2.R;
import com.example.jinsu.work2.adapter.EmployerContractWorkerAdapter;
import com.example.jinsu.work2.databinding.FragmentContractWorkerBinding;
import com.example.jinsu.work2.model.Contract;
import com.example.jinsu.work2.util.CallonClick;
import com.example.jinsu.work2.viewmodel.MainViewModel;
import com.example.jinsu.work2.viewmodel.VIewModelFactory;

import java.util.ArrayList;

public class EmployerContractWorkerFragment extends Fragment implements CallonClick {
    private MainViewModel mainViewModel;
    private VIewModelFactory vIewModelFactory;
    private FragmentContractWorkerBinding binding;
    private RequestManager requestManager;
    private ArrayList<Contract> list;
    private EmployerContractWorkerAdapter adapter;

    public EmployerContractWorkerFragment()
    { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return DataBindingUtil.inflate(inflater, R.layout.fragment_contract_worker,container,false).getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        requestManager = Glide.with(this);
        binding = DataBindingUtil.getBinding(getView());
        vIewModelFactory = new VIewModelFactory(this);
        mainViewModel = ViewModelProviders.of(this,vIewModelFactory).get(MainViewModel.class);
        binding.setContractWorker(mainViewModel);
        initRecyclerView();
    }

    @Override
    public void onBtnClick(View view) {

    }

    @Override
    public void textChanged(int index) {

    }

    @Override
    public void onResume()
    {
        setList();
        super.onResume();
    }

    private void initRecyclerView()
    {
        list = new ArrayList<>();
        adapter = new EmployerContractWorkerAdapter(list, requestManager,new EmployerContractWorkerAdapter.onClickCallback() {
            @Override
            public void onClick(int position) {

            }
        });
        binding.employerContractWorkerRecycle.setHasFixedSize(true);
        binding.employerContractWorkerRecycle.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.employerContractWorkerRecycle.setAdapter(adapter);
//       setList();
    }

    private void setList()
    {
        mainViewModel.getContractWorkers(new MainViewModel.ContractWorkerCallback() {
            @Override
            public void get(ArrayList<Contract> contracts) {
                list.addAll(contracts);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
