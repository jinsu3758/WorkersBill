package com.example.jinsu.work2.network.model;


import com.google.gson.annotations.Expose;

/*
{
  "id": 227,
  "company_id": 3,
  "employer_id": 1,
  "employee_id": 0,
  "company_name": "테스트 주식회사\uD83D\uDE00",
  "employer_name": "test123",
  "employee_name": "",
  "begin": null,
  "end": null,
  "working_place": "",
  "working_description": "",
  "bonus_pay": 0,
  "bonus_rate_of_over_time": 0.5,
  "payment_cycle": "MONTHLY",
  "payment_day": 1,
  "payment_method": "BANK_TRANSFER",
  "reg_date": "2018-07-24T08:57:15.496+0000",
  "update_date": null,
  "publish_date": null,
  "approve_date": null,
  "company_phone": null,
  "company_address": null,
  "employee_phone": null,
  "employee_address": null,
  "published": false,
  "approved": false,
  "insu_employment": false,
  "insu_industrial_accident": false,
  "insu_national": false,
  "insu_health": false
}
 */
public class CompanyContract {

    @Expose public String id;
    @Expose public Integer company_id;
    @Expose public Integer employer_id;
    @Expose public Integer employee_id;
    @Expose public String company_name;
    @Expose public String employer_name;
    @Expose public String employee_name;
    @Expose public String begin;
    @Expose public String end;
    @Expose public String working_place;
    @Expose public String working_description;
    @Expose public Integer bonus_pay;
    @Expose public Integer bonus_rate_of_over_time;
    @Expose public String payment_cycle;
    @Expose public Integer payment_day;
    @Expose public String payment_method;
    @Expose public String reg_date;
    @Expose public String update_date;
    @Expose public String publish_date;
    @Expose public String approve_date;
    @Expose public String company_phone;
    @Expose public String company_address;
    @Expose public String employee_phone;
    @Expose public String employee_address;
    @Expose public Boolean published;
    @Expose public Boolean approved;
    @Expose public Boolean insu_employment;
    @Expose public Boolean insu_industrial_accident;
    @Expose public Boolean insu_national;
    @Expose public Boolean insu_health;
}
