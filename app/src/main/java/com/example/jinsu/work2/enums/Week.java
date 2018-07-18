package com.example.jinsu.work2.enums;

/**
 * Created by kasum on 2018-06-03.
 */

public enum Week {
    Monday(0),
    Tueseday(1),
    Wednesday(2),
    Thursday(3),
    Friday(4),
    Saturday(5),
    Sunday(6);

    private final int value;
    private Week(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
