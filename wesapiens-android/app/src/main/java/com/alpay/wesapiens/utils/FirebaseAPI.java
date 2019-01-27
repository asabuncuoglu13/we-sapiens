package com.alpay.wesapiens.utils;

import com.alpay.wesapiens.models.Frame;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FirebaseAPI {
    @GET("questions.json")
    Call<List<Frame>> loadFrames();
}
