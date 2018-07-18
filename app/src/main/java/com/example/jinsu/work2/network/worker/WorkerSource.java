package com.example.jinsu.work2.network.worker;

import com.example.jinsu.work2.model.Worker;

import java.util.ArrayList;

public interface WorkerSource {
    interface LoadReqWorkerCallback{
        void onReqWorkerLoad(ArrayList<Worker> list);
    }
    void getReqWorker(WorkerSource.LoadReqWorkerCallback callback);

    interface LoadCurWorkerCallback{
        void onCurWorkerLoad(ArrayList<Worker> list);
    }
    void getCurWorker(WorkerSource.LoadCurWorkerCallback callback);


}
