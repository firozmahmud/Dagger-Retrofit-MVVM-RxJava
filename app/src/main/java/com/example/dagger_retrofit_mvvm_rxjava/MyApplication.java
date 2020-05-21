package com.example.dagger_retrofit_mvvm_rxjava;

import android.app.Application;
import android.content.Context;

import com.example.dagger_retrofit_mvvm_rxjava.di.AppComponent;
import com.example.dagger_retrofit_mvvm_rxjava.di.AppModule;
import com.example.dagger_retrofit_mvvm_rxjava.di.DaggerAppComponent;
import com.example.dagger_retrofit_mvvm_rxjava.di.UtilsModule;

public class MyApplication extends Application {

    AppComponent appComponent;
    Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        context = this;
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).utilsModule(new UtilsModule()).build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }
}
