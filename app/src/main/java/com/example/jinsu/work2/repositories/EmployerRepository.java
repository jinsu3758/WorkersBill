package com.example.jinsu.work2.repositories;

import com.example.jinsu.work2.model.Contract;
import com.example.jinsu.work2.model.Place;
import com.example.jinsu.work2.model.Worker;
import com.example.jinsu.work2.network.contract.ContractDataSource;
import com.example.jinsu.work2.network.contract.ContractSource;
import com.example.jinsu.work2.network.place.PlaceDataSource;
import com.example.jinsu.work2.network.place.PlaceSource;
import com.example.jinsu.work2.network.worker.WorkerDataSource;
import com.example.jinsu.work2.network.worker.WorkerSource;

import java.util.ArrayList;

public class EmployerRepository implements ContractSource, WorkerSource, PlaceSource {
    private static EmployerRepository employerRepository = new EmployerRepository();

    private PlaceDataSource placeDataSource;
    private ContractDataSource contractDataSource;
    private WorkerDataSource workerDataSource;

    public static EmployerRepository getInstance()
    {
        return employerRepository;
    }

    private EmployerRepository()
    {
        contractDataSource = new ContractDataSource();
        workerDataSource = new WorkerDataSource();

    }




    @Override
    public void getContractWorker(LoadContractWorkerCallback callback) {
        contractDataSource.getContractWorker(new LoadContractWorkerCallback() {
            @Override
            public void onContractWorkerLoad(ArrayList<Contract> list) {
                if(callback != null)
                {
                    callback.onContractWorkerLoad(list);
                }
            }
        });
    }

    @Override
    public void getContractTemp(LoadContractTempCallback callback) {
        contractDataSource.getContractTemp(new LoadContractTempCallback() {
            @Override
            public void onContractTempLoad(ArrayList<Contract> list) {
                if(callback != null)
                {
                    callback.onContractTempLoad(list);
                }
            }
        });
    }

    @Override
    public void getReqWorker(LoadReqWorkerCallback callback) {
        workerDataSource.getReqWorker(new LoadReqWorkerCallback() {
            @Override
            public void onReqWorkerLoad(ArrayList<Worker> list) {
                if(callback != null)
                {
                    callback.onReqWorkerLoad(list);
                }
            }
        });
    }


    @Override
    public void getCurWorker(LoadCurWorkerCallback callback) {
        workerDataSource.getCurWorker(new LoadCurWorkerCallback() {
            @Override
            public void onCurWorkerLoad(ArrayList<Worker> list) {
                if(callback != null)
                {
                    callback.onCurWorkerLoad(list);
                }
            }
        });
    }

    @Override
    public void getPlace(LoadPlaceCallback callback) {
        placeDataSource.getPlace(new LoadPlaceCallback() {
            @Override
            public void onPlaceLoad(ArrayList<Place> list) {
                if(callback != null)
                {
                    callback.onPlaceLoad(list);
                }
            }
        });
    }
}
