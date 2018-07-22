package com.example.jinsu.work2.fragment.employer;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jinsu.work2.R;
import com.example.jinsu.work2.adapter.EmployerContractAdapter;
import com.example.jinsu.work2.databinding.FragmentContractTempBinding;
import com.example.jinsu.work2.model.Contract;
import com.example.jinsu.work2.util.CallonClick;
import com.example.jinsu.work2.viewmodel.MainViewModel;
import com.example.jinsu.work2.viewmodel.VIewModelFactory;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;

public class EmployerContractTempFragment extends Fragment implements CallonClick {


    private MainViewModel mainViewModel;
    private VIewModelFactory vIewModelFactory;
    private FragmentContractTempBinding binding;

    private ArrayList<Contract> unreceived_list;
    private List<Contract> temp_list;

    private EmployerContractAdapter unreceived_adapter;
    private EmployerContractAdapter temp_adapter;

    public EmployerContractTempFragment()
    { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return DataBindingUtil.inflate(inflater, R.layout.fragment_contract_temp,container,false).getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding = DataBindingUtil.getBinding(getView());
        vIewModelFactory = new VIewModelFactory(this);
        mainViewModel = ViewModelProviders.of(this,vIewModelFactory).get(MainViewModel.class);
        binding.setContractTemp(mainViewModel);
        initRecyclerView();
    }

    @Override
    public void onBtnClick(View view) {

    }

    @Override
    public void textChanged(String text) {

    }

    @Override
    public void onResume()
    {
        setList();
        super.onResume();
    }

    private void initRecyclerView()
    {
        unreceived_list = new ArrayList<>();
        temp_list = new RealmList<>();
        mainViewModel.onSaveContractTemp("오");

        unreceived_adapter = new EmployerContractAdapter(unreceived_list, new EmployerContractAdapter.onClickCallback() {
            @Override
            public void onClick(int position) {

            }
        });
        temp_adapter = new EmployerContractAdapter(temp_list, new EmployerContractAdapter.onClickCallback() {
            @Override
            public void onClick(int position) {

            }
        });
        binding.contractTempRecycle1.setHasFixedSize(true);
        binding.contractTempRecycle1.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.contractTempRecycle1.setAdapter(unreceived_adapter);
        binding.contractTempRecycle1.setNestedScrollingEnabled(false);

        binding.contractTempRecycle2.setHasFixedSize(true);
        binding.contractTempRecycle2.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.contractTempRecycle2.setAdapter(temp_adapter);
        binding.contractTempRecycle2.setNestedScrollingEnabled(false);
    }

    private void setList()
    {

        List<Contract> list;
        list = Realm.getDefaultInstance().where(Contract.class).findAll();
        temp_list.addAll(list);
        if(!temp_list.isEmpty())
        {
            Log.d("employer_temp",temp_list.get(0).getName());
        }
        else
        {
            Log.d("employer_temp","비어 있음");
        }
        temp_adapter.notifyDataSetChanged();
        mainViewModel.setContractTempSum(temp_list.size());

        mainViewModel.getContractTemp(new MainViewModel.ContractTempCallback() {
            @Override
            public void get(ArrayList<Contract> contracts) {
                unreceived_list.addAll(contracts);
                unreceived_adapter.notifyDataSetChanged();
            }
        });

    }
}
