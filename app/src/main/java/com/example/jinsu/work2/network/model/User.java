package com.example.jinsu.work2.network.model;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.HashMap;

public class User {

    @Expose public Integer id;
    @Expose public String uid;
    @Expose public String name;
    @Expose public String email;
    @Expose public String phone;
    @Expose public String postcode;
    @Expose public String address;
    @Expose public ArrayList<HashMap<String,String>> roles;
    @Expose public String reg_date;

}
