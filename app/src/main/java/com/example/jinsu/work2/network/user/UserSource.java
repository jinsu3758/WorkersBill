package com.example.jinsu.work2.network.user;

import android.content.Context;

import com.example.jinsu.work2.model.User;

import java.util.ArrayList;

public interface UserSource {
    interface LoadDataCallback{
        void onUserLoad(ArrayList<User> list);
    }
    void getUsers(Context context,LoadDataCallback callback);
}
