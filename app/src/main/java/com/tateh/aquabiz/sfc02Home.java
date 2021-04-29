package com.tateh.aquabiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Toast;

public class sfc02Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sfc02_home);
    }

    int backButtonCount=0;
    CountDownTimer timer;
    @Override
    public void onBackPressed() {
        if(backButtonCount >= 1)
        {
            if(timer != null) {
                timer.cancel();
                timer = null;
            }
            finishAndRemoveTask();
        }
        else
        {
            backButtonCount++;
            timer = new CountDownTimer(500,500) {
                public void onTick(long millisUntilFinished) { }
                public void onFinish() {
                    Toast.makeText(sfc02Home.this, "Press the back button twice to close the application.", Toast.LENGTH_SHORT).show();
                    backButtonCount=0;
                }
            }.start();
        }

    }

    public void btnSpecies(View view) {
        if(setting_global.getpreferences(this, R.string.sfc03_species01).equals("0")){
            if(setting_global.isNetworkAvailable(this)){
                setting_global.ShowProgressDialog(sfc02Home.this);
                setting_connection speciesBackgroundTask = new setting_connection(sfc02Home.this);
                speciesBackgroundTask.execute("Species", "aquabiz");
            }else setting_global.noInternetDisplay(this);
        }else setting_global.gointent(this,sfc03Species01.class);
    }

    public void btn_products(View view) {
        if(setting_global.getpreferences(this, R.string.sfc04_products01).equals("0")){
            if(setting_global.isNetworkAvailable(this)){
                setting_global.editpreferences(this, R.string.sfc03_saan, "home");
                setting_global.ShowProgressDialog(sfc02Home.this);
                setting_connection productsBackgroundTask = new setting_connection(sfc02Home.this);
                productsBackgroundTask.execute("Products", "aquabiz");
            }else setting_global.noInternetDisplay(this);
        }else setting_global.gointent(this,sfc04Products01.class);
    }
    public void btn_locator(View view) {
        setting_global.editpreferences(this, R.string.sfc03_saan, "home");
        if(setting_global.getpreferences(this, R.string.sfc05_branch).equals("0")){
            if(setting_global.isNetworkAvailable(this)){
                setting_global.ShowProgressDialog(sfc02Home.this);
                setting_connection productsBackgroundTask = new setting_connection(sfc02Home.this);
                productsBackgroundTask.execute("Branch", "aquabiz");
            }else setting_global.noInternetDisplay(this);
        }else setting_global.gointent(this,sfc05Locator01.class);
    }
    public void btn_blog(View view) {
        if(setting_global.isNetworkAvailable(this)){
            setting_global.gointent(this,sfc06Blog.class);
        }else setting_global.noInternetDisplay(this);
    }

    public void btn_weatherTideTable(View view) {
        if(setting_global.getpreferences(this, R.string.sfc07_weather_tide_dam).equals("0")){
            if(setting_global.isNetworkAvailable(this)){
                setting_global.ShowProgressDialog(sfc02Home.this);
                setting_connection productsBackgroundTask = new setting_connection(sfc02Home.this);
                productsBackgroundTask.execute("WeatherTideDam", "aquabiz");
            }else setting_global.noInternetDisplay(this);
        }else setting_global.gointent(this,sfc07WeatherTideDam.class);
    }

    public void btn_priceWatch(View view) {
        if(setting_global.getpreferences(this, R.string.sfc08_price_watch).equals("0")){
            if(setting_global.isNetworkAvailable(this)){
                setting_global.ShowProgressDialog(sfc02Home.this);
                setting_connection productsBackgroundTask = new setting_connection(sfc02Home.this);
                productsBackgroundTask.execute("PriceWatch", "aquabiz");
            }else setting_global.noInternetDisplay(this);
        }else setting_global.gointent(this,sfc08PriceWatch.class);
    }

    public void btn_aquacultureRX(View view) {
        if(setting_global.getpreferences(this, R.string.sfc09_aquarx01).equals("0")){
            if(setting_global.isNetworkAvailable(this)){
                setting_global.ShowProgressDialog(sfc02Home.this);
                setting_connection productsBackgroundTask = new setting_connection(sfc02Home.this);
                productsBackgroundTask.execute("AquaRX", "aquabiz");
            }else setting_global.noInternetDisplay(this);
        }else setting_global.gointent(this,sfc09AquaRX01.class);
    }

    public void btn_toolkit(View view) {

    }

    public void btn_notification(View view) {
    }

    public void btn_announcement(View view) {
    }

    public void btn_setting(View view) {
    }
}