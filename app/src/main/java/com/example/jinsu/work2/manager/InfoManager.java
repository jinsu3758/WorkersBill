package com.example.jinsu.work2.manager;

import org.jsoup.helper.StringUtil;

public class InfoManager {

    public static String mOAuthToken;

    public static String getOAuthToken() {
        if(StringUtil.isBlank(mOAuthToken)) {
            return "";
        }
        return mOAuthToken;
    }

    public static void setOAuthToken(String token) {
        mOAuthToken = token;
    }

}
