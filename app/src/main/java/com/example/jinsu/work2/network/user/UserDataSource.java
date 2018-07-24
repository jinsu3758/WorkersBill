//package com.example.jinsu.work2.network.user;
//
//import android.content.Context;
//
//import com.example.jinsu.work2.model.User;
//import com.example.jinsu.work2.network.ApiUtil;
//import com.example.jinsu.work2.util.PreferenceUtil;
//
//import java.util.ArrayList;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class UserDataSource implements UserSource {
//
//    @Override
//    public void getUsers(Context context, LoadDataCallback callback) {
//        ArrayList<User> users = new ArrayList<>();
////        users.add(new User("login","url"));
//        if(callback != null)
//        {
//            callback.onUserLoad(users);
//        }
//    }
//
//
//    public void onUser(String email)
//    {
//
//    }
//
//    public void saveUser(String email, String authCode)
//    {
//        Call<String> call = ApiUtil.getRetroService().onLogin(email,authCode);
//        call.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                String token = response.body();
//                User user = PreferenceUtil.loadUser();
////                user.setToken(token);
//                PreferenceUtil.saveUser(user);
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//
//            }
//        });
//    }
//
//
//}
