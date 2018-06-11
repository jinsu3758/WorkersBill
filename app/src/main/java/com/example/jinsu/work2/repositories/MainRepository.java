package com.example.jinsu.work2.repositories;

import com.example.jinsu.work2.model.User;

//
/*Repository는 data를 어디서 조회하는지와 update 되었을 때 호출해야 하는 api가 무엇인지를 알고 있으며,
여러 data source를 중재하는 역할*/
//

public class MainRepository {


   // private UserDao userDao;
    private User user;
    private android.os.Handler handler;

   /* @Inject
    public MainRepository(RetroService retroService,UserDao userDao)
    {
        this.retroService = retroService;
        this.userDao = userDao;
    }*/

    public MainRepository()
    {
        handler = new android.os.Handler();
    }



    /*public LiveData<User> initUser()
    {
        Connect();
        Call<LiveData<User>> call = retroService.getRespos("meansoup");

        call.enqueue(new Callback<LiveData<User>>() {
            @Override
            public void onResponse(Call<LiveData<User>> call, Response<LiveData<User>> response) {
                Log.d("main_repo","연결 성공 initUser()");
                userDao.InsertUser(response.body().getValue());
                user = response.body();
            }

            @Override
            public void onFailure(Call<LiveData<User>> call, Throwable t) {
                Log.e("main_repo","연결 실패 initUser()");

            }
        });
        return user;
    }*/

    //유저 데이터 get
    /*public LiveData<User> getUser(String userId)
    {
        refrechUser(userId);
        return userDao.load(userId);
    }*/

   /* public User getUser() {

   *//* //데이터가 변경 될 때만 업데이트
    private void refrechUser(final String userId)
    {
            boolean isUserexist = userDao.isUser();
            if(isUserexist == true)
            {
                Connect();

                Call<LiveData<User>> call = retroService.getRespos(userId);

                call.enqueue(new Callback<LiveData<User>>() {
                    @Override
                    public void onResponse(Call<LiveData<User>> call, Response<LiveData<User>> response) {
                        userDao.InsertUser(response.body().getValue());
                    }

                    @Override
                    public void onFailure(Call<LiveData<User>> call, Throwable t) {

                    }
                });
                }
    }*//*
        Connect();
        Call<User> call = retroService.getRespos("meansoup");

        try {
            user = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        *//*Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    user = call.execute().body();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();*//*

        return user;
    }*/
}
