package com.alpay.wesapiens.models;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FirebaseAPI {
    @GET("questions.json")
    Call<List<Question>> loadQuestions();
}
