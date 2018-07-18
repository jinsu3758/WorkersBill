package com.example.jinsu.work2.model;

public class Place {
    private String name;
    private String address;
    private String phone;
    private String owner;

    public Place(String name, String address, String phone, String owner) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.owner = owner;
   }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getOwner() {
        return owner;
    }
}
