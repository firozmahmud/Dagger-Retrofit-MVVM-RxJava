package com.example.dagger_retrofit_mvvm_rxjava.utils;


import com.google.gson.JsonElement;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiCallInterface {

    @GET(Urls.POSTS)
    Observable<JsonElement> getData();
}
