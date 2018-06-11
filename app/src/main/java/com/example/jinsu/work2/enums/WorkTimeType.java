package com.example.jinsu.work2.enums;


/**
 * Created by kasum on 2018-06-03.
 */

public enum WorkTimeType {
    Work(1),
    Lunch(2);

    private final int value;
    private WorkTimeType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
