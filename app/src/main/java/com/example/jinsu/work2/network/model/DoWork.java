package com.example.jinsu.work2.network.model;

import com.google.gson.annotations.Expose;

public class DoWork {

    @Expose public Integer id;
    @Expose public Integer company_id;
    @Expose public Integer user_id;
    @Expose public String begin;
    @Expose public String end;
    @Expose public String index_date;
    @Expose public String address;
    @Expose public String reg_date;

}
