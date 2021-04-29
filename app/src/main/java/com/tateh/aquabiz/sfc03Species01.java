package com.tateh.aquabiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Objects;
import java.util.StringTokenizer;

public class sfc03Species01 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sfc03_species01);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Species");

        ListView listView = findViewById(R.id.listview);
        String species = setting_global.getpreferences(this, R.string.sfc03_species01);
        ArrayList<sfc03Species01_Item> view_adapters = new ArrayList<>();
        StringTokenizer tokens = new StringTokenizer(species, "♠");
        int bilang = tokens.countTokens();
        StringBuilder kabuuan = new StringBuilder();
        for (int i = 0; i<bilang; i++){
            if( i % 2 == 0){
                if(i == bilang-1){
                    kabuuan = new StringBuilder(tokens.nextToken() + "♦-");
                    sfc03Species01_Item adapter = new sfc03Species01_Item(kabuuan.toString());
                    view_adapters.add(adapter);
                }else kabuuan = new StringBuilder(tokens.nextToken());
            }else{
                kabuuan.append("♦").append(tokens.nextToken());
                sfc03Species01_Item adapter = new sfc03Species01_Item(kabuuan.toString());
                view_adapters.add(adapter);
                kabuuan = new StringBuilder();
            }
        }

        sfc03Species01_Adapter lAdapter = new sfc03Species01_Adapter(this, R.layout.activity_sfc03_species01_adapter, view_adapters);
        listView.setAdapter(lAdapter);
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
}