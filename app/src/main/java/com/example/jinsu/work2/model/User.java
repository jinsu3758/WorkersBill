package com.example.jinsu.work2.model;

public class User {
    private String email;
    private String token;
    private String userId;

    public User(String email, String token) {
        this.email = email;
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
