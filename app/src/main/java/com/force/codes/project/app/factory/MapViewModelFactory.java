/*
 * Created by Force Porquillo on 6/21/20 12:52 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/21/20 12:52 AM
 */

package com.force.codes.project.app.factory;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.force.codes.project.app.data_layer.repositories.interfaces.MapRepository;
import com.force.codes.project.app.presentation_layer.views.viewmodels.MapViewModel;

public class MapViewModelFactory implements ViewModelProvider.Factory{
    private MapRepository mapRepository;

    public MapViewModelFactory(MapRepository mapRepository){
        this.mapRepository = mapRepository;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class <T> modelClass){
        if(modelClass.isAssignableFrom(MapViewModel.class)){
            return (T) new MapViewModel(mapRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
