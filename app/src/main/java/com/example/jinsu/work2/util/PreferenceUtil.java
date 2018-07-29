package com.example.jinsu.work2.util;

import android.content.SharedPreferences;

import com.example.jinsu.work2.common.Constants;
import com.example.jinsu.work2.network.model.Join;
import com.google.gson.Gson;

public class PreferenceUtil {
    public static void saveUser(Join join)
    {
        Gson gson = new Gson();
        String json = gson.toJson(join,Join.class);
        SharedPreferences.Editor prefsEditor = Constants.preferences.edit();

        prefsEditor.putString(Constants.PREF_USR, json).apply();
    }

    public static Join loadUser()
    {
        Gson gson = new Gson();
        String json = Constants.preferences.getString(Constants.PREF_USR,null);
        return gson.fromJson(json,Join.class);
    }

    public static void savePlaceId(int id)
    {
        SharedPreferences.Editor prefEditor = Constants.preferences.edit();

        prefEditor.putInt(Constants.PREF_PLACE,id).apply();
    }

    public static int loadPlaceId()
    {
        return Constants.preferences.getInt(Constants.PREF_PLACE, 0);
    }
}
