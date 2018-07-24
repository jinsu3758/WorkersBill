package com.example.jinsu.work2.network;

import android.net.Uri;
import android.os.Bundle;

import com.example.jinsu.work2.network.date.DateDeserializer;
import com.example.jinsu.work2.network.date.DateSerializer;
import com.example.jinsu.work2.network.date.UriDeserializer;
import com.example.jinsu.work2.network.date.UriSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.CopyOnWriteArrayList;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by shs on 2015-04-10.
 */
public class CommonClass implements Cloneable {

    public String toJson()
    {
        Gson gson = new GsonBuilder().setPrettyPrinting()
                .registerTypeAdapter(Uri.class, new UriSerializer())
                .registerTypeAdapter(Date.class, new DateSerializer())
                .create();
        return gson.toJson(this);
    }

    public static String toJson(Object T)
    {
        Gson gson = new GsonBuilder().setPrettyPrinting()
                .registerTypeAdapter(Uri.class, new UriSerializer())
                .registerTypeAdapter(Date.class, new DateSerializer())
                .create();
        return gson.toJson(T);
    }

    public static String toArrayJson(ArrayList<?> T)
    {
        Gson gson = new GsonBuilder().setPrettyPrinting()
                .registerTypeAdapter(Uri.class, new UriSerializer())
                .registerTypeAdapter(Date.class, new DateSerializer())
                .create();
        return gson.toJson(new CopyOnWriteArrayList(T));
    }

    public static Object fromArrayJson(Type type, String json)
    {
        Gson gson = new GsonBuilder().setPrettyPrinting()
                .registerTypeAdapter(Uri.class, new UriDeserializer())
                .registerTypeAdapter(Date.class, new DateDeserializer())
                .create();

        return gson.fromJson(json, type);
    }

    public static Object fromJSon(Class T, String json)
    {
        try {
            Gson gson = new GsonBuilder().serializeNulls()
                    .registerTypeAdapter(Uri.class, new UriDeserializer())
                    .registerTypeAdapter(Date.class, new DateDeserializer())
                    .create();
            return gson.fromJson(json, T);
        } catch (Exception e) {
            return null;
        }
    }

    public static Bundle toBundle(String json)
    {
        Bundle savedBundle = new Bundle();
        savedBundle.putString("toBundle",json);
        return savedBundle;
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public static String showError(RetrofitError error) {
        String errorMsg = "Server connection error";
        if(error != null) {
            Response response = error.getResponse();
            if(response != null) {
                errorMsg += response.getStatus() + " " +  response.getReason();
            }
        }
        return errorMsg;
    }

}
