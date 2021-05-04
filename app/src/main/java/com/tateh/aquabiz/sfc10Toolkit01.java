package com.tateh.aquabiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import java.util.Objects;

public class sfc10Toolkit01 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sfc10_toolkit01);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Toolkit");
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        setting_global.gointent(this,sfc02Home.class);
    }

    public void btn_home(View view) {
        onBackPressed();
    }

    public void btn_notification(View view) {
    }

    public void btn_announcement(View view) {
    }

    public void btn_setting(View view) {
    }

    public void btn_directory(View view) {
    }

    public void btn_Calculator(View view) {
    }

    public void btn_tatehtv(View view) {
    }

    public void btn_fbpage(View view) {
        if(setting_global.isNetworkAvailable(this)){
            Intent facebookAppIntent;
            try {
                facebookAppIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/146583292415"));
                startActivity(facebookAppIntent);
            } catch (ActivityNotFoundException e) {
                facebookAppIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/tatehfeeds"));
                startActivity(facebookAppIntent);
            }
        }else setting_global.noInternetDisplay(this);
    }
}