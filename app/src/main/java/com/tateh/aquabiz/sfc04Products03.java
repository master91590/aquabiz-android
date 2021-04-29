package com.tateh.aquabiz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Objects;
import java.util.StringTokenizer;

public class sfc04Products03 extends AppCompatActivity {
    static String url, product;
    static DownloadManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sfc04_products03);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Tateh Product");
        manager = (DownloadManager) getApplication().getSystemService(Context.DOWNLOAD_SERVICE);

        ListView listView = findViewById(R.id.listview);
        ImageView imgProducts = findViewById(R.id.imgProducts);
        String kabuuan = setting_global.getpreferences(this, R.string.sfc04_products03);
        StringTokenizer tokens = new StringTokenizer(kabuuan, "♣");
        tokens.nextToken();
        product = tokens.nextToken();
        tokens.nextToken();
        tokens.nextToken();
        String img = tokens.nextToken();
        imgProducts.setImageBitmap(setting_global.StringToBitMap(img));
        url = tokens.nextToken();
        StringTokenizer tokens2 = new StringTokenizer(tokens.nextToken(), "^");
        ArrayList<sfc03Species01_Item> view_adapters = new ArrayList<>();
        int ilan = tokens2.countTokens();
        for (int i = 0; i<ilan; i++){
            sfc03Species01_Item adapter = new sfc03Species01_Item(tokens2.nextToken());
            view_adapters.add(adapter);
        }
        sfc04Products03_Adapter lAdapter = new sfc04Products03_Adapter(this, R.layout.activity_sfc04_products03_adapter, view_adapters);
        listView.setAdapter(lAdapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        if(setting_global.getpreferences(this, R.string.sfc03_saan).equals("branch")) setting_global.gointent(this,sfc05Locator02.class);
        else setting_global.gointent(this,sfc04Products02.class);
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