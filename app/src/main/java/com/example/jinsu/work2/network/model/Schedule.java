package com.example.jinsu.work2.network.model;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

public class Schedule {
    @Expose public String id;
    @Expose public int contract_id;
    @Expose public int employee_id;
    @Expose public String title;
    @Expose public String salary_method;
    @Expose public int rest_of_hour;
    @Expose public int hour_pay;
    @Expose public String reg_date;
    @Expose public ArrayList<WorkScheduleItem> weekly_items;

}
