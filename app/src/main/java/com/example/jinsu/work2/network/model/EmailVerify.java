package com.example.jinsu.work2.network.model;

import com.google.gson.annotations.Expose;

public class EmailVerify {

    @Expose public String email;
    @Expose public int code;

    public EmailVerify(String email, int code) {
        this.email = email;
        this.code = code;
    }
}
