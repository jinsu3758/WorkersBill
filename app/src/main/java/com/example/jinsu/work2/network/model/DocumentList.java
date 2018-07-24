package com.example.jinsu.work2.network.model;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

public class DocumentList {
    @Expose public ArrayList<Documents> documents;
    @Expose public Meta meta;
}
