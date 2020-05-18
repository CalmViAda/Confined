package com.example.confined.presentation.controller;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.confined.R;
import com.example.confined.data.BoredApi;
import com.example.confined.presentation.model.Bored;
import com.example.confined.presentation.view.FightBoreActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FightBoreController {

    static final String BASE_URL = "https://www.boredapi.com/";
    private static Retrofit retrofit = null;
    FightBoreActivity view;
    String activity;
    String type;
    Float accessibility;
    Integer participants;
    Float price;
    String link;
    Integer key;



    public FightBoreController(FightBoreActivity view){
        this.view=view;
    }




    public void makeApiCall(final TextView firstline, final TextView secondline, final ImageView iconBor) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

       BoredApi boredApi = retrofit.create(BoredApi.class);


        Call<Bored> call= boredApi.getBored();
        call.enqueue(new Callback<Bored>() {
            @Override
            public void onResponse(Call<Bored> call, Response<Bored> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String newLine = System.getProperty("line.separator");
                    activity=response.body().getActivity();
                    type=response.body().getType();
                    accessibility=response.body().getAccessibility();
                    price=response.body().getPrice();
                    participants=response.body().getParticipants();
                    key=response.body().getKey();
                    link=response.body().getLink();

                    firstline.setText(type);
                    secondline.setText(activity+newLine+newLine+"Accessibility: "+accessibility+newLine+"Price: $"+price*100+newLine+"Participants: "+participants+newLine+"Link: "+link);
                    Picasso.get().load(R.drawable.stargold).resize(150,150).into(iconBor);
                }
            }
            @Override
            public void onFailure(Call<Bored> call, Throwable t) {
                view.showError();
            }
        });
    }
}
