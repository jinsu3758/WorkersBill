package com.example.jinsu.work2.repositories;

import android.content.Context;

import com.example.jinsu.work2.model.User;
import com.example.jinsu.work2.network.user.UserDataSource;
import com.example.jinsu.work2.network.user.UserSource;

import java.util.ArrayList;

//
/*Repository는 data를 어디서 조회하는지와 update 되었을 때 호출해야 하는 api가 무엇인지를 알고 있으며,
여러 data source를 중재하는 역할*/
//

public class MainRepository implements UserSource {
    private static MainRepository mainRepository = new MainRepository();
    private UserDataSource userDataSource;

    public static MainRepository getInstance()
    {
        return mainRepository;
    }

    private MainRepository()
    {
        userDataSource = new UserDataSource();

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
}