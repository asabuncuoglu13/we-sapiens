package com.alpay.wesapiens.controller;

import java.util.List;

import com.alpay.wesapiens.models.FirebaseAPI;
import com.alpay.wesapiens.models.Question;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FirebaseAPIController implements Callback<List<Question>> {

    static final String BASE_URL = "https://codenotesalpay.firebaseio.com/";

    public void start() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        FirebaseAPI firebaseAPI = retrofit.create(FirebaseAPI.class);

        Call<List<Question>> call = firebaseAPI.loadQuestions();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {
        if(response.isSuccessful()) {
            List<Question> changesList = response.body();
            for (Question question : changesList){
                System.out.println(question.getTitle());
            }
        } else {
            System.out.println(response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<List<Question>> call, Throwable t) {
        t.printStackTrace();
    }
}