package com.example.jinsu.work2.util;

import android.content.SharedPreferences;

import com.example.jinsu.work2.common.Constants;
import com.example.jinsu.work2.model.User;
import com.google.gson.Gson;

public class PreferenceUtil {
    public static void saveUser(User user)
    {
        Gson gson = new Gson();
        String json = gson.toJson(user,User.class);
        SharedPreferences.Editor prefsEditor = Constants.preferences.edit();

        prefsEditor.putString(Constants.PREF_USR, json).apply();
    }

    public static User loadUser()
    {
        Gson gson = new Gson();
        String json = Constants.preferences.getString(Constants.PREF_USR,null);
        return gson.fromJson(json,User.class);
    }
}
