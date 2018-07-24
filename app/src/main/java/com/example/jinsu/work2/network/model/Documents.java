package com.example.jinsu.work2.network.model;

import com.google.gson.annotations.Expose;

public class Documents {
    @Expose public Address address;
    @Expose public String address_type;
    @Expose public String x;
    @Expose public String y;
    @Expose public String address_name;
    @Expose public RoadAddress road_address;

}
