package com.example.confined.presentation.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.confined.R;
import com.example.confined.Singletons;
import com.example.confined.presentation.controller.CountriesListController;
import com.example.confined.presentation.model.CountriesInfected;
import com.example.confined.presentation.model.OnItemClickListener;

import java.util.List;


public class CountriesListActivityDetails extends AppCompatActivity {


    private List<CountriesInfected> infectedCountriesResponses;
    private CountriesListController controller;
    private TextView textView_details;
    private TextView textView_details2;
    private TextView textView_details3;
    private TextView textView_details4;
    private TextView textView_details5;
    private TextView textView_details6;
    private TextView textView_details7;
    private TextView textView_details8;



    Toolbar toolbarm;
    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countries_list_details);

        toolbarm = findViewById(R.id.m_toolbar);
        setSupportActionBar(toolbarm);

        textView_details=findViewById(R.id.details_description);
        textView_details2=findViewById(R.id.details_description2);
        textView_details3=findViewById(R.id.details_description3);
        textView_details4=findViewById(R.id.details_description4);
        textView_details5=findViewById(R.id.details_description5);
        textView_details6=findViewById(R.id.details_description6);
        textView_details7=findViewById(R.id.details_description7);
        textView_details8=findViewById(R.id.details_description8);

        Intent intent = getIntent();
        String countryJson = intent.getStringExtra("countryKey");
        CountriesInfected countriesInfected = Singletons.getGson().fromJson(countryJson, CountriesInfected.class);
        showDetail(countriesInfected);
    }

    private void showDetail(CountriesInfected countriesInfected) {
    textView_details.setText(""+countriesInfected.getCountry());
    textView_details2.setText(""+countriesInfected.getNewConfirmed());
    textView_details3.setText(""+countriesInfected.getTotalConfirmed());
    textView_details4.setText(""+countriesInfected.getNewRecovered());
    textView_details6.setText(""+countriesInfected.getTotalRecovered());
    textView_details7.setText(""+countriesInfected.getNewDeaths());
    textView_details8.setText(""+countriesInfected.getTotalDeaths());
    textView_details5.setText("Last Date: "+countriesInfected.getDate());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.app_bar_title, menu);
        return true;
    }
}


