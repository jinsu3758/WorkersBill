package com.example.jinsu.work2.network.worker;

import com.example.jinsu.work2.model.Worker;

import java.util.ArrayList;

public class WorkerDataSource implements WorkerSource {
    @Override
    public void getReqWorker(LoadReqWorkerCallback callback) {
        ArrayList<Worker> list = new ArrayList<>();
        list.add(new Worker("name"));
        if(callback != null)
        {
            callback.onReqWorkerLoad(list);
        }
    }

    @Override
    public void getCurWorker(LoadCurWorkerCallback callback) {
        ArrayList<Worker> list = new ArrayList<>();
        list.add(new Worker("name"));
        if(callback != null)
        {
            callback.onCurWorkerLoad(list);
        }
    }
}
