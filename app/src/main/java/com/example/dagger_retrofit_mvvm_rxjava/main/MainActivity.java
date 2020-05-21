package com.example.dagger_retrofit_mvvm_rxjava.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.dagger_retrofit_mvvm_rxjava.R;
import com.example.dagger_retrofit_mvvm_rxjava.MyApplication;
import com.example.dagger_retrofit_mvvm_rxjava.databinding.ActivityMainBinding;
import com.example.dagger_retrofit_mvvm_rxjava.utils.ApiResponse;
import com.example.dagger_retrofit_mvvm_rxjava.utils.Constant;
import com.example.dagger_retrofit_mvvm_rxjava.utils.ViewModelFactory;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class MainActivity extends AppCompatActivity {

    @Inject
    ViewModelFactory viewModelFactory;

    private MainViewModel viewModel;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        ((MyApplication) getApplication()).getAppComponent().inject(this);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel.class);


        setListener();
        observeData();
    }

    private void setListener() {

        binding.getDataBtn.setOnClickListener(v -> {

            if (!Constant.checkInternetConnection(MainActivity.this)) {

                Toast.makeText(MainActivity.this, "No internet", Toast.LENGTH_SHORT).show();
            } else {
                viewModel.hitApi();
            }

        });
    }


    private void observeData() {
        viewModel.loginResponse().observe(this, this::consumeResponse);
    }

    private void consumeResponse(ApiResponse apiResponse) {

        switch (apiResponse.status) {
            case LOADING:
                Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show();
                break;
            case SUCCESS:
                Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
                renderSuccessResponse(apiResponse.data);
                break;
            case ERROR:
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(this, "nothing", Toast.LENGTH_SHORT).show();
        }

    }

    private void renderSuccessResponse(JsonElement data) {

        List<MyData> myData = parseJSON(data);

        if (!data.isJsonNull()) {
            Toast.makeText(this, "success data", Toast.LENGTH_SHORT).show();
            Log.d("firozmahmud", myData.get(0).getTitle());
        } else {
            Toast.makeText(this, "Error data", Toast.LENGTH_SHORT).show();
        }
    }


    private List<MyData> parseJSON(JsonElement jsonElement) {

        Type type = new TypeToken<List<MyData>>() {
        }.getType();

        List<MyData> dataList = new Gson().fromJson(jsonElement, type);

        /**
         * If data is JsonObject
         * then
         * MyData myData = new Gson().fromJson(jsonElement, MyData.class);
         */

        return dataList;
    }
}
