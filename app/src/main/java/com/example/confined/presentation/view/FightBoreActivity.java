package com.example.confined.presentation.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.confined.R;
import com.example.confined.presentation.controller.FightBoreController;
import com.example.confined.presentation.model.Bored;

import java.util.List;


public class FightBoreActivity extends AppCompatActivity {
    Toolbar toolbarm;
    List<Bored> boredL;
    private RecyclerView recyclerView;
    //private ListAdapterBored mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    FightBoreController controller;

    TextView firstline;
    TextView secondline;
    ImageView iconBor;

    static final String BASE_URL = "https://www.boredapi.com/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight_bore);

        toolbarm = findViewById(R.id.m_toolbar);
        firstline = findViewById(R.id.firstline);
        secondline=findViewById(R.id.secondline);

        iconBor = (ImageView) findViewById(R.id.iconBored);
        setSupportActionBar(toolbarm);
        controller = new FightBoreController(
                this);
        controller.makeApiCall(firstline,secondline,iconBor);
    }


    public void showError() {
        Toast.makeText(getApplicationContext(), "Api Error", Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.app_bar_title,menu);
        return true;
    }
}