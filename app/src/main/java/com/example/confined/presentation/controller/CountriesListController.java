package com.example.confined.presentation.controller;

import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.confined.Constants;
import com.example.confined.Singletons;
import com.example.confined.presentation.model.CountriesInfected;
import com.example.confined.presentation.model.RestInfectedCountriesResponse;
import com.example.confined.presentation.view.CountriesListActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountriesListController {

    private SharedPreferences sharedPreferences;
    private Gson gson;
    private CountriesListActivity view;


    public CountriesListController(CountriesListActivity view, Gson gson, SharedPreferences sharedPreferences) {
        this.view = view;
        this.gson = gson;
        this.sharedPreferences = sharedPreferences;
    }


    public void onStart() {

        List<CountriesInfected> countriesInfectedList = getDatafromCache();
        if (countriesInfectedList != null) {
            view.showList(countriesInfectedList);
        } else {
            makeApiCall();
        }
    }


    private void makeApiCall() {

        Call<RestInfectedCountriesResponse> call = Singletons.getInfectedCountriesApi().getInfectedCountriesResponse();
        call.enqueue(new Callback<RestInfectedCountriesResponse>() {
            @Override
            public void onResponse(Call<RestInfectedCountriesResponse> call, Response<RestInfectedCountriesResponse> resp) {
                if (resp.isSuccessful() && resp.body() != null) {
                    List<CountriesInfected> countryList = resp.body().getCountryInfos();
                    saveList(countryList);
                    view.showList(countryList);
                    //Toast.makeText(getApplicationContext(), "Api Success", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RestInfectedCountriesResponse> call, Throwable t) {
                view.showError();
            }
        });
    }


    private void saveList(List<CountriesInfected> countriesInfectedList) {
        String jsonString = gson.toJson(countriesInfectedList);
        sharedPreferences
                .edit()
                .putString(Constants.Key_Country_List, jsonString)
                .apply();

        Toast.makeText(view.getApplicationContext(), "List Saved", Toast.LENGTH_SHORT).show();
    }


    private List<CountriesInfected> getDatafromCache() {
        String jsonCountryInfo = sharedPreferences.getString(Constants.Key_Country_List, null);
        if (jsonCountryInfo == null) {
            return null;
        } else {
            Type listType = new TypeToken<List<CountriesInfected>>() {
            }.getType();
            return gson.fromJson(jsonCountryInfo, listType);
        }
    }


    public void onItemClick(CountriesInfected countriesInfected) {
        view.navigateToDetails(countriesInfected);
    }
}
