package com.example.confined.presentation.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.confined.R;
import com.example.confined.presentation.view.MainActivity;

public class InitScreenActivity extends AppCompatActivity {

    private ImageButton buttonstart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init_screen);

        buttonstart=(ImageButton)  findViewById(R.id.btnStart);
        buttonstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeInitScreen();
            }
        });
    }
    public void closeInitScreen(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
