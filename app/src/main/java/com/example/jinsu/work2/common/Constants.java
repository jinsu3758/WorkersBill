package com.example.jinsu.work2.common;

import android.content.SharedPreferences;

public class Constants {
    public static final String BASE_URL = "http://api.wbwb.kr";

    //enums에 추가 예정
    public static final String PREF_KEY = "PREF_KEY";
    public static final String PREF_USR = "PREF_USER";
    public static final String PREF_PLACE = "PREF_PLACE";
    public static final String CALC_KEY = "CALC_KEY";


    public static final int REQUEST_PICTURE = 1;
    public static final int REQUEST_PHOTO_ALBUM = 2;
    public static final int REQUEST_EMPLOYER = 3;
    public static final int REQUEST_EMPLOYEE = 4;

    public static final int RESPONSE_DUPLICATE = 10;
    public static final int RESPONSE_SUCCESS = 11;
    public static final int RESPONSE_FAIL = 12;
    public static final int SEARCH_ADDRESS_ACTIVITY = 10000;

    //수정 예정
    public static SharedPreferences preferences;
}
