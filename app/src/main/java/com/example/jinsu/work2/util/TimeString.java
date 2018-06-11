package com.example.jinsu.work2.util;

/**
 * Created by kasum on 2018-06-03.
 */

public class TimeString {
    public static String ConvertMinuitesToTimeString(short t) {
        int h = t / 60;
        int m = t % 60;

        return String.format("%02d:%02d", h, m);
    }

    public static short ConvertTimeStringToMinuites(String str) {
        short h = 0;
        short m = 0;

        try {
            String[] st = str.split(":");

            h = Short.parseShort(st[0]);
            m = Short.parseShort(st[1]);
        } catch(Exception e) {
            return 0;
        }

        return (short)(h * 60 + m);
    }
}
