package com.example.jinsu.work2.model;


import com.example.jinsu.work2.enums.Week;

/**
 * Created by kasum on 2018-06-03.
 */

public class RestTime {
    private Week week;
    private short time;

    public RestTime(Week week, short time) {
        this.week = week;
        this.time = time;
    }

    public Week getWeek() {
        return week;
    }

    public void setWeek(Week week) {
        this.week = week;
    }

    public short getTime() {
        return time;
    }

    public void setTime(short time) {
        this.time = time;
    }
}
