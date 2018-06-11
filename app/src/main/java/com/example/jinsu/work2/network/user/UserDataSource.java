package com.example.jinsu.work2.network.user;

import android.content.Context;

import com.example.jinsu.work2.model.User;
import com.example.jinsu.work2.repositories.EmployerRepository;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserDataSource implements UserSource {

    @Override
    public void getUsers(Context context, LoadDataCallback callback) {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("login","url"));
        if(callback != null)
        {
            callback.onUserLoad(users);
        }
    }

    public void onUser(String email)
    {
        Call<String> call = EmployerRepository.getInstance().getRetroService().postUser("email");

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }


}
