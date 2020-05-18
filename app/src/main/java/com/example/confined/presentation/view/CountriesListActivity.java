package com.example.confined.presentation.view;

import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.EdgeEffect;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.confined.Singletons;
import com.example.confined.presentation.controller.CountriesListController;
import com.example.confined.R;
import com.example.confined.presentation.model.CountriesInfected;
import com.example.confined.presentation.model.OnItemClickListener;
import com.google.gson.GsonBuilder;

import java.util.List;


public class CountriesListActivity extends AppCompatActivity {


    private List<CountriesInfected> infectedCountriesResponses;
    private CountriesListController controller;
    private Toolbar toolbarm;
    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countries_list);

        controller = new CountriesListController(
                this,
                Singletons.getGson(),
                Singletons.getSharedPreferences(getApplicationContext()));
        controller.onStart();

        toolbarm = findViewById(R.id.m_toolbar);
        setSupportActionBar(toolbarm);
    }


    public void showList(List<CountriesInfected> countriesInfectedList) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new ListAdapter(countriesInfectedList, new OnItemClickListener() {
            @Override
            public void onItemClick(CountriesInfected item) {
                controller.onItemClick(item);
            }
        });
        recyclerView.setAdapter(mAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.app_bar, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }


    public void showError() {
        Toast.makeText(getApplicationContext(), "Api Error", Toast.LENGTH_SHORT).show();
    }


    public void navigateToDetails(CountriesInfected countriesInfected) {
        Intent myIntent = new Intent(CountriesListActivity.this, CountriesListActivityDetails.class);
        myIntent.putExtra("countryKey", Singletons.getGson().toJson(countriesInfected));

        CountriesListActivity.this.startActivity(myIntent);
        Toast.makeText(getApplicationContext(), "TODO NAVIGATE", Toast.LENGTH_SHORT).show();
    }


}