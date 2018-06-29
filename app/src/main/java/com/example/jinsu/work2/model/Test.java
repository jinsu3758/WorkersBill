package com.example.jinsu.work2.model;

public class Test {
    int cash_key;
    String id;
    String pw;
    String nickname;
    String uuid;
    int point;
    String profile_img;
    int id_group;

    public Test(int cash_key, String id, String pw, String nickname, String uuid, int point, String profile_img, int id_group) {
        this.cash_key = cash_key;
        this.id = id;
        this.pw = pw;
        this.nickname = nickname;
        this.uuid = uuid;
        this.point = point;
        this.profile_img = profile_img;
        this.id_group = id_group;
    }

    public int getCash_key() {
        return cash_key;
    }

    public String getId() {
        return id;
    }

    public String getPw() {
        return pw;
    }

    public String getNickname() {
        return nickname;
    }

    public String getUuid() {
        return uuid;
    }

    public int getPoint() {
        return point;
    }

    public String getProfile_img() {
        return profile_img;
    }

    public int getId_group() {
        return id_group;
    }
}

