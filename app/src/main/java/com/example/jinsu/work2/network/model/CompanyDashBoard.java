package com.example.jinsu.work2.network.model;

import com.google.gson.annotations.Expose;

public class CompanyDashBoard {
    @Expose public Company company;
    @Expose public String work_time;
    @Expose public Boolean request_approve_contract;
}
