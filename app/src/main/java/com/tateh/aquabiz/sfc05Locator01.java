package com.tateh.aquabiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Objects;
import java.util.StringTokenizer;

public class sfc05Locator01 extends AppCompatActivity implements OnMapReadyCallback {
    AppCompatButton btn_mapview, btn_lisview;
    LinearLayout llMapView, llListView;
    AppCompatButton btnLuzon, btnVisayas, btnMindanao;
    ListView listviewLuzon, listviewVisayas, listviewMindanao;
    ArrayList<sfc03Species01_Item> view_adapters_luzon = new ArrayList<>();
    ArrayList<sfc03Species01_Item> view_adapters_visayas = new ArrayList<>();
    ArrayList<sfc03Species01_Item> view_adapters_mindanao = new ArrayList<>();
    GoogleMap mGoogleMap;
    String branch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sfc05_locator01);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Branch Locator");

        btn_mapview = findViewById(R.id.btn_mapview);
        btn_lisview = findViewById(R.id.btn_lisview);
        llMapView = findViewById(R.id.llMapView);
        llListView = findViewById(R.id.llListView);
        btnLuzon = findViewById(R.id.btnLuzon);
        btnVisayas = findViewById(R.id.btnVisayas);
        btnMindanao = findViewById(R.id.btnMindanao);
        listviewLuzon = findViewById(R.id.listviewLuzon);
        listviewVisayas = findViewById(R.id.listviewVisayas);
        listviewMindanao = findViewById(R.id.listviewMindanao);
        btnLuzon.setEnabled(false);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        Objects.requireNonNull(mapFragment).getMapAsync(this);

        branch = setting_global.getpreferences(this, R.string.sfc05_branch);

        StringTokenizer tokens = new StringTokenizer(branch, "♠");
        while (tokens.hasMoreTokens()){
            String kabuuan = tokens.nextToken();
            StringTokenizer tokens2 = new StringTokenizer(kabuuan, "♣");
            tokens2.nextToken();
            tokens2.nextToken();
            tokens2.nextToken();
            tokens2.nextToken();
            String location = tokens2.nextToken();
            sfc03Species01_Item adapter = new sfc03Species01_Item(kabuuan);
            if(location.equals("LUZON")) {
                view_adapters_luzon.add(adapter);
            }else if(location.equals("VISAYAS")) {
                view_adapters_visayas.add(adapter);
            }else{
                view_adapters_mindanao.add(adapter);
            }
        }

        sfc05Locator01_Adapter lAdapterLuzon = new sfc05Locator01_Adapter(this, R.layout.activity_sfc05_locator01_adapter, view_adapters_luzon);
        listviewLuzon.setAdapter(lAdapterLuzon);

        sfc05Locator01_Adapter lAdapterVisayas = new sfc05Locator01_Adapter(this, R.layout.activity_sfc05_locator01_adapter, view_adapters_visayas);
        listviewVisayas.setAdapter(lAdapterVisayas);

        sfc05Locator01_Adapter lAdapterMindanao = new sfc05Locator01_Adapter(this, R.layout.activity_sfc05_locator01_adapter, view_adapters_mindanao);
        listviewMindanao.setAdapter(lAdapterMindanao);

        listviewLuzon.setOnItemClickListener((parent, view, position, id) -> saanpunta(String.valueOf(view_adapters_luzon.get(position).getKabuuan())));
        listviewVisayas.setOnItemClickListener((parent, view, position, id) -> saanpunta(String.valueOf(view_adapters_visayas.get(position).getKabuuan())));
        listviewMindanao.setOnItemClickListener((parent, view, position, id) -> saanpunta(String.valueOf(view_adapters_mindanao.get(position).getKabuuan())));
    }

    private void saanpunta(String kabuuan) {
        StringTokenizer tokens = new StringTokenizer(kabuuan, "♣");
        String id = tokens.nextToken();
        setting_global.editpreferences(this, R.string.sfc05_branch_selected, id);
        setting_global.gointent(this, sfc05Locator02.class);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        googleMap.getUiSettings().setZoomControlsEnabled(true);

        LatLng latlng = new LatLng(12.814208, 121.660836);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng,6));

        StringTokenizer tokens = new StringTokenizer(branch, "♠");
        while (tokens.hasMoreTokens()){
            String kabuuan = tokens.nextToken();
            StringTokenizer tokens2 = new StringTokenizer(kabuuan, "♣");
            String id = tokens2.nextToken();
            String name = tokens2.nextToken();
            tokens2.nextToken();
            String address = tokens2.nextToken();
            tokens2.nextToken();
            String latitude = tokens2.nextToken();
            String longitude = tokens2.nextToken();

            latlng = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));
            mGoogleMap.addMarker(new MarkerOptions().position(latlng).title("Santeh Feeds Corporation - " + name).snippet(address)).setTag(Integer.parseInt(id));
        }
        mGoogleMap.setOnInfoWindowClickListener(marker -> {
            int id = (int) marker.getTag();
            saanpunta(String.valueOf(id));
        });
    }

    public void btn_mapview(View view) {
        btn_mapview.setEnabled(false);
        btn_lisview.setEnabled(true);
        llMapView.setVisibility(View.VISIBLE);
        llListView.setVisibility(View.GONE);
        btn_mapview.setBackgroundResource(R.drawable.sfc05_locator01_mapview);
        btn_lisview.setBackgroundResource(R.drawable.sfc05_locator01_listview_inactive);
    }

    public void btn_lisview(View view) {
        btn_lisview.setEnabled(false);
        btn_mapview.setEnabled(true);
        llListView.setVisibility(View.VISIBLE);
        llMapView.setVisibility(View.GONE);
        btn_lisview.setBackgroundResource(R.drawable.sfc05_locator01_listview);
        btn_mapview.setBackgroundResource(R.drawable.sfc05_locator01_mapview_inactive);
    }

    public void btnLuzon(View view) {
        btnLuzon.setBackgroundColor(ContextCompat.getColor(this, R.color.green_500));
        btnVisayas.setBackgroundColor(ContextCompat.getColor(this, R.color.green_200));
        btnMindanao.setBackgroundColor(ContextCompat.getColor(this, R.color.green_200));
        btnLuzon.setEnabled(false);
        btnVisayas.setEnabled(true);
        btnMindanao.setEnabled(true);
        listviewLuzon.setVisibility(View.VISIBLE);
        listviewVisayas.setVisibility(View.GONE);
        listviewMindanao.setVisibility(View.GONE);
    }

    public void btnVisayas(View view) {
        btnVisayas.setBackgroundColor(ContextCompat.getColor(this, R.color.green_500));
        btnLuzon.setBackgroundColor(ContextCompat.getColor(this, R.color.green_200));
        btnMindanao.setBackgroundColor(ContextCompat.getColor(this, R.color.green_200));
        btnVisayas.setEnabled(false);
        btnLuzon.setEnabled(true);
        btnMindanao.setEnabled(true);
        listviewVisayas.setVisibility(View.VISIBLE);
        listviewLuzon.setVisibility(View.GONE);
        listviewMindanao.setVisibility(View.GONE);
    }

    public void btnMindanao(View view) {
        btnMindanao.setBackgroundColor(ContextCompat.getColor(this, R.color.green_500));
        btnLuzon.setBackgroundColor(ContextCompat.getColor(this, R.color.green_200));
        btnVisayas.setBackgroundColor(ContextCompat.getColor(this, R.color.green_200));
        btnMindanao.setEnabled(false);
        btnLuzon.setEnabled(true);
        btnVisayas.setEnabled(true);
        listviewMindanao.setVisibility(View.VISIBLE);
        listviewLuzon.setVisibility(View.GONE);
        listviewVisayas.setVisibility(View.GONE);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        String saan = setting_global.getpreferences(this, R.string.sfc03_saan);
        if(saan.equals("faq")) setting_global.gointent(this,sfc03Species05.class);
        else setting_global.gointent(this,sfc02Home.class);
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