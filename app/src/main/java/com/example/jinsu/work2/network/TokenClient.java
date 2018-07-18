package com.example.jinsu.work2.network;

import com.example.jinsu.work2.common.Constants;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TokenClient<T> {
    private T service;

    public T getClient(Class<? extends T> type)
    {

        Retrofit.Builder retroBuilder = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());

        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                String token = " ";
                Request request;
                request = chain.request().newBuilder().addHeader("Authorization", token).build();
                return chain.proceed(request);
            }
        };
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.interceptors().add(interceptor);
        OkHttpClient client = builder.build();

        retroBuilder.client(client);
        Retrofit retrofit = retroBuilder.build();

        service = retrofit.create(type);
        return service;
    }
}
