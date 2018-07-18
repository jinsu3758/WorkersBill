package com.example.jinsu.work2.model;


import com.example.jinsu.work2.enums.Week;
import com.example.jinsu.work2.enums.WorkTimeType;

/**
 * Created by kasum on 2018-06-03.
 */

public class WorkTime {
    private Week week;
    private WorkTimeType type;
    private short start;
    private short end;

    public WorkTime() {
        this.start = -1;
        this.end = -1;
    }

    public WorkTime(Week week, WorkTime src) {
        this.week = week;

        this.type = src.type;
        this.start = src.start;
        this.end = src.end;
    }

    public Week getWeek() {
        return week;
    }

    public void setWeek(Week week) {
        this.week = week;
    }

    public WorkTimeType getType() {
        return type;
    }

    public void setType(WorkTimeType type) {
        this.type = type;
    }

    public short getStart() {
        return start;
    }

    public void setStart(short start) {
        this.start = start;
    }

    public short getEnd() {
        return end;
    }

    public void setEnd(short end) {
        this.end = end;
    }
}
