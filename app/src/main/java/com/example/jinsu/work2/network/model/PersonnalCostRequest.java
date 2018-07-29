package com.example.jinsu.work2.network.model;

import com.google.gson.annotations.Expose;

/*
{"employee_id":"0","title":"","salary_method":"TIME","company_id":"3"}
 */
public class PersonnalCostRequest {
    @Expose public Integer employee_id;
    @Expose public String title;
    @Expose public String salary_method;
    @Expose public Integer company_id;
}
