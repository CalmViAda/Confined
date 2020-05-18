package com.example.confined.data;

import com.example.confined.presentation.model.GlobalDataResponse;
import com.example.confined.presentation.model.RestInfectedCountriesResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface InfectedCountriesApi {
    @GET("summary")
    Call<RestInfectedCountriesResponse> getInfectedCountriesResponse();
    @GET("summary")
    Call<GlobalDataResponse> getGlobalData();
}
