package com.tateh.aquabiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Toast;

public class sfc01SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sfc01_splash_screen);

        new CountDownTimer(4000,1000) {

            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                setting_global.gointent(sfc01SplashScreen.this, sfc02Home.class);
            }
        }.start();
    }

    boolean isalang=true;
    @Override
    public void onBackPressed() {
        if(isalang) {
            Toast.makeText(this, "Please wait.", Toast.LENGTH_SHORT).show();
            isalang=false;
        }
    }
}