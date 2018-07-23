package com.example.jinsu.work2.network.model;

import com.google.gson.annotations.Expose;

public class LoginResponse {

    @Expose int user_id;
    @Expose String access_token;
    @Expose String refresh_token;
    @Expose int validity_period;
    @Expose boolean expired;

}
