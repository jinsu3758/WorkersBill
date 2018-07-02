package com.example.jinsu.work2.repositories;

import android.content.Context;

import com.example.jinsu.work2.model.Contract;
import com.example.jinsu.work2.model.EmployerPlace;
import com.example.jinsu.work2.model.User;
import com.example.jinsu.work2.model.Worker;
import com.example.jinsu.work2.network.EmployerPlaceSource;
import com.example.jinsu.work2.network.RestClient;
import com.example.jinsu.work2.network.RetroService;
import com.example.jinsu.work2.network.contract.ContractDataSource;
import com.example.jinsu.work2.network.contract.ContractSource;
import com.example.jinsu.work2.network.user.UserDataSource;
import com.example.jinsu.work2.network.user.UserSource;
import com.example.jinsu.work2.network.worker.WorkerDataSource;
import com.example.jinsu.work2.network.worker.WorkerSource;

import java.util.ArrayList;

public class EmployerRepository implements UserSource, ContractSource, WorkerSource {
    private static EmployerRepository employerRepository;

    private RetroService retroService;
    private RestClient<RetroService> restClient;
    private UserDataSource userDataSource;
    ArrayList<EmployerPlace> employerPlaces;

    private ContractDataSource contractDataSource;
    private WorkerDataSource workerDataSource;

    public static EmployerRepository getInstance()
    {
        if(employerRepository == null)
        {
            employerRepository = new EmployerRepository();
        }
        return employerRepository;
    }



    private EmployerRepository()
    {
        userDataSource = new UserDataSource();
        contractDataSource = new ContractDataSource();
        workerDataSource = new WorkerDataSource();
        employerPlaces = new ArrayList<>();
        employerPlaces.add(new EmployerPlace("워커스빌","er","er","eq"));
    }

    //서버와 연결
    public void Connect()
    {
        restClient = new RestClient<>();
        retroService = restClient.getClient(RetroService.class);
    }



    @Override
    public void getUsers(Context context, LoadDataCallback callback) {
        userDataSource.getUsers(context, new LoadDataCallback() {
            @Override
            public void onUserLoad(ArrayList<User> list) {
                if(callback !=null)
                {
                    callback.onUserLoad(list);
                }
            }
        });
    }

    public void postUser(String email)
    {
        userDataSource.onUser(email);
    }

    public RetroService getRetroService() {
        return retroService;
    }


    public void getEmployerPlace(EmployerPlaceSource employerPlaceDataSource)
    {
        employerPlaceDataSource.getPlace(employerPlaces);
    }

    public void addEmployerPlace(EmployerPlace place)
    {
        employerPlaces.add(place);
        return;
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
}
