package com.example.jinsu.work2.network.model;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

/**
 * {

 {
 "id": 55,
 "schedule_id": 51,
 "schedule_type": "WEEKLY",
 "title": "",
 "item_type": "work",
 "day_of_week": 1,
 "begin": "10:00:00",
 "end": "18:00:00",
 "reg_date": "2018-07-17T04:50:04.307+0000",
 "meals": [
 {
 "id": 60,
 "schedule_id": 51,
 "schedule_type": "WEEKLY",
 "title": "",
 "item_type": "meal",
 "day_of_week": 1,
 "begin": "12:00:00",
 "end": "13:00:00",
 "reg_date": "2018-07-17T04:50:04.308+0000",
 "meals": []
 }
 ]

 */
public class WorkScheduleItem {
    @Expose public Integer id;
    @Expose public Integer schedule_id;
    @Expose public String schedule_type;
    @Expose public String title;
    @Expose public String item_type;
    @Expose public Integer day_of_week;
    @Expose public String begin;
    @Expose public String end;
    @Expose public String reg_date;
    @Expose public ArrayList<WorkScheduleItem> meals;
}
