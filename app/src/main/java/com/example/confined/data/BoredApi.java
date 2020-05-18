package com.example.confined.data;

import com.example.confined.presentation.model.Bored;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BoredApi {
    @GET("/api/activity/")
    Call<Bored> getBored();
}
