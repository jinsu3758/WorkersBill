package com.example.jinsu.work2.model;

import io.realm.RealmObject;

public class Contract extends RealmObject {
    String name;
    String date;


    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
