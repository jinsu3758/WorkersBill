package com.example.jinsu.work2.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient<T> {
    private T service;
    public static final String BASE_URL = "https://api.github.com";

    public T getClient(Class<? extends T> type)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(type);
        return service;
    }
}
