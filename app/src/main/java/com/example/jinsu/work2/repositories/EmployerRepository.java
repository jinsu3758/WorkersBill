package com.example.jinsu.work2.repositories;

import android.content.Context;

import com.example.jinsu.work2.model.EmployerPlace;
import com.example.jinsu.work2.model.User;
import com.example.jinsu.work2.network.RestClient;
import com.example.jinsu.work2.network.RetroService;
import com.example.jinsu.work2.network.EmployerPlaceSource;
import com.example.jinsu.work2.network.user.UserDataSource;
import com.example.jinsu.work2.network.user.UserSource;

import java.util.ArrayList;

public class EmployerRepository implements UserSource {
    private static EmployerRepository employerRepository;

    private RetroService retroService;
    private RestClient<RetroService> restClient;
    private UserDataSource userDataSource;
    ArrayList<EmployerPlace> employerPlaces;


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


    /**
     *
     * EmployerPlace
     * 사업장 model
     *
     */
    public void getEmployerPlace(EmployerPlaceSource employerPlaceDataSource)
    {

        employerPlaceDataSource.getPlace(employerPlaces);
    }

    public void addEmployerPlace(EmployerPlace place)
    {
        employerPlaces.add(place);
        return;
    }


}
