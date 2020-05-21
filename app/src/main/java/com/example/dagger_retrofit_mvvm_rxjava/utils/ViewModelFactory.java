package com.example.dagger_retrofit_mvvm_rxjava.utils;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.dagger_retrofit_mvvm_rxjava.main.Repository;
import com.example.dagger_retrofit_mvvm_rxjava.main.MainActivity;
import com.example.dagger_retrofit_mvvm_rxjava.main.MainViewModel;

import javax.inject.Inject;


public class ViewModelFactory implements ViewModelProvider.Factory {

    private Repository repository;

    @Inject
    public ViewModelFactory(Repository repository) {
        this.repository = repository;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(repository);
        }
        throw new IllegalArgumentException("Unknown class name");
    }
}
