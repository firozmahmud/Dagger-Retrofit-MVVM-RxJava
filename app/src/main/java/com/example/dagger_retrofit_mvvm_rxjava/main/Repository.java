package com.example.dagger_retrofit_mvvm_rxjava.main;

import com.example.dagger_retrofit_mvvm_rxjava.utils.ApiCallInterface;
import com.google.gson.JsonElement;

import io.reactivex.Observable;

public class Repository {

    private ApiCallInterface apiCallInterface;

    public Repository(ApiCallInterface apiCallInterface) {
        this.apiCallInterface = apiCallInterface;
    }

    /*
     * method to call login api
     * */
    public Observable<JsonElement> executeLogin(String mobileNumber, String password) {
        return apiCallInterface.login(mobileNumber, password);
    }
}
