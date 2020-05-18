package com.example.confined.presentation.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;

import com.example.confined.R;


public class MoreInfoActivity extends AppCompatActivity {

    Toolbar toolbarm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info);

        toolbarm = findViewById(R.id.m_toolbar);
        setSupportActionBar(toolbarm);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.app_bar_title,menu);
        return true;
    }
}
