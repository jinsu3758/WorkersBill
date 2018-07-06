package com.example.jinsu.work2.network.place;

import com.example.jinsu.work2.model.Place;

import java.util.ArrayList;

public interface PlaceSource {
    interface LoadPlaceCallback{
        void onPlaceLoad(ArrayList<Place> list);
    }
    void getPlace(PlaceSource.LoadPlaceCallback callback);
}
