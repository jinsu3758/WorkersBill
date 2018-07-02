package com.example.jinsu.work2.network.contract;

import com.example.jinsu.work2.model.Contract;

import java.util.ArrayList;

public interface ContractSource {
    interface LoadContractWorkerCallback{
        void onContractWorkerLoad(ArrayList<Contract> list);
    }
    void getContractWorker(ContractSource.LoadContractWorkerCallback callback);

    interface LoadContractTempCallback{
        void onContractTempLoad(ArrayList<Contract> list);
    }
    void getContractTemp(ContractSource.LoadContractTempCallback callback);


}
