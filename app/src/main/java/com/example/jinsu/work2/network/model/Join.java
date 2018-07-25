package com.example.jinsu.work2.network.model;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.HashMap;

public class Join {

    //가입 성공 시
    @Expose public int id;
    @Expose public int uid;
    @Expose public ArrayList<HashMap<String,String>> roles;
    @Expose public String reg_date;
    ////


    @Expose public String email;
    @Expose public String name;
    @Expose public String phone;
    @Expose public String postcode;
    @Expose public String address;
    @Expose public String signature;
    @Expose public String signature_type;
    @Expose public String role;



}
