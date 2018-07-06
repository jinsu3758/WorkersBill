package com.example.jinsu.work2.util;

import android.os.AsyncTask;

import org.jsoup.Jsoup;

import java.io.IOException;

/**
 * Created by jinsu on 2018-03-13.
 */

public class ParsingIp extends AsyncTask<Void, Void, String>
{
    @Override
    protected String doInBackground(Void... voids) {
        org.jsoup.nodes.Document doc = null;
        try {
            doc = Jsoup.connect("http://www.checkip.org").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String public_ip = doc.getElementById("yourip").select("h1").first().select("span").text();
        return public_ip;
    }
}
