package com.example.confined.presentation.controller;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.confined.Constants;
import com.example.confined.R;
import com.example.confined.Singletons;
import com.example.confined.data.InfectedCountriesApi;
import com.example.confined.presentation.model.CountriesInfected;
import com.example.confined.presentation.model.GlobalData;
import com.example.confined.presentation.model.GlobalDataResponse;
import com.example.confined.presentation.view.CountriesListActivity;
import com.example.confined.presentation.view.FightBoreActivity;
import com.example.confined.presentation.view.MainActivity;
import com.example.confined.presentation.view.MoreInfoActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainController {
    private MainActivity view;
    private Integer infected;
    private Integer recovered;
    private Integer deaths;


    public MainController(MainActivity view) {
        this.view = view;
    }


    public void makeApiCall(final TextView infect, final TextView recov, final TextView death) {

        Call<GlobalDataResponse> call = Singletons.getInfectedCountriesApi().getGlobalData();
        call.enqueue(new Callback<GlobalDataResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<GlobalDataResponse> call, Response<GlobalDataResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    GlobalData globalData = response.body().getGlobal();

                    infected = globalData.getTotalConfirmed();
                    recovered = globalData.getTotalRecovered();
                    deaths = globalData.getTotalDeaths();
                    infect.setText("" + infected);
                    recov.setText("" + recovered);
                    death.setText("" + deaths);
                }
            }

            @Override
            public void onFailure(Call<GlobalDataResponse> call, Throwable t) {
                view.showError();
            }
        });
    }


}
