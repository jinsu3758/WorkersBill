package com.example.jinsu.work2.model;

import java.util.ArrayList;

public class User {
    private String address;
    private Boolean deleted;
    private String email;
    private int id;
    private String last_access_date;
    private String last_login_date;
    private String name;
    private String phone;
    private String postcode;
    private String reg_date;
    private String role;
    private ArrayList<String> roles;
    private String uid;
    private Boolean verified_email;
    private String signature;
    private String signature_type;

    public User(String address, Boolean deleted, String email, int id, String last_access_date
            , String last_login_date, String name, String phone, String postcode, String reg_date
            , String role, ArrayList<String> roles, String uid, Boolean verified_email, String signature, String signature_type)
    {
        this.address = address;
        this.deleted = deleted;
        this.email = email;
        this.id = id;
        this.last_access_date = last_access_date;
        this.last_login_date = last_login_date;
        this.name = name;
        this.phone = phone;
        this.postcode = postcode;
        this.reg_date = reg_date;
        this.role = role;
        this.roles = roles;
        this.uid = uid;
        this.verified_email = verified_email;
        this.signature = signature;
        this.signature_type = signature_type;
    }

    public String getAddress() {
        return address;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public String getLast_access_date() {
        return last_access_date;
    }

    public String getLast_login_date() {
        return last_login_date;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getReg_date() {
        return reg_date;
    }

    public String getRole() {
        return role;
    }

    public ArrayList<String> getRoles() {
        return roles;
    }

    public String getUid() {
        return uid;
    }

    public Boolean getVerified_email() {
        return verified_email;
    }

    public String getSignature() {
        return signature;
    }

    public String getSignature_type() {
        return signature_type;
    }
}
