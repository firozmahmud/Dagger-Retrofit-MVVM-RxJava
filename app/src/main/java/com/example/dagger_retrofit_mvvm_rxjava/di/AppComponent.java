package com.example.dagger_retrofit_mvvm_rxjava.di;

import com.example.dagger_retrofit_mvvm_rxjava.main.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, UtilsModule.class})
public interface AppComponent {
    void inject(MainActivity mainActivity);
}
