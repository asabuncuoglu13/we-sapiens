package com.alpay.wesapiens.controller;

import java.util.List;

import com.alpay.wesapiens.models.Frame;
import com.alpay.wesapiens.utils.FirebaseAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FrameAPIController implements Callback<List<Frame>> {

    AppCompatActivity appCompatActivity;
    static final String BASE_URL = "https://codenotesalpay.firebaseio.com/";

    public FrameAPIController(AppCompatActivity activity){
        appCompatActivity = activity;
    }

    public void start() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        FirebaseAPI firebaseAPI = retrofit.create(FirebaseAPI.class);

        Call<List<Frame>> call = firebaseAPI.loadFrames();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<Frame>> call, Response<List<Frame>> response) {
        if(response.isSuccessful()) {
            List<Frame> frameList = response.body();
        } else {
            // error
        }
    }

    @Override
    public void onFailure(Call<List<Frame>> call, Throwable t) {
        // failure
    }
}