package com.example.jinsu.work2.network.model;

import com.google.gson.annotations.Expose;

public class NotificationModel {
    @Expose public Integer id;
    @Expose public String message;
    @Expose public String reg_date;
    @Expose public Boolean read;
    @Expose public Integer company_id;
}
