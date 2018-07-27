package com.example.jinsu.work2.network.model;

import com.google.gson.annotations.Expose;

public class LoginResponse {

    @Expose public int user_id;
    @Expose public String access_token;
    @Expose public String refresh_token;
    @Expose public int validity_period;
    @Expose public boolean expired;

}
