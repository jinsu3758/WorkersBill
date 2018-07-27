package com.example.jinsu.work2.network.model;

import com.google.gson.annotations.Expose;

/**
 * http://wiki.lof.kr/pages/viewpage.action?pageId=2031767
 * {
 "id": 198,
 "name": "테스트 주식회사\uD83D\uDE00",
 "registration_number": "1234567899",
 "employer_name": "test123",
 "employer_user_id": 1,
 "phone": "123123",
 "head_postcode": 12345,

 "head_address": "어드드드레렐스스스",
 "postcode": 12345,
 "address": "어드드드레렐스스스",
 "wifi_ip_address": "126.123.21.12",
 "reg_date": "2018-07-24T05:26:00.290+0000",
 "count_unread_notification": 0,
 "less_than5_employees": false,
 "usage_wifi": true
 }


 */
public class Company {
    @Expose public Integer id;
    @Expose public String name;
    @Expose public String registration_number;
    @Expose public String employer_name;
    @Expose public Integer employer_user_id;
    @Expose public String phone;
    @Expose public Integer head_postcode;
    @Expose public String head_address;
    @Expose public Integer postcode;
    @Expose public String address;
    @Expose public String wifi_ip_address;
    @Expose public String reg_date;
    @Expose public Integer count_unread_notification;
    @Expose public Boolean less_than5_employees;
    @Expose public Boolean is_less_then_5_employee;
    @Expose public Boolean usage_wifi;

}
