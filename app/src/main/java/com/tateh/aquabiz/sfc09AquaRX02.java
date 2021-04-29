package com.tateh.aquabiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.StringTokenizer;

public class sfc09AquaRX02 extends AppCompatActivity {
    static String isda;
    String last_type = "wala";
    ListView listviewInfectious, listviewNonInfectious;
    AppCompatButton btnInfectious, btnNonInfectious;
    ArrayList<sfc03Species01_Item> view_adapters_infectious = new ArrayList<>();
    ArrayList<sfc03Species01_Item> view_adapters_non_infectious = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sfc09_aqua_rx02);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        btnInfectious = findViewById(R.id.btnInfectious);
        btnNonInfectious = findViewById(R.id.btnNonInfectious);
        listviewInfectious = findViewById(R.id.listviewInfectious);
        listviewNonInfectious = findViewById(R.id.listviewNonInfectious);
        btnInfectious.setEnabled(false);

        String selected = setting_global.getpreferences(this, R.string.sfc09_aquarx02_selected);
        StringTokenizer tokens = new StringTokenizer(selected, "♣");
        isda = tokens.nextToken();
        String sakit = tokens.nextToken();
        setTitle(isda + " Diseases");

        String []arrSakit = sakit.split("♥");
        view_adapters_infectious.clear();
        view_adapters_non_infectious.clear();
        String duseases = setting_global.getpreferences(this, R.string.sfc09_aquarx02);
        StringTokenizer tokensDiseases = new StringTokenizer(duseases, "♠");

        while (tokensDiseases.hasMoreTokens()){
            String kabuuan = tokensDiseases.nextToken();
            StringTokenizer tokensHiwalay = new StringTokenizer(kabuuan, "♣");
            String id = tokensHiwalay.nextToken();
            if (Arrays.asList(arrSakit).contains(id)) {
                tokensHiwalay.nextToken();
                String kind = tokensHiwalay.nextToken();
                String type = tokensHiwalay.nextToken();
                sfc03Species01_Item adapter = new sfc03Species01_Item(kabuuan);
                sfc03Species01_Item adapterType = new sfc03Species01_Item(type);
                if(kind.equals("Infectious")){
                    if(!last_type.equals("type")){
                        view_adapters_infectious.add(adapterType);
                        last_type = type;
                    }
                    view_adapters_infectious.add(adapter);
                }else{
                    if(!last_type.equals("type")){
                        view_adapters_non_infectious.add(adapterType);
                        last_type = type;
                    }
                    view_adapters_non_infectious.add(adapter);
                }
            }
        }

        sfc09AquaRX02_Adapter lAdapterInfectious = new sfc09AquaRX02_Adapter(this, R.layout.activity_sfc09_aqua_rx02_adapter, view_adapters_infectious);
        listviewInfectious.setAdapter(lAdapterInfectious);

        sfc09AquaRX02_Adapter lAdapterNonInfectious = new sfc09AquaRX02_Adapter(this, R.layout.activity_sfc09_aqua_rx02_adapter, view_adapters_non_infectious);
        listviewNonInfectious.setAdapter(lAdapterNonInfectious);
    }

    public void btnInfectious(View view) {
        listviewInfectious.setVisibility(View.VISIBLE);
        listviewNonInfectious.setVisibility(View.GONE);
        btnInfectious.setBackgroundColor(ContextCompat.getColor(this, R.color.green_500));
        btnNonInfectious.setBackgroundColor(ContextCompat.getColor(this, R.color.green_200));
        btnInfectious.setEnabled(false);
        btnNonInfectious.setEnabled(true);
    }

    public void btnNonInfectious(View view) {
        listviewNonInfectious.setVisibility(View.VISIBLE);
        listviewInfectious.setVisibility(View.GONE);
        btnNonInfectious.setBackgroundColor(ContextCompat.getColor(this, R.color.green_500));
        btnInfectious.setBackgroundColor(ContextCompat.getColor(this, R.color.green_200));
        btnNonInfectious.setEnabled(false);
        btnInfectious.setEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    @Override
    public void onBackPressed() {
        setting_global.gointent(this,sfc09AquaRX01.class);
    }

    public void btn_home(View view) {
        setting_global.gointent(this,sfc02Home.class);
    }

    public void btn_notification(View view) {
    }

    public void btn_announcement(View view) {
    }

    public void btn_setting(View view) {
    }
}