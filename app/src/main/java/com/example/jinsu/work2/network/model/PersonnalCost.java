package com.example.jinsu.work2.network.model;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

/**
 * {
`
 }
 */
public class PersonnalCost {
    @Expose public Integer id;
    @Expose public Integer contract_id;
    @Expose public Integer company_id;
    @Expose public Integer employee_id;
    @Expose public String title;
    @Expose public String salary_method;
    @Expose public Integer rest_of_hour;
    @Expose public Integer hour_pay;
    @Expose public String reg_date;
    @Expose public String update_date;
    @Expose public ArrayList<WorkScheduleItem> weekly_items;
}
