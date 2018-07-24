//package com.example.jinsu.work2.network;
//
//import com.example.jinsu.work2.common.Constants;
//
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//public class RetroClient<T> {
//    private T service;
//
//    public T getClient(Class<? extends T> type)
//    {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(Constants.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        service = retrofit.create(type);
//        return service;
//    }
//}
