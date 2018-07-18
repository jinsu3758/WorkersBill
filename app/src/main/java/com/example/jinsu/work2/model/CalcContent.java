package com.example.jinsu.work2.model;

import io.realm.RealmObject;

public class CalcContent extends RealmObject {
    private String name;
    private String total_wage;
    private String date;



    public String getName() {
        return name;
    }

    public String getTotal_wage() {
        return total_wage;
    }

    public String getDate() {
        return date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTotal_wage(String total_wage) {
        this.total_wage = total_wage;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
