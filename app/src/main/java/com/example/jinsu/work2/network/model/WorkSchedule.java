package com.example.jinsu.work2.network.model;

import com.google.gson.annotations.Expose;

/*
{"day_of_week":"1","item_type":"work","begin":"10:00","end":"18:00"},
 */
public class WorkSchedule {
    @Expose public String day_of_week;
    @Expose public String item_type;
    @Expose public String begin;
    @Expose public String end;

    public WorkSchedule(String day_of_week) {
        this.day_of_week = day_of_week;
        this.item_type = "work";
        this.begin = "10:00";
        this.end = "18:00";
    }
}
