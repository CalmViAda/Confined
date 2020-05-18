package com.example.confined.presentation.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


import com.example.confined.R;
import com.example.confined.Singletons;
import com.example.confined.data.InfectedCountriesApi;
import com.example.confined.presentation.controller.CountriesListController;
import com.example.confined.presentation.controller.MainController;
import com.example.confined.presentation.model.GlobalData;
import com.example.confined.presentation.model.GlobalDataResponse;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    MainController controller;
    Toolbar toolbar;
    TextView infect;
    TextView recov;
    TextView death;
    Integer infected;
    Integer recovered;
    Integer deaths;
    static final String BASE_URL = "https://api.covid19api.com/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_home);

        infect=findViewById(R.id.numberInfect);
        recov=findViewById(R.id.numberRecov);
        death=findViewById(R.id.numberDeath);

        controller = new MainController(MainActivity.this);
        controller.makeApiCall(infect,recov,death);


    }


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen((GravityCompat.START))) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                break;
            case R.id.nav_country:
                Intent intent = new Intent(MainActivity.this, CountriesListActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_bored:
                Intent intent2 = new Intent(MainActivity.this, FightBoreActivity.class);
                startActivity(intent2);
                break;
            case R.id.nav_info:
                Intent intent3 = new Intent(MainActivity.this, MoreInfoActivity.class);
                startActivity(intent3);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    public void showError() {
        Toast.makeText(getApplicationContext(), "Api Error", Toast.LENGTH_SHORT).show();
    }

}
