package com.tateh.aquabiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Objects;
import java.util.StringTokenizer;

public class sfc04Products01 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sfc04_products01);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Tateh Products");

        ListView listView = findViewById(R.id.listview);
        ArrayList<sfc03Species01_Item> view_adapters = new ArrayList<>();
        String species = setting_global.getpreferences(this, R.string.sfc04_products01);
        StringTokenizer tokens = new StringTokenizer(species, "♠");
        int ilan = tokens.countTokens();
        for (int i = 0; i<ilan; i++){
            sfc03Species01_Item adapter = new sfc03Species01_Item(tokens.nextToken());
            view_adapters.add(adapter);
        }
        sfc04Products01_Adapter lAdapter = new sfc04Products01_Adapter(this, R.layout.activity_sfc04_products01_adapter, view_adapters);
        listView.setAdapter(lAdapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            String kabuuan = String.valueOf(view_adapters.get(position).getKabuuan());
            StringTokenizer tokens2 = new StringTokenizer(kabuuan, "♣");
            String isda = tokens2.nextToken();
            tokens2.nextToken();
            String produkto = tokens2.nextToken();
            setting_global.editpreferences(this, R.string.sfc03_saan,"products");
            setting_global.editpreferences(this, R.string.sfc04_products02_selected, isda + "♣" + produkto);
            setting_global.gointent(this, sfc04Products02.class);
        });
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