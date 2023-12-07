package com.example.warehousemanager.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.warehousemanager.R;

public class SplashScreen extends AppCompatActivity {
    TextView tvLogo ;
    ImageView imgLogo , imgBg;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        tvLogo = findViewById(R.id.splash_textView);
        imgLogo = findViewById(R.id.splash_logo);
        imgBg = findViewById(R.id.splash_bg);
        tvLogo.animate().translationY(4000).setDuration(1000).setStartDelay(2000);
        imgLogo.animate().translationY(4000).setDuration(1000).setStartDelay(2000);
        imgBg.animate().translationY(-4000).setDuration(1000).setStartDelay(2000);
        Handler handler  =new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreen.this, LoginActivity.class));
            }
        },4000);
    }
}