package com.example.jinsu.work2.network.contract;

import com.example.jinsu.work2.model.Contract;

import java.util.ArrayList;

public class ContractDataSource implements ContractSource {

    @Override
    public void getContractWorker(LoadContractWorkerCallback callback) {
        ArrayList<Contract> list = new ArrayList<>();
        Contract contract = new Contract();
        contract.setDate("김김");
        contract.setName("김김김");
        list.add(contract);
        if(callback != null)
        {
            callback.onContractWorkerLoad(list);
        }
    }

    @Override
    public void getContractTemp(LoadContractTempCallback callback) {
        ArrayList<Contract> list = new ArrayList<>();
        Contract contract = new Contract();
        contract.setDate("김김");
        contract.setName("김김김");
        for(int i=0; i< 10; i++) {
            list.add(contract);
        }
        if(callback != null)
        {
            callback.onContractTempLoad(list);
        }
    }
}
