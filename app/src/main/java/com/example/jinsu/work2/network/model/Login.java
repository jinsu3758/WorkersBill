package com.example.jinsu.work2.network.model;

import com.google.gson.annotations.Expose;

public class Login {

    private final String GRANT_TYPE_PASSWORD = "password";

    @Expose String email;
    @Expose String password;
    @Expose String grant_type;

    public Login(String email, String password) {
        this.email = email;
        this.password = password;
        this.grant_type = GRANT_TYPE_PASSWORD;
    }
}
