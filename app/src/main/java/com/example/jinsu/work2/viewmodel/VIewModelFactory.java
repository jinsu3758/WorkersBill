package com.example.jinsu.work2.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.jinsu.work2.util.CallonClick;

public class VIewModelFactory implements ViewModelProvider.Factory {
    private final CallonClick onClick;

    public VIewModelFactory(CallonClick onClick) {
        this.onClick = onClick;
    }


    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(MainViewModel.class))
        {
            return (T) new MainViewModel(onClick);
        }

        throw new IllegalArgumentException("Unknown viemodel class");

    }
}
