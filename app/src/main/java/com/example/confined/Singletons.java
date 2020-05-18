package com.example.confined;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.confined.data.InfectedCountriesApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Singletons {

    private static Gson gsonInstance;
    private static InfectedCountriesApi infectedCountriesApiInstance;
    private static SharedPreferences sharedPreferencesInstance;

    public static Gson getGson() {
        if (gsonInstance == null) {
            gsonInstance = new GsonBuilder()
                    .setLenient()
                    .create();
        }
        return gsonInstance;
    }

    public static InfectedCountriesApi getInfectedCountriesApi() {
        if (infectedCountriesApiInstance == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .build();

            infectedCountriesApiInstance = retrofit.create(InfectedCountriesApi.class);
        }
        return infectedCountriesApiInstance;
    }

    public static SharedPreferences getSharedPreferences(Context context){
        if (sharedPreferencesInstance==null){
            sharedPreferencesInstance=context.getSharedPreferences("application_confined", Context.MODE_PRIVATE);
        }
        return sharedPreferencesInstance;
    }
}
