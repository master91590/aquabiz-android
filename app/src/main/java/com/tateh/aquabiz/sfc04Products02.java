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

public class sfc04Products02 extends AppCompatActivity {
    ListView listViewGrowout, listViewNursery, listViewHatchery;
    AppCompatButton btnGrowout, btnNursery, btnHatchery;
    ArrayList<sfc03Species01_Item> view_adapters_growout = new ArrayList<>();
    ArrayList<sfc03Species01_Item> view_adapters_nursery = new ArrayList<>();
    ArrayList<sfc03Species01_Item> view_adapters_hatchery = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sfc04_products02);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        String selected = setting_global.getpreferences(this, R.string.sfc04_products02_selected);
        StringTokenizer tokens2 = new StringTokenizer(selected, "♣");
        String isda = tokens2.nextToken();
        String produkto = tokens2.nextToken();
        setTitle("Products for " + isda);

        btnGrowout = findViewById(R.id.btnGrowout);
        btnNursery = findViewById(R.id.btnNursery);
        btnHatchery = findViewById(R.id.btnHatchery);
        listViewGrowout = findViewById(R.id.listviewGrowout);
        listViewNursery = findViewById(R.id.listviewNursery);
        listViewHatchery = findViewById(R.id.listviewHatchery);
        boolean hatch = false, nurse=false;

        String []arrProdukto = produkto.split("♥");
        view_adapters_growout.clear();
        view_adapters_nursery.clear();
        view_adapters_hatchery.clear();
        String products = setting_global.getpreferences(this, R.string.sfc04_products02);
        StringTokenizer tokensProducts = new StringTokenizer(products, "♠");
        while (tokensProducts.hasMoreTokens()){
            String kabuuan = tokensProducts.nextToken();
            StringTokenizer tokensHiwalay = new StringTokenizer(kabuuan, "♣");
            String id = tokensHiwalay.nextToken();
            if (Arrays.asList(arrProdukto).contains(id)) {
                tokensHiwalay.nextToken();
                String kind = tokensHiwalay.nextToken();
                sfc03Species01_Item adapter = new sfc03Species01_Item(kabuuan);
                if(kind.equals("Hatchery")){
                    hatch = true;
                    btnHatchery.setVisibility(View.VISIBLE);
                    view_adapters_hatchery.add(adapter);
                }else if(kind.equals("Nursery")){
                    nurse = true;
                    btnNursery.setVisibility(View.VISIBLE);
                    view_adapters_nursery.add(adapter);
                }else{
                    view_adapters_growout.add(adapter);
                }
            }
        }

        if(hatch){
            sfc04Products02_Adapter lAdapterHatchery = new sfc04Products02_Adapter(this, R.layout.activity_sfc04_products02_adapter, view_adapters_hatchery);
            listViewHatchery.setAdapter(lAdapterHatchery);
        }

        if(nurse){
            sfc04Products02_Adapter lAdapterNursery = new sfc04Products02_Adapter(this, R.layout.activity_sfc04_products02_adapter, view_adapters_nursery);
            listViewNursery.setAdapter(lAdapterNursery);
        }

        sfc04Products02_Adapter lAdapterGrowout = new sfc04Products02_Adapter(this, R.layout.activity_sfc04_products02_adapter, view_adapters_growout);
        listViewGrowout.setAdapter(lAdapterGrowout);

        btnGrowout.setEnabled(false);

        listViewGrowout.setOnItemClickListener((parent, view, position, id) -> {
            String kabuuan = String.valueOf(view_adapters_growout.get(position).getKabuuan());
            setting_global.editpreferences(this, R.string.sfc04_products03, kabuuan);
            setting_global.gointent(this, sfc04Products03.class);
        });

        listViewNursery.setOnItemClickListener((parent, view, position, id) -> {
            String kabuuan = String.valueOf(view_adapters_nursery.get(position).getKabuuan());
            setting_global.editpreferences(this, R.string.sfc04_products03, kabuuan);
            setting_global.gointent(this, sfc04Products03.class);
        });

        listViewHatchery.setOnItemClickListener((parent, view, position, id) -> {
            String kabuuan = String.valueOf(view_adapters_hatchery.get(position).getKabuuan());
            setting_global.display(kabuuan,"", this);
            setting_global.editpreferences(this, R.string.sfc04_products03, kabuuan);
            setting_global.gointent(this, sfc04Products03.class);
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        String saan = setting_global.getpreferences(this, R.string.sfc03_saan);
        if(saan.equals("species")) setting_global.gointent(this,sfc03Species02.class);
        else if(saan.equals("faq")) setting_global.gointent(this,sfc03Species05.class);
        else setting_global.gointent(this,sfc04Products01.class);
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

    public void btnGrowout(View view) {
        enabledisable(1);
        listViewGrowout.setVisibility(View.VISIBLE);
        listViewNursery.setVisibility(View.GONE);
        listViewHatchery.setVisibility(View.GONE);
    }

    public void btnNursery(View view) {
        enabledisable(2);
        listViewNursery.setVisibility(View.VISIBLE);
        listViewGrowout.setVisibility(View.GONE);
        listViewHatchery.setVisibility(View.GONE);
    }

    public void btnHatchery(View view) {
        enabledisable(3);
        listViewHatchery.setVisibility(View.VISIBLE);
        listViewGrowout.setVisibility(View.GONE);
        listViewNursery.setVisibility(View.GONE);
    }

    private void enabledisable(int num) {
        btnGrowout.setBackgroundColor(ContextCompat.getColor(this, R.color.green_200));
        btnNursery.setBackgroundColor(ContextCompat.getColor(this, R.color.green_200));
        btnHatchery.setBackgroundColor(ContextCompat.getColor(this, R.color.green_200));
        btnGrowout.setEnabled(true);
        btnNursery.setEnabled(true);
        btnHatchery.setEnabled(true);
        if(num == 3) {
            btnHatchery.setBackgroundColor(ContextCompat.getColor(this, R.color.green_500));
            btnHatchery.setEnabled(false);
        }else if(num == 2) {
            btnNursery.setBackgroundColor(ContextCompat.getColor(this, R.color.green_500));
            btnNursery.setEnabled(false);
        }else{
            btnGrowout.setBackgroundColor(ContextCompat.getColor(this, R.color.green_500));
            btnGrowout.setEnabled(false);
        }
    }
}